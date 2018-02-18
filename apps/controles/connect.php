<?php
/**
 * conexão procedural com o banco de dados postgresql.
 * é necesspario instalar o php7.0-pgsql para usalo.
 */
function conecta($query){
    /**
     * string de conexão, altere os dados do banco aqui 
     * host endereco no qual irá se conectar
     * port no padrão do postgres é 5432
     * user é o usuario configurado em seu postgre
     * password altere para sua senha padrão
    */
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

