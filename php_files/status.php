<?php
require "connection.php";
$ID = $_POST["ID"];
$new_status = $_POST["new_status"];

$check = "SELECT * FROM ZAMOWIENIE WHERE 
        idZAMOWIENIE LIKE '$ID' ";

$result = mysqli_query($connection,$check);

if(mysqli_num_rows($result) > 0) {
    if ($new_status == '1') {
        $update = "UPDATE ZAMOWIENIE SET STATUS = 'ODEBRANE' 
                    WHERE idZAMOWIENIE LIKE '$ID' ";
        mysqli_query($connection,$update);
        echo "status changed";
      }
    if ($new_status == '0') {
        $update = "UPDATE ZAMOWIENIE SET STATUS = 'NIE ODEBRANE'
                    WHERE idZAMOWIENIE LIKE '$ID' ";
        mysqli_query($connection,$update);
        echo "status changed";
      }
} else {
    echo "invaild data";
}
?>