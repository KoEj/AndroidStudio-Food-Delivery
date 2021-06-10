<?php
require "connection.php";
// $first_name = "Jan";
// $second_name = "Kowalski";
// $ID = "1";
// $new_pswd = "przyklad";
// $new_pswd_2 = "przyklad";

$first_name = $_POST["first_name"];
$second_name = $_POST["second_name"];
$ID = $_POST["ID"];
$new_pswd = $_POST["new_pswd"];
$new_pswd_2 = $_POST["new_pswd_2"];


$check = "SELECT * FROM PRACOWNIK WHERE 
        IMIE LIKE '$first_name' and 
        NAZWISKO LIKE '$second_name' and    
        idPRACOWNIK LIKE '$ID' ";

$result = mysqli_query($connection,$check);

if(mysqli_num_rows($result) > 0) {
    if ($new_pswd == $new_pswd_2) {
        $passwordhash = password_hash($new_pswd,PASSWORD_DEFAULT);
        $update = "UPDATE PRACOWNIK SET HASLO = '$passwordhash' 
                    WHERE idPRACOWNIK LIKE '$ID' ";
        mysqli_query($connection,$update);
        echo "password changed";
    } else {
        echo "password not changed";
    }
} else {
    echo "invaild data";
}
?>