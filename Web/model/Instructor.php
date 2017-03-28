<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

class Instructor
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Instructor Attributes
  private $name;

  //Instructor Associations
  private $managementSystem;
  private $courses;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aName, $aManagementSystem)
  {
    $this->name = $aName;
    $didAddManagementSystem = $this->setManagementSystem($aManagementSystem);
    if (!$didAddManagementSystem)
    {
      throw new Exception("Unable to create instructor due to managementSystem");
    }
    $this->courses = array();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function setName($aName)
  {
    $wasSet = false;
    $this->name = $aName;
    $wasSet = true;
    return $wasSet;
  }

  public function getName()
  {
    return $this->name;
  }

  public function getManagementSystem()
  {
    return $this->managementSystem;
  }

  public function getCourse_index($index)
  {
    $aCourse = $this->courses[$index];
    return $aCourse;
  }

  public function getCourses()
  {
    $newCourses = $this->courses;
    return $newCourses;
  }

  public function numberOfCourses()
  {
    $number = count($this->courses);
    return $number;
  }

  public function hasCourses()
  {
    $has = $this->numberOfCourses() > 0;
    return $has;
  }

  public function indexOfCourse($aCourse)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->courses as $course)
    {
      if ($course->equals($aCourse))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public function setManagementSystem($aManagementSystem)
  {
    $wasSet = false;
    if ($aManagementSystem == null)
    {
      return $wasSet;
    }
    
    $existingManagementSystem = $this->managementSystem;
    $this->managementSystem = $aManagementSystem;
    if ($existingManagementSystem != null && $existingManagementSystem != $aManagementSystem)
    {
      $existingManagementSystem->removeInstructor($this);
    }
    $this->managementSystem->addInstructor($this);
    $wasSet = true;
    return $wasSet;
  }

  public static function minimumNumberOfCourses()
  {
    return 0;
  }

  public function addCourseVia($aSemester, $aCourseName, $aCourseCode, $aNumTutorial, $aNumLab, $aNumStudent, $aCredit, $aNumTaNeeded, $aNumGraderNeeded, $aHourRequiredTa, $aHourRequiredGrader, $aBudgetCalculated, $aManagementSystem)
  {
    return new Course($aSemester, $aCourseName, $aCourseCode, $aNumTutorial, $aNumLab, $aNumStudent, $aCredit, $aNumTaNeeded, $aNumGraderNeeded, $aHourRequiredTa, $aHourRequiredGrader, $aBudgetCalculated, $this, $aManagementSystem);
  }

  public function addCourse($aCourse)
  {
    $wasAdded = false;
    if ($this->indexOfCourse($aCourse) !== -1) { return false; }
    $existingInstructor = $aCourse->getInstructor();
    $isNewInstructor = $existingInstructor != null && $this !== $existingInstructor;
    if ($isNewInstructor)
    {
      $aCourse->setInstructor($this);
    }
    else
    {
      $this->courses[] = $aCourse;
    }
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeCourse($aCourse)
  {
    $wasRemoved = false;
    //Unable to remove aCourse, as it must always have a instructor
    if ($this !== $aCourse->getInstructor())
    {
      unset($this->courses[$this->indexOfCourse($aCourse)]);
      $this->courses = array_values($this->courses);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addCourseAt($aCourse, $index)
  {  
    $wasAdded = false;
    if($this->addCourse($aCourse))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfCourses()) { $index = $this->numberOfCourses() - 1; }
      array_splice($this->courses, $this->indexOfCourse($aCourse), 1);
      array_splice($this->courses, $index, 0, array($aCourse));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveCourseAt($aCourse, $index)
  {
    $wasAdded = false;
    if($this->indexOfCourse($aCourse) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfCourses()) { $index = $this->numberOfCourses() - 1; }
      array_splice($this->courses, $this->indexOfCourse($aCourse), 1);
      array_splice($this->courses, $index, 0, array($aCourse));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addCourseAt($aCourse, $index);
    }
    return $wasAdded;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    $placeholderManagementSystem = $this->managementSystem;
    $this->managementSystem = null;
    $placeholderManagementSystem->removeInstructor($this);
    foreach ($this->courses as $aCourse)
    {
      $aCourse->delete();
    }
  }

}
?>