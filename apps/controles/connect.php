<?php

function conecta($query){
    $con_string = "host=localhost port=5432 dbname=concurso user=postgres password=123123";
    $conn = pg_connect($con_string);
    if (!$conn) {
        die("Connection failed: " . $conn);
        pg_close($conn);
    } 
    $result = pg_query($conn, $query);
    pg_close($conn);
    return $result;
}

