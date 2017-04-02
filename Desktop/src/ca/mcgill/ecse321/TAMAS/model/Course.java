/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

package ca.mcgill.ecse321.TAMAS.model;
import java.util.*;
import java.sql.Date;

// line 21 "../../../../../TAMAS.ump"
public class Course
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Course Attributes
  private String semester;
  private String courseName;
  private String courseCode;
  private int numTutorial;
  private int numLab;
  private int numStudent;
  private int credit;
  private int numTaNeeded;
  private int numGraderNeeded;
  private int labHour;
  private int tutorialHour;
  private int totalGraderHour;
  private double budgetCalculated;

  //Course Associations
  private Allocation courseJobAllocation;
  private List<JobPosting> jobPosting;
  private Instructor instructor;
  private ManagementSystem managementSystem;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Course(String aSemester, String aCourseName, String aCourseCode, int aNumTutorial, int aNumLab, int aNumStudent, int aCredit, int aNumTaNeeded, int aNumGraderNeeded, int aLabHour, int aTutorialHour, int aTotalGraderHour, double aBudgetCalculated, Instructor aInstructor, ManagementSystem aManagementSystem)
  {
    semester = aSemester;
    courseName = aCourseName;
    courseCode = aCourseCode;
    numTutorial = aNumTutorial;
    numLab = aNumLab;
    numStudent = aNumStudent;
    credit = aCredit;
    numTaNeeded = aNumTaNeeded;
    numGraderNeeded = aNumGraderNeeded;
    labHour = aLabHour;
    tutorialHour = aTutorialHour;
    totalGraderHour = aTotalGraderHour;
    budgetCalculated = aBudgetCalculated;
    jobPosting = new ArrayList<JobPosting>();
    boolean didAddInstructor = setInstructor(aInstructor);
    if (!didAddInstructor)
    {
      throw new RuntimeException("Unable to create course due to instructor");
    }
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

  public boolean setCourseName(String aCourseName)
  {
    boolean wasSet = false;
    courseName = aCourseName;
    wasSet = true;
    return wasSet;
  }

  public boolean setCourseCode(String aCourseCode)
  {
    boolean wasSet = false;
    courseCode = aCourseCode;
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

  public boolean setNumTaNeeded(int aNumTaNeeded)
  {
    boolean wasSet = false;
    numTaNeeded = aNumTaNeeded;
    wasSet = true;
    return wasSet;
  }

  public boolean setNumGraderNeeded(int aNumGraderNeeded)
  {
    boolean wasSet = false;
    numGraderNeeded = aNumGraderNeeded;
    wasSet = true;
    return wasSet;
  }

  public boolean setLabHour(int aLabHour)
  {
    boolean wasSet = false;
    labHour = aLabHour;
    wasSet = true;
    return wasSet;
  }

  public boolean setTutorialHour(int aTutorialHour)
  {
    boolean wasSet = false;
    tutorialHour = aTutorialHour;
    wasSet = true;
    return wasSet;
  }

  public boolean setTotalGraderHour(int aTotalGraderHour)
  {
    boolean wasSet = false;
    totalGraderHour = aTotalGraderHour;
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

  public String getCourseName()
  {
    return courseName;
  }

  public String getCourseCode()
  {
    return courseCode;
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

  public int getNumTaNeeded()
  {
    return numTaNeeded;
  }

  public int getNumGraderNeeded()
  {
    return numGraderNeeded;
  }

  public int getLabHour()
  {
    return labHour;
  }

  public int getTutorialHour()
  {
    return tutorialHour;
  }

  public int getTotalGraderHour()
  {
    return totalGraderHour;
  }

  public double getBudgetCalculated()
  {
    return budgetCalculated;
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

  public ManagementSystem getManagementSystem()
  {
    return managementSystem;
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

  public JobPosting addJobPosting(String aJobTitle, Date aSubmissionDeadline, String aPerferredExperience, double aHourRate, ManagementSystem aManagementSystem)
  {
    return new JobPosting(aJobTitle, aSubmissionDeadline, aPerferredExperience, aHourRate, aManagementSystem, this);
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
    ManagementSystem placeholderManagementSystem = managementSystem;
    this.managementSystem = null;
    placeholderManagementSystem.removeCourse(this);
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "semester" + ":" + getSemester()+ "," +
            "courseName" + ":" + getCourseName()+ "," +
            "courseCode" + ":" + getCourseCode()+ "," +
            "numTutorial" + ":" + getNumTutorial()+ "," +
            "numLab" + ":" + getNumLab()+ "," +
            "numStudent" + ":" + getNumStudent()+ "," +
            "credit" + ":" + getCredit()+ "," +
            "numTaNeeded" + ":" + getNumTaNeeded()+ "," +
            "numGraderNeeded" + ":" + getNumGraderNeeded()+ "," +
            "labHour" + ":" + getLabHour()+ "," +
            "tutorialHour" + ":" + getTutorialHour()+ "," +
            "totalGraderHour" + ":" + getTotalGraderHour()+ "," +
            "budgetCalculated" + ":" + getBudgetCalculated()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "courseJobAllocation = "+(getCourseJobAllocation()!=null?Integer.toHexString(System.identityHashCode(getCourseJobAllocation())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "instructor = "+(getInstructor()!=null?Integer.toHexString(System.identityHashCode(getInstructor())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "managementSystem = "+(getManagementSystem()!=null?Integer.toHexString(System.identityHashCode(getManagementSystem())):"null")
     + outputString;
  }
}