<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-15de3ff modeling language!*/

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

  //Helper Variables
  private $cachedHashCode;
  private $canSetJobPosting;
  private $canSetApplicant;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aApplicationStatus, $aJobPosting, $aApplicant)
  {
    $this->cachedHashCode = -1;
    $this->canSetJobPosting = true;
    $this->canSetApplicant = true;
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
    if (!$this->canSetJobPosting) { return false; }
    //Must provide jobPosting to application
    if ($aJobPosting == null)
    {
      return $wasSet;
    }

    //jobPosting already at maximum (3)
    if ($aJobPosting->numberOfApplications() >= JobPosting::maximumNumberOfApplications())
    {
      return $wasSet;
    }
    
    $existingJobPosting = $this->jobPosting;
    $this->jobPosting = $aJobPosting;
    if ($existingJobPosting != null && $existingJobPosting != $aJobPosting)
    {
      $didRemove = $existingJobPosting->removeApplication($this);
      if (!$didRemove)
      {
        $this->jobPosting = $existingJobPosting;
        return $wasSet;
      }
    }
    $this->jobPosting->addApplication($this);
    $wasSet = true;
    return $wasSet;
  }

  public function setApplicant($aApplicant)
  {
    $wasSet = false;
    if (!$this->canSetApplicant) { return false; }
//     if ($aApplicant.nil?)
//     {
//       return wasSet;
//     }

    $existingApplicant = $this->applicant;
    $this->applicant = $aApplicant;
    if (!$existingApplicant->is_null() && !$existingApplicant->equals($aApplicant))
    {
      $existingApplicant->removeApplication($this);
    }
    if (!$this->applicant->addApplication($this))
    {
      $this->applicant = $existingApplicant;
      $wasSet = false;
    }
    else
    {
      $wasSet = true;
    }
    return $wasSet;
  }
  public function equals($compareTo)
  {
    if ($compareTo == null) { return false; }
    if (get_class($this) != get_class($compareTo)) { return false; }

    if ($this->jobPosting != $compareTo->jobPosting)
    {
      return false;
    }

    if ($this->applicant != $compareTo->applicant)
    {
      return false;
    }

    return true;
  }

  public function hashCode()
  {
    if ($this->cachedHashCode != -1)
    {
      return $this->cachedHashCode;
    }
    $this->cachedHashCode = 17;
    if ($this->jobPosting != null)
    {
      $this->cachedHashCode = $this->cachedHashCode * 23 + spl_object_hash($this->jobPosting);
    }
    else
    {
      $this->cachedHashCode = $this->cachedHashCode * 23;
    }
    if ($this->applicant != null)
    {
      $this->cachedHashCode = $this->cachedHashCode * 23 + spl_object_hash($this->applicant);
    }
    else
    {
      $this->cachedHashCode = $this->cachedHashCode * 23;
    }

    $this->canSetJobPosting = false;
    $this->canSetApplicant = false;
    return $this->cachedHashCode;
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