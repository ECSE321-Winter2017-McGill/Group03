<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

class JobPosting
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //JobPosting Attributes
  private $jobTitle;
  private $submissionDeadline;
  private $perferredExperience;
  private $numNeeded;
  private $hourRate;

  //JobPosting Associations
  private $managementSystem;
  private $course;
  private $applications;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aJobTitle, $aSubmissionDeadline, $aPerferredExperience, $aNumNeeded, $aHourRate, $aManagementSystem, $aCourse)
  {
    $this->jobTitle = $aJobTitle;
    $this->submissionDeadline = $aSubmissionDeadline;
    $this->perferredExperience = $aPerferredExperience;
    $this->numNeeded = $aNumNeeded;
    $this->hourRate = $aHourRate;
    $didAddManagementSystem = $this->setManagementSystem($aManagementSystem);
    if (!$didAddManagementSystem)
    {
      throw new Exception("Unable to create jobPosting due to managementSystem");
    }
    $didAddCourse = $this->setCourse($aCourse);
    if (!$didAddCourse)
    {
      throw new Exception("Unable to create jobPosting due to course");
    }
    $this->applications = array();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function setJobTitle($aJobTitle)
  {
    $wasSet = false;
    $this->jobTitle = $aJobTitle;
    $wasSet = true;
    return $wasSet;
  }

  public function setSubmissionDeadline($aSubmissionDeadline)
  {
    $wasSet = false;
    $this->submissionDeadline = $aSubmissionDeadline;
    $wasSet = true;
    return $wasSet;
  }

  public function setPerferredExperience($aPerferredExperience)
  {
    $wasSet = false;
    $this->perferredExperience = $aPerferredExperience;
    $wasSet = true;
    return $wasSet;
  }

  public function setNumNeeded($aNumNeeded)
  {
    $wasSet = false;
    $this->numNeeded = $aNumNeeded;
    $wasSet = true;
    return $wasSet;
  }

  public function setHourRate($aHourRate)
  {
    $wasSet = false;
    $this->hourRate = $aHourRate;
    $wasSet = true;
    return $wasSet;
  }

  public function getJobTitle()
  {
    return $this->jobTitle;
  }

  public function getSubmissionDeadline()
  {
    return $this->submissionDeadline;
  }

  public function getPerferredExperience()
  {
    return $this->perferredExperience;
  }

  public function getNumNeeded()
  {
    return $this->numNeeded;
  }

  public function getHourRate()
  {
    return $this->hourRate;
  }

  public function getManagementSystem()
  {
    return $this->managementSystem;
  }

  public function getCourse()
  {
    return $this->course;
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
      $existingManagementSystem->removeJobPosting($this);
    }
    $this->managementSystem->addJobPosting($this);
    $wasSet = true;
    return $wasSet;
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
      $existingCourse->removeJobPosting($this);
    }
    $this->course->addJobPosting($this);
    $wasSet = true;
    return $wasSet;
  }

  public static function minimumNumberOfApplications()
  {
    return 0;
  }

  public function addApplication($aApplication)
  {
    $wasAdded = false;
    if ($this->indexOfApplication($aApplication) !== -1) { return false; }
    $this->applications[] = $aApplication;
    if ($aApplication->indexOfJobPosting($this) != -1)
    {
      $wasAdded = true;
    }
    else
    {
      $wasAdded = $aApplication->addJobPosting($this);
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
    if ($aApplication->indexOfJobPosting($this) == -1)
    {
      $wasRemoved = true;
    }
    else
    {
      $wasRemoved = $aApplication->removeJobPosting($this);
      if (!$wasRemoved)
      {
        $this->applications[$oldIndex] = $aApplication;
        ksort($this->applications);
      }
    }
    $this->applications = array_values($this->applications);
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
    $placeholderManagementSystem = $this->managementSystem;
    $this->managementSystem = null;
    $placeholderManagementSystem->removeJobPosting($this);
    $placeholderCourse = $this->course;
    $this->course = null;
    $placeholderCourse->removeJobPosting($this);
    $copyOfApplications = $this->applications;
    $this->applications = array();
    foreach ($copyOfApplications as $aApplication)
    {
      if ($aApplication->numberOfJobPostings() <= Application::minimumNumberOfJobPostings())
      {
        $aApplication->delete();
      }
      else
      {
        $aApplication->removeJobPosting($this);
      }
    }
  }

}
?>