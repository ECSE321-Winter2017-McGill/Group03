<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

class Applicant
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Applicant Attributes
  private $studentID;
  private $name;
  private $previousExperience;
  private $isUnderGraduated;
  private $major;
  private $year;
  private $firstChoice;
  private $secondChoice;
  private $thirdChoice;
  private $evaluation;
  private $totalAppointmentHours;

  //Applicant Associations
  private $allocations;
  private $offeredJobs;
  private $managementSystem;
  private $applications;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aStudentID, $aName, $aPreviousExperience, $aIsUnderGraduated, $aMajor, $aYear, $aFirstChoice, $aSecondChoice, $aThirdChoice, $aEvaluation, $aTotalAppointmentHours, $aManagementSystem)
  {
    $this->studentID = $aStudentID;
    $this->name = $aName;
    $this->previousExperience = $aPreviousExperience;
    $this->isUnderGraduated = $aIsUnderGraduated;
    $this->major = $aMajor;
    $this->year = $aYear;
    $this->firstChoice = $aFirstChoice;
    $this->secondChoice = $aSecondChoice;
    $this->thirdChoice = $aThirdChoice;
    $this->evaluation = $aEvaluation;
    $this->totalAppointmentHours = $aTotalAppointmentHours;
    $this->allocations = array();
    $this->offeredJobs = array();
    $didAddManagementSystem = $this->setManagementSystem($aManagementSystem);
    if (!$didAddManagementSystem)
    {
      throw new Exception("Unable to create applicant due to managementSystem");
    }
    $this->applications = array();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function setStudentID($aStudentID)
  {
    $wasSet = false;
    $this->studentID = $aStudentID;
    $wasSet = true;
    return $wasSet;
  }

  public function setName($aName)
  {
    $wasSet = false;
    $this->name = $aName;
    $wasSet = true;
    return $wasSet;
  }

  public function setPreviousExperience($aPreviousExperience)
  {
    $wasSet = false;
    $this->previousExperience = $aPreviousExperience;
    $wasSet = true;
    return $wasSet;
  }

  public function setIsUnderGraduated($aIsUnderGraduated)
  {
    $wasSet = false;
    $this->isUnderGraduated = $aIsUnderGraduated;
    $wasSet = true;
    return $wasSet;
  }

  public function setMajor($aMajor)
  {
    $wasSet = false;
    $this->major = $aMajor;
    $wasSet = true;
    return $wasSet;
  }

  public function setYear($aYear)
  {
    $wasSet = false;
    $this->year = $aYear;
    $wasSet = true;
    return $wasSet;
  }

  public function setFirstChoice($aFirstChoice)
  {
    $wasSet = false;
    $this->firstChoice = $aFirstChoice;
    $wasSet = true;
    return $wasSet;
  }

  public function setSecondChoice($aSecondChoice)
  {
    $wasSet = false;
    $this->secondChoice = $aSecondChoice;
    $wasSet = true;
    return $wasSet;
  }

  public function setThirdChoice($aThirdChoice)
  {
    $wasSet = false;
    $this->thirdChoice = $aThirdChoice;
    $wasSet = true;
    return $wasSet;
  }

  public function setEvaluation($aEvaluation)
  {
    $wasSet = false;
    $this->evaluation = $aEvaluation;
    $wasSet = true;
    return $wasSet;
  }

  public function setTotalAppointmentHours($aTotalAppointmentHours)
  {
    $wasSet = false;
    $this->totalAppointmentHours = $aTotalAppointmentHours;
    $wasSet = true;
    return $wasSet;
  }

  public function getStudentID()
  {
    return $this->studentID;
  }

  public function getName()
  {
    return $this->name;
  }

  public function getPreviousExperience()
  {
    return $this->previousExperience;
  }

  public function getIsUnderGraduated()
  {
    return $this->isUnderGraduated;
  }

  public function getMajor()
  {
    return $this->major;
  }

  public function getYear()
  {
    return $this->year;
  }

  public function getFirstChoice()
  {
    return $this->firstChoice;
  }

  public function getSecondChoice()
  {
    return $this->secondChoice;
  }

  public function getThirdChoice()
  {
    return $this->thirdChoice;
  }

  public function getEvaluation()
  {
    return $this->evaluation;
  }

  public function getTotalAppointmentHours()
  {
    return $this->totalAppointmentHours;
  }

  public function isIsUnderGraduated()
  {
    return $this->isUnderGraduated;
  }

  public function getAllocation_index($index)
  {
    $aAllocation = $this->allocations[$index];
    return $aAllocation;
  }

  public function getAllocations()
  {
    $newAllocations = $this->allocations;
    return $newAllocations;
  }

  public function numberOfAllocations()
  {
    $number = count($this->allocations);
    return $number;
  }

  public function hasAllocations()
  {
    $has = $this->numberOfAllocations() > 0;
    return $has;
  }

  public function indexOfAllocation($aAllocation)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->allocations as $allocation)
    {
      if ($allocation->equals($aAllocation))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public function getOfferedJob_index($index)
  {
    $aOfferedJob = $this->offeredJobs[$index];
    return $aOfferedJob;
  }

  public function getOfferedJobs()
  {
    $newOfferedJobs = $this->offeredJobs;
    return $newOfferedJobs;
  }

  public function numberOfOfferedJobs()
  {
    $number = count($this->offeredJobs);
    return $number;
  }

  public function hasOfferedJobs()
  {
    $has = $this->numberOfOfferedJobs() > 0;
    return $has;
  }

  public function indexOfOfferedJob($aOfferedJob)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->offeredJobs as $offeredJob)
    {
      if ($offeredJob->equals($aOfferedJob))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public function getManagementSystem()
  {
    return $this->managementSystem;
  }

  public function getApplication_index($index)
  {
    $aApplication = $this->applications[$index];
    return $aApplication;
  }

  public function getApplications()
  {
    $newApplications = $this->applications;
    return $newApplications;
  }

  public function numberOfApplications()
  {
    $number = count($this->applications);
    return $number;
  }

  public function hasApplications()
  {
    $has = $this->numberOfApplications() > 0;
    return $has;
  }

  public function indexOfApplication($aApplication)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->applications as $application)
    {
      if ($application->equals($aApplication))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public static function minimumNumberOfAllocations()
  {
    return 0;
  }

  public function addAllocation($aAllocation)
  {
    $wasAdded = false;
    if ($this->indexOfAllocation($aAllocation) !== -1) { return false; }
    $this->allocations[] = $aAllocation;
    if ($aAllocation->indexOfApplicant($this) != -1)
    {
      $wasAdded = true;
    }
    else
    {
      $wasAdded = $aAllocation->addApplicant($this);
      if (!$wasAdded)
      {
        array_pop($this->allocations);
      }
    }
    return $wasAdded;
  }

  public function removeAllocation($aAllocation)
  {
    $wasRemoved = false;
    if ($this->indexOfAllocation($aAllocation) == -1)
    {
      return $wasRemoved;
    }

    $oldIndex = $this->indexOfAllocation($aAllocation);
    unset($this->allocations[$oldIndex]);
    if ($aAllocation->indexOfApplicant($this) == -1)
    {
      $wasRemoved = true;
    }
    else
    {
      $wasRemoved = $aAllocation->removeApplicant($this);
      if (!$wasRemoved)
      {
        $this->allocations[$oldIndex] = $aAllocation;
        ksort($this->allocations);
      }
    }
    $this->allocations = array_values($this->allocations);
    return $wasRemoved;
  }

  public function addAllocationAt($aAllocation, $index)
  {  
    $wasAdded = false;
    if($this->addAllocation($aAllocation))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfAllocations()) { $index = $this->numberOfAllocations() - 1; }
      array_splice($this->allocations, $this->indexOfAllocation($aAllocation), 1);
      array_splice($this->allocations, $index, 0, array($aAllocation));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveAllocationAt($aAllocation, $index)
  {
    $wasAdded = false;
    if($this->indexOfAllocation($aAllocation) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfAllocations()) { $index = $this->numberOfAllocations() - 1; }
      array_splice($this->allocations, $this->indexOfAllocation($aAllocation), 1);
      array_splice($this->allocations, $index, 0, array($aAllocation));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addAllocationAt($aAllocation, $index);
    }
    return $wasAdded;
  }

  public static function minimumNumberOfOfferedJobs()
  {
    return 0;
  }

  public static function maximumNumberOfOfferedJobs()
  {
    return 3;
  }

  public function addOfferedJobVia($aOfferDescription, $aCourse)
  {
    if ($this->numberOfOfferedJobs() >= self::maximumNumberOfOfferedJobs())
    {
      return null;
    }
    else
    {
      return new OfferedJob($aOfferDescription, $aCourse, $this);
    }
  }

  public function addOfferedJob($aOfferedJob)
  {
    $wasAdded = false;
    if ($this->indexOfOfferedJob($aOfferedJob) !== -1) { return false; }
    if ($this->numberOfOfferedJobs() >= self::maximumNumberOfOfferedJobs())
    {
      return $wasAdded;
    }

    $existingApplicant = $aOfferedJob->getApplicant();
    $isNewApplicant = $existingApplicant != null && $this !== $existingApplicant;
    if ($isNewApplicant)
    {
      $aOfferedJob->setApplicant($this);
    }
    else
    {
      $this->offeredJobs[] = $aOfferedJob;
    }
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeOfferedJob($aOfferedJob)
  {
    $wasRemoved = false;
    //Unable to remove aOfferedJob, as it must always have a applicant
    if ($this !== $aOfferedJob->getApplicant())
    {
      unset($this->offeredJobs[$this->indexOfOfferedJob($aOfferedJob)]);
      $this->offeredJobs = array_values($this->offeredJobs);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addOfferedJobAt($aOfferedJob, $index)
  {  
    $wasAdded = false;
    if($this->addOfferedJob($aOfferedJob))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfOfferedJobs()) { $index = $this->numberOfOfferedJobs() - 1; }
      array_splice($this->offeredJobs, $this->indexOfOfferedJob($aOfferedJob), 1);
      array_splice($this->offeredJobs, $index, 0, array($aOfferedJob));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveOfferedJobAt($aOfferedJob, $index)
  {
    $wasAdded = false;
    if($this->indexOfOfferedJob($aOfferedJob) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfOfferedJobs()) { $index = $this->numberOfOfferedJobs() - 1; }
      array_splice($this->offeredJobs, $this->indexOfOfferedJob($aOfferedJob), 1);
      array_splice($this->offeredJobs, $index, 0, array($aOfferedJob));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addOfferedJobAt($aOfferedJob, $index);
    }
    return $wasAdded;
  }

  public function setManagementSystem($aManagementSystem)
  {
    $wasSet = false;
    //Must provide managementSystem to applicant
    if ($aManagementSystem == null)
    {
      return $wasSet;
    }

    if ($this->managementSystem != null && $this->managementSystem->numberOfApplicants() <= ManagementSystem::minimumNumberOfApplicants())
    {
      return $wasSet;
    }

    $existingManagementSystem = $this->managementSystem;
    $this->managementSystem = $aManagementSystem;
    if ($existingManagementSystem != null && $existingManagementSystem != $aManagementSystem)
    {
      $didRemove = $existingManagementSystem->removeApplicant($this);
      if (!$didRemove)
      {
        $this->managementSystem = $existingManagementSystem;
        return $wasSet;
      }
    }
    $this->managementSystem->addApplicant($this);
    $wasSet = true;
    return $wasSet;
  }

  public static function minimumNumberOfApplications()
  {
    return 0;
  }

  public static function maximumNumberOfApplications()
  {
    return 3;
  }

  public function addApplicationVia($aApplicationStatus, $aJobPosting)
  {
    if ($this->numberOfApplications() >= self::maximumNumberOfApplications())
    {
      return null;
    }
    else
    {
      return new Application($aApplicationStatus, $aJobPosting, $this);
    }
  }

  public function addApplication($aApplication)
  {
    $wasAdded = false;
    if ($this->indexOfApplication($aApplication) !== -1) { return false; }
    if ($this->numberOfApplications() >= self::maximumNumberOfApplications())
    {
      return $wasAdded;
    }

    $existingApplicant = $aApplication->getApplicant();
    $isNewApplicant = $existingApplicant != null && $this !== $existingApplicant;
    if ($isNewApplicant)
    {
      $aApplication->setApplicant($this);
    }
    else
    {
      $this->applications[] = $aApplication;
    }
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeApplication($aApplication)
  {
    $wasRemoved = false;
    //Unable to remove aApplication, as it must always have a applicant
    if ($this !== $aApplication->getApplicant())
    {
      unset($this->applications[$this->indexOfApplication($aApplication)]);
      $this->applications = array_values($this->applications);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addApplicationAt($aApplication, $index)
  {  
    $wasAdded = false;
    if($this->addApplication($aApplication))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfApplications()) { $index = $this->numberOfApplications() - 1; }
      array_splice($this->applications, $this->indexOfApplication($aApplication), 1);
      array_splice($this->applications, $index, 0, array($aApplication));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveApplicationAt($aApplication, $index)
  {
    $wasAdded = false;
    if($this->indexOfApplication($aApplication) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfApplications()) { $index = $this->numberOfApplications() - 1; }
      array_splice($this->applications, $this->indexOfApplication($aApplication), 1);
      array_splice($this->applications, $index, 0, array($aApplication));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addApplicationAt($aApplication, $index);
    }
    return $wasAdded;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    $copyOfAllocations = $this->allocations;
    $this->allocations = array();
    foreach ($copyOfAllocations as $aAllocation)
    {
      $aAllocation->removeApplicant($this);
    }
    foreach ($this->offeredJobs as $aOfferedJob)
    {
      $aOfferedJob->delete();
    }
    $placeholderManagementSystem = $this->managementSystem;
    $this->managementSystem = null;
    $placeholderManagementSystem->removeApplicant($this);
    foreach ($this->applications as $aApplication)
    {
      $aApplication->delete();
    }
  }

}
?>