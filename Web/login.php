<?php
include("Config.php");
session_start();
$error=NULL;

if($_SERVER["REQUEST_METHOD"] == "GET") {
	// username and password sent from form

	$sql = "SELECT * FROM xml WHERE data='data'";
	$result = mysqli_query($db,$sql);
	//$row = mysqli_fetch_array($result,MYSQLI_ASSOC);

	$row = mysqli_fetch_array($result);
	$dataa = $row[1];
}
	echo $dataa;
?>
