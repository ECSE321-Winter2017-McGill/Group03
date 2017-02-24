<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

class ManagementSystem
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ManagementSystem Associations
  private $courses;
  private $applicants;
  private $jobPosting;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct()
  {
    $this->courses = array();
    $this->applicants = array();
    $this->jobPosting = array();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function getCourse_index($index)
  {
    $aCourse = $this->courses[$index];
    return $aCourse;
  }

  public function getCourses()
  {
    $newCourses = $this->courses;
    return $newCourses;
  }

  public function numberOfCourses()
  {
    $number = count($this->courses);
    return $number;
  }

  public function hasCourses()
  {
    $has = $this->numberOfCourses() > 0;
    return $has;
  }

  public function indexOfCourse($aCourse)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->courses as $course)
    {
      if ($course->equals($aCourse))
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

  public static function minimumNumberOfCourses()
  {
    return 0;
  }

  public function addCourseVia($aSemester, $aCourseCoude, $aNumTutorial, $aNumLab, $aNumStudent, $aCredit, $aHourRequiredTa, $aHourRequiredGrader, $aBudgetCalculated, $aCourseJobAllocation)
  {
    return new Course($aSemester, $aCourseCoude, $aNumTutorial, $aNumLab, $aNumStudent, $aCredit, $aHourRequiredTa, $aHourRequiredGrader, $aBudgetCalculated, $aCourseJobAllocation, $this);
  }

  public function addCourse($aCourse)
  {
    $wasAdded = false;
    if ($this->indexOfCourse($aCourse) !== -1) { return false; }
    $existingManagementSystem = $aCourse->getManagementSystem();
    $isNewManagementSystem = $existingManagementSystem != null && $this !== $existingManagementSystem;
    if ($isNewManagementSystem)
    {
      $aCourse->setManagementSystem($this);
    }
    else
    {
      $this->courses[] = $aCourse;
    }
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeCourse($aCourse)
  {
    $wasRemoved = false;
    //Unable to remove aCourse, as it must always have a managementSystem
    if ($this !== $aCourse->getManagementSystem())
    {
      unset($this->courses[$this->indexOfCourse($aCourse)]);
      $this->courses = array_values($this->courses);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addCourseAt($aCourse, $index)
  {  
    $wasAdded = false;
    if($this->addCourse($aCourse))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfCourses()) { $index = $this->numberOfCourses() - 1; }
      array_splice($this->courses, $this->indexOfCourse($aCourse), 1);
      array_splice($this->courses, $index, 0, array($aCourse));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveCourseAt($aCourse, $index)
  {
    $wasAdded = false;
    if($this->indexOfCourse($aCourse) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfCourses()) { $index = $this->numberOfCourses() - 1; }
      array_splice($this->courses, $this->indexOfCourse($aCourse), 1);
      array_splice($this->courses, $index, 0, array($aCourse));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addCourseAt($aCourse, $index);
    }
    return $wasAdded;
  }

  public function isNumberOfApplicantsValid()
  {
    $isValid = $this->numberOfApplicants() >= self::minimumNumberOfApplicants();
    return $isValid;
  }

  public static function minimumNumberOfApplicants()
  {
    return 1;
  }

  public function addApplicantVia($aStudentID, $aName, $aPreviousExperience, $aIsUnderGraduated, $aPreferredCourse, $aMajor, $aYear, $aOption1, $aOption2, $aOption3, $aAllocation)
  {
    return new Applicant($aStudentID, $aName, $aPreviousExperience, $aIsUnderGraduated, $aPreferredCourse, $aMajor, $aYear, $aOption1, $aOption2, $aOption3, $aAllocation, $this);
  }

  public function addApplicant($aApplicant)
  {
    $wasAdded = false;
    if ($this->indexOfApplicant($aApplicant) !== -1) { return false; }
    $existingManagementSystem = $aApplicant->getManagementSystem();
    $isNewManagementSystem = $existingManagementSystem != null && $this !== $existingManagementSystem;

    if ($isNewManagementSystem && $existingManagementSystem->numberOfApplicants() <= self::minimumNumberOfApplicants())
    {
      return $wasAdded;
    }

    if ($isNewManagementSystem)
    {
      $aApplicant->setManagementSystem($this);
    }
    else
    {
      $this->applicants[] = $aApplicant;
    }
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeApplicant($aApplicant)
  {
    $wasRemoved = false;
    //Unable to remove aApplicant, as it must always have a managementSystem
    if ($this === $aApplicant->getManagementSystem())
    {
      return $wasRemoved;
    }

    //managementSystem already at minimum (1)
    if ($this->numberOfApplicants() <= self::minimumNumberOfApplicants())
    {
      return $wasRemoved;
    }

    unset($this->applicants[$this->indexOfApplicant($aApplicant)]);
    $this->applicants = array_values($this->applicants);
    $wasRemoved = true;
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

  public static function minimumNumberOfJobPosting()
  {
    return 0;
  }

  public function addJobPostingVia($aJobTitle, $aSubmissionDeadline, $aPerferredExperience, $aNumNeeded, $aHourRate, $aCourse)
  {
    return new JobPosting($aJobTitle, $aSubmissionDeadline, $aPerferredExperience, $aNumNeeded, $aHourRate, $this, $aCourse);
  }

  public function addJobPosting($aJobPosting)
  {
    $wasAdded = false;
    if ($this->indexOfJobPosting($aJobPosting) !== -1) { return false; }
    $existingManagementSystem = $aJobPosting->getManagementSystem();
    $isNewManagementSystem = $existingManagementSystem != null && $this !== $existingManagementSystem;
    if ($isNewManagementSystem)
    {
      $aJobPosting->setManagementSystem($this);
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
    //Unable to remove aJobPosting, as it must always have a managementSystem
    if ($this !== $aJobPosting->getManagementSystem())
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

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    while (count($this->courses) > 0)
    {
      $aCourse = $this->courses[count($this->courses) - 1];
      $aCourse->delete();
      unset($this->courses[$this->indexOfCourse($aCourse)]);
      $this->courses = array_values($this->courses);
    }
    
    while (count($this->applicants) > 0)
    {
      $aApplicant = $this->applicants[count($this->applicants) - 1];
      $aApplicant->delete();
      unset($this->applicants[$this->indexOfApplicant($aApplicant)]);
      $this->applicants = array_values($this->applicants);
    }
    
    while (count($this->jobPosting) > 0)
    {
      $aJobPosting = $this->jobPosting[count($this->jobPosting) - 1];
      $aJobPosting->delete();
      unset($this->jobPosting[$this->indexOfJobPosting($aJobPosting)]);
      $this->jobPosting = array_values($this->jobPosting);
    }
    
  }

}
?>