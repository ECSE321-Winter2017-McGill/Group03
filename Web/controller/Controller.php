<?php
require_once __DIR__ . '\..\model\ManagementSystem.php';
require_once __DIR__ . '\..\model\Allocation.php';
require_once __DIR__ . '\..\persistence\PersistenceTAMAS.php';
require_once __DIR__ . '\..\model\JobPosting.php';
require_once __DIR__ . '\..\model\Course.php';
require_once __DIR__ . '\..\model\Instructor.php';
require_once __DIR__ . '\..\controller\InputValidator.php';
class Controller {
	public function __construct() {
	}
	public function createJobPosting($jobTitle, $deadLine, $perferredExperience, $numNeeded, $hourlyRate, $course) {
		$error = ""; // empty errors.
		$jt = InputValidator::validate_input ( $jobTitle );
		$dl = InputValidator::validate_input ( $deadLine );
		$pe = InputValidator::validate_input ( $perferredExperience );
		$nn = InputValidator::validate_input ( $numNeeded );
		$hr = InputValidator::validate_input ( $hourlyRate );
		$c = InputValidator::validate_input ( $course ); // validation check
		
		if ($jt == null || strlen ( $jt ) == 0) { // errors
			$error .= "@1job title cannot be empty! ";
		}
		if ($dl == null || strlen ( $dl ) == 0) {
			$error .= "@2dead line must be specified correctly (YYYY-MM-DD)! ";
		}
		if ($pe == null || strlen ( $pe ) == 0) {
			$error .= "@3perferred experience cannot be empty! ";
		}
		if ($nn == null || strlen ( $nn ) == 0) {
			$error .= "@4num needed cannot be empty! ";
		}
		if ($hr == null || strlen ( $hr ) == 0) {
			$error .= "@5hourly rate cannot be empty! ";
		}
		if ($c == null || strlen ( $c ) == 0) {
			$error .= "@6course cannot be empty! ";
		}
		if (strlen ( $error ) > 0) {
			throw new Exception ( trim ( $error ) );
		} else {
			try {
				$pm = new PersistenceTAMAS (); // new persistence manager
				$rm = $pm->loadDataFromStore (); // get TAMAS management system
				$co = new Course ( "winter 2017", $c, 1, 1, 100, 60, 60, 60, 100.00, new Allocation (), new Instructor ( "test", $rm ), $rm ); // create a fake course
				$jp = new JobPosting ( $jt, $dl, $pe, $nn, $hr, $rm, $co ); // create a jobposting
				$rm->addJobPosting ( $jp ); // add job posting
				$pm->writeDataToStore ( $rm ); // store data to file
			} catch ( Exception $e ) {
				throw new Exception ( trim ( $error ) ); // throw any exceptions
			}
		}
	}
}
