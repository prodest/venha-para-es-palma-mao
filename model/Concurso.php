<?php

    /*Classe responsavel pelo controle dos Concursos*/
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

        public function getOrgao(){
            return $this->orgao;
        }
        
        public function getEdital(){
            return $this->edital;
        }

        public function getCodigo(){
            return $this->codConcurso;
        }

        /*RETURN: String com as vagas do concurso*/
        public function getListaDeVagasString(){
            $stringAux = substr($this->listaDeVagas, 1, strlen($this->listaDeVagas)-2);
            return $stringAux;
        }

        /*RETURN: Array com as vagas do concurso*/
        public function getListaDeVagas(){
            $stringAux = substr($this->listaDeVagas, 1, strlen($this->listaDeVagas)-2);
            $list = explode(", ", $stringAux);
            return $list;
        }
    }