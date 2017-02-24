<?php
//This is a database manager, currentlly still in develpoment
class DBM{
    function __construct(){
        define('DB_SERVER', 'test.cabyhhnybi2l.us-west-2.rds.amazonaws.com');
        define('DB_USERNAME', 'wzs1234566');
        define('DB_PASSWORD', 'yuwen120');
        define('DB_DATABASE', 'test');
    }

    function updateDB(){
        $db = mysqli_connect(DB_SERVER,DB_USERNAME,DB_PASSWORD,DB_DATABASE);        
        $filename = 'DBMdata.txt';
        $str = file_get_contents($filename);
        $error=null;
        // Check connection
        if ($db->connect_error) {
            echo "Connection failed: " ;
        } 
        $sql = "update object set object= '$str' where id=1";
        $db->query($sql);
}

    function downloadDB(){
        $filename = 'DBMdata.txt';
        $db = mysqli_connect(DB_SERVER,DB_USERNAME,DB_PASSWORD,DB_DATABASE);        
        $sql = "SELECT * from object where id=1";
        $result = $db->query($sql);
        $str="";
        if ($result->num_rows > 0) {
            while($row = $result->fetch_assoc()) {
                $str= $row['object'];
            }
        } else {
            echo "0 results";
        }
        file_put_contents($filename, $str);
    }
}
?>