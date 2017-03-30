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
  private $instructors;
  private $applicants;
  private $jobPostings;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct()
  {
    $this->courses = array();
    $this->instructors = array();
    $this->applicants = array();
    $this->jobPostings = array();
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

  public function getInstructor_index($index)
  {
    $aInstructor = $this->instructors[$index];
    return $aInstructor;
  }

  public function getInstructors()
  {
    $newInstructors = $this->instructors;
    return $newInstructors;
  }

  public function numberOfInstructors()
  {
    $number = count($this->instructors);
    return $number;
  }

  public function hasInstructors()
  {
    $has = $this->numberOfInstructors() > 0;
    return $has;
  }

  public function indexOfInstructor($aInstructor)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->instructors as $instructor)
    {
      if ($instructor->equals($aInstructor))
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

  public static function minimumNumberOfCourses()
  {
    return 0;
  }

  public function addCourseVia($aSemester, $aCourseName, $aCourseCode, $aNumTutorial, $aNumLab, $aNumStudent, $aCredit, $aNumTaNeeded, $aNumGraderNeeded, $aHourRequiredTa, $aHourRequiredGrader, $aBudgetCalculated, $aInstructor)
  {
    return new Course($aSemester, $aCourseName, $aCourseCode, $aNumTutorial, $aNumLab, $aNumStudent, $aCredit, $aNumTaNeeded, $aNumGraderNeeded, $aHourRequiredTa, $aHourRequiredGrader, $aBudgetCalculated, $aInstructor, $this);
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

  public static function minimumNumberOfInstructors()
  {
    return 0;
  }

  public function addInstructorVia($aName)
  {
    return new Instructor($aName, $this);
  }

  public function addInstructor($aInstructor)
  {
    $wasAdded = false;
    if ($this->indexOfInstructor($aInstructor) !== -1) { return false; }
    $existingManagementSystem = $aInstructor->getManagementSystem();
    $isNewManagementSystem = $existingManagementSystem != null && $this !== $existingManagementSystem;
    if ($isNewManagementSystem)
    {
      $aInstructor->setManagementSystem($this);
    }
    else
    {
      $this->instructors[] = $aInstructor;
    }
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeInstructor($aInstructor)
  {
    $wasRemoved = false;
    //Unable to remove aInstructor, as it must always have a managementSystem
    if ($this !== $aInstructor->getManagementSystem())
    {
      unset($this->instructors[$this->indexOfInstructor($aInstructor)]);
      $this->instructors = array_values($this->instructors);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addInstructorAt($aInstructor, $index)
  {  
    $wasAdded = false;
    if($this->addInstructor($aInstructor))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfInstructors()) { $index = $this->numberOfInstructors() - 1; }
      array_splice($this->instructors, $this->indexOfInstructor($aInstructor), 1);
      array_splice($this->instructors, $index, 0, array($aInstructor));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveInstructorAt($aInstructor, $index)
  {
    $wasAdded = false;
    if($this->indexOfInstructor($aInstructor) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfInstructors()) { $index = $this->numberOfInstructors() - 1; }
      array_splice($this->instructors, $this->indexOfInstructor($aInstructor), 1);
      array_splice($this->instructors, $index, 0, array($aInstructor));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addInstructorAt($aInstructor, $index);
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

  public function addApplicantVia($aStudentID, $aName, $aPreviousExperience, $aIsUnderGraduated, $aMajor, $aYear, $aFirstChoice, $aSecondChoice, $aThirdChoice, $aEvaluation, $aTotalAppointmentHours)
  {
    return new Applicant($aStudentID, $aName, $aPreviousExperience, $aIsUnderGraduated, $aMajor, $aYear, $aFirstChoice, $aSecondChoice, $aThirdChoice, $aEvaluation, $aTotalAppointmentHours, $this);
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

  public static function minimumNumberOfJobPostings()
  {
    return 0;
  }

  public function addJobPostingVia($aJobTitle, $aSubmissionDeadline, $aPerferredExperience, $aHourRate, $aCourse)
  {
    return new JobPosting($aJobTitle, $aSubmissionDeadline, $aPerferredExperience, $aHourRate, $this, $aCourse);
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
      $this->jobPostings[] = $aJobPosting;
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
      unset($this->jobPostings[$this->indexOfJobPosting($aJobPosting)]);
      $this->jobPostings = array_values($this->jobPostings);
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
    
    while (count($this->instructors) > 0)
    {
      $aInstructor = $this->instructors[count($this->instructors) - 1];
      $aInstructor->delete();
      unset($this->instructors[$this->indexOfInstructor($aInstructor)]);
      $this->instructors = array_values($this->instructors);
    }
    
    while (count($this->applicants) > 0)
    {
      $aApplicant = $this->applicants[count($this->applicants) - 1];
      $aApplicant->delete();
      unset($this->applicants[$this->indexOfApplicant($aApplicant)]);
      $this->applicants = array_values($this->applicants);
    }
    
    while (count($this->jobPostings) > 0)
    {
      $aJobPosting = $this->jobPostings[count($this->jobPostings) - 1];
      $aJobPosting->delete();
      unset($this->jobPostings[$this->indexOfJobPosting($aJobPosting)]);
      $this->jobPostings = array_values($this->jobPostings);
    }
    
  }

}
?>