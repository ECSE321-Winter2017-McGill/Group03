/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse321.TAMAS.model;

// line 140 "../../../../../TAMAS.ump"
public class TAJob extends OfferedJob
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TAJob Attributes
  private int appointmentHour;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TAJob(String aOfferDescription, Course aCourse, Applicant aApplicant, int aAppointmentHour)
  {
    super(aOfferDescription, aCourse, aApplicant);
    appointmentHour = aAppointmentHour;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setAppointmentHour(int aAppointmentHour)
  {
    boolean wasSet = false;
    appointmentHour = aAppointmentHour;
    wasSet = true;
    return wasSet;
  }

  public int getAppointmentHour()
  {
    return appointmentHour;
  }

  public void delete()
  {
    super.delete();
  }


  public String toString()
  {
    String outputString = "";
    return super.toString() + "["+
            "appointmentHour" + ":" + getAppointmentHour()+ "]"
     + outputString;
  }
}