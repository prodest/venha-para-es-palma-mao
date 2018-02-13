<?php 
    class Candidato{
        private $cpf;
        private $nome;
        private $dataNascimento;
        private $profissoes;

        function __construct($nome, $cpf, $dataNascimento, $profissoes){
            $this->nome = $nome;
            $this->cpf = $cpf;
            $this->dataNascimento = $dataNascimento;
            $this->profissoes = $profissoes;
        }

        public function getCpf(){
            return $this->cpf;
        }

        public function getNome(){
            return $this->nome;
        }

        public function getDataNascimento(){
            return $this->dataNascimento;
        }

        public function getLProfissoesString(){
            $stringAux = substr($this->profissoes, 1, strlen($this->profissoes)-2);
            return $stringAux;
        }

        public function getProfissoes(){
            $stringAux = substr($this->profissoes, 1, strlen($this->profissoes)-2);
            $list = explode(", ", $stringAux);
            return $list;
        }
    }