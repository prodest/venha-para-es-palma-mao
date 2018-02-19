<?php

  /*
  * AQUI VOCÊ ENCONTRARÁ FUNÇÕES QUE MANIPULAM STRINGS E ARQUIVOS DE TEXTO.
  *
  * CANDIDATOS.TXT E CONCURSOS.TXT SÃO TRATADOS AQUI.
  * ESTES ARQUIVOS DEVEM ESTAR PADRONIZADOS COMO NO EXEMPLO ABAIXO
  *
  * CANDIDATOS:
  * NOME E/OU SOBRENOME DATA_NASCIMENTO CPF COM PONTOS E TRAÇO [PROFISSOES SEPARADAS POR VIRGULAS]
  * Lindsey Craft       19/05/1976      182.845.084-34         [carpinteiro, professor de matemática]
  *
  * CONCURSOS:
  * ÓRGAO EDITAL CÓDIGO       [VAGAS SEPARADAS POR VÍRGULAS]
  * SEDU  9/2016 61828450843  [carpinteiro, analista de sistemas, marceneiro]
  */

  /*
  * RETORNA O NOME DO CANDIDATO ENCONTRADO NO ARQUIVO TXT
  * INPUT: vetor onde cada posição contém uma palavra de uma linha do txt
  * OUTPUT: string (nome e sobrenome do candidato)
  */
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

  /*
  * RETORNA A DATA DE NASCIMENTO DO CANDIDATO ENCONTRADO NO ARQUIVO TXT
  * INPUT: vetor onde cada posição contém uma palavra de uma linha do txt
  * OUTPUT: string (data de nascimento do candidato)
  */
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

  /*
  * TRANSFORMA UMA DATA NO PADRAO DD/MM/YYYY EM YYYY-MM-DD
  * INPUT: string no formato dd/mm/yyyy
  * OUTPUT: string no formato yyyy-mm-dd
  */
  function data_br2sql($data){
    $dia = $data[0].$data[1];
    $mes = $data[3].$data[4];
    $ano = $data[6].$data[7].$data[8].$data[9];
    $data_sql = $ano."-".$mes."-".$dia;
    return $data_sql;
  }

  /*
  * RETORNA O CPF DO CANDIDATO ENCONTRADO NO ARQUIVO TXT
  * INPUT: vetor onde cada posição contém uma palavra de uma linha do txt
  * OUTPUT: string (CPF do candidato)
  */
  function retorna_cpf($vet){
    foreach ($vet as $palavra) {
      if(is_cpf($palavra)){
        return $palavra;
      }
    }
  }

  /*
  * VERIFICA SE UMA STRING ESTA NO FORMATO DE UM CPF XXX.XXX.XXX-XX
  * INPUT: string
  * OUTPUT: true caso a string esteja no formato XXX.XXX.XXX-XX e false caso contrario
  */
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


  /*
  * RETIRA TODOS OS CARACTERES NAO NUMERICOS DA STRING
  * INPUT: string
  * OUTPUT: string com apenas caracteres numericos
  */
  function somente_numeros($var){
    $numeros = '';

    for ($i=0; $i < strlen($var) ; $i++) {
      if(is_numeric($var[$i])){
        $numeros .= $var[$i];
      }
    }

    return $numeros;
  }


  /*
  * RETORNA PROFISSOES DO CANDIDATO ENCONTRADO NO ARQUIVO TXT
  * INPUT: vetor com todas as palavras de uma linha do txt
  * OUTPUT: string (profissoes do candidato no formato [prof1, prof2])
  */
  function retorna_profissoes($vet){
    $profissoes = "";
    /*utilizei esta flag pois copiei letra por letra para a profissao
    e após encontrar o caracter '[' desejava copiar todas as letras até o final
    */
    $flag = 0;
    foreach ($vet as $palavra) {
      if (!empty($palavra)){
        //após encontrar o caracter que indica o inicio das profissoes
        //seta a flag como um para que se possa copiar todos os outros caracteres
        if($palavra[0]=='[' || $flag == 1){
          $profissoes .= $palavra." ";
          $flag = 1;
        }
      }
    }
    unset($palavra);
    return $profissoes;
  }

  /*
  * RETIRA TODOS OS CARACTERES DIFERENTES DE LETRAS(COM OU SEM ACENTO) NUMEROS OU ESPAÇO
  * INPUT: string
  * OUTPUT: string com apenas caracteres validos
  */
  function retira_caracter_invalido($str){
    $nova_palavra = "";
    $pattern = '/[0-9a-zA-ZÀ-Úà-ú ]+/'; //expressao regular que permite apenas letra numero ou espaço

    for ($i=0; $i < strlen($str); $i++) {
      // verifica se um caracter se enquadra no modelo da expressao regular
      // ou seja, se é letra numero ou espaço
      if(preg_match($pattern, $str[$i])){
        $nova_palavra .= $str[$i];
      }
    }
    return $nova_palavra;
  }

  /*
  * RETIRA ESPAÇO DO INICIO E FINAL DE UMA STRING, CASO EXISTAM
  * INPUT: string
  * OUTPUT: string sem espeços no inicio e final
  */
  function retira_espaco_ini_fim($str){
    $nova_palavra = "";

    for ($i=0; $i < strlen($str); $i++) {
      if(($i == 0 || $i == (strlen($str)-1)) && $str[$i] == ' ' ){
        continue;
      }
      $nova_palavra .= $str[$i];
    }
    return $nova_palavra;
  }


  /*
  * RETORNA O CODIGO DE UM CONCURSO ENCONTRADO NO ARQUIVO TXT
  * PRE-REQUISITO: o código deve ser composto apenas por números
  * INPUT: vetor onde cada posição contém uma palavra de uma linha do txt
  * OUTPUT: string (codigo do concurso)
  */
  function retorna_codigo($vet){
    foreach ($vet as $palavra) {
      if(is_numeric($palavra)){
        return $palavra;
      }
    }
  }
