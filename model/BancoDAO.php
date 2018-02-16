<?php

    /*Classe que realiza conexao e iteração com banco de dados */
    class BancoDAO{
        /* Para mudar de banco de dados basta apenas mudar os dados */
        private $HOST = "den1.mysql6.gear.host:3306";
        private $USERNAME = "prodest";
        private $PASSWORD = "Uy1i1-10B3-7";
        private $DATABASE = "prodest";
        private $connection;

        function __construct(){
            $this->connection = mysqli_connect($this->HOST, $this->USERNAME, $this->PASSWORD, $this->DATABASE);
        }
        /*RETURN: Um dicionário com os dados do banco de dados*/
        public function executeQuery($sql){
            $result = mysqli_query($this->connection, $sql);
            $arr = Array();
            while ($line = mysqli_fetch_assoc($result)){
                $arr[] = $line;
            }
        if(count($arr) == 1){
                return $arr[0];
            }
            return $arr;
         }
    } 
