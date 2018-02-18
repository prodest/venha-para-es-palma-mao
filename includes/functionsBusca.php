<?php
  /*
  * AQUI VOCÊ ENCONTRARÁ FUNÇÕES RELACIONADAS AO BD MUITO UTILIZADAS PELO INDEX.PHP
  */


  require_once('includes/mysqli.php');


  /*
  * RETORNA UM VETOR COM AS PROFISSOES DE UM CANDIDATO COM BASE EM SEU CPF
  * PRÉ-REQUISITO: CPF deve estar cadastrado no bd
  * INPUT: string (cpf do candidato apenas com numeros)
  * OUTPUT: vetor de profissoes do candidato
  */
  function retorna_vet_profissoes_cpf($cpf){
    global $MySQLi;

    /* Dado um CPF exibe todas as profissoes do candidato */
    $sql = "SELECT vaga_profissao.vaga_profissao_descricao_varchar
    FROM vaga_profissao JOIN candidato_profissao JOIN candidato
    WHERE candidato.candidato_id = candidato_profissao.candidato_id
    AND vaga_profissao.vaga_profissao_id = candidato_profissao.vaga_profissao_id
    AND candidato.candidato_cpf_varchar = '".$cpf."';";

    $resultado = $MySQLi->query($sql) OR trigger_error($MySQLi->error, E_USER_ERROR);

    $i = 0;
    while($profissao = mysqli_fetch_row($resultado)){
      $vet_profissoes[$i] = $profissao[0];
      $i++;
    }
    return $vet_profissoes;

  }

  /*
  * GERA UM SQL QUE PERMITE BUSCAR TODOS OS CONCURSOS QUE CONTENHAM TAIS PROFISSOES
  * PRÉ-REQUISITO: vetor de profissoes nao nulo
  * INPUT: vetor de string com no minimo uma profissao
  * OUTPUT: string (sql que permite realizar a busca)
  */
  function sql_busca_concursos_por_profissoes($vet_profissoes){
    $sql_concurso = "SELECT DISTINCT C.concurso_orgao_varchar, C.concurso_codigo_varchar, C.concurso_edital_varchar
    FROM concurso AS C JOIN concurso_vaga AS CV JOIN vaga_profissao AS VP
    WHERE C.concurso_id=CV.concurso_vaga_id
    AND CV.vaga_profissao_id=VP.vaga_profissao_id
    AND (";

    foreach ($vet_profissoes as $profissao) {
      $sql_concurso .= "VP.vaga_profissao_descricao_varchar = \"".$profissao."\" OR ";
    }
    for ($i=(strlen($sql_concurso)-4); $i < strlen($sql_concurso); $i++) {
      if($i==(strlen($sql_concurso)-4)){
        $sql_concurso[$i] = ')';
      }else {
        $sql_concurso[$i] = ' ';
      }
    }

    return $sql_concurso;
  }

  /*
  * RETORNA UM VETOR COM AS VAGAS DE UM CONCURSO COM BASE EM SEU CÓDIGO
  * PRÉ-REQUISITO: Código deve estar cadastrado no bd
  * INPUT: string (código do concurso apenas com numeros)
  * OUTPUT: vetor de string com as vagas do concurso
  */
  function retorna_vet_vagas_codigo($codigo){
    global $MySQLi;

    /* Dado um codigo retorna todas as vagas do concurso */
    $sql = "SELECT vaga_profissao.vaga_profissao_descricao_varchar
    FROM vaga_profissao JOIN concurso JOIN concurso_vaga
    WHERE vaga_profissao.vaga_profissao_id = concurso_vaga.vaga_profissao_id
    AND concurso.concurso_id=concurso_vaga.concurso_id
    AND concurso.concurso_codigo_varchar = ".$codigo.";";

    $resultado = $MySQLi->query($sql) OR trigger_error($MySQLi->error, E_USER_ERROR);

    $i = 0;
    while($vaga = mysqli_fetch_row($resultado)){
      $vet_vagas[$i] = $vaga[0];
      $i++;
    }
    return $vet_vagas;

  }

  /*
  * VERIFICA SE UM CANDIDATO ESTA CADASTRADO NO BD COM BASE EM SEU CPF
  * PRÉ-REQUISITO: string contendo o CPF não nulo
  * INPUT: string (cpf do candidato apenas com numeros)
  * OUTPUT: true caso encontre no bd, false caso contrario
  */
  function existe_candidato($cpf){
    global $MySQLi;
    if(!empty($cpf)){
      $sql = "SELECT * FROM `candidato` WHERE candidato.candidato_cpf_varchar = ".$cpf.";";
      $resultado = $MySQLi->query($sql);
      if($resultado->num_rows){
        return true;
      }
    }
    return false;
  }

  /*
  * VERIFICA SE UM CONCURSO ESTA CADASTRADO NO BD COM BASE EM SEU CÓDIGO
  * PRÉ-REQUISITO: string contendo o CÓDIGO não nulo
  * INPUT: string (código do concurso apenas com numeros)
  * OUTPUT: true caso encontre no bd, false caso contrario
  */
  function existe_concurso($codigo){
    global $MySQLi;
    if(!empty($codigo)){
      $sql = "SELECT * FROM `concurso` WHERE concurso.concurso_codigo_varchar = ".$codigo.";";
      $resultado = $MySQLi->query($sql);
      if($resultado->num_rows){
        return true;
      }
    }
    return false;
  }

  /*
  * DADO UM CPF COM APENAS NUMEROS RETORNA UM CPF NO FORMATO XXX.XXX.XXX-XX
  * PRÉ-REQUISITO: string contendo o cpf não nulo
  * INPUT: string (cpf apenas com numeros)
  * OUTPUT: cpf no formato xxx.xxx.xxx-xx
  */
  function mascara_cpf($cpf){
    $novo_cpf = "";
    for ($i=0; $i < strlen($cpf); $i++) {
      if($i==3 || $i==6){
        $novo_cpf .= '.';
      }
      if($i==9){
        $novo_cpf .= '-';
      }
      $novo_cpf .= $cpf[$i];
    }
    return $novo_cpf;
  }

  function total_candidatos(){
    global $MySQLi;
    //query
    $sql = "SELECT COUNT(*) AS 'total' FROM candidato";

    //realizando a busca
    $resultado = $MySQLi->query($sql) OR trigger_error($MySQLi->error, E_USER_ERROR);
    //capturando o resultado
    $total = $resultado->fetch_object();
    //retorna total_candidatos
    return $total->total;

  }

  function total_concursos(){
    global $MySQLi;
    //query
    $sql = "SELECT COUNT(*) AS 'total' FROM concurso";

    //realizando a busca
    $resultado = $MySQLi->query($sql) OR trigger_error($MySQLi->error, E_USER_ERROR);
    //capturando o resultado
    $total = $resultado->fetch_object();
    //retorna total_candidatos
    return $total->total;

  }

  function total_profissoes(){
    global $MySQLi;
    //query
    $sql = "SELECT COUNT(*) AS 'total' FROM vaga_profissao";

    //realizando a busca
    $resultado = $MySQLi->query($sql) OR trigger_error($MySQLi->error, E_USER_ERROR);
    //capturando o resultado
    $total = $resultado->fetch_object();
    //retorna total_candidatos
    return $total->total;

  }
