<?php
require_once '../controller/Controller.php';
session_start ();
$c = new Controller (); // create a new controller
$_SESSION ["errorEvaluation"] = "";
$flag = 0;
try { // try create a job posting
	$c->writeEval ( $_POST ['TAs'], $_POST ['evaluation'] );
} catch ( Exception $e ) { // catch exceptions
	$flag = 1;
	$errors = explode ( "@", $e->getMessage () );
	foreach ( $errors as $error ) {
		if (substr ( $error, 0, 1 ) == "1") {
			$_SESSION ["errorEvaluation"] = substr ( $error, 1 );
		}
	}
}
if ($flag == 0)
	echo "success";
?>
