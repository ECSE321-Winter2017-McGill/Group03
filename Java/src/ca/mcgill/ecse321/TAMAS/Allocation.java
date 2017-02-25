/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse321.TAMAS;
import java.util.*;

// line 156 "../../../../TAMAS.ump"
public class Allocation
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Allocation Associations
  private Course course;
  private List<Applicant> applicants;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Allocation(Course aCourse)
  {
    if (aCourse == null || aCourse.getCourseJobAllocation() != null)
    {
      throw new RuntimeException("Unable to create Allocation due to aCourse");
    }
    course = aCourse;
    applicants = new ArrayList<Applicant>();
  }

  public Allocation(String aSemesterForCourse, String aCourseCoudeForCourse, int aNumTutorialForCourse, int aNumLabForCourse, int aNumStudentForCourse, int aCreditForCourse, int aHourRequiredTaForCourse, int aHourRequiredGraderForCourse, double aBudgetCalculatedForCourse, Instructor aInstructorForCourse, ManagementSystem aManagementSystemForCourse)
  {
    course = new Course(aSemesterForCourse, aCourseCoudeForCourse, aNumTutorialForCourse, aNumLabForCourse, aNumStudentForCourse, aCreditForCourse, aHourRequiredTaForCourse, aHourRequiredGraderForCourse, aBudgetCalculatedForCourse, this, aInstructorForCourse, aManagementSystemForCourse);
    applicants = new ArrayList<Applicant>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public Course getCourse()
  {
    return course;
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

  public static int minimumNumberOfApplicants()
  {
    return 0;
  }

  public Applicant addApplicant(int aStudentID, String aName, String aPreviousExperience, boolean aIsUnderGraduated, String aPreferredCourse, String aMajor, int aYear, String aOption1, String aOption2, String aOption3, int aTotalAppointmentHours, ManagementSystem aManagementSystem)
  {
    return new Applicant(aStudentID, aName, aPreviousExperience, aIsUnderGraduated, aPreferredCourse, aMajor, aYear, aOption1, aOption2, aOption3, aTotalAppointmentHours, this, aManagementSystem);
  }

  public boolean addApplicant(Applicant aApplicant)
  {
    boolean wasAdded = false;
    if (applicants.contains(aApplicant)) { return false; }
    Allocation existingAllocation = aApplicant.getAllocation();
    boolean isNewAllocation = existingAllocation != null && !this.equals(existingAllocation);
    if (isNewAllocation)
    {
      aApplicant.setAllocation(this);
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
    //Unable to remove aApplicant, as it must always have a allocation
    if (!this.equals(aApplicant.getAllocation()))
    {
      applicants.remove(aApplicant);
      wasRemoved = true;
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
    Course existingCourse = course;
    course = null;
    if (existingCourse != null)
    {
      existingCourse.delete();
    }
    for(int i=applicants.size(); i > 0; i--)
    {
      Applicant aApplicant = applicants.get(i - 1);
      aApplicant.delete();
    }
  }

}