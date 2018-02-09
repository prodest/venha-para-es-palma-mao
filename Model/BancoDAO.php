<?php
    class BancoDAO{

        private $HOST = "sql10.freesqldatabase.com:3306";
        private $USERNAME = "sql10219733";
        private $PASSWORD = "LkR4dH66iL";
        private $DATABASE = "sql10219733";
        private $connection;

        function __construct(){
            $this->connection = mysqli_connect($this->HOST, $this->USERNAME, $this->PASSWORD, $this->DATABASE);
        }

        public function executeQuery($sql){
            $result = mysqli_query($this->connection, $sql);
            //$table = Array();
            while ($line = mysqli_fetch_assoc($result)){
                echo $line['cod_concurso']."<br>";

            }

        }
    }


?>