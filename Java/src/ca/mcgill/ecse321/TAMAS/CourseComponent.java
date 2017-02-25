/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse321.TAMAS;

// line 55 "../../../../TAMAS.ump"
public class CourseComponent
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //CourseComponent Attributes
  private String hour;
  private String sessionCode;

  //CourseComponent State Machines
  public enum Component { TUTORIAL, LAB }
  private Component component;

  //CourseComponent Associations
  private Course course;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public CourseComponent(String aHour, String aSessionCode, Course aCourse)
  {
    hour = aHour;
    sessionCode = aSessionCode;
    boolean didAddCourse = setCourse(aCourse);
    if (!didAddCourse)
    {
      throw new RuntimeException("Unable to create courseComponent due to course");
    }
    setComponent(Component.TUTORIAL);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setHour(String aHour)
  {
    boolean wasSet = false;
    hour = aHour;
    wasSet = true;
    return wasSet;
  }

  public boolean setSessionCode(String aSessionCode)
  {
    boolean wasSet = false;
    sessionCode = aSessionCode;
    wasSet = true;
    return wasSet;
  }

  public String getHour()
  {
    return hour;
  }

  public String getSessionCode()
  {
    return sessionCode;
  }

  public String getComponentFullName()
  {
    String answer = component.toString();
    return answer;
  }

  public Component getComponent()
  {
    return component;
  }

  public boolean setComponent(Component aComponent)
  {
    component = aComponent;
    return true;
  }

  public Course getCourse()
  {
    return course;
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
      existingCourse.removeCourseComponent(this);
    }
    course.addCourseComponent(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Course placeholderCourse = course;
    this.course = null;
    placeholderCourse.removeCourseComponent(this);
  }


  public String toString()
  {
    String outputString = "";
    return super.toString() + "["+
            "hour" + ":" + getHour()+ "," +
            "sessionCode" + ":" + getSessionCode()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "course = "+(getCourse()!=null?Integer.toHexString(System.identityHashCode(getCourse())):"null")
     + outputString;
  }
}