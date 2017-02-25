/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse321.TAMAS;
import java.util.*;

// line 83 "../../../../TAMAS.ump"
public class Applicant
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Applicant Attributes
  private int studentID;
  private String name;
  private String previousExperience;
  private boolean isUnderGraduated;
  private String preferredCourse;
  private String major;
  private int year;
  private String option1;
  private String option2;
  private String option3;
  private int totalAppointmentHours;

  //Applicant Associations
  private Allocation allocation;
  private List<OfferedJob> offeredJobs;
  private ManagementSystem managementSystem;
  private List<Application> applications;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Applicant(int aStudentID, String aName, String aPreviousExperience, boolean aIsUnderGraduated, String aPreferredCourse, String aMajor, int aYear, String aOption1, String aOption2, String aOption3, int aTotalAppointmentHours, Allocation aAllocation, ManagementSystem aManagementSystem)
  {
    studentID = aStudentID;
    name = aName;
    previousExperience = aPreviousExperience;
    isUnderGraduated = aIsUnderGraduated;
    preferredCourse = aPreferredCourse;
    major = aMajor;
    year = aYear;
    option1 = aOption1;
    option2 = aOption2;
    option3 = aOption3;
    totalAppointmentHours = aTotalAppointmentHours;
    boolean didAddAllocation = setAllocation(aAllocation);
    if (!didAddAllocation)
    {
      throw new RuntimeException("Unable to create applicant due to allocation");
    }
    offeredJobs = new ArrayList<OfferedJob>();
    boolean didAddManagementSystem = setManagementSystem(aManagementSystem);
    if (!didAddManagementSystem)
    {
      throw new RuntimeException("Unable to create applicant due to managementSystem");
    }
    applications = new ArrayList<Application>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setStudentID(int aStudentID)
  {
    boolean wasSet = false;
    studentID = aStudentID;
    wasSet = true;
    return wasSet;
  }

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setPreviousExperience(String aPreviousExperience)
  {
    boolean wasSet = false;
    previousExperience = aPreviousExperience;
    wasSet = true;
    return wasSet;
  }

  public boolean setIsUnderGraduated(boolean aIsUnderGraduated)
  {
    boolean wasSet = false;
    isUnderGraduated = aIsUnderGraduated;
    wasSet = true;
    return wasSet;
  }

  public boolean setPreferredCourse(String aPreferredCourse)
  {
    boolean wasSet = false;
    preferredCourse = aPreferredCourse;
    wasSet = true;
    return wasSet;
  }

  public boolean setMajor(String aMajor)
  {
    boolean wasSet = false;
    major = aMajor;
    wasSet = true;
    return wasSet;
  }

  public boolean setYear(int aYear)
  {
    boolean wasSet = false;
    year = aYear;
    wasSet = true;
    return wasSet;
  }

  public boolean setOption1(String aOption1)
  {
    boolean wasSet = false;
    option1 = aOption1;
    wasSet = true;
    return wasSet;
  }

  public boolean setOption2(String aOption2)
  {
    boolean wasSet = false;
    option2 = aOption2;
    wasSet = true;
    return wasSet;
  }

  public boolean setOption3(String aOption3)
  {
    boolean wasSet = false;
    option3 = aOption3;
    wasSet = true;
    return wasSet;
  }

  public boolean setTotalAppointmentHours(int aTotalAppointmentHours)
  {
    boolean wasSet = false;
    totalAppointmentHours = aTotalAppointmentHours;
    wasSet = true;
    return wasSet;
  }

  public int getStudentID()
  {
    return studentID;
  }

  public String getName()
  {
    return name;
  }

  public String getPreviousExperience()
  {
    return previousExperience;
  }

  public boolean getIsUnderGraduated()
  {
    return isUnderGraduated;
  }

  public String getPreferredCourse()
  {
    return preferredCourse;
  }

  public String getMajor()
  {
    return major;
  }

  public int getYear()
  {
    return year;
  }

  public String getOption1()
  {
    return option1;
  }

  public String getOption2()
  {
    return option2;
  }

  public String getOption3()
  {
    return option3;
  }

  public int getTotalAppointmentHours()
  {
    return totalAppointmentHours;
  }

  public boolean isIsUnderGraduated()
  {
    return isUnderGraduated;
  }

  public Allocation getAllocation()
  {
    return allocation;
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

  public boolean setAllocation(Allocation aAllocation)
  {
    boolean wasSet = false;
    if (aAllocation == null)
    {
      return wasSet;
    }

    Allocation existingAllocation = allocation;
    allocation = aAllocation;
    if (existingAllocation != null && !existingAllocation.equals(aAllocation))
    {
      existingAllocation.removeApplicant(this);
    }
    allocation.addApplicant(this);
    wasSet = true;
    return wasSet;
  }

  public static int minimumNumberOfOfferedJobs()
  {
    return 0;
  }

  public static int maximumNumberOfOfferedJobs()
  {
    return 3;
  }

  public OfferedJob addOfferedJob(String aOfferDescription, Course aCourse)
  {
    if (numberOfOfferedJobs() >= maximumNumberOfOfferedJobs())
    {
      return null;
    }
    else
    {
      return new OfferedJob(aOfferDescription, aCourse, this);
    }
  }

  public boolean addOfferedJob(OfferedJob aOfferedJob)
  {
    boolean wasAdded = false;
    if (offeredJobs.contains(aOfferedJob)) { return false; }
    if (numberOfOfferedJobs() >= maximumNumberOfOfferedJobs())
    {
      return wasAdded;
    }

    Applicant existingApplicant = aOfferedJob.getApplicant();
    boolean isNewApplicant = existingApplicant != null && !this.equals(existingApplicant);
    if (isNewApplicant)
    {
      aOfferedJob.setApplicant(this);
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
    //Unable to remove aOfferedJob, as it must always have a applicant
    if (!this.equals(aOfferedJob.getApplicant()))
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
    //Must provide managementSystem to applicant
    if (aManagementSystem == null)
    {
      return wasSet;
    }

    if (managementSystem != null && managementSystem.numberOfApplicants() <= ManagementSystem.minimumNumberOfApplicants())
    {
      return wasSet;
    }

    ManagementSystem existingManagementSystem = managementSystem;
    managementSystem = aManagementSystem;
    if (existingManagementSystem != null && !existingManagementSystem.equals(aManagementSystem))
    {
      boolean didRemove = existingManagementSystem.removeApplicant(this);
      if (!didRemove)
      {
        managementSystem = existingManagementSystem;
        return wasSet;
      }
    }
    managementSystem.addApplicant(this);
    wasSet = true;
    return wasSet;
  }

  public static int minimumNumberOfApplications()
  {
    return 0;
  }

  public static int maximumNumberOfApplications()
  {
    return 3;
  }

  public boolean addApplication(Application aApplication)
  {
    boolean wasAdded = false;
    if (applications.contains(aApplication)) { return false; }
    if (numberOfApplications() >= maximumNumberOfApplications())
    {
      return wasAdded;
    }

    applications.add(aApplication);
    if (aApplication.indexOfApplicant(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aApplication.addApplicant(this);
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
    if (aApplication.indexOfApplicant(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aApplication.removeApplicant(this);
      if (!wasRemoved)
      {
        applications.add(oldIndex,aApplication);
      }
    }
    return wasRemoved;
  }

  public boolean setApplications(Application... newApplications)
  {
    boolean wasSet = false;
    ArrayList<Application> verifiedApplications = new ArrayList<Application>();
    for (Application aApplication : newApplications)
    {
      if (verifiedApplications.contains(aApplication))
      {
        continue;
      }
      verifiedApplications.add(aApplication);
    }

    if (verifiedApplications.size() != newApplications.length || verifiedApplications.size() > maximumNumberOfApplications())
    {
      return wasSet;
    }

    ArrayList<Application> oldApplications = new ArrayList<Application>(applications);
    applications.clear();
    for (Application aNewApplication : verifiedApplications)
    {
      applications.add(aNewApplication);
      if (oldApplications.contains(aNewApplication))
      {
        oldApplications.remove(aNewApplication);
      }
      else
      {
        aNewApplication.addApplicant(this);
      }
    }

    for (Application anOldApplication : oldApplications)
    {
      anOldApplication.removeApplicant(this);
    }
    wasSet = true;
    return wasSet;
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
    Allocation placeholderAllocation = allocation;
    this.allocation = null;
    placeholderAllocation.removeApplicant(this);
    for(int i=offeredJobs.size(); i > 0; i--)
    {
      OfferedJob aOfferedJob = offeredJobs.get(i - 1);
      aOfferedJob.delete();
    }
    ManagementSystem placeholderManagementSystem = managementSystem;
    this.managementSystem = null;
    placeholderManagementSystem.removeApplicant(this);
    ArrayList<Application> copyOfApplications = new ArrayList<Application>(applications);
    applications.clear();
    for(Application aApplication : copyOfApplications)
    {
      aApplication.removeApplicant(this);
    }
  }


  public String toString()
  {
    String outputString = "";
    return super.toString() + "["+
            "studentID" + ":" + getStudentID()+ "," +
            "name" + ":" + getName()+ "," +
            "previousExperience" + ":" + getPreviousExperience()+ "," +
            "isUnderGraduated" + ":" + getIsUnderGraduated()+ "," +
            "preferredCourse" + ":" + getPreferredCourse()+ "," +
            "major" + ":" + getMajor()+ "," +
            "year" + ":" + getYear()+ "," +
            "option1" + ":" + getOption1()+ "," +
            "option2" + ":" + getOption2()+ "," +
            "option3" + ":" + getOption3()+ "," +
            "totalAppointmentHours" + ":" + getTotalAppointmentHours()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "allocation = "+(getAllocation()!=null?Integer.toHexString(System.identityHashCode(getAllocation())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "managementSystem = "+(getManagementSystem()!=null?Integer.toHexString(System.identityHashCode(getManagementSystem())):"null")
     + outputString;
  }
}