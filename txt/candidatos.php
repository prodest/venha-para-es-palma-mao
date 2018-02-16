<?php

  require_once('../includes/mysqli.php');
  require_once('../includes/functions.php');
  require_once('../includes/functionsSql.php');

  header("Content-Type: text/html; charset=utf-8",true);
  ini_set('default_charset', 'UTF-8');

  $sql_candidato = "INSERT INTO `candidato`
  (`candidato_id`, `candidato_nome_varchar`, `candidato_nascimento_date`, `candidato_cpf_varchar`) VALUES ";

  $sql_candidato_profissao =  "INSERT INTO `candidato_profissao`
  (`candidato_profissao_id`, `candidato_id`, `vaga_profissao_id`) VALUES ";

  //ABRE O ARQUIVO TXT
  $ponteiro = fopen ("candidatos.txt", "r");

  //LÊ O ARQUIVO ATÉ CHEGAR AO FIM
  while (!feof ($ponteiro)) {
    //LÊ UMA LINHA DO ARQUIVO
    $linha = fgets($ponteiro);
    if(!empty($linha)){
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
        $profissao = retira_caracter_invalido($profissao);
        $profissao = retira_espaco_ini_fim($profissao);
      }
      unset($profissao);

      //ADICIONANDO UM CANDIDATO AO SQL
      $sql_candidato .= adiciona_candidato_sql($nome,$data_nascimento,$cpf).",";

      // INSERINDO PROFISSOES AO BANCO
      $sql_profissoes = "";
      foreach ($vet_profissoes as $profissao) {
        $sql_profissoes .= gera_sql_profissao($profissao);
      }
      unset($profissao);
      $retorno = insere_bd($sql_profissoes);

    }
  }//FECHA WHILE

  //FECHA O PONTEIRO DO ARQUIVO
  fclose ($ponteiro);

  // //ISERINDO CANDIDATOS NO BANCO
  $sql_candidato[(strlen($sql_candidato)-1)] = ';';
  $retorno = insere_bd($sql_candidato);

#############################################################################

  //LÊ NOVAMENTE O ARQUIVO PARA ADICIONAR PROFISSOES AOS CANDIDATOS
  //DESTA FORMA O TEMPO PARA REALIZAR OS INSERTS DIMINUI ABSURDAMENTE

  //ABRE O ARQUIVO TXT
  $ponteiro = fopen ("candidatos.txt", "r");

  while (!feof ($ponteiro)) {
    //LÊ UMA LINHA DO ARQUIVO
    $linha = fgets($ponteiro);
    if(!empty($linha)){
      $vet_str = explode(" ", $linha);

      //filtra valores em cada linha
      $cpf = retorna_cpf($vet_str);
      $cpf = somente_numeros($cpf);
      $profissoes = retorna_profissoes($vet_str);
      $vet_profissoes = explode(",", $profissoes);

      $candidato_id = retorna_candidato_id($cpf);

      foreach ($vet_profissoes as &$profissao) {
        $profissao = retira_caracter_invalido($profissao);
        $profissao = retira_espaco_ini_fim($profissao);

        $profissao_id = retorna_profissao_id($profissao);
        //ADICIONA PROFISSOES AO CANDIDATO SQL
        $sql_candidato_profissao .= adiciona_candidato_profissao_sql($candidato_id, $profissao_id).",";
      }
      unset($profissao);
    }
  }//FECHA WHILE

  //ISERINDO PROFISSOES DOS CANDIDATOS NO BANCO
  $sql_candidato_profissao[(strlen($sql_candidato_profissao)-1)] = ';';
  $retorno = insere_bd($sql_candidato_profissao);

  //FECHA O PONTEIRO DO ARQUIVO
  fclose ($ponteiro);


  require_once('../includes/mysqli-close.php');
