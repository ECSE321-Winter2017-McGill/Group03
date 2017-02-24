<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

class GraderJob extends OfferedJob
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aDescription, $aCourse, $aApplicant)
  {
    parent::__construct($aDescription, $aCourse, $aApplicant);
  }

  //------------------------
  // INTERFACE
  //------------------------

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