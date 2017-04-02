<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

class Application
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Application Attributes
  private $applicationStatus;

  //Application Associations
  private $jobPosting;
  private $applicant;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aApplicationStatus, $aJobPosting, $aApplicant)
  {
    $this->applicationStatus = $aApplicationStatus;
    $didAddJobPosting = $this->setJobPosting($aJobPosting);
    if (!$didAddJobPosting)
    {
      throw new Exception("Unable to create application due to jobPosting");
    }
    $didAddApplicant = $this->setApplicant($aApplicant);
    if (!$didAddApplicant)
    {
      throw new Exception("Unable to create application due to applicant");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function setApplicationStatus($aApplicationStatus)
  {
    $wasSet = false;
    $this->applicationStatus = $aApplicationStatus;
    $wasSet = true;
    return $wasSet;
  }

  public function getApplicationStatus()
  {
    return $this->applicationStatus;
  }

  public function getJobPosting()
  {
    return $this->jobPosting;
  }

  public function getApplicant()
  {
    return $this->applicant;
  }

  public function setJobPosting($aJobPosting)
  {
    $wasSet = false;
    if ($aJobPosting == null)
    {
      return $wasSet;
    }
    
    $existingJobPosting = $this->jobPosting;
    $this->jobPosting = $aJobPosting;
    if ($existingJobPosting != null && $existingJobPosting != $aJobPosting)
    {
      $existingJobPosting->removeApplication($this);
    }
    $this->jobPosting->addApplication($this);
    $wasSet = true;
    return $wasSet;
  }

  public function setApplicant($aApplicant)
  {
    $wasSet = false;
    //Must provide applicant to application
    if ($aApplicant == null)
    {
      return $wasSet;
    }

    //applicant already at maximum (3)
    if ($aApplicant->numberOfApplications() >= Applicant::maximumNumberOfApplications())
    {
      return $wasSet;
    }
    
    $existingApplicant = $this->applicant;
    $this->applicant = $aApplicant;
    if ($existingApplicant != null && $existingApplicant != $aApplicant)
    {
      $didRemove = $existingApplicant->removeApplication($this);
      if (!$didRemove)
      {
        $this->applicant = $existingApplicant;
        return $wasSet;
      }
    }
    $this->applicant->addApplication($this);
    $wasSet = true;
    return $wasSet;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    $placeholderJobPosting = $this->jobPosting;
    $this->jobPosting = null;
    $placeholderJobPosting->removeApplication($this);
    $placeholderApplicant = $this->applicant;
    $this->applicant = null;
    $placeholderApplicant->removeApplication($this);
  }

}
?>