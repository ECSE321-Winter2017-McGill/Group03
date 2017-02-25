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
  private $preferredCourse;
  private $major;
  private $year;
  private $option1;
  private $option2;
  private $option3;
  private $totalAppointmentHours;

  //Applicant Associations
  private $allocation;
  private $offeredJobs;
  private $managementSystem;
  private $applications;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aStudentID, $aName, $aPreviousExperience, $aIsUnderGraduated, $aPreferredCourse, $aMajor, $aYear, $aOption1, $aOption2, $aOption3, $aTotalAppointmentHours, $aAllocation, $aManagementSystem)
  {
    $this->studentID = $aStudentID;
    $this->name = $aName;
    $this->previousExperience = $aPreviousExperience;
    $this->isUnderGraduated = $aIsUnderGraduated;
    $this->preferredCourse = $aPreferredCourse;
    $this->major = $aMajor;
    $this->year = $aYear;
    $this->option1 = $aOption1;
    $this->option2 = $aOption2;
    $this->option3 = $aOption3;
    $this->totalAppointmentHours = $aTotalAppointmentHours;
    $didAddAllocation = $this->setAllocation($aAllocation);
    if (!$didAddAllocation)
    {
      throw new Exception("Unable to create applicant due to allocation");
    }
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

  public function setPreferredCourse($aPreferredCourse)
  {
    $wasSet = false;
    $this->preferredCourse = $aPreferredCourse;
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

  public function setOption1($aOption1)
  {
    $wasSet = false;
    $this->option1 = $aOption1;
    $wasSet = true;
    return $wasSet;
  }

  public function setOption2($aOption2)
  {
    $wasSet = false;
    $this->option2 = $aOption2;
    $wasSet = true;
    return $wasSet;
  }

  public function setOption3($aOption3)
  {
    $wasSet = false;
    $this->option3 = $aOption3;
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

  public function getPreferredCourse()
  {
    return $this->preferredCourse;
  }

  public function getMajor()
  {
    return $this->major;
  }

  public function getYear()
  {
    return $this->year;
  }

  public function getOption1()
  {
    return $this->option1;
  }

  public function getOption2()
  {
    return $this->option2;
  }

  public function getOption3()
  {
    return $this->option3;
  }

  public function getTotalAppointmentHours()
  {
    return $this->totalAppointmentHours;
  }

  public function isIsUnderGraduated()
  {
    return $this->isUnderGraduated;
  }

  public function getAllocation()
  {
    return $this->allocation;
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

  public function setAllocation($aAllocation)
  {
    $wasSet = false;
    if ($aAllocation == null)
    {
      return $wasSet;
    }
    
    $existingAllocation = $this->allocation;
    $this->allocation = $aAllocation;
    if ($existingAllocation != null && $existingAllocation != $aAllocation)
    {
      $existingAllocation->removeApplicant($this);
    }
    $this->allocation->addApplicant($this);
    $wasSet = true;
    return $wasSet;
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

  public function addApplication($aApplication)
  {
    $wasAdded = false;
    if ($this->indexOfApplication($aApplication) !== -1) { return false; }
    if ($this->numberOfApplications() >= self::maximumNumberOfApplications())
    {
      return $wasAdded;
    }

    $this->applications[] = $aApplication;
    if ($aApplication->indexOfApplicant($this) != -1)
    {
      $wasAdded = true;
    }
    else
    {
      $wasAdded = $aApplication->addApplicant($this);
      if (!$wasAdded)
      {
        array_pop($this->applications);
      }
    }
    return $wasAdded;
  }

  public function removeApplication($aApplication)
  {
    $wasRemoved = false;
    if ($this->indexOfApplication($aApplication) == -1)
    {
      return $wasRemoved;
    }

    $oldIndex = $this->indexOfApplication($aApplication);
    unset($this->applications[$oldIndex]);
    if ($aApplication->indexOfApplicant($this) == -1)
    {
      $wasRemoved = true;
    }
    else
    {
      $wasRemoved = $aApplication->removeApplicant($this);
      if (!$wasRemoved)
      {
        $this->applications[$oldIndex] = $aApplication;
        ksort($this->applications);
      }
    }
    $this->applications = array_values($this->applications);
    return $wasRemoved;
  }

  public function setApplications($newApplications)
  {
    $wasSet = false;
    $verifiedApplications = array();
    foreach ($newApplications as $aApplication)
    {
      if (array_search($aApplication,$verifiedApplications) !== false)
      {
        continue;
      }
      $verifiedApplications[] = $aApplication;
    }

    if (count($verifiedApplications) != count($newApplications) || count($verifiedApplications) > self::maximumNumberOfApplications())
    {
      return $wasSet;
    }

    $oldApplications = $this->applications;
    $this->applications = array();
    foreach ($verifiedApplications as $aNewApplication)
    {
      $this->applications[] = $aNewApplication;
      $removeIndex = array_search($aNewApplication,$oldApplications);
      if ($removeIndex !== false)
      {
        unset($oldApplications[$removeIndex]);
        $oldApplications = array_values($oldApplications);
      }
      else
      {
        $aNewApplication->addApplicant($this);
      }
    }

    foreach ($oldApplications as $anOldApplication)
    {
      $anOldApplication->removeApplicant($this);
    }
    $wasSet = true;
    return $wasSet;
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
    $placeholderAllocation = $this->allocation;
    $this->allocation = null;
    $placeholderAllocation->removeApplicant($this);
    foreach ($this->offeredJobs as $aOfferedJob)
    {
      $aOfferedJob->delete();
    }
    $placeholderManagementSystem = $this->managementSystem;
    $this->managementSystem = null;
    $placeholderManagementSystem->removeApplicant($this);
    $copyOfApplications = $this->applications;
    $this->applications = array();
    foreach ($copyOfApplications as $aApplication)
    {
      $aApplication->removeApplicant($this);
    }
  }

}
?>