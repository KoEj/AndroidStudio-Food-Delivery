<?php
require "connection.php";
$ID = $_POST['ID'];

$order = "SELECT MAX(idZAMOWIENIE) FROM `PRZYDZIAL ZAMOWIENIA` 
            WHERE idPRACOWNIK LIKE '$ID' ";

$result = mysqli_query($connection,$order);

if(mysqli_num_rows($result) > 0) {
    if ($order != NULL) {
        echo '#Wyszukano'.PHP_EOL;
        $status = "SELECT STATUS FROM ZAMOWIENIE WHERE idZAMOWIENIE 
            LIKE ($order) ";
        $set_status = mysqli_query($connection,$status);
        
        $idKLIENT = "SELECT * FROM KLIENT WHERE idKLIENT LIKE
            (SELECT idKLIENT FROM ZAMOWIENIE WHERE idZAMOWIENIE LIKE ($order)) ";
        $set_klient = mysqli_query($connection,$idKLIENT);

        $idADRES = "SELECT * FROM ADRES WHERE idADRES LIKE 
            (SELECT idADRES FROM ZAMOWIENIE WHERE idZAMOWIENIE LIKE ($order)) ";
        $set_adres = mysqli_query($connection,$idADRES);

        $adresLOKALU = "SELECT `ADRES LOKALU` FROM ZAMOWIENIE WHERE idZAMOWIENIE 
            LIKE ($order) ";
        $set_lokal = mysqli_query($connection,$adresLOKALU);

        $rodzajPLATNOSCI = "SELECT `RODZAJ PLATNOSCI` FROM ZAMOWIENIE 
            WHERE idZAMOWIENIE LIKE ($order) ";
        $set_platnosc = mysqli_query($connection,$rodzajPLATNOSCI);

        
        $string_result       = $result      ->FETCH_ARRAY()[0] ?? '';
        $string_set_status   = $set_status  ->fetch_array()[0] ?? '';
        //$string_set_klient   = $set_IDklient  ->fetch_array()[0] ?? '';
        $string_set_lokal    = $set_lokal   ->fetch_array()[0] ?? '';
        $string_set_platnosc = $set_platnosc->fetch_array()[0] ?? '';

        $row_adres = mysqli_fetch_assoc($set_adres);
        $row_klient = mysqli_fetch_assoc($set_klient);

        echo '#Status: '.$string_set_status.PHP_EOL.'#';
        foreach($set_klient as $row_klient => $value) {
            echo 'Klient: '.implode(" ",$value);
        }
        echo ''.PHP_EOL.'#';
        foreach($set_adres as $row_adres => $value) {
            echo 'Adres: '.implode(" ",$value);
        }
        echo ''.PHP_EOL;
        echo '#Lokal: '.$string_set_lokal.PHP_EOL;
        echo '#Platnosc: '.$string_set_platnosc.PHP_EOL;
        echo '#'.$string_result;
        } else {
         echo "#Brak przydzialu";   
        }
    } else {
    echo "#Nie znaleziono";
}

?>