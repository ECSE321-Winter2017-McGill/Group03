<?php
require_once '../controller/Controller.php';
session_start ();
$c = new Controller (); // create a new controller
$_SESSION ["errorJobTitle"] = "";
$_SESSION ["errorDeadLine"] = "";
$_SESSION ["errorPerferredExperience"] = "";
$_SESSION ["errorNumberNeeded"] = "";
$_SESSION ["errorHourlyRate"] = "";
$_SESSION ["errorCourse"] = ""; // set error messages to empty
$flag=0;
try { // try create a job posting
	$c->createJobPosting ( $_POST ['jobTitle'], $_POST ['deadLine'], $_POST ['perferredExperience'], $_POST ['numberNeeded'], $_POST ['hourlyRate'], $_POST ['courses'] );
}
catch ( Exception $e ) { // catch exceptions
	$flag=1;
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
	<?php

	if ($flag==0)
		echo '<meta http-equiv="refresh" content="0; url=viewAllJObPostings.php" />';
	else
		echo '<meta http-equiv="refresh" content="0; url=jobPostings.php" />';
    ?>

	<!-- <meta http-equiv="refresh" content="0; url=viewAllJObPostings.php" /> -->
</head>
<body>
</body>
</html>