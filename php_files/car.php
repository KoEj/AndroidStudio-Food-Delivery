<?php
require "connection.php";
$ID = $_POST["ID"];

$order = "SELECT * FROM `PRZYDZIAL SAMOCHODU` 
            WHERE idPRACOWNIK LIKE '$ID' ";

$result = mysqli_query($connection,$order);

if(mysqli_num_rows($result) > 0) {
    echo "Ok";
    //$today_date = "SELECT  AS Today";  

    //poprawna wersja, dla testow uzyto zlej
    //$next_date = "SELECT MIN(DATA) FROM `PRZYDZIAL SAMOCHODU` WHERE idPRACOWNIK 
    //            LIKE '$ID' AND `DATA` >= CURDATE() ";

    //testowe!
    $next_date = "SELECT MAX(DATA) FROM `PRZYDZIAL SAMOCHODU` WHERE idPRACOWNIK 
                LIKE '$ID' AND `DATA` < CURDATE() ";
        $next_date_check = mysqli_query($connection,$next_date);
    if($next_date_check != NULL) {
        //najblizsza zmiana
        $car = "SELECT * FROM `PRZYDZIAL SAMOCHODU` WHERE 
            idPRACOWNIK LIKE '$ID' AND `DATA` LIKE ($next_date)";

        $result_car = mysqli_query($connection,$car);

        if(mysqli_num_rows($result_car) > 0) {
            echo "Ok";
            $idCar = "SELECT idSAMOCHOD FROM `PRZYDZIAL SAMOCHODU` WHERE 
                        idPRACOWNIK LIKE '$ID' AND `DATA` LIKE ($next_date)";
            $set_idCar = mysqli_query($connection,$idCar);
            //$set_idCar       = $set_idCar     ->FETCH_ARRAY()[0] ?? '';
            //echo "".$string_idCar;

            $time_start = "SELECT `GODZINA ROZPOCZECIA` FROM `PRZYDZIAL SAMOCHODU` WHERE idPRACOWNIK LIKE '$ID' AND `DATA` LIKE ($next_date)";
            $time_end = "SELECT `GODZINA ZAKONCZENIA` FROM `PRZYDZIAL SAMOCHODU` WHERE idPRACOWNIK LIKE '$ID' AND `DATA` LIKE ($next_date)";
            $date = "SELECT `DATA` FROM `PRZYDZIAL SAMOCHODU` WHERE idPRACOWNIK LIKE '$ID' AND `DATA` LIKE ($next_date)";
            
            $set_time_start = mysqli_query($connection,$time_start);
            $set_time_end = mysqli_query($connection,$time_end);
            $set_date = mysqli_query($connection,$date);
            
            $string_time_start = $set_time_start ->fetch_array()[0] ?? '';
            $string_time_end = $set_time_end ->fetch_array()[0] ?? '';
            $string_date = $set_date ->fetch_array()[0] ?? '';
            
            echo "#Godzina rozpoczecia: ".$string_time_start.PHP_EOL;
            echo "%Godzina zakonczenia: ".$string_time_end.PHP_EOL;
            echo "%Data wynajmu: ".$string_date.PHP_EOL;
            
            $car_rej = "SELECT `NUMER REJESTRACYJNY` FROM SAMOCHOD WHERE idSAMOCHOD LIKE ($idCar)";
            $car_vin = "SELECT `NUMER VIN` FROM SAMOCHOD WHERE idSAMOCHOD LIKE ($idCar)";
            $car_paliwo = "SELECT `RODZAJ PALIWA` FROM SAMOCHOD WHERE idSAMOCHOD LIKE ($idCar)";
            $car_km = "SELECT `LICZBA PRZEJECHANYCH KILOMETROW` FROM SAMOCHOD WHERE idSAMOCHOD LIKE ($idCar)";
            $car_serwis = "SELECT `SL_SERWIS` FROM SAMOCHOD WHERE idSAMOCHOD LIKE ($idCar)";

            $set_car_rej = mysqli_query($connection,$car_rej);
            $set_car_vin = mysqli_query($connection,$car_vin);
            $set_car_paliwo = mysqli_query($connection,$car_paliwo);
            $set_car_km = mysqli_query($connection,$car_km);
            $set_car_serwis = mysqli_query($connection,$car_serwis);
            
            $string_car_rej = $set_car_rej ->fetch_array()[0] ?? '';
            $string_car_vin = $set_car_vin ->fetch_array()[0] ?? '';
            $string_car_paliwo = $set_car_paliwo ->fetch_array()[0] ?? '';
            $string_car_km = $set_car_km ->fetch_array()[0] ?? '';
            $string_car_serwis = $set_car_serwis ->fetch_array()[0] ?? '';

            echo "#Nr rejestracyjny: ".$string_car_rej.PHP_EOL;
            echo "%Nr VIN: ".$string_car_vin.PHP_EOL;
            echo "%Rodzaj paliwa: ".$string_car_paliwo.PHP_EOL;
            echo "%Stan licznika: ".$string_car_km.PHP_EOL;
            echo "%Serwis auta: ".$string_car_serwis.PHP_EOL;

        }
        else {
            echo "Nie znaleziono nastepnej zmiany";
        }

    } else {
        echo "Nie znaleziono najblizszej zmiany";
    }
} else {
    echo "Brak przydzialu";
}
?>