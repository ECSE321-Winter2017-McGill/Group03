<?php
   include("Config.php");
   session_start();
   $error=NULL;
   
   if($_SERVER["REQUEST_METHOD"] == "POST") {
      // username and password sent from form 
      
      $myusername = mysqli_real_escape_string($db,$_POST['username']);
      $mypassword = mysqli_real_escape_string($db,$_POST['password']); 
      
      $sql = "SELECT id FROM login WHERE name = '$myusername' and password = '$mypassword'";
      $result = mysqli_query($db,$sql);
      $row = mysqli_fetch_array($result,MYSQLI_ASSOC);
      
      $count = mysqli_num_rows($result);
      
      // If result matched $myusername and $mypassword, table row must be 1 row
		
      if($count == 1) {
         //session_register("$myusername");
         $_SESSION['login_user'] = $myusername;
         
         header("location: dashboard.php");
      }else {
         $_SESSION ['loginError'] = "Your Login Name or Password is invalid";
        header("location: ../index.php");
      } 
   }
?>
<!DOCTYPE html>
<html>
<head>
<!-- <meta http-equiv="refresh" content="0; url=index.php" /> -->
</head>
</html>