<?php

  require_once('../includes/mysqli.php');
  require_once('../includes/functions.php');
  require_once('../includes/functionsSql.php');

  header("Content-Type: text/html; charset=utf-8",true);
  ini_set('default_charset', 'UTF-8');

  $sql_concurso = "INSERT INTO `concurso`
  (`concurso_id`, `concurso_orgao_varchar`, `concurso_edital_varchar`, `concurso_codigo_varchar`) VALUES ";
  //(NULL, 'orgao', 'edital', 'codigo');

  $sql_concurso_vaga = "INSERT INTO `concurso_vaga`
  (`concurso_vaga_id`, `vaga_profissao_id`, `concurso_id`) VALUES ";
  //(NULL, '4', '1');

  //ABRE O ARQUIVO TXT
  $ponteiro = fopen ("concursos.txt", "r");

  //LÊ O ARQUIVO ATÉ CHEGAR AO FIM
  while (!feof ($ponteiro)) {
    //LÊ UMA LINHA DO ARQUIVO
    $linha = fgets($ponteiro);
    if(!empty($linha)){
      $vet_str = explode(" ", $linha);

      //filtra valores em cada linha
      $orgao = retorna_nome($vet_str);
      $edital = retorna_data_nascimento($vet_str);
      $codigo = retorna_codigo($vet_str);
      $vagas = retorna_profissoes($vet_str);
      $vet_vagas = explode(",", $vagas);
      foreach ($vet_vagas as &$vaga) {
        $vaga = retira_caracter_invalido($vaga);
        $vaga = retira_espaco_ini_fim($vaga);
      }
      unset($vaga);
      //ADICIONANDO UM CONCURSO AO SQL
      $sql_concurso .= adiciona_candidato_sql($orgao,$edital,$codigo).",";

      // INSERINDO VAGAS AO BANCO
      $sql_vaga = "";
      foreach ($vet_vagas as $vaga) {
        $sql_vaga .= gera_sql_profissao($vaga);
      }
      unset($vaga);
      $retorno = insere_bd($sql_vaga);

    }
  }//FECHA WHILE

  //FECHA O PONTEIRO DO ARQUIVO
  fclose ($ponteiro);

  // //ISERINDO CONCURSOS NO BANCO
  $sql_concurso[(strlen($sql_concurso)-1)] = ';';
  $retorno = insere_bd($sql_concurso);

#############################################################################

  //LÊ NOVAMENTE O ARQUIVO PARA ADICIONAR PROFISSOES AOS CANDIDATOS
  //DESTA FORMA O TEMPO PARA REALIZAR OS INSERTS DIMINUI ABSURDAMENTE

  //ABRE O ARQUIVO TXT
  $ponteiro = fopen ("concursos.txt", "r");

  while (!feof ($ponteiro)) {
    //LÊ UMA LINHA DO ARQUIVO
    $linha = fgets($ponteiro);
    if(!empty($linha)){
      $vet_str = explode(" ", $linha);

      //filtra valores em cada linha
      $codigo = retorna_codigo($vet_str);
      $vagas = retorna_profissoes($vet_str);
      $vet_vagas = explode(",", $vagas);

      $concurso_id = retorna_concurso_id($codigo);

      foreach ($vet_vagas as &$vaga) {
        $vaga = retira_caracter_invalido($vaga);
        $vaga = retira_espaco_ini_fim($vaga);

        $vaga_id = retorna_profissao_id($vaga);
        //ADICIONA VAGAS AO CONCURSO SQL
        $sql_concurso_vaga .= adiciona_candidato_profissao_sql($vaga_id, $concurso_id).",";
      }
      unset($profissao);


    }
  }//FECHA WHILE

  // //ISERINDO PROFISSOES DOS CANDIDATOS NO BANCO
  $sql_concurso_vaga[(strlen($sql_concurso_vaga)-1)] = ';';
  $retorno = insere_bd($sql_concurso_vaga);

  //FECHA O PONTEIRO DO ARQUIVO
  fclose ($ponteiro);


  require_once('../includes/mysqli-close.php');
