/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse321.TAMAS;
import java.sql.Date;
import java.util.*;

// line 67 "../../../../TAMAS.ump"
public class JobPosting
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //JobPosting Attributes
  private String jobTitle;
  private Date submissionDeadline;
  private String perferredExperience;
  private int numNeeded;
  private double hourRate;

  //JobPosting Associations
  private ManagementSystem managementSystem;
  private Course course;
  private List<Application> applications;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public JobPosting(String aJobTitle, Date aSubmissionDeadline, String aPerferredExperience, int aNumNeeded, double aHourRate, ManagementSystem aManagementSystem, Course aCourse)
  {
    jobTitle = aJobTitle;
    submissionDeadline = aSubmissionDeadline;
    perferredExperience = aPerferredExperience;
    numNeeded = aNumNeeded;
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

  public boolean setNumNeeded(int aNumNeeded)
  {
    boolean wasSet = false;
    numNeeded = aNumNeeded;
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

  public int getNumNeeded()
  {
    return numNeeded;
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

  public boolean addApplication(Application aApplication)
  {
    boolean wasAdded = false;
    if (applications.contains(aApplication)) { return false; }
    applications.add(aApplication);
    if (aApplication.indexOfJobPosting(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aApplication.addJobPosting(this);
      if (!wasAdded)
      {
        applications.remove(aApplication);
      }
    }
    return wasAdded;
  }

  public boolean removeApplication(Application aApplication)
  {
    boolean wasRemoved = false;
    if (!applications.contains(aApplication))
    {
      return wasRemoved;
    }

    int oldIndex = applications.indexOf(aApplication);
    applications.remove(oldIndex);
    if (aApplication.indexOfJobPosting(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aApplication.removeJobPosting(this);
      if (!wasRemoved)
      {
        applications.add(oldIndex,aApplication);
      }
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
    ArrayList<Application> copyOfApplications = new ArrayList<Application>(applications);
    applications.clear();
    for(Application aApplication : copyOfApplications)
    {
      if (aApplication.numberOfJobPostings() <= Application.minimumNumberOfJobPostings())
      {
        aApplication.delete();
      }
      else
      {
        aApplication.removeJobPosting(this);
      }
    }
  }


  public String toString()
  {
    String outputString = "";
    return super.toString() + "["+
            "jobTitle" + ":" + getJobTitle()+ "," +
            "perferredExperience" + ":" + getPerferredExperience()+ "," +
            "numNeeded" + ":" + getNumNeeded()+ "," +
            "hourRate" + ":" + getHourRate()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "submissionDeadline" + "=" + (getSubmissionDeadline() != null ? !getSubmissionDeadline().equals(this)  ? getSubmissionDeadline().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "managementSystem = "+(getManagementSystem()!=null?Integer.toHexString(System.identityHashCode(getManagementSystem())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "course = "+(getCourse()!=null?Integer.toHexString(System.identityHashCode(getCourse())):"null")
     + outputString;
  }
}