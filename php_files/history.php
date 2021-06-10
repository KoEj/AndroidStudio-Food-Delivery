<?php
require "connection.php";
$ID = $_POST['ID'];
//$ID = "50";

$order = "SELECT idZAMOWIENIE FROM `PRZYDZIAL ZAMOWIENIA` 
            WHERE idPRACOWNIK LIKE '$ID' ";

$result = mysqli_query($connection,$order);
$N = mysqli_num_rows($result);

if($N > 0) {
        echo "#Wyszukano#".$N;

        $i=0;
        while($res = mysqli_fetch_array($result)) {
            $table[$i++]=$res;
        }
        //print_r($table);

        for ($i = 0; $i < $N; $i++) {
            echo "#";
            echo $rows = $table[$i]['idZAMOWIENIE']; 
            echo "%"; 
            $find = "SELECT `STATUS`,`RODZAJ PLATNOSCI`,`idKLIENT` FROM ZAMOWIENIE WHERE idZAMOWIENIE LIKE '$rows' ";
            $find_cn = mysqli_query($connection,$find);
            $string_status = $find_cn ->fetch_array()[0] ?? '';
            $find_cn = mysqli_query($connection,$find);
            $string_payment = $find_cn ->fetch_array()[1] ?? '';
            $find_cn = mysqli_query($connection,$find);
            $string_ID = $find_cn ->fetch_array()[2] ?? '';

            $find_2 = "SELECT `IMIE`,`NAZWISKO` FROM KLIENT WHERE idKLIENT LIKE '$string_ID' ";
            $find_2cn = mysqli_query($connection,$find_2);
            $string_fname = $find_2cn ->fetch_array()[0] ?? '';
            $find_2cn = mysqli_query($connection,$find_2);
            $string_sname = $find_2cn ->fetch_array()[1] ?? '';

            echo $string_status." ".$string_payment." ".$string_fname." ".$string_sname;
        }
        

        // $set_status = mysqli_query($connection,$status);
        
        // $idKLIENT = "SELECT * FROM KLIENT WHERE idKLIENT LIKE
        //     (SELECT idKLIENT FROM ZAMOWIENIE WHERE idZAMOWIENIE LIKE ($order)) ";
        // $set_klient = mysqli_query($connection,$idKLIENT);

        // $idADRES = "SELECT * FROM ADRES WHERE idADRES LIKE 
        //     (SELECT idADRES FROM ZAMOWIENIE WHERE idZAMOWIENIE LIKE ($order)) ";
        // $set_adres = mysqli_query($connection,$idADRES);

        // $adresLOKALU = "SELECT `ADRES LOKALU` FROM ZAMOWIENIE WHERE idZAMOWIENIE 
        //     LIKE ($order) ";
        // $set_lokal = mysqli_query($connection,$adresLOKALU);

        // $rodzajPLATNOSCI = "SELECT `RODZAJ PLATNOSCI` FROM ZAMOWIENIE 
        //     WHERE idZAMOWIENIE LIKE ($order) ";
        // $set_platnosc = mysqli_query($connection,$rodzajPLATNOSCI);

        
        // 
        // $string_set_status   = $set_status  ->fetch_array()[0] ?? '';
        // //$string_set_klient   = $set_IDklient  ->fetch_array()[0] ?? '';
        // $string_set_lokal    = $set_lokal   ->fetch_array()[0] ?? '';
        // $string_set_platnosc = $set_platnosc->fetch_array()[0] ?? '';

        // echo ''.PHP_EOL.'#';
        // foreach($set_adres as $row_adres => $value) {
        //     echo 'Adres: '.implode(" ",$value);
        // }
        //echo ''.PHP_EOL;
        //echo '#Lokal: '.$string_set_lokal.PHP_EOL;
        //echo '#Platnosc: '.$string_set_platnosc.PHP_EOL;
        //echo '#'.$string_result;
        
} else {
    echo "#Nie znaleziono";
}

?>