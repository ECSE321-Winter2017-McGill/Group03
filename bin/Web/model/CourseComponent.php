<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

class CourseComponent
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //CourseComponent Attributes
  private $hour;
  private $sessionCode;

  //CourseComponent State Machines
  private static $ComponentTUTORIAL = 1;
  private static $ComponentLAB = 2;
  private $component;

  //CourseComponent Associations
  private $course;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aHour, $aSessionCode, $aCourse)
  {
    $this->hour = $aHour;
    $this->sessionCode = $aSessionCode;
    $didAddCourse = $this->setCourse($aCourse);
    if (!$didAddCourse)
    {
      throw new Exception("Unable to create courseComponent due to course");
    }
    $this->setComponent(self::$ComponentTUTORIAL);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function setHour($aHour)
  {
    $wasSet = false;
    $this->hour = $aHour;
    $wasSet = true;
    return $wasSet;
  }

  public function setSessionCode($aSessionCode)
  {
    $wasSet = false;
    $this->sessionCode = $aSessionCode;
    $wasSet = true;
    return $wasSet;
  }

  public function getHour()
  {
    return $this->hour;
  }

  public function getSessionCode()
  {
    return $this->sessionCode;
  }

  public function getComponentFullName()
  {
    $answer = $this->getComponent();
    return $answer;
  }

  public function getComponent()
  {
    if ($this->component == self::$ComponentTUTORIAL) { return "ComponentTUTORIAL"; }
    elseif ($this->component == self::$ComponentLAB) { return "ComponentLAB"; }
    return null;
  }

  public function setComponent($aComponent)
  {
    if ($aComponent == "ComponentTUTORIAL" || $aComponent == self::$ComponentTUTORIAL)
    {
      $this->component = self::$ComponentTUTORIAL;
      return true;
    }
    elseif ($aComponent == "ComponentLAB" || $aComponent == self::$ComponentLAB)
    {
      $this->component = self::$ComponentLAB;
      return true;
    }
    else
    {
      return false;
    }
  }

  public function getCourse()
  {
    return $this->course;
  }

  public function setCourse($aCourse)
  {
    $wasSet = false;
    if ($aCourse == null)
    {
      return $wasSet;
    }
    
    $existingCourse = $this->course;
    $this->course = $aCourse;
    if ($existingCourse != null && $existingCourse != $aCourse)
    {
      $existingCourse->removeCourseComponent($this);
    }
    $this->course->addCourseComponent($this);
    $wasSet = true;
    return $wasSet;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    $placeholderCourse = $this->course;
    $this->course = null;
    $placeholderCourse->removeCourseComponent($this);
  }

}
?>