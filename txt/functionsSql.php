<?php
  require_once('../includes/mysqli.php');
  header("Content-Type: text/html; charset=utf-8",true);
  ini_set('default_charset', 'UTF-8');

  /* retorna 1 caso obtenha sucesso e 0 caso falhe */
  function insere_bd($query){
    global $MySQLi;
    if(!empty($query)){
      if ($MySQLi->query($query) === TRUE) {
        return 1;
      } else {
        echo "Error: " . $query . "<br>" . $MySQLi->error . "<br><br>";
        return 0;
      }
    }
    return 0;
  }

  function gera_sql_candidato($nome,$data_nascimento,$cpf){
    $sql = "INSERT INTO `candidato`
    (`candidato_id`, `candidato_nome_varchar`, `candidato_nascimento_date`, `candidato_cpf_varchar`)
    VALUES (NULL, \"".$nome."\", '".$data_nascimento."', '".$cpf."');";

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

  function gera_sql_candidato_profissao($cpf, $vet_profissoes){
    $sql = "INSERT INTO `candidato_profissao` (`candidato_profissao_id`, `candidato_id`, `vaga_profissao_id`)
    VALUES";// (NULL, '1', '1'), (NULL, '1', '2');

    $candidato_id = retorna_id_candidato($cpf);
    if(!empty($candidato_id)){
      for ($i = 0; $i < count($vet_profissoes); $i++) {
        if(!empty($vet_profissoes[$i])){
          $id_profissao = retorna_id_profissao($vet_profissoes[$i]);
          if(!empty($id_profissao)){
            if($i == (count($vet_profissoes)-1)){
              $sql .= "(NULL, '".$candidato_id."', '".$id_profissao."');";
            }else{
              $sql .= "(NULL, '".$candidato_id."', '".$id_profissao."'),";
            }
          }else{
            $sql = NULL;
          }
        }else{
          $sql = NULL;
        }
      }
    }else {
      $sql = NULL;
    }
    return $sql;
  }

  function retorna_id_candidato($cpf){
    global $MySQLi;
    //query
    $sql = "SELECT candidato.candidato_id FROM `candidato`
    WHERE candidato.candidato_cpf_varchar = '".$cpf."';";
    // $sql = "SELECT candidato.candidato_id FROM `candidato`
    // WHERE candidato.candidato_cpf_varchar = 12345678901;";
    //realizando a busca
    $resultado = $MySQLi->query($sql) OR trigger_error($MySQLi->error, E_USER_ERROR);
    //capturando o resultado
    $id = $resultado->fetch_object();
    //retorna id
    if(isset($id->candidato_id)){
      return $id->candidato_id;
    }else{
      return NULL;
    }
  }

  function retorna_id_profissao($profissao){
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
      return NULL;
    }
  }
