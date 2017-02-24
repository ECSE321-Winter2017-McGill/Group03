<?php
// this is configs for AWS login database

// DO NOT EDIT OR INSERT ANYTHING IN THIS DATABASE

   define('DB_SERVER', 'test.cabyhhnybi2l.us-west-2.rds.amazonaws.com');
   define('DB_USERNAME', 'wzs1234566');
   define('DB_PASSWORD', 'yuwen120');
   define('DB_DATABASE', 'test');
   $db = mysqli_connect(DB_SERVER,DB_USERNAME,DB_PASSWORD,DB_DATABASE);
?>