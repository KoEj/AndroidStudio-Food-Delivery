<?php
require "connection.php";
$ID = $_POST["ID"]; 
// $ID = "21";
$date = $_POST["date"];
// $date = "0205-12-14";//;
//$password= $_POST["password"];

$checking = "SELECT * FROM ZMIANA WHERE idPRACOWNIK LIKE '$ID' 
            and `DATA` LIKE '$date' ";

$resulte = mysqli_query($connection,$checking);

if(mysqli_num_rows($resulte) > 0) {
    echo "Ok";
    $time_start = "SELECT `GODZINA ROZPOCZECIA` FROM ZMIANA WHERE idPRACOWNIK LIKE '$ID' 
                    and `DATA` LIKE '$date' ";
    $time_end = "SELECT `GODZINA ZAKONCZENIA` FROM ZMIANA WHERE idPRACOWNIK LIKE '$ID' 
                    and `DATA` LIKE '$date' ";
    $position = "SELECT `STANOWISKO` FROM ZMIANA WHERE idPRACOWNIK LIKE '$ID' 
                    and `DATA` LIKE '$date' ";  

    $set_time_start = mysqli_query($connection,$time_start);
    $set_time_end = mysqli_query($connection,$time_end);
    $set_position = mysqli_query($connection,$position);

    $string_time_start = $set_time_start ->fetch_array()[0] ?? '';
    $string_time_end = $set_time_end ->fetch_array()[0] ?? '';
    $string_position = $set_position ->fetch_array()[0] ?? '';

    echo "#Zmiana: ".$string_time_start;
    echo " - ".$string_time_end.PHP_EOL;
    echo "Stanowisko: ".$string_position.PHP_EOL;
    echo "Data: ".$date.PHP_EOL;

} else {
    echo "#brak wyniku w bazie";
}
?>