<?php
  require_once('includes/mysqli.php');

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


    function retorna_vet_candidato($cpf){
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
