<?php

  header("Content-Type: text/html; charset=utf-8",true);
  ini_set('default_charset', 'UTF-8');

  require_once('../includes/mysqli.php');

  require_once('functions.php');
  require_once('functionsSql.php');


  //ABRE O ARQUIVO TXT
  $ponteiro = fopen ("candidatos.txt", "r");

  //LÊ O ARQUIVO ATÉ CHEGAR AO FIM
  while (!feof ($ponteiro)) {
    //LÊ UMA LINHA DO ARQUIVO
    $linha = fgets($ponteiro);
    $vet_str = explode(" ", $linha);

    //filtra valores em cada linha
    $nome = retorna_nome($vet_str);
    $data_nascimento = retorna_data_nascimento($vet_str);
    $data_nascimento = data_br2sql($data_nascimento);
    $cpf = retorna_cpf($vet_str);
    $cpf = somente_numeros($cpf);
    $profissoes = retorna_profissoes($vet_str);
    $vet_profissoes = explode(",", $profissoes);
    foreach ($vet_profissoes as &$profissao) {
      $profissao = retira_caracter($profissao, '[');
      $profissao = retira_caracter($profissao, ']');
    }
    unset($profissao);

    //ADICIONANDO UM CANDIDATO AO BANCO
    //$sql_candidato = gera_sql_candidato($nome,$data_nascimento,$cpf);
    //$retorno = insere_bd($sql_candidato);

    // // ADICIONANDO PROFISSOES AO BANCO
    // $sql_profissoes = "";
    // foreach ($vet_profissoes as &$profissao) {
    //   $sql_profissoes .= gera_sql_profissao($profissao);
    // }
    // unset($profissao);
    // $retorno = insere_bd($sql_profissoes);

    // ADICIONA PROFISSOES AO CANDIDATO
    $sql_usuario_profissao = gera_sql_candidato_profissao($cpf, $vet_profissoes);
    $retorno = insere_bd($sql_usuario_profissao);


  }//FECHA WHILE

  //FECHA O PONTEIRO DO ARQUIVO
  fclose ($ponteiro);

  require_once('../includes/mysqli-close.php');
