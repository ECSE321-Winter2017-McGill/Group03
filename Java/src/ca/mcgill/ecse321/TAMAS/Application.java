/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse321.TAMAS;
import java.util.*;

// line 114 "../../../../TAMAS.ump"
public class Application
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Application Attributes
  private String applicationStatus;

  //Application Associations
  private List<JobPosting> jobPostings;
  private List<Applicant> applicants;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Application(String aApplicationStatus, JobPosting... allJobPostings)
  {
    applicationStatus = aApplicationStatus;
    jobPostings = new ArrayList<JobPosting>();
    boolean didAddJobPostings = setJobPostings(allJobPostings);
    if (!didAddJobPostings)
    {
      throw new RuntimeException("Unable to create Application, must have 1 to 3 jobPostings");
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

  public boolean isNumberOfJobPostingsValid()
  {
    boolean isValid = numberOfJobPostings() >= minimumNumberOfJobPostings() && numberOfJobPostings() <= maximumNumberOfJobPostings();
    return isValid;
  }

  public static int minimumNumberOfJobPostings()
  {
    return 1;
  }

  public static int maximumNumberOfJobPostings()
  {
    return 3;
  }

  public boolean addJobPosting(JobPosting aJobPosting)
  {
    boolean wasAdded = false;
    if (jobPostings.contains(aJobPosting)) { return false; }
    if (numberOfJobPostings() >= maximumNumberOfJobPostings())
    {
      return wasAdded;
    }

    jobPostings.add(aJobPosting);
    if (aJobPosting.indexOfApplication(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aJobPosting.addApplication(this);
      if (!wasAdded)
      {
        jobPostings.remove(aJobPosting);
      }
    }
    return wasAdded;
  }

  public boolean removeJobPosting(JobPosting aJobPosting)
  {
    boolean wasRemoved = false;
    if (!jobPostings.contains(aJobPosting))
    {
      return wasRemoved;
    }

    if (numberOfJobPostings() <= minimumNumberOfJobPostings())
    {
      return wasRemoved;
    }

    int oldIndex = jobPostings.indexOf(aJobPosting);
    jobPostings.remove(oldIndex);
    if (aJobPosting.indexOfApplication(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aJobPosting.removeApplication(this);
      if (!wasRemoved)
      {
        jobPostings.add(oldIndex,aJobPosting);
      }
    }
    return wasRemoved;
  }

  public boolean setJobPostings(JobPosting... newJobPostings)
  {
    boolean wasSet = false;
    ArrayList<JobPosting> verifiedJobPostings = new ArrayList<JobPosting>();
    for (JobPosting aJobPosting : newJobPostings)
    {
      if (verifiedJobPostings.contains(aJobPosting))
      {
        continue;
      }
      verifiedJobPostings.add(aJobPosting);
    }

    if (verifiedJobPostings.size() != newJobPostings.length || verifiedJobPostings.size() < minimumNumberOfJobPostings() || verifiedJobPostings.size() > maximumNumberOfJobPostings())
    {
      return wasSet;
    }

    ArrayList<JobPosting> oldJobPostings = new ArrayList<JobPosting>(jobPostings);
    jobPostings.clear();
    for (JobPosting aNewJobPosting : verifiedJobPostings)
    {
      jobPostings.add(aNewJobPosting);
      if (oldJobPostings.contains(aNewJobPosting))
      {
        oldJobPostings.remove(aNewJobPosting);
      }
      else
      {
        aNewJobPosting.addApplication(this);
      }
    }

    for (JobPosting anOldJobPosting : oldJobPostings)
    {
      anOldJobPosting.removeApplication(this);
    }
    wasSet = true;
    return wasSet;
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
    ArrayList<JobPosting> copyOfJobPostings = new ArrayList<JobPosting>(jobPostings);
    jobPostings.clear();
    for(JobPosting aJobPosting : copyOfJobPostings)
    {
      aJobPosting.removeApplication(this);
    }
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
            "applicationStatus" + ":" + getApplicationStatus()+ "]"
     + outputString;
  }
}