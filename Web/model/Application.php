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
  private $jobPostings;
  private $applicants;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aApplicationStatus, $allJobPostings)
  {
    $this->applicationStatus = $aApplicationStatus;
    $this->jobPostings = array();
    $didAddJobPostings = $this->setJobPostings($allJobPostings);
    if (!$didAddJobPostings)
    {
      throw new Exception("Unable to create Application, must have 1 to 3 jobPostings");
    }
    $this->applicants = array();
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

  public function getJobPosting_index($index)
  {
    $aJobPosting = $this->jobPostings[$index];
    return $aJobPosting;
  }

  public function getJobPostings()
  {
    $newJobPostings = $this->jobPostings;
    return $newJobPostings;
  }

  public function numberOfJobPostings()
  {
    $number = count($this->jobPostings);
    return $number;
  }

  public function hasJobPostings()
  {
    $has = $this->numberOfJobPostings() > 0;
    return $has;
  }

  public function indexOfJobPosting($aJobPosting)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->jobPostings as $jobPosting)
    {
      if ($jobPosting->equals($aJobPosting))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
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

  public function isNumberOfJobPostingsValid()
  {
    $isValid = $this->numberOfJobPostings() >= self::minimumNumberOfJobPostings() && $this->numberOfJobPostings() <= self::maximumNumberOfJobPostings();
    return $isValid;
  }

  public static function minimumNumberOfJobPostings()
  {
    return 1;
  }

  public static function maximumNumberOfJobPostings()
  {
    return 3;
  }

  public function addJobPosting($aJobPosting)
  {
    $wasAdded = false;
    if ($this->indexOfJobPosting($aJobPosting) !== -1) { return false; }
    if ($this->numberOfJobPostings() >= self::maximumNumberOfJobPostings())
    {
      return $wasAdded;
    }

    $this->jobPostings[] = $aJobPosting;
    if ($aJobPosting->indexOfApplication($this) != -1)
    {
      $wasAdded = true;
    }
    else
    {
      $wasAdded = $aJobPosting->addApplication($this);
      if (!$wasAdded)
      {
        array_pop($this->jobPostings);
      }
    }
    return $wasAdded;
  }

  public function removeJobPosting($aJobPosting)
  {
    $wasRemoved = false;
    if ($this->indexOfJobPosting($aJobPosting) == -1)
    {
      return $wasRemoved;
    }

    if ($this->numberOfJobPostings() <= self::minimumNumberOfJobPostings())
    {
      return $wasRemoved;
    }

    $oldIndex = $this->indexOfJobPosting($aJobPosting);
    unset($this->jobPostings[$oldIndex]);
    if ($aJobPosting->indexOfApplication($this) == -1)
    {
      $wasRemoved = true;
    }
    else
    {
      $wasRemoved = $aJobPosting->removeApplication($this);
      if (!$wasRemoved)
      {
        $this->jobPostings[$oldIndex] = $aJobPosting;
        ksort($this->jobPostings);
      }
    }
    $this->jobPostings = array_values($this->jobPostings);
    return $wasRemoved;
  }

  public function setJobPostings($newJobPostings)
  {
    $wasSet = false;
    $verifiedJobPostings = array();
    foreach ($newJobPostings as $aJobPosting)
    {
      if (array_search($aJobPosting,$verifiedJobPostings) !== false)
      {
        continue;
      }
      $verifiedJobPostings[] = $aJobPosting;
    }

    if (count($verifiedJobPostings) != count($newJobPostings) || count($verifiedJobPostings) < self::minimumNumberOfJobPostings() || count($verifiedJobPostings) > self::maximumNumberOfJobPostings())
    {
      return $wasSet;
    }

    $oldJobPostings = $this->jobPostings;
    $this->jobPostings = array();
    foreach ($verifiedJobPostings as $aNewJobPosting)
    {
      $this->jobPostings[] = $aNewJobPosting;
      $removeIndex = array_search($aNewJobPosting,$oldJobPostings);
      if ($removeIndex !== false)
      {
        unset($oldJobPostings[$removeIndex]);
        $oldJobPostings = array_values($oldJobPostings);
      }
      else
      {
        $aNewJobPosting->addApplication($this);
      }
    }

    foreach ($oldJobPostings as $anOldJobPosting)
    {
      $anOldJobPosting->removeApplication($this);
    }
    $wasSet = true;
    return $wasSet;
  }

  public function addJobPostingAt($aJobPosting, $index)
  {  
    $wasAdded = false;
    if($this->addJobPosting($aJobPosting))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfJobPostings()) { $index = $this->numberOfJobPostings() - 1; }
      array_splice($this->jobPostings, $this->indexOfJobPosting($aJobPosting), 1);
      array_splice($this->jobPostings, $index, 0, array($aJobPosting));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveJobPostingAt($aJobPosting, $index)
  {
    $wasAdded = false;
    if($this->indexOfJobPosting($aJobPosting) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfJobPostings()) { $index = $this->numberOfJobPostings() - 1; }
      array_splice($this->jobPostings, $this->indexOfJobPosting($aJobPosting), 1);
      array_splice($this->jobPostings, $index, 0, array($aJobPosting));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addJobPostingAt($aJobPosting, $index);
    }
    return $wasAdded;
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
    if ($aApplicant->indexOfApplication($this) != -1)
    {
      $wasAdded = true;
    }
    else
    {
      $wasAdded = $aApplicant->addApplication($this);
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
    if ($aApplicant->indexOfApplication($this) == -1)
    {
      $wasRemoved = true;
    }
    else
    {
      $wasRemoved = $aApplicant->removeApplication($this);
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
    $copyOfJobPostings = $this->jobPostings;
    $this->jobPostings = array();
    foreach ($copyOfJobPostings as $aJobPosting)
    {
      $aJobPosting->removeApplication($this);
    }
    $copyOfApplicants = $this->applicants;
    $this->applicants = array();
    foreach ($copyOfApplicants as $aApplicant)
    {
      $aApplicant->removeApplication($this);
    }
  }

}
?>