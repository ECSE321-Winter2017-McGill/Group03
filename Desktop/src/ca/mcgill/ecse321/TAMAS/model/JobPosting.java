/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse321.TAMAS.model;
import java.sql.Date;
import java.util.*;

// line 58 "../../../../../TAMAS.ump"
public class JobPosting
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //JobPosting Attributes
  private String jobTitle;
  private Date submissionDeadline;
  private String perferredExperience;
  private double hourRate;

  //JobPosting Associations
  private ManagementSystem managementSystem;
  private Course course;
  private List<Application> applications;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public JobPosting(String aJobTitle, Date aSubmissionDeadline, String aPerferredExperience, double aHourRate, ManagementSystem aManagementSystem, Course aCourse)
  {
    jobTitle = aJobTitle;
    submissionDeadline = aSubmissionDeadline;
    perferredExperience = aPerferredExperience;
    hourRate = aHourRate;
    boolean didAddManagementSystem = setManagementSystem(aManagementSystem);
    if (!didAddManagementSystem)
    {
      throw new RuntimeException("Unable to create jobPosting due to managementSystem");
    }
    boolean didAddCourse = setCourse(aCourse);
    if (!didAddCourse)
    {
      throw new RuntimeException("Unable to create jobPosting due to course");
    }
    applications = new ArrayList<Application>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setJobTitle(String aJobTitle)
  {
    boolean wasSet = false;
    jobTitle = aJobTitle;
    wasSet = true;
    return wasSet;
  }

  public boolean setSubmissionDeadline(Date aSubmissionDeadline)
  {
    boolean wasSet = false;
    submissionDeadline = aSubmissionDeadline;
    wasSet = true;
    return wasSet;
  }

  public boolean setPerferredExperience(String aPerferredExperience)
  {
    boolean wasSet = false;
    perferredExperience = aPerferredExperience;
    wasSet = true;
    return wasSet;
  }

  public boolean setHourRate(double aHourRate)
  {
    boolean wasSet = false;
    hourRate = aHourRate;
    wasSet = true;
    return wasSet;
  }

  public String getJobTitle()
  {
    return jobTitle;
  }

  public Date getSubmissionDeadline()
  {
    return submissionDeadline;
  }

  public String getPerferredExperience()
  {
    return perferredExperience;
  }

  public double getHourRate()
  {
    return hourRate;
  }

  public ManagementSystem getManagementSystem()
  {
    return managementSystem;
  }

  public Course getCourse()
  {
    return course;
  }

  public Application getApplication(int index)
  {
    Application aApplication = applications.get(index);
    return aApplication;
  }

  public List<Application> getApplications()
  {
    List<Application> newApplications = Collections.unmodifiableList(applications);
    return newApplications;
  }

  public int numberOfApplications()
  {
    int number = applications.size();
    return number;
  }

  public boolean hasApplications()
  {
    boolean has = applications.size() > 0;
    return has;
  }

  public int indexOfApplication(Application aApplication)
  {
    int index = applications.indexOf(aApplication);
    return index;
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
      existingManagementSystem.removeJobPosting(this);
    }
    managementSystem.addJobPosting(this);
    wasSet = true;
    return wasSet;
  }

  public boolean setCourse(Course aCourse)
  {
    boolean wasSet = false;
    if (aCourse == null)
    {
      return wasSet;
    }

    Course existingCourse = course;
    course = aCourse;
    if (existingCourse != null && !existingCourse.equals(aCourse))
    {
      existingCourse.removeJobPosting(this);
    }
    course.addJobPosting(this);
    wasSet = true;
    return wasSet;
  }

  public static int minimumNumberOfApplications()
  {
    return 0;
  }

  public Application addApplication(int aHour, Applicant aApplicant)
  {
    return new Application(aHour, this, aApplicant);
  }

  public boolean addApplication(Application aApplication)
  {
    boolean wasAdded = false;
    if (applications.contains(aApplication)) { return false; }
    JobPosting existingJobPosting = aApplication.getJobPosting();
    boolean isNewJobPosting = existingJobPosting != null && !this.equals(existingJobPosting);
    if (isNewJobPosting)
    {
      aApplication.setJobPosting(this);
    }
    else
    {
      applications.add(aApplication);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeApplication(Application aApplication)
  {
    boolean wasRemoved = false;
    //Unable to remove aApplication, as it must always have a jobPosting
    if (!this.equals(aApplication.getJobPosting()))
    {
      applications.remove(aApplication);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addApplicationAt(Application aApplication, int index)
  {  
    boolean wasAdded = false;
    if(addApplication(aApplication))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfApplications()) { index = numberOfApplications() - 1; }
      applications.remove(aApplication);
      applications.add(index, aApplication);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveApplicationAt(Application aApplication, int index)
  {
    boolean wasAdded = false;
    if(applications.contains(aApplication))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfApplications()) { index = numberOfApplications() - 1; }
      applications.remove(aApplication);
      applications.add(index, aApplication);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addApplicationAt(aApplication, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    ManagementSystem placeholderManagementSystem = managementSystem;
    this.managementSystem = null;
    placeholderManagementSystem.removeJobPosting(this);
    Course placeholderCourse = course;
    this.course = null;
    placeholderCourse.removeJobPosting(this);
    for(int i=applications.size(); i > 0; i--)
    {
      Application aApplication = applications.get(i - 1);
      aApplication.delete();
    }
  }


  public String toString()
  {
    String outputString = "";
    return super.toString() + "["+
            "jobTitle" + ":" + getJobTitle()+ "," +
            "perferredExperience" + ":" + getPerferredExperience()+ "," +
            "hourRate" + ":" + getHourRate()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "submissionDeadline" + "=" + (getSubmissionDeadline() != null ? !getSubmissionDeadline().equals(this)  ? getSubmissionDeadline().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "managementSystem = "+(getManagementSystem()!=null?Integer.toHexString(System.identityHashCode(getManagementSystem())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "course = "+(getCourse()!=null?Integer.toHexString(System.identityHashCode(getCourse())):"null")
     + outputString;
  }
}