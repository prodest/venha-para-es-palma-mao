<?php
  require_once('mysqli.php');
  header("Content-Type: text/html; charset=utf-8",true);
  ini_set('default_charset', 'UTF-8');

  /* retorna true caso obtenha sucesso e uma mensagem de erro caso falhe */
  function insere_bd($query){
    global $MySQLi;
    if(!empty($query)){
      if ($MySQLi->query($query) === TRUE) {
        return true;
      } else {
        return "Error: " . $query . "<br>" . $MySQLi->error . "<br><br>";
      }
    }
    return 0;
  }

  function adiciona_candidato_sql($nome,$data_nascimento,$cpf){
    $sql = "(NULL, \"".$nome."\", '".$data_nascimento."', '".$cpf."')";
    return $sql;
  }

  function adiciona_profissao_sql($profissao){
    $sql = "(NULL, '".$profissao."')";
    return $sql;
  }

  function adiciona_candidato_profissao_sql($candidato_id, $profissao_id){
    $sql = "(NULL, '".$candidato_id."', '".$profissao_id."')";
    return $sql;
  }

  function gera_sql_profissao($profissao){
    if(!empty($profissao)){
      $sql = "INSERT INTO `vaga_profissao`(`vaga_profissao_id`, `vaga_profissao_descricao_varchar`)
          SELECT NULL, '".$profissao."'
          FROM DUAL
          WHERE NOT EXISTS(SELECT `vaga_profissao_descricao_varchar` FROM `vaga_profissao`
          WHERE `vaga_profissao_descricao_varchar` = '".$profissao."'); ";
    }
    return $sql;
  }

  function retorna_candidato_id($cpf){
    global $MySQLi;
    //query
    $sql = "SELECT candidato.candidato_id FROM `candidato`
    WHERE candidato.candidato_cpf_varchar = '".$cpf."';";

    //realizando a busca
    $resultado = $MySQLi->query($sql) OR trigger_error($MySQLi->error, E_USER_ERROR);
    //capturando o resultado
    $id = $resultado->fetch_object();
    //retorna id
    if(isset($id->candidato_id)){
      return $id->candidato_id;
    }else{
      echo "Candidato de cpf ".$cpf." não encontrado! <br>";
      return NULL;
    }
  }

  function retorna_profissao_id($profissao){
    global $MySQLi;
    //query
    $sql = "SELECT vaga_profissao.vaga_profissao_id FROM `vaga_profissao`
    WHERE vaga_profissao.vaga_profissao_descricao_varchar = '".$profissao."';";

    //realizando a busca
    $resultado = $MySQLi->query($sql) OR trigger_error($MySQLi->error, E_USER_ERROR);
    //capturando o resultado
    $id = $resultado->fetch_object();
    //retorna id
    if(!empty($id)){
      return $id->vaga_profissao_id;
    }else{
      echo "Profissao ".$profissao." não encontrada! <br>";
      return NULL;
    }
  }

  function retorna_concurso_id($codigo){
    global $MySQLi;
    //query
    $sql = "SELECT concurso.concurso_id FROM concurso WHERE concurso.concurso_codigo_varchar = \"$codigo\"";

    //realizando a busca
    $resultado = $MySQLi->query($sql) OR trigger_error($MySQLi->error, E_USER_ERROR);
    //capturando o resultado
    $id = $resultado->fetch_object();
    //retorna id
    if(!empty($id)){
      return $id->concurso_id;
    }else{
      echo "Concurso de código ".$codigo." não encontrado! <br>";
      return NULL;
    }
  }
