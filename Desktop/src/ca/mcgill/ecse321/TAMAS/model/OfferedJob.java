/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse321.TAMAS.model;


// line 133 "../../../../../TAMAS.ump"

public class OfferedJob
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //OfferedJob Attributes
  private String offerDescription;

  //OfferedJob State Machines
  public enum Job { TA, GRADER }
  private Job job;

  //OfferedJob Associations
  private Course course;
  private Applicant applicant;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public OfferedJob(String aOfferDescription, Course aCourse, Applicant aApplicant)
  {
    offerDescription = aOfferDescription;
    boolean didAddCourse = setCourse(aCourse);
    if (!didAddCourse)
    {
      throw new RuntimeException("Unable to create offeredJob due to course");
    }
    boolean didAddApplicant = setApplicant(aApplicant);
    if (!didAddApplicant)
    {
      throw new RuntimeException("Unable to create offeredJob due to applicant");
    }
    setJob(Job.TA);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setOfferDescription(String aOfferDescription)
  {
    boolean wasSet = false;
    offerDescription = aOfferDescription;
    wasSet = true;
    return wasSet;
  }

  public String getOfferDescription()
  {
    return offerDescription;
  }

  public String getJobFullName()
  {
    String answer = job.toString();
    return answer;
  }

  public Job getJob()
  {
    return job;
  }

  public boolean setJob(Job aJob)
  {
    job = aJob;
    return true;
  }

  public Course getCourse()
  {
    return course;
  }

  public Applicant getApplicant()
  {
    return applicant;
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
      existingCourse.removeOfferedJob(this);
    }
    course.addOfferedJob(this);
    wasSet = true;
    return wasSet;
  }

  public boolean setApplicant(Applicant aApplicant)
  {
    boolean wasSet = false;
    //Must provide applicant to offeredJob
    if (aApplicant == null)
    {
      return wasSet;
    }

    //applicant already at maximum (3)
    if (aApplicant.numberOfOfferedJobs() >= Applicant.maximumNumberOfOfferedJobs())
    {
      return wasSet;
    }
    
    Applicant existingApplicant = applicant;
    applicant = aApplicant;
    if (existingApplicant != null && !existingApplicant.equals(aApplicant))
    {
      boolean didRemove = existingApplicant.removeOfferedJob(this);
      if (!didRemove)
      {
        applicant = existingApplicant;
        return wasSet;
      }
    }
    applicant.addOfferedJob(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Course placeholderCourse = course;
    this.course = null;
    placeholderCourse.removeOfferedJob(this);
    Applicant placeholderApplicant = applicant;
    this.applicant = null;
    placeholderApplicant.removeOfferedJob(this);
  }


  public String toString()
  {
    String outputString = "";
    return super.toString() + "["+
            "offerDescription" + ":" + getOfferDescription()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "course = "+(getCourse()!=null?Integer.toHexString(System.identityHashCode(getCourse())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "applicant = "+(getApplicant()!=null?Integer.toHexString(System.identityHashCode(getApplicant())):"null")
     + outputString;
  }
}