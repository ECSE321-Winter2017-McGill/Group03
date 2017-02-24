<?php
  
?>
<!DOCTYPE html>
<html>
<head>
<?php
 include("Config.php");
   session_start();
   $error=null;
// Check connection
if ($db->connect_error) {
    die("Connection failed: " . $db->connect_error);
} 
$sql = "USE test";
$result = $db->query($sql);
$sql = "SELECT * from login";
$result = $db->query($sql);

if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
        echo "id: " . $row["ID"]. " - Name: " . $row["Name"] . $row["Password"]."<br>";
    }
} else {
    echo "0 results";
}
$db->close();
?>
<!--<meta http-equiv="refresh" content="3; url=index.php" />-->
</head>
</html>
