<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

class TAJob extends OfferedJob
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TAJob Attributes
  private $appointmentHour;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aOfferDescription, $aCourse, $aApplicant, $aAppointmentHour)
  {
    parent::__construct($aOfferDescription, $aCourse, $aApplicant);
    $this->appointmentHour = $aAppointmentHour;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function setAppointmentHour($aAppointmentHour)
  {
    $wasSet = false;
    $this->appointmentHour = $aAppointmentHour;
    $wasSet = true;
    return $wasSet;
  }

  public function getAppointmentHour()
  {
    return $this->appointmentHour;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    parent::delete();
  }

}
?>