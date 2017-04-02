<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

class OfferedJob
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //OfferedJob Attributes
  private $offerDescription;

  //OfferedJob State Machines
  private static $JobTA = 1;
  private static $JobGRADER = 2;
  private $job;

  //OfferedJob Associations
  private $course;
  private $applicant;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aOfferDescription, $aCourse, $aApplicant)
  {
    $this->offerDescription = $aOfferDescription;
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
    $this->setJob(self::$JobTA);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function setOfferDescription($aOfferDescription)
  {
    $wasSet = false;
    $this->offerDescription = $aOfferDescription;
    $wasSet = true;
    return $wasSet;
  }

  public function getOfferDescription()
  {
    return $this->offerDescription;
  }

  public function getJobFullName()
  {
    $answer = $this->getJob();
    return $answer;
  }

  public function getJob()
  {
    if ($this->job == self::$JobTA) { return "JobTA"; }
    elseif ($this->job == self::$JobGRADER) { return "JobGRADER"; }
    return null;
  }

  public function setJob($aJob)
  {
    if ($aJob == "JobTA" || $aJob == self::$JobTA)
    {
      $this->job = self::$JobTA;
      return true;
    }
    elseif ($aJob == "JobGRADER" || $aJob == self::$JobGRADER)
    {
      $this->job = self::$JobGRADER;
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