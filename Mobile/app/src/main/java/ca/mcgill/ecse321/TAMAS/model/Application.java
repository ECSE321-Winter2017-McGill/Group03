/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

package ca.mcgill.ecse321.TAMAS.model;

// line 101 "../../../../../TAMAS.ump"
public class Application
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Application State Machines
  public enum Status { SELECTED, REJECTED, PENDING, OFFER_ACCEPTED, OFFER_DECLINED }
  private Status status;

  //Application Associations
  private JobPosting jobPosting;
  private Applicant applicant;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Application(JobPosting aJobPosting, Applicant aApplicant)
  {
    boolean didAddJobPosting = setJobPosting(aJobPosting);
    if (!didAddJobPosting)
    {
      throw new RuntimeException("Unable to create application due to jobPosting");
    }
    boolean didAddApplicant = setApplicant(aApplicant);
    if (!didAddApplicant)
    {
      throw new RuntimeException("Unable to create application due to applicant");
    }
    setStatus(Status.SELECTED);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public String getStatusFullName()
  {
    String answer = status.toString();
    return answer;
  }

  public Status getStatus()
  {
    return status;
  }

  public boolean setStatus(Status aStatus)
  {
    status = aStatus;
    return true;
  }

  public JobPosting getJobPosting()
  {
    return jobPosting;
  }

  public Applicant getApplicant()
  {
    return applicant;
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

  public boolean setApplicant(Applicant aApplicant)
  {
    boolean wasSet = false;
    //Must provide applicant to application
    if (aApplicant == null)
    {
      return wasSet;
    }

    //applicant already at maximum (3)
    if (aApplicant.numberOfApplications() >= Applicant.maximumNumberOfApplications())
    {
      return wasSet;
    }
    
    Applicant existingApplicant = applicant;
    applicant = aApplicant;
    if (existingApplicant != null && !existingApplicant.equals(aApplicant))
    {
      boolean didRemove = existingApplicant.removeApplication(this);
      if (!didRemove)
      {
        applicant = existingApplicant;
        return wasSet;
      }
    }
    applicant.addApplication(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    JobPosting placeholderJobPosting = jobPosting;
    this.jobPosting = null;
    placeholderJobPosting.removeApplication(this);
    Applicant placeholderApplicant = applicant;
    this.applicant = null;
    placeholderApplicant.removeApplication(this);
  }

}