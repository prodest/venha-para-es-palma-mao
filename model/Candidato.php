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

        function getCpf(){
            return $this->cpf;
        }

        function getNome(){
            return $this->nome;
        }

        function getDataNascimento(){
            return $this->dataNascimento;
        }

        function getLProfissoesString(){
            $stringAux = substr($this->profissoes, 1, strlen($this->profissoes)-2);
            return $stringAux;
        }

        function getProfissoes(){
            $stringAux = substr($this->profissoes, 1, strlen($this->profissoes)-2);
            $list = explode(", ", $stringAux);
            return $list;
        }

    }