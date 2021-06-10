<?php
$database = "mydb";
$server_user = "test";
$server_password = "test";
$host = "192.168.1.42";

$connection = mysqli_connect($host,$server_user,$server_password,$database);
if($connection) {
    echo "Connected\n";
} else {
    echo "Not Connected\n";
}

?>