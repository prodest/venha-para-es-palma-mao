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

        public function getOrgao(){
            return $this->orgao;
        }
        
        public function getEdital(){
            return $this->edital;
        }

        public function getCodigo(){
            return $this->codConcurso;
        }

        public function getListaDeVagasString(){
            $stringAux = substr($this->listaDeVagas, 1, strlen($this->listaDeVagas)-2);
            return $stringAux;
        }

        public function getListaDeVagas(){
            $stringAux = substr($this->listaDeVagas, 1, strlen($this->listaDeVagas)-2);
            $list = explode(", ", $stringAux);
            return $list;
        }
    }