/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse321.TAMAS;
import java.util.*;

// line 17 "../../../../TAMAS.ump"
public class Instructor
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Instructor Attributes
  private String name;

  //Instructor Associations
  private ManagementSystem managementSystem;
  private List<Course> courses;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Instructor(String aName, ManagementSystem aManagementSystem)
  {
    name = aName;
    if (aManagementSystem == null || aManagementSystem.getInstructors() != null)
    {
      throw new RuntimeException("Unable to create Instructor due to aManagementSystem");
    }
    managementSystem = aManagementSystem;
    courses = new ArrayList<Course>();
  }

  public Instructor(String aName)
  {
    name = aName;
    managementSystem = new ManagementSystem(this);
    courses = new ArrayList<Course>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public ManagementSystem getManagementSystem()
  {
    return managementSystem;
  }

  public Course getCourse(int index)
  {
    Course aCourse = courses.get(index);
    return aCourse;
  }

  public List<Course> getCourses()
  {
    List<Course> newCourses = Collections.unmodifiableList(courses);
    return newCourses;
  }

  public int numberOfCourses()
  {
    int number = courses.size();
    return number;
  }

  public boolean hasCourses()
  {
    boolean has = courses.size() > 0;
    return has;
  }

  public int indexOfCourse(Course aCourse)
  {
    int index = courses.indexOf(aCourse);
    return index;
  }

  public static int minimumNumberOfCourses()
  {
    return 0;
  }

  public Course addCourse(String aSemester, String aCourseCoude, int aNumTutorial, int aNumLab, int aNumStudent, int aCredit, int aHourRequiredTa, int aHourRequiredGrader, double aBudgetCalculated, Allocation aCourseJobAllocation, ManagementSystem aManagementSystem)
  {
    return new Course(aSemester, aCourseCoude, aNumTutorial, aNumLab, aNumStudent, aCredit, aHourRequiredTa, aHourRequiredGrader, aBudgetCalculated, aCourseJobAllocation, this, aManagementSystem);
  }

  public boolean addCourse(Course aCourse)
  {
    boolean wasAdded = false;
    if (courses.contains(aCourse)) { return false; }
    Instructor existingInstructor = aCourse.getInstructor();
    boolean isNewInstructor = existingInstructor != null && !this.equals(existingInstructor);
    if (isNewInstructor)
    {
      aCourse.setInstructor(this);
    }
    else
    {
      courses.add(aCourse);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeCourse(Course aCourse)
  {
    boolean wasRemoved = false;
    //Unable to remove aCourse, as it must always have a instructor
    if (!this.equals(aCourse.getInstructor()))
    {
      courses.remove(aCourse);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addCourseAt(Course aCourse, int index)
  {  
    boolean wasAdded = false;
    if(addCourse(aCourse))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCourses()) { index = numberOfCourses() - 1; }
      courses.remove(aCourse);
      courses.add(index, aCourse);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveCourseAt(Course aCourse, int index)
  {
    boolean wasAdded = false;
    if(courses.contains(aCourse))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCourses()) { index = numberOfCourses() - 1; }
      courses.remove(aCourse);
      courses.add(index, aCourse);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addCourseAt(aCourse, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    ManagementSystem existingManagementSystem = managementSystem;
    managementSystem = null;
    if (existingManagementSystem != null)
    {
      existingManagementSystem.delete();
    }
    for(int i=courses.size(); i > 0; i--)
    {
      Course aCourse = courses.get(i - 1);
      aCourse.delete();
    }
  }


  public String toString()
  {
    String outputString = "";
    return super.toString() + "["+
            "name" + ":" + getName()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "managementSystem = "+(getManagementSystem()!=null?Integer.toHexString(System.identityHashCode(getManagementSystem())):"null")
     + outputString;
  }
}