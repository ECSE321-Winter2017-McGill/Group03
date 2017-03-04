/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse321.TAMAS.model;
import java.util.*;
import java.sql.Date;

// line 21 "../../../../TAMAS.ump"
public class Course
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Course Attributes
  private String semester;
  private String courseCoude;
  private int numTutorial;
  private int numLab;
  private int numStudent;
  private int credit;
  private int hourRequiredTa;
  private int hourRequiredGrader;
  private double budgetCalculated;

  //Course Associations
  private List<CourseComponent> courseComponents;
  private Allocation courseJobAllocation;
  private List<JobPosting> jobPosting;
  private Instructor instructor;
  private List<OfferedJob> offeredJobs;
  private ManagementSystem managementSystem;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Course(String aSemester, String aCourseCoude, int aNumTutorial, int aNumLab, int aNumStudent, int aCredit, int aHourRequiredTa, int aHourRequiredGrader, double aBudgetCalculated, Instructor aInstructor, ManagementSystem aManagementSystem)
  {
    semester = aSemester;
    courseCoude = aCourseCoude;
    numTutorial = aNumTutorial;
    numLab = aNumLab;
    numStudent = aNumStudent;
    credit = aCredit;
    hourRequiredTa = aHourRequiredTa;
    hourRequiredGrader = aHourRequiredGrader;
    budgetCalculated = aBudgetCalculated;
    courseComponents = new ArrayList<CourseComponent>();
    jobPosting = new ArrayList<JobPosting>();
    boolean didAddInstructor = setInstructor(aInstructor);
    if (!didAddInstructor)
    {
      throw new RuntimeException("Unable to create course due to instructor");
    }
    offeredJobs = new ArrayList<OfferedJob>();
    boolean didAddManagementSystem = setManagementSystem(aManagementSystem);
    if (!didAddManagementSystem)
    {
      throw new RuntimeException("Unable to create course due to managementSystem");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setSemester(String aSemester)
  {
    boolean wasSet = false;
    semester = aSemester;
    wasSet = true;
    return wasSet;
  }

  public boolean setCourseCoude(String aCourseCoude)
  {
    boolean wasSet = false;
    courseCoude = aCourseCoude;
    wasSet = true;
    return wasSet;
  }

  public boolean setNumTutorial(int aNumTutorial)
  {
    boolean wasSet = false;
    numTutorial = aNumTutorial;
    wasSet = true;
    return wasSet;
  }

  public boolean setNumLab(int aNumLab)
  {
    boolean wasSet = false;
    numLab = aNumLab;
    wasSet = true;
    return wasSet;
  }

  public boolean setNumStudent(int aNumStudent)
  {
    boolean wasSet = false;
    numStudent = aNumStudent;
    wasSet = true;
    return wasSet;
  }

  public boolean setCredit(int aCredit)
  {
    boolean wasSet = false;
    credit = aCredit;
    wasSet = true;
    return wasSet;
  }

  public boolean setHourRequiredTa(int aHourRequiredTa)
  {
    boolean wasSet = false;
    hourRequiredTa = aHourRequiredTa;
    wasSet = true;
    return wasSet;
  }

  public boolean setHourRequiredGrader(int aHourRequiredGrader)
  {
    boolean wasSet = false;
    hourRequiredGrader = aHourRequiredGrader;
    wasSet = true;
    return wasSet;
  }

  public boolean setBudgetCalculated(double aBudgetCalculated)
  {
    boolean wasSet = false;
    budgetCalculated = aBudgetCalculated;
    wasSet = true;
    return wasSet;
  }

  public String getSemester()
  {
    return semester;
  }

  public String getCourseCoude()
  {
    return courseCoude;
  }

  public int getNumTutorial()
  {
    return numTutorial;
  }

  public int getNumLab()
  {
    return numLab;
  }

  public int getNumStudent()
  {
    return numStudent;
  }

  public int getCredit()
  {
    return credit;
  }

  public int getHourRequiredTa()
  {
    return hourRequiredTa;
  }

  public int getHourRequiredGrader()
  {
    return hourRequiredGrader;
  }

  public double getBudgetCalculated()
  {
    return budgetCalculated;
  }

  public CourseComponent getCourseComponent(int index)
  {
    CourseComponent aCourseComponent = courseComponents.get(index);
    return aCourseComponent;
  }

  public List<CourseComponent> getCourseComponents()
  {
    List<CourseComponent> newCourseComponents = Collections.unmodifiableList(courseComponents);
    return newCourseComponents;
  }

  public int numberOfCourseComponents()
  {
    int number = courseComponents.size();
    return number;
  }

  public boolean hasCourseComponents()
  {
    boolean has = courseComponents.size() > 0;
    return has;
  }

  public int indexOfCourseComponent(CourseComponent aCourseComponent)
  {
    int index = courseComponents.indexOf(aCourseComponent);
    return index;
  }

  public Allocation getCourseJobAllocation()
  {
    return courseJobAllocation;
  }

  public boolean hasCourseJobAllocation()
  {
    boolean has = courseJobAllocation != null;
    return has;
  }

  public JobPosting getJobPosting(int index)
  {
    JobPosting aJobPosting = jobPosting.get(index);
    return aJobPosting;
  }

  public List<JobPosting> getJobPosting()
  {
    List<JobPosting> newJobPosting = Collections.unmodifiableList(jobPosting);
    return newJobPosting;
  }

  public int numberOfJobPosting()
  {
    int number = jobPosting.size();
    return number;
  }

  public boolean hasJobPosting()
  {
    boolean has = jobPosting.size() > 0;
    return has;
  }

  public int indexOfJobPosting(JobPosting aJobPosting)
  {
    int index = jobPosting.indexOf(aJobPosting);
    return index;
  }

  public Instructor getInstructor()
  {
    return instructor;
  }

  public OfferedJob getOfferedJob(int index)
  {
    OfferedJob aOfferedJob = offeredJobs.get(index);
    return aOfferedJob;
  }

  public List<OfferedJob> getOfferedJobs()
  {
    List<OfferedJob> newOfferedJobs = Collections.unmodifiableList(offeredJobs);
    return newOfferedJobs;
  }

  public int numberOfOfferedJobs()
  {
    int number = offeredJobs.size();
    return number;
  }

  public boolean hasOfferedJobs()
  {
    boolean has = offeredJobs.size() > 0;
    return has;
  }

  public int indexOfOfferedJob(OfferedJob aOfferedJob)
  {
    int index = offeredJobs.indexOf(aOfferedJob);
    return index;
  }

  public ManagementSystem getManagementSystem()
  {
    return managementSystem;
  }

  public static int minimumNumberOfCourseComponents()
  {
    return 0;
  }

  public CourseComponent addCourseComponent(String aHour, String aSessionCode)
  {
    return new CourseComponent(aHour, aSessionCode, this);
  }

  public boolean addCourseComponent(CourseComponent aCourseComponent)
  {
    boolean wasAdded = false;
    if (courseComponents.contains(aCourseComponent)) { return false; }
    Course existingCourse = aCourseComponent.getCourse();
    boolean isNewCourse = existingCourse != null && !this.equals(existingCourse);
    if (isNewCourse)
    {
      aCourseComponent.setCourse(this);
    }
    else
    {
      courseComponents.add(aCourseComponent);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeCourseComponent(CourseComponent aCourseComponent)
  {
    boolean wasRemoved = false;
    //Unable to remove aCourseComponent, as it must always have a course
    if (!this.equals(aCourseComponent.getCourse()))
    {
      courseComponents.remove(aCourseComponent);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addCourseComponentAt(CourseComponent aCourseComponent, int index)
  {  
    boolean wasAdded = false;
    if(addCourseComponent(aCourseComponent))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCourseComponents()) { index = numberOfCourseComponents() - 1; }
      courseComponents.remove(aCourseComponent);
      courseComponents.add(index, aCourseComponent);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveCourseComponentAt(CourseComponent aCourseComponent, int index)
  {
    boolean wasAdded = false;
    if(courseComponents.contains(aCourseComponent))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCourseComponents()) { index = numberOfCourseComponents() - 1; }
      courseComponents.remove(aCourseComponent);
      courseComponents.add(index, aCourseComponent);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addCourseComponentAt(aCourseComponent, index);
    }
    return wasAdded;
  }

  public boolean setCourseJobAllocation(Allocation aNewCourseJobAllocation)
  {
    boolean wasSet = false;
    if (courseJobAllocation != null && !courseJobAllocation.equals(aNewCourseJobAllocation) && equals(courseJobAllocation.getCourse()))
    {
      //Unable to setCourseJobAllocation, as existing courseJobAllocation would become an orphan
      return wasSet;
    }

    courseJobAllocation = aNewCourseJobAllocation;
    Course anOldCourse = aNewCourseJobAllocation != null ? aNewCourseJobAllocation.getCourse() : null;

    if (!this.equals(anOldCourse))
    {
      if (anOldCourse != null)
      {
        anOldCourse.courseJobAllocation = null;
      }
      if (courseJobAllocation != null)
      {
        courseJobAllocation.setCourse(this);
      }
    }
    wasSet = true;
    return wasSet;
  }

  public static int minimumNumberOfJobPosting()
  {
    return 0;
  }

  public JobPosting addJobPosting(String aJobTitle, Date aSubmissionDeadline, String aPerferredExperience, int aNumNeeded, double aHourRate, ManagementSystem aManagementSystem)
  {
    return new JobPosting(aJobTitle, aSubmissionDeadline, aPerferredExperience, aNumNeeded, aHourRate, aManagementSystem, this);
  }

  public boolean addJobPosting(JobPosting aJobPosting)
  {
    boolean wasAdded = false;
    if (jobPosting.contains(aJobPosting)) { return false; }
    Course existingCourse = aJobPosting.getCourse();
    boolean isNewCourse = existingCourse != null && !this.equals(existingCourse);
    if (isNewCourse)
    {
      aJobPosting.setCourse(this);
    }
    else
    {
      jobPosting.add(aJobPosting);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeJobPosting(JobPosting aJobPosting)
  {
    boolean wasRemoved = false;
    //Unable to remove aJobPosting, as it must always have a course
    if (!this.equals(aJobPosting.getCourse()))
    {
      jobPosting.remove(aJobPosting);
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
      if(index > numberOfJobPosting()) { index = numberOfJobPosting() - 1; }
      jobPosting.remove(aJobPosting);
      jobPosting.add(index, aJobPosting);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveJobPostingAt(JobPosting aJobPosting, int index)
  {
    boolean wasAdded = false;
    if(jobPosting.contains(aJobPosting))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfJobPosting()) { index = numberOfJobPosting() - 1; }
      jobPosting.remove(aJobPosting);
      jobPosting.add(index, aJobPosting);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addJobPostingAt(aJobPosting, index);
    }
    return wasAdded;
  }

  public boolean setInstructor(Instructor aInstructor)
  {
    boolean wasSet = false;
    if (aInstructor == null)
    {
      return wasSet;
    }

    Instructor existingInstructor = instructor;
    instructor = aInstructor;
    if (existingInstructor != null && !existingInstructor.equals(aInstructor))
    {
      existingInstructor.removeCourse(this);
    }
    instructor.addCourse(this);
    wasSet = true;
    return wasSet;
  }

  public static int minimumNumberOfOfferedJobs()
  {
    return 0;
  }

  public OfferedJob addOfferedJob(String aOfferDescription, Applicant aApplicant)
  {
    return new OfferedJob(aOfferDescription, this, aApplicant);
  }

  public boolean addOfferedJob(OfferedJob aOfferedJob)
  {
    boolean wasAdded = false;
    if (offeredJobs.contains(aOfferedJob)) { return false; }
    Course existingCourse = aOfferedJob.getCourse();
    boolean isNewCourse = existingCourse != null && !this.equals(existingCourse);
    if (isNewCourse)
    {
      aOfferedJob.setCourse(this);
    }
    else
    {
      offeredJobs.add(aOfferedJob);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeOfferedJob(OfferedJob aOfferedJob)
  {
    boolean wasRemoved = false;
    //Unable to remove aOfferedJob, as it must always have a course
    if (!this.equals(aOfferedJob.getCourse()))
    {
      offeredJobs.remove(aOfferedJob);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addOfferedJobAt(OfferedJob aOfferedJob, int index)
  {  
    boolean wasAdded = false;
    if(addOfferedJob(aOfferedJob))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOfferedJobs()) { index = numberOfOfferedJobs() - 1; }
      offeredJobs.remove(aOfferedJob);
      offeredJobs.add(index, aOfferedJob);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveOfferedJobAt(OfferedJob aOfferedJob, int index)
  {
    boolean wasAdded = false;
    if(offeredJobs.contains(aOfferedJob))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOfferedJobs()) { index = numberOfOfferedJobs() - 1; }
      offeredJobs.remove(aOfferedJob);
      offeredJobs.add(index, aOfferedJob);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addOfferedJobAt(aOfferedJob, index);
    }
    return wasAdded;
  }

  public boolean setManagementSystem(ManagementSystem aManagementSystem)
  {
    boolean wasSet = false;
    if (aManagementSystem == null)
    {
      return wasSet;
    }

    ManagementSystem existingManagementSystem = managementSystem;
    managementSystem = aManagementSystem;
    if (existingManagementSystem != null && !existingManagementSystem.equals(aManagementSystem))
    {
      existingManagementSystem.removeCourse(this);
    }
    managementSystem.addCourse(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    while (courseComponents.size() > 0)
    {
      CourseComponent aCourseComponent = courseComponents.get(courseComponents.size() - 1);
      aCourseComponent.delete();
      courseComponents.remove(aCourseComponent);
    }
    
    Allocation existingCourseJobAllocation = courseJobAllocation;
    courseJobAllocation = null;
    if (existingCourseJobAllocation != null)
    {
      existingCourseJobAllocation.delete();
      existingCourseJobAllocation.setCourse(null);
    }
    while (jobPosting.size() > 0)
    {
      JobPosting aJobPosting = jobPosting.get(jobPosting.size() - 1);
      aJobPosting.delete();
      jobPosting.remove(aJobPosting);
    }
    
    Instructor placeholderInstructor = instructor;
    this.instructor = null;
    placeholderInstructor.removeCourse(this);
    while (offeredJobs.size() > 0)
    {
      OfferedJob aOfferedJob = offeredJobs.get(offeredJobs.size() - 1);
      aOfferedJob.delete();
      offeredJobs.remove(aOfferedJob);
    }
    
    ManagementSystem placeholderManagementSystem = managementSystem;
    this.managementSystem = null;
    placeholderManagementSystem.removeCourse(this);
  }


  public String toString()
  {
    String outputString = "";
    return super.toString() + "["+
            "semester" + ":" + getSemester()+ "," +
            "courseCoude" + ":" + getCourseCoude()+ "," +
            "numTutorial" + ":" + getNumTutorial()+ "," +
            "numLab" + ":" + getNumLab()+ "," +
            "numStudent" + ":" + getNumStudent()+ "," +
            "credit" + ":" + getCredit()+ "," +
            "hourRequiredTa" + ":" + getHourRequiredTa()+ "," +
            "hourRequiredGrader" + ":" + getHourRequiredGrader()+ "," +
            "budgetCalculated" + ":" + getBudgetCalculated()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "courseJobAllocation = "+(getCourseJobAllocation()!=null?Integer.toHexString(System.identityHashCode(getCourseJobAllocation())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "instructor = "+(getInstructor()!=null?Integer.toHexString(System.identityHashCode(getInstructor())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "managementSystem = "+(getManagementSystem()!=null?Integer.toHexString(System.identityHashCode(getManagementSystem())):"null")
     + outputString;
  }
}