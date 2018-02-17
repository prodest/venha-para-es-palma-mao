<?php

class conecta{
    private $conn;

    public function __construct() {
        $this->conecta();
    }

     function getResult(string $sql) {
    
        $this->conecta();
        $result = $this->conn->query($sql);
        $this->conn->close();
        return $result;
 
    }

     function setQuery(string $sql) {
        $this->conecta();
        if ($this->conn->query($sql)) {
            $this->conn->close();
            return true;
        } else {
            $this->conn->close();
            return true;
        }
    }
 function conecta(){
        $servername = "localhost";
        $username = "postgres";
        $password = "123123";
        $dbname = "concurso";

        $con_string = "host=localhost port=5432 dbname=concurso user=postgres password=123123";
        $conn = pg_connect($con_string);
        //$this->conn = new pg_connect($servername, $username, $password, $dbname);
        // Check connection
        if ($conn->connect_error) {
            die("Connection failed: " . $conn->connect_error);
        }
        echo "Connected successfully";    
    }
    }