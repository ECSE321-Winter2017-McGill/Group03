<?php
require_once '../controller/Controller.php';
session_start ();
$c = new Controller ();
$_SESSION ["errorJobTitle"] = "";
$_SESSION ["errorDeadLine"] = "";
$_SESSION ["errorPerferredExperience"] = "";
$_SESSION ["errorNumberNeeded"] = "";
$_SESSION ["errorHourlyRate"] = "";
$_SESSION ["errorCourse"] = "";

try {
	$c->createJobPosting ( $_POST ['jobTitle'], $_POST ['deadLine'], $_POST ['perferredExperience'], $_POST ['numberNeeded'], $_POST ['hourlyRate'], $_POST ['courses'] );
}
catch ( Exception $e ) {
	$errors = explode ( "@", $e->getMessage () );
	foreach ( $errors as $error ) {
		if (substr ( $error, 0, 1 ) == "1") {
			$_SESSION ["errorJobTitle"] = substr ( $error, 1 );
		}
		if (substr ( $error, 0, 1 ) == "2") {
			$_SESSION ["errorDeadLine"] = substr ( $error, 1 );
		}
		if (substr ( $error, 0, 1 ) == "3") {
			$_SESSION ["errorPerferredExperience"] = substr ( $error, 1 );
		}
		if (substr ( $error, 0, 1 ) == "4") {
			$_SESSION ["errorNumberNeeded"] = substr ( $error, 1 );
		}
		if (substr ( $error, 0, 1 ) == "5") {
			$_SESSION ["errorHourlyRate"] = substr ( $error, 1 );
		}
		if (substr ( $error, 0, 1 ) == "6") {
			$_SESSION ["errorCourse"] = substr ( $error, 1 );
		}
	}
}
?>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="refresh" content="0; url=jobPostings.php" />
</head>
<body>
</body>
</html>