<?php

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

    // ADICIONANDO UM CANDIDATO AO BANCO
    $sql_candidato = gera_sql_candidato($nome,$data_nascimento,$cpf);
    //$retorno = insere_bd($sql_candidato);


    // ADICIONANDO PROFISSOES AO BANCO
    $sql_profissoes = gera_sql_profissoes($vet_profissoes);
    echo $sql_profissoes."<br>";


  }//FECHA WHILE

  //FECHA O PONTEIRO DO ARQUIVO
  fclose ($ponteiro);

  require_once('../includes/mysqli-close.php');
