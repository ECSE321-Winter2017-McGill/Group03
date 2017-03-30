/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse321.TAMAS.model;
import java.util.*;

// line 160 "../../../../../TAMAS.ump"
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
    boolean didAddCourse = setCourse(aCourse);
    if (!didAddCourse)
    {
      throw new RuntimeException("Unable to create courseJobAllocation due to course");
    }
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

  public boolean setCourse(Course aNewCourse)
  {
    boolean wasSet = false;
    if (aNewCourse == null)
    {
      //Unable to setCourse to null, as courseJobAllocation must always be associated to a course
      return wasSet;
    }
    
    Allocation existingCourseJobAllocation = aNewCourse.getCourseJobAllocation();
    if (existingCourseJobAllocation != null && !equals(existingCourseJobAllocation))
    {
      //Unable to setCourse, the current course already has a courseJobAllocation, which would be orphaned if it were re-assigned
      return wasSet;
    }
    
    Course anOldCourse = course;
    course = aNewCourse;
    course.setCourseJobAllocation(this);

    if (anOldCourse != null)
    {
      anOldCourse.setCourseJobAllocation(null);
    }
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
    if (aApplicant.indexOfAllocation(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aApplicant.addAllocation(this);
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
    if (aApplicant.indexOfAllocation(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aApplicant.removeAllocation(this);
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
    Course existingCourse = course;
    course = null;
    if (existingCourse != null)
    {
      existingCourse.setCourseJobAllocation(null);
    }
    ArrayList<Applicant> copyOfApplicants = new ArrayList<Applicant>(applicants);
    applicants.clear();
    for(Applicant aApplicant : copyOfApplicants)
    {
      aApplicant.removeAllocation(this);
    }
  }

}