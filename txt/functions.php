<?php

  function retorna_nome($vet){
    $nome = "";

    foreach ($vet as $palavra) {
      if (!empty($palavra)){
        if(!is_numeric($palavra[0])){
          $nome .= $palavra." ";
        }else{
          break;
        }
      }
    }
    unset($palavra);

    return $nome;
  }

  function retorna_data_nascimento($vet){
    foreach ($vet as $palavra) {
      if (!empty($palavra)){
        if(is_numeric($palavra[0])){
          return $palavra;
        }
      }
    }
    unset($palavra);
  }

  /* precisa receber uma data no formato dd/mm/yyyy */
  function data_br2sql($data){
    $dia = $data[0].$data[1];
    $mes = $data[3].$data[4];
    $ano = $data[6].$data[7].$data[8].$data[9];
    $data_sql = $ano."-".$mes."-".$dia;
    return $data_sql;
  }

  function retorna_cpf($vet){
    foreach ($vet as $palavra) {
      if(is_cpf($palavra)){
        return $palavra;
      }
    }
  }

  /* CPF deve estar no formato xxx.xxx.xxx-xx */
  function is_cpf($cpf){
    //verifica se a string é do tamanho padrao de um cpf
    if(strlen($cpf) == 14){
      for ($i=0; $i < strlen($cpf); $i++) {
        //caso a letra atual nao seja um numero
        if(!is_numeric($cpf[$i])){
          //verifica se nao é ponto
          if($i == 3 || $i == 7 && $cpf != '.'){
            //verifica se nao é traço
            if($i == 11 && $cpf != '-'){
              //não é numero, nem ponto, nem traço na posição padrao, entao nao é cpf
              return false;
            }
          }
        }
      }
    }else{
      //nao tem a quantidade padrao de caracteres
      return false;
    }
    //passou em todos os testes
    return true;
  }

  function somente_numeros($var){
    $numeros = '';

    for ($i=0; $i < strlen($var) ; $i++) {
      if(is_numeric($var[$i])){
        $numeros .= $var[$i];
      }
    }

    return $numeros;
  }

  function retorna_profissoes($vet){
    $profissoes = "";
    $flag = 0;
    foreach ($vet as $palavra) {
      if (!empty($palavra)){
        if($palavra[0]=='[' || $flag == 1){
          $profissoes .= $palavra." ";
          $flag = 1;
        }
      }
    }
    unset($palavra);
    return $profissoes;
  }

  function retira_caracter($str,$caracter){
    $nova_palavra = "";

    for ($i=0; $i < strlen($str); $i++) {
      if($str[$i] != $caracter){
        $nova_palavra .= $str[$i];
      }
    }
    return $nova_palavra;
  }
