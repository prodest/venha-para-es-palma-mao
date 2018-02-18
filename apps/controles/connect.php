<?php
/**
 * conexão simples com o banco de dados postgresql
 */
function conecta($query){
    /**string de conexão, altere os dados do banco aqui */
    $con_string = " host=localhost 
                    port=5432 
                    dbname=concurso 
                    user=postgres 
                    password=123123";
                    
    $conn = pg_connect($con_string);
    if (!$conn) {
        die("Connection failed: " . $conn);
    } 
    $result = pg_query($conn, $query);
    pg_close($conn);
    return $result;
}

