<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

class Course
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Course Attributes
  private $semester;
  private $courseCoude;
  private $numTutorial;
  private $numLab;
  private $numStudent;
  private $credit;
  private $hourRequiredTa;
  private $hourRequiredGrader;
  private $budgetCalculated;

  //Course Associations
  private $courseComponents;
  private $courseJobAllocation;
  private $jobPosting;
  private $instructor;
  private $offeredJobs;
  private $managementSystem;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aSemester = null, $aCourseCoude = null, $aNumTutorial = null, $aNumLab = null, $aNumStudent = null, $aCredit = null, $aHourRequiredTa = null, $aHourRequiredGrader = null, $aBudgetCalculated = null, $aCourseJobAllocation = null, $aInstructor = null, $aManagementSystem = null)
  {
    if (func_num_args() == 0) { return; }

    $this->semester = $aSemester;
    $this->courseCoude = $aCourseCoude;
    $this->numTutorial = $aNumTutorial;
    $this->numLab = $aNumLab;
    $this->numStudent = $aNumStudent;
    $this->credit = $aCredit;
    $this->hourRequiredTa = $aHourRequiredTa;
    $this->hourRequiredGrader = $aHourRequiredGrader;
    $this->budgetCalculated = $aBudgetCalculated;
    $this->courseComponents = array();
    if ($aCourseJobAllocation == null || $aCourseJobAllocation->getCourse() != null)
    {
      throw new Exception("Unable to create Course due to aCourseJobAllocation");
    }
    $this->courseJobAllocation = $aCourseJobAllocation;
    $this->jobPosting = array();
    $didAddInstructor = $this->setInstructor($aInstructor);
    if (!$didAddInstructor)
    {
      throw new Exception("Unable to create course due to instructor");
    }
    $this->offeredJobs = array();
    $didAddManagementSystem = $this->setManagementSystem($aManagementSystem);
    if (!$didAddManagementSystem)
    {
      throw new Exception("Unable to create course due to managementSystem");
    }
  }
  public static function newInstance($aSemester, $aCourseCoude, $aNumTutorial, $aNumLab, $aNumStudent, $aCredit, $aHourRequiredTa, $aHourRequiredGrader, $aBudgetCalculated, $aInstructor, $aManagementSystem)
  {
    $thisInstance = new Course();
    $thisInstance->semester = $aSemester;
    $thisInstance->courseCoude = $aCourseCoude;
    $thisInstance->numTutorial = $aNumTutorial;
    $thisInstance->numLab = $aNumLab;
    $thisInstance->numStudent = $aNumStudent;
    $thisInstance->credit = $aCredit;
    $thisInstance->hourRequiredTa = $aHourRequiredTa;
    $thisInstance->hourRequiredGrader = $aHourRequiredGrader;
    $thisInstance->budgetCalculated = $aBudgetCalculated;
    $this->courseComponents = array();
    $thisInstance->courseJobAllocation = new Allocation($thisInstance);
    $this->jobPosting = array();$this->instructors = array();
    $this->instructors[] = $aInstructor;
    $this->offeredJobs = array();$this->managementSystems = array();
    $this->managementSystems[] = $aManagementSystem;
    return $thisInstance;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function setSemester($aSemester)
  {
    $wasSet = false;
    $this->semester = $aSemester;
    $wasSet = true;
    return $wasSet;
  }

  public function setCourseCoude($aCourseCoude)
  {
    $wasSet = false;
    $this->courseCoude = $aCourseCoude;
    $wasSet = true;
    return $wasSet;
  }

  public function setNumTutorial($aNumTutorial)
  {
    $wasSet = false;
    $this->numTutorial = $aNumTutorial;
    $wasSet = true;
    return $wasSet;
  }

  public function setNumLab($aNumLab)
  {
    $wasSet = false;
    $this->numLab = $aNumLab;
    $wasSet = true;
    return $wasSet;
  }

  public function setNumStudent($aNumStudent)
  {
    $wasSet = false;
    $this->numStudent = $aNumStudent;
    $wasSet = true;
    return $wasSet;
  }

  public function setCredit($aCredit)
  {
    $wasSet = false;
    $this->credit = $aCredit;
    $wasSet = true;
    return $wasSet;
  }

  public function setHourRequiredTa($aHourRequiredTa)
  {
    $wasSet = false;
    $this->hourRequiredTa = $aHourRequiredTa;
    $wasSet = true;
    return $wasSet;
  }

  public function setHourRequiredGrader($aHourRequiredGrader)
  {
    $wasSet = false;
    $this->hourRequiredGrader = $aHourRequiredGrader;
    $wasSet = true;
    return $wasSet;
  }

  public function setBudgetCalculated($aBudgetCalculated)
  {
    $wasSet = false;
    $this->budgetCalculated = $aBudgetCalculated;
    $wasSet = true;
    return $wasSet;
  }

  public function getSemester()
  {
    return $this->semester;
  }

  public function getCourseCoude()
  {
    return $this->courseCoude;
  }

  public function getNumTutorial()
  {
    return $this->numTutorial;
  }

  public function getNumLab()
  {
    return $this->numLab;
  }

  public function getNumStudent()
  {
    return $this->numStudent;
  }

  public function getCredit()
  {
    return $this->credit;
  }

  public function getHourRequiredTa()
  {
    return $this->hourRequiredTa;
  }

  public function getHourRequiredGrader()
  {
    return $this->hourRequiredGrader;
  }

  public function getBudgetCalculated()
  {
    return $this->budgetCalculated;
  }

  public function getCourseComponent_index($index)
  {
    $aCourseComponent = $this->courseComponents[$index];
    return $aCourseComponent;
  }

  public function getCourseComponents()
  {
    $newCourseComponents = $this->courseComponents;
    return $newCourseComponents;
  }

  public function numberOfCourseComponents()
  {
    $number = count($this->courseComponents);
    return $number;
  }

  public function hasCourseComponents()
  {
    $has = $this->numberOfCourseComponents() > 0;
    return $has;
  }

  public function indexOfCourseComponent($aCourseComponent)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->courseComponents as $courseComponent)
    {
      if ($courseComponent->equals($aCourseComponent))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public function getCourseJobAllocation()
  {
    return $this->courseJobAllocation;
  }

  public function getJobPosting_index($index)
  {
    $aJobPosting = $this->jobPosting[$index];
    return $aJobPosting;
  }

  public function getJobPosting()
  {
    $newJobPosting = $this->jobPosting;
    return $newJobPosting;
  }

  public function numberOfJobPosting()
  {
    $number = count($this->jobPosting);
    return $number;
  }

  public function hasJobPosting()
  {
    $has = $this->numberOfJobPosting() > 0;
    return $has;
  }

  public function indexOfJobPosting($aJobPosting)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->jobPosting as $jobPosting)
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

  public function getInstructor()
  {
    return $this->instructor;
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

  public static function minimumNumberOfCourseComponents()
  {
    return 0;
  }

  public function addCourseComponentVia($aHour, $aSessionCode)
  {
    return new CourseComponent($aHour, $aSessionCode, $this);
  }

  public function addCourseComponent($aCourseComponent)
  {
    $wasAdded = false;
    if ($this->indexOfCourseComponent($aCourseComponent) !== -1) { return false; }
    $existingCourse = $aCourseComponent->getCourse();
    $isNewCourse = $existingCourse != null && $this !== $existingCourse;
    if ($isNewCourse)
    {
      $aCourseComponent->setCourse($this);
    }
    else
    {
      $this->courseComponents[] = $aCourseComponent;
    }
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeCourseComponent($aCourseComponent)
  {
    $wasRemoved = false;
    //Unable to remove aCourseComponent, as it must always have a course
    if ($this !== $aCourseComponent->getCourse())
    {
      unset($this->courseComponents[$this->indexOfCourseComponent($aCourseComponent)]);
      $this->courseComponents = array_values($this->courseComponents);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addCourseComponentAt($aCourseComponent, $index)
  {  
    $wasAdded = false;
    if($this->addCourseComponent($aCourseComponent))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfCourseComponents()) { $index = $this->numberOfCourseComponents() - 1; }
      array_splice($this->courseComponents, $this->indexOfCourseComponent($aCourseComponent), 1);
      array_splice($this->courseComponents, $index, 0, array($aCourseComponent));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveCourseComponentAt($aCourseComponent, $index)
  {
    $wasAdded = false;
    if($this->indexOfCourseComponent($aCourseComponent) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfCourseComponents()) { $index = $this->numberOfCourseComponents() - 1; }
      array_splice($this->courseComponents, $this->indexOfCourseComponent($aCourseComponent), 1);
      array_splice($this->courseComponents, $index, 0, array($aCourseComponent));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addCourseComponentAt($aCourseComponent, $index);
    }
    return $wasAdded;
  }

  public static function minimumNumberOfJobPosting()
  {
    return 0;
  }

  public function addJobPostingVia($aJobTitle, $aSubmissionDeadline, $aPerferredExperience, $aNumNeeded, $aHourRate, $aManagementSystem)
  {
    return new JobPosting($aJobTitle, $aSubmissionDeadline, $aPerferredExperience, $aNumNeeded, $aHourRate, $aManagementSystem, $this);
  }

  public function addJobPosting($aJobPosting)
  {
    $wasAdded = false;
    if ($this->indexOfJobPosting($aJobPosting) !== -1) { return false; }
    $existingCourse = $aJobPosting->getCourse();
    $isNewCourse = $existingCourse != null && $this !== $existingCourse;
    if ($isNewCourse)
    {
      $aJobPosting->setCourse($this);
    }
    else
    {
      $this->jobPosting[] = $aJobPosting;
    }
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeJobPosting($aJobPosting)
  {
    $wasRemoved = false;
    //Unable to remove aJobPosting, as it must always have a course
    if ($this !== $aJobPosting->getCourse())
    {
      unset($this->jobPosting[$this->indexOfJobPosting($aJobPosting)]);
      $this->jobPosting = array_values($this->jobPosting);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addJobPostingAt($aJobPosting, $index)
  {  
    $wasAdded = false;
    if($this->addJobPosting($aJobPosting))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfJobPosting()) { $index = $this->numberOfJobPosting() - 1; }
      array_splice($this->jobPosting, $this->indexOfJobPosting($aJobPosting), 1);
      array_splice($this->jobPosting, $index, 0, array($aJobPosting));
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
      if($index > $this->numberOfJobPosting()) { $index = $this->numberOfJobPosting() - 1; }
      array_splice($this->jobPosting, $this->indexOfJobPosting($aJobPosting), 1);
      array_splice($this->jobPosting, $index, 0, array($aJobPosting));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addJobPostingAt($aJobPosting, $index);
    }
    return $wasAdded;
  }

  public function setInstructor($aInstructor)
  {
    $wasSet = false;
    if ($aInstructor == null)
    {
      return $wasSet;
    }
    
    $existingInstructor = $this->instructor;
    $this->instructor = $aInstructor;
    if ($existingInstructor != null && $existingInstructor != $aInstructor)
    {
      $existingInstructor->removeCourse($this);
    }
    $this->instructor->addCourse($this);
    $wasSet = true;
    return $wasSet;
  }

  public static function minimumNumberOfOfferedJobs()
  {
    return 0;
  }

  public function addOfferedJobVia($aOfferDescription, $aApplicant)
  {
    return new OfferedJob($aOfferDescription, $this, $aApplicant);
  }

  public function addOfferedJob($aOfferedJob)
  {
    $wasAdded = false;
    if ($this->indexOfOfferedJob($aOfferedJob) !== -1) { return false; }
    $existingCourse = $aOfferedJob->getCourse();
    $isNewCourse = $existingCourse != null && $this !== $existingCourse;
    if ($isNewCourse)
    {
      $aOfferedJob->setCourse($this);
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
    //Unable to remove aOfferedJob, as it must always have a course
    if ($this !== $aOfferedJob->getCourse())
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
    if ($aManagementSystem == null)
    {
      return $wasSet;
    }
    
    $existingManagementSystem = $this->managementSystem;
    $this->managementSystem = $aManagementSystem;
    if ($existingManagementSystem != null && $existingManagementSystem != $aManagementSystem)
    {
      $existingManagementSystem->removeCourse($this);
    }
    $this->managementSystem->addCourse($this);
    $wasSet = true;
    return $wasSet;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    while (count($this->courseComponents) > 0)
    {
      $aCourseComponent = $this->courseComponents[count($this->courseComponents) - 1];
      $aCourseComponent->delete();
      unset($this->courseComponents[$this->indexOfCourseComponent($aCourseComponent)]);
      $this->courseComponents = array_values($this->courseComponents);
    }
    
    $existingCourseJobAllocation = $this->courseJobAllocation;
    $this->courseJobAllocation = null;
    if ($existingCourseJobAllocation != null)
    {
      $existingCourseJobAllocation->delete();
    }
    while (count($this->jobPosting) > 0)
    {
      $aJobPosting = $this->jobPosting[count($this->jobPosting) - 1];
      $aJobPosting->delete();
      unset($this->jobPosting[$this->indexOfJobPosting($aJobPosting)]);
      $this->jobPosting = array_values($this->jobPosting);
    }
    
    $placeholderInstructor = $this->instructor;
    $this->instructor = null;
    $placeholderInstructor->removeCourse($this);
    while (count($this->offeredJobs) > 0)
    {
      $aOfferedJob = $this->offeredJobs[count($this->offeredJobs) - 1];
      $aOfferedJob->delete();
      unset($this->offeredJobs[$this->indexOfOfferedJob($aOfferedJob)]);
      $this->offeredJobs = array_values($this->offeredJobs);
    }
    
    $placeholderManagementSystem = $this->managementSystem;
    $this->managementSystem = null;
    $placeholderManagementSystem->removeCourse($this);
  }

}
?>