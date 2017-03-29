<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

class Allocation
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Allocation Associations
  private $course;
  private $applicants;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aCourse)
  {
    $didAddCourse = $this->setCourse($aCourse);
    if (!$didAddCourse)
    {
      throw new Exception("Unable to create courseJobAllocation due to course");
    }
    $this->applicants = array();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function getCourse()
  {
    return $this->course;
  }

  public function getApplicant_index($index)
  {
    $aApplicant = $this->applicants[$index];
    return $aApplicant;
  }

  public function getApplicants()
  {
    $newApplicants = $this->applicants;
    return $newApplicants;
  }

  public function numberOfApplicants()
  {
    $number = count($this->applicants);
    return $number;
  }

  public function hasApplicants()
  {
    $has = $this->numberOfApplicants() > 0;
    return $has;
  }

  public function indexOfApplicant($aApplicant)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->applicants as $applicant)
    {
      if ($applicant->equals($aApplicant))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public function setCourse($aNewCourse)
  {
    $wasSet = false;
    if ($aNewCourse == null)
    {
      //Unable to setCourse to null, as courseJobAllocation must always be associated to a course
      return $wasSet;
    }
    
    $existingCourseJobAllocation = $aNewCourse->getCourseJobAllocation();
    if ($existingCourseJobAllocation != null && $this != $existingCourseJobAllocation)
    {
      //Unable to setCourse, the current course already has a courseJobAllocation, which would be orphaned if it were re-assigned
      return $wasSet;
    }
    
    $anOldCourse = $this->course;
    $this->course = $aNewCourse;
    $this->course->setCourseJobAllocation($this);
    
    if ($anOldCourse != null)
    {
      $anOldCourse->setCourseJobAllocation(null);
    }
    $wasSet = true;
    return $wasSet;
  }

  public static function minimumNumberOfApplicants()
  {
    return 0;
  }

  public function addApplicant($aApplicant)
  {
    $wasAdded = false;
    if ($this->indexOfApplicant($aApplicant) !== -1) { return false; }
    $this->applicants[] = $aApplicant;
    if ($aApplicant->indexOfAllocation($this) != -1)
    {
      $wasAdded = true;
    }
    else
    {
      $wasAdded = $aApplicant->addAllocation($this);
      if (!$wasAdded)
      {
        array_pop($this->applicants);
      }
    }
    return $wasAdded;
  }

  public function removeApplicant($aApplicant)
  {
    $wasRemoved = false;
    if ($this->indexOfApplicant($aApplicant) == -1)
    {
      return $wasRemoved;
    }

    $oldIndex = $this->indexOfApplicant($aApplicant);
    unset($this->applicants[$oldIndex]);
    if ($aApplicant->indexOfAllocation($this) == -1)
    {
      $wasRemoved = true;
    }
    else
    {
      $wasRemoved = $aApplicant->removeAllocation($this);
      if (!$wasRemoved)
      {
        $this->applicants[$oldIndex] = $aApplicant;
        ksort($this->applicants);
      }
    }
    $this->applicants = array_values($this->applicants);
    return $wasRemoved;
  }

  public function addApplicantAt($aApplicant, $index)
  {  
    $wasAdded = false;
    if($this->addApplicant($aApplicant))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfApplicants()) { $index = $this->numberOfApplicants() - 1; }
      array_splice($this->applicants, $this->indexOfApplicant($aApplicant), 1);
      array_splice($this->applicants, $index, 0, array($aApplicant));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveApplicantAt($aApplicant, $index)
  {
    $wasAdded = false;
    if($this->indexOfApplicant($aApplicant) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfApplicants()) { $index = $this->numberOfApplicants() - 1; }
      array_splice($this->applicants, $this->indexOfApplicant($aApplicant), 1);
      array_splice($this->applicants, $index, 0, array($aApplicant));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addApplicantAt($aApplicant, $index);
    }
    return $wasAdded;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    $existingCourse = $this->course;
    $this->course = null;
    if ($existingCourse != null)
    {
      $existingCourse->setCourseJobAllocation(null);
    }
    $copyOfApplicants = $this->applicants;
    $this->applicants = array();
    foreach ($copyOfApplicants as $aApplicant)
    {
      $aApplicant->removeAllocation($this);
    }
  }

}
?>