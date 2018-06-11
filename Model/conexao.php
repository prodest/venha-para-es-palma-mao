<?php

$servidor = "localhost";
$usuario = "root";
$senha = "";
$dbname = "espalmamao";

try {
    $conn = new mysqli($servidor, $usuario, $senha, $dbname);

} catch (Exception $e) {
    if ($conn->connect_errno) {
        printf("Connect failed: %s\n", $conn->connect_error);
        exit();
    }
    echo 'Exceção capturada: ', $e->getMessage(), "\n";
}
