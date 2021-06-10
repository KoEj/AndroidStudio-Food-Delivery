<?php
require "connection.php";
$username = $_POST["username"];
$password= $_POST["password"];
// $username = "1";
// $password= "przyklad";

$check_password = "SELECT * FROM PRACOWNIK WHERE idPRACOWNIK LIKE '$username' ";

$result = mysqli_query($connection,$check_password);
$row = mysqli_fetch_array($result,MYSQLI_ASSOC);
$hash = $row['HASLO'];

if(password_verify($password,$hash)) {
    echo "login success";
} else {
    echo "login not success";
}
?>