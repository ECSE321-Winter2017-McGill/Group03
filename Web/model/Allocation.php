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

  public function __construct($aCourse = null)
  {
    if (func_num_args() == 0) { return; }

    if ($aCourse == null || $aCourse->getCourseJobAllocation() != null)
    {
      throw new Exception("Unable to create Allocation due to aCourse");
    }
    $this->course = $aCourse;
    $this->applicants = array();
  }
  public static function newInstance($aSemesterForCourse, $aCourseCoudeForCourse, $aNumTutorialForCourse, $aNumLabForCourse, $aNumStudentForCourse, $aCreditForCourse, $aHourRequiredTaForCourse, $aHourRequiredGraderForCourse, $aBudgetCalculatedForCourse, $aInstructorForCourse, $aManagementSystemForCourse)
  {
    $thisInstance = new Allocation();
    $thisInstance->course = new Course($aSemesterForCourse, $aCourseCoudeForCourse, $aNumTutorialForCourse, $aNumLabForCourse, $aNumStudentForCourse, $aCreditForCourse, $aHourRequiredTaForCourse, $aHourRequiredGraderForCourse, $aBudgetCalculatedForCourse, $thisInstance, $aInstructorForCourse, $aManagementSystemForCourse);
    $this->applicants = array();
    return $thisInstance;
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

  public static function minimumNumberOfApplicants()
  {
    return 0;
  }

  public function addApplicantVia($aStudentID, $aName, $aPreviousExperience, $aIsUnderGraduated, $aPreferredCourse, $aMajor, $aYear, $aOption1, $aOption2, $aOption3, $aTotalAppointmentHours, $aManagementSystem)
  {
    return new Applicant($aStudentID, $aName, $aPreviousExperience, $aIsUnderGraduated, $aPreferredCourse, $aMajor, $aYear, $aOption1, $aOption2, $aOption3, $aTotalAppointmentHours, $this, $aManagementSystem);
  }

  public function addApplicant($aApplicant)
  {
    $wasAdded = false;
    if ($this->indexOfApplicant($aApplicant) !== -1) { return false; }
    $existingAllocation = $aApplicant->getAllocation();
    $isNewAllocation = $existingAllocation != null && $this !== $existingAllocation;
    if ($isNewAllocation)
    {
      $aApplicant->setAllocation($this);
    }
    else
    {
      $this->applicants[] = $aApplicant;
    }
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeApplicant($aApplicant)
  {
    $wasRemoved = false;
    //Unable to remove aApplicant, as it must always have a allocation
    if ($this !== $aApplicant->getAllocation())
    {
      unset($this->applicants[$this->indexOfApplicant($aApplicant)]);
      $this->applicants = array_values($this->applicants);
      $wasRemoved = true;
    }
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
      $existingCourse->delete();
    }
    foreach ($this->applicants as $aApplicant)
    {
      $aApplicant->delete();
    }
  }

}
?>