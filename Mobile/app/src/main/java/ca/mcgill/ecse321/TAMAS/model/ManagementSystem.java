/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse321.TAMAS.model;
import java.util.*;
import java.sql.Date;

// line 5 "../../../../../TAMAS.ump"
public class ManagementSystem
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ManagementSystem Associations
  private List<Course> courses;
  private List<Instructor> instructors;
  private List<Applicant> applicants;
  private List<JobPosting> jobPostings;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ManagementSystem()
  {
    courses = new ArrayList<Course>();
    instructors = new ArrayList<Instructor>();
    applicants = new ArrayList<Applicant>();
    jobPostings = new ArrayList<JobPosting>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public Course getCourse(int index)
  {
    Course aCourse = courses.get(index);
    return aCourse;
  }

  public List<Course> getCourses()
  {
    List<Course> newCourses = Collections.unmodifiableList(courses);
    return newCourses;
  }

  public int numberOfCourses()
  {
    int number = courses.size();
    return number;
  }

  public boolean hasCourses()
  {
    boolean has = courses.size() > 0;
    return has;
  }

  public int indexOfCourse(Course aCourse)
  {
    int index = courses.indexOf(aCourse);
    return index;
  }

  public Instructor getInstructor(int index)
  {
    Instructor aInstructor = instructors.get(index);
    return aInstructor;
  }

  public List<Instructor> getInstructors()
  {
    List<Instructor> newInstructors = Collections.unmodifiableList(instructors);
    return newInstructors;
  }

  public int numberOfInstructors()
  {
    int number = instructors.size();
    return number;
  }

  public boolean hasInstructors()
  {
    boolean has = instructors.size() > 0;
    return has;
  }

  public int indexOfInstructor(Instructor aInstructor)
  {
    int index = instructors.indexOf(aInstructor);
    return index;
  }

  public Applicant getApplicant(int index)
  {
    Applicant aApplicant = applicants.get(index);
    return aApplicant;
  }

  public List<Applicant> getApplicants()
  {
    List<Applicant> newApplicants = Collections.unmodifiableList(applicants);
    return newApplicants;
  }

  public int numberOfApplicants()
  {
    int number = applicants.size();
    return number;
  }

  public boolean hasApplicants()
  {
    boolean has = applicants.size() > 0;
    return has;
  }

  public int indexOfApplicant(Applicant aApplicant)
  {
    int index = applicants.indexOf(aApplicant);
    return index;
  }

  public JobPosting getJobPosting(int index)
  {
    JobPosting aJobPosting = jobPostings.get(index);
    return aJobPosting;
  }

  public List<JobPosting> getJobPostings()
  {
    List<JobPosting> newJobPostings = Collections.unmodifiableList(jobPostings);
    return newJobPostings;
  }

  public int numberOfJobPostings()
  {
    int number = jobPostings.size();
    return number;
  }

  public boolean hasJobPostings()
  {
    boolean has = jobPostings.size() > 0;
    return has;
  }

  public int indexOfJobPosting(JobPosting aJobPosting)
  {
    int index = jobPostings.indexOf(aJobPosting);
    return index;
  }

  public static int minimumNumberOfCourses()
  {
    return 0;
  }

  public Course addCourse(String aSemester, String aCourseName, String aCourseCode, int aNumTutorial, int aNumLab, int aNumStudent, int aCredit, int aNumTaNeeded, int aNumGraderNeeded, int aLabHour, int aTutorialHour, int aTotalGraderHour, double aBudgetCalculated, Instructor aInstructor)
  {
    return new Course(aSemester, aCourseName, aCourseCode, aNumTutorial, aNumLab, aNumStudent, aCredit, aNumTaNeeded, aNumGraderNeeded, aLabHour, aTutorialHour, aTotalGraderHour, aBudgetCalculated, aInstructor, this);
  }

  public boolean addCourse(Course aCourse)
  {
    boolean wasAdded = false;
    if (courses.contains(aCourse)) { return false; }
    ManagementSystem existingManagementSystem = aCourse.getManagementSystem();
    boolean isNewManagementSystem = existingManagementSystem != null && !this.equals(existingManagementSystem);
    if (isNewManagementSystem)
    {
      aCourse.setManagementSystem(this);
    }
    else
    {
      courses.add(aCourse);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeCourse(Course aCourse)
  {
    boolean wasRemoved = false;
    //Unable to remove aCourse, as it must always have a managementSystem
    if (!this.equals(aCourse.getManagementSystem()))
    {
      courses.remove(aCourse);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addCourseAt(Course aCourse, int index)
  {  
    boolean wasAdded = false;
    if(addCourse(aCourse))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCourses()) { index = numberOfCourses() - 1; }
      courses.remove(aCourse);
      courses.add(index, aCourse);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveCourseAt(Course aCourse, int index)
  {
    boolean wasAdded = false;
    if(courses.contains(aCourse))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCourses()) { index = numberOfCourses() - 1; }
      courses.remove(aCourse);
      courses.add(index, aCourse);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addCourseAt(aCourse, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfInstructors()
  {
    return 0;
  }

  public Instructor addInstructor(String aName)
  {
    return new Instructor(aName, this);
  }

  public boolean addInstructor(Instructor aInstructor)
  {
    boolean wasAdded = false;
    if (instructors.contains(aInstructor)) { return false; }
    ManagementSystem existingManagementSystem = aInstructor.getManagementSystem();
    boolean isNewManagementSystem = existingManagementSystem != null && !this.equals(existingManagementSystem);
    if (isNewManagementSystem)
    {
      aInstructor.setManagementSystem(this);
    }
    else
    {
      instructors.add(aInstructor);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeInstructor(Instructor aInstructor)
  {
    boolean wasRemoved = false;
    //Unable to remove aInstructor, as it must always have a managementSystem
    if (!this.equals(aInstructor.getManagementSystem()))
    {
      instructors.remove(aInstructor);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addInstructorAt(Instructor aInstructor, int index)
  {  
    boolean wasAdded = false;
    if(addInstructor(aInstructor))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfInstructors()) { index = numberOfInstructors() - 1; }
      instructors.remove(aInstructor);
      instructors.add(index, aInstructor);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveInstructorAt(Instructor aInstructor, int index)
  {
    boolean wasAdded = false;
    if(instructors.contains(aInstructor))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfInstructors()) { index = numberOfInstructors() - 1; }
      instructors.remove(aInstructor);
      instructors.add(index, aInstructor);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addInstructorAt(aInstructor, index);
    }
    return wasAdded;
  }

  public boolean isNumberOfApplicantsValid()
  {
    boolean isValid = numberOfApplicants() >= minimumNumberOfApplicants();
    return isValid;
  }

  public static int minimumNumberOfApplicants()
  {
    return 1;
  }

  public Applicant addApplicant(int aStudentID, String aName, String aPreviousExperience, boolean aIsUnderGraduated, String aMajor, String aYear, String aFirstChoice, String aSecondChoice, String aThirdChoice, String aEvaluation, int aTotalAppointmentHours)
  {
    Applicant aNewApplicant = new Applicant(aStudentID, aName, aPreviousExperience, aIsUnderGraduated, aMajor, aYear, aFirstChoice, aSecondChoice, aThirdChoice, aEvaluation, aTotalAppointmentHours, this);
    return aNewApplicant;
  }

  public boolean addApplicant(Applicant aApplicant)
  {
    boolean wasAdded = false;
    if (applicants.contains(aApplicant)) { return false; }
    ManagementSystem existingManagementSystem = aApplicant.getManagementSystem();
    boolean isNewManagementSystem = existingManagementSystem != null && !this.equals(existingManagementSystem);

    if (isNewManagementSystem && existingManagementSystem.numberOfApplicants() <= minimumNumberOfApplicants())
    {
      return wasAdded;
    }
    if (isNewManagementSystem)
    {
      aApplicant.setManagementSystem(this);
    }
    else
    {
      applicants.add(aApplicant);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeApplicant(Applicant aApplicant)
  {
    boolean wasRemoved = false;
    //Unable to remove aApplicant, as it must always have a managementSystem
    if (this.equals(aApplicant.getManagementSystem()))
    {
      return wasRemoved;
    }

    //managementSystem already at minimum (1)
    if (numberOfApplicants() <= minimumNumberOfApplicants())
    {
      return wasRemoved;
    }

    applicants.remove(aApplicant);
    wasRemoved = true;
    return wasRemoved;
  }

  public boolean addApplicantAt(Applicant aApplicant, int index)
  {  
    boolean wasAdded = false;
    if(addApplicant(aApplicant))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfApplicants()) { index = numberOfApplicants() - 1; }
      applicants.remove(aApplicant);
      applicants.add(index, aApplicant);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveApplicantAt(Applicant aApplicant, int index)
  {
    boolean wasAdded = false;
    if(applicants.contains(aApplicant))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfApplicants()) { index = numberOfApplicants() - 1; }
      applicants.remove(aApplicant);
      applicants.add(index, aApplicant);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addApplicantAt(aApplicant, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfJobPostings()
  {
    return 0;
  }

  public JobPosting addJobPosting(String aJobTitle, Date aSubmissionDeadline, String aPerferredExperience, double aHourRate, Course aCourse)
  {
    return new JobPosting(aJobTitle, aSubmissionDeadline, aPerferredExperience, aHourRate, this, aCourse);
  }

  public boolean addJobPosting(JobPosting aJobPosting)
  {
    boolean wasAdded = false;
    if (jobPostings.contains(aJobPosting)) { return false; }
    ManagementSystem existingManagementSystem = aJobPosting.getManagementSystem();
    boolean isNewManagementSystem = existingManagementSystem != null && !this.equals(existingManagementSystem);
    if (isNewManagementSystem)
    {
      aJobPosting.setManagementSystem(this);
    }
    else
    {
      jobPostings.add(aJobPosting);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeJobPosting(JobPosting aJobPosting)
  {
    boolean wasRemoved = false;
    //Unable to remove aJobPosting, as it must always have a managementSystem
    if (!this.equals(aJobPosting.getManagementSystem()))
    {
      jobPostings.remove(aJobPosting);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addJobPostingAt(JobPosting aJobPosting, int index)
  {  
    boolean wasAdded = false;
    if(addJobPosting(aJobPosting))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfJobPostings()) { index = numberOfJobPostings() - 1; }
      jobPostings.remove(aJobPosting);
      jobPostings.add(index, aJobPosting);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveJobPostingAt(JobPosting aJobPosting, int index)
  {
    boolean wasAdded = false;
    if(jobPostings.contains(aJobPosting))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfJobPostings()) { index = numberOfJobPostings() - 1; }
      jobPostings.remove(aJobPosting);
      jobPostings.add(index, aJobPosting);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addJobPostingAt(aJobPosting, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    while (courses.size() > 0)
    {
      Course aCourse = courses.get(courses.size() - 1);
      aCourse.delete();
      courses.remove(aCourse);
    }
    
    while (instructors.size() > 0)
    {
      Instructor aInstructor = instructors.get(instructors.size() - 1);
      aInstructor.delete();
      instructors.remove(aInstructor);
    }
    
    while (applicants.size() > 0)
    {
      Applicant aApplicant = applicants.get(applicants.size() - 1);
      aApplicant.delete();
      applicants.remove(aApplicant);
    }
    
    while (jobPostings.size() > 0)
    {
      JobPosting aJobPosting = jobPostings.get(jobPostings.size() - 1);
      aJobPosting.delete();
      jobPostings.remove(aJobPosting);
    }
    
  }

}