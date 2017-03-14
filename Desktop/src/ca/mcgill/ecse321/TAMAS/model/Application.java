/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse321.TAMAS.model;
import java.util.*;

// line 112 "../../../../../TAMAS.ump"
public class Application
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Application Attributes
  private String applicationStatus;

  //Application Associations
  private JobPosting jobPosting;
  private List<Applicant> applicants;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Application(String aApplicationStatus, JobPosting aJobPosting)
  {
    applicationStatus = aApplicationStatus;
    boolean didAddJobPosting = setJobPosting(aJobPosting);
    if (!didAddJobPosting)
    {
      throw new RuntimeException("Unable to create application due to jobPosting");
    }
    applicants = new ArrayList<Applicant>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setApplicationStatus(String aApplicationStatus)
  {
    boolean wasSet = false;
    applicationStatus = aApplicationStatus;
    wasSet = true;
    return wasSet;
  }

  public String getApplicationStatus()
  {
    return applicationStatus;
  }

  public JobPosting getJobPosting()
  {
    return jobPosting;
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

  public boolean setJobPosting(JobPosting aJobPosting)
  {
    boolean wasSet = false;
    if (aJobPosting == null)
    {
      return wasSet;
    }

    JobPosting existingJobPosting = jobPosting;
    jobPosting = aJobPosting;
    if (existingJobPosting != null && !existingJobPosting.equals(aJobPosting))
    {
      existingJobPosting.removeApplication(this);
    }
    jobPosting.addApplication(this);
    wasSet = true;
    return wasSet;
  }

  public static int minimumNumberOfApplicants()
  {
    return 0;
  }

  public boolean addApplicant(Applicant aApplicant)
  {
    boolean wasAdded = false;
    if (applicants.contains(aApplicant)) { return false; }
    applicants.add(aApplicant);
    if (aApplicant.indexOfApplication(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aApplicant.addApplication(this);
      if (!wasAdded)
      {
        applicants.remove(aApplicant);
      }
    }
    return wasAdded;
  }

  public boolean removeApplicant(Applicant aApplicant)
  {
    boolean wasRemoved = false;
    if (!applicants.contains(aApplicant))
    {
      return wasRemoved;
    }

    int oldIndex = applicants.indexOf(aApplicant);
    applicants.remove(oldIndex);
    if (aApplicant.indexOfApplication(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aApplicant.removeApplication(this);
      if (!wasRemoved)
      {
        applicants.add(oldIndex,aApplicant);
      }
    }
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

  public void delete()
  {
    JobPosting placeholderJobPosting = jobPosting;
    this.jobPosting = null;
    placeholderJobPosting.removeApplication(this);
    ArrayList<Applicant> copyOfApplicants = new ArrayList<Applicant>(applicants);
    applicants.clear();
    for(Applicant aApplicant : copyOfApplicants)
    {
      aApplicant.removeApplication(this);
    }
  }


  public String toString()
  {
    String outputString = "";
    return super.toString() + "["+
            "applicationStatus" + ":" + getApplicationStatus()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "jobPosting = "+(getJobPosting()!=null?Integer.toHexString(System.identityHashCode(getJobPosting())):"null")
     + outputString;
  }
}