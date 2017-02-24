<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

class OfferedJob
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //OfferedJob Attributes
  private $description;

  //OfferedJob Associations
  private $course;
  private $applicant;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aDescription, $aCourse, $aApplicant)
  {
    $this->description = $aDescription;
    $didAddCourse = $this->setCourse($aCourse);
    if (!$didAddCourse)
    {
      throw new Exception("Unable to create offeredJob due to course");
    }
    $didAddApplicant = $this->setApplicant($aApplicant);
    if (!$didAddApplicant)
    {
      throw new Exception("Unable to create offeredJob due to applicant");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function setDescription($aDescription)
  {
    $wasSet = false;
    $this->description = $aDescription;
    $wasSet = true;
    return $wasSet;
  }

  public function getDescription()
  {
    return $this->description;
  }

  public function getCourse()
  {
    return $this->course;
  }

  public function getApplicant()
  {
    return $this->applicant;
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
      $existingCourse->removeOfferedJob($this);
    }
    $this->course->addOfferedJob($this);
    $wasSet = true;
    return $wasSet;
  }

  public function setApplicant($aApplicant)
  {
    $wasSet = false;
    //Must provide applicant to offeredJob
    if ($aApplicant == null)
    {
      return $wasSet;
    }

    //applicant already at maximum (3)
    if ($aApplicant->numberOfOfferedJobs() >= Applicant::maximumNumberOfOfferedJobs())
    {
      return $wasSet;
    }
    
    $existingApplicant = $this->applicant;
    $this->applicant = $aApplicant;
    if ($existingApplicant != null && $existingApplicant != $aApplicant)
    {
      $didRemove = $existingApplicant->removeOfferedJob($this);
      if (!$didRemove)
      {
        $this->applicant = $existingApplicant;
        return $wasSet;
      }
    }
    $this->applicant->addOfferedJob($this);
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
    $placeholderCourse->removeOfferedJob($this);
    $placeholderApplicant = $this->applicant;
    $this->applicant = null;
    $placeholderApplicant->removeOfferedJob($this);
  }

}
?>