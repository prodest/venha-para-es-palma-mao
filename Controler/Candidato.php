<?php

class Usuario{

private $nome;
private $dataNas;
private $cpf;

function __contruct($nome, $dataNas, $cpf){

    $this->nome = $nome;
    $this->cpf = $cpf;
    $this->dataNas = $dataNas;
}

}
?>