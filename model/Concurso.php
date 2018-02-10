<?php
    class Concurso{
        private $orgao;
        private $edital;
        private $codConcurso;
        private $listaDeVagas;

        function __construct($orgao, $edital, $codConcurso, $listaDeVagas){
            $this->orgao = $orgao;
            $this->edital = $edital;
            $this->codConcurso = $codConcurso;
            $this->listaDeVagas = $listaDeVagas;
        }

        function getOrgao(){
            return $this->orgao;
        }
        
        function getEdital(){
            return $this->edital;
        }

        function getCodConcurso(){
            return $this->codConcurso;
        }

        function getListaDeVagasString(){
            $stringAux = substr($this->listaDeVagas, 1, strlen($this->listaDeVagas)-2);
            return $stringAux;
        }

        function getListaDeVagas(){
            $stringAux = substr($this->listaDeVagas, 1, strlen($this->listaDeVagas)-2);
            $list = explode(", ", $stringAux);
            return $list;
        }
    }