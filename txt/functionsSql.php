<?php
  require_once('../includes/mysqli.php');

  function gera_sql_candidato($nome,$data_nascimento,$cpf){
    $sql = "INSERT INTO `candidato`
    (`candidato_id`, `candidato_nome_varchar`, `candidato_nascimento_date`, `candidato_cpf_varchar`)
    VALUES (NULL, '".$nome."', '".$data_nascimento."', '".$cpf."');";

    return $sql;

  }

  function insere_bd($query){
    if ($MySQLi->query($query) === TRUE) {
        return "Insert realizado com sucesso!";
    } else {
        echo "Error: " . $sql . "<br>" . $conn->error;
    }
  }

  function gera_sql_profissoes($vet_profissoes){
    $sql = "INSERT INTO `vaga_profissao`
    (`vaga_profissao_id`, `vaga_profissao_descricao_varchar`)
    VALUES ";

    for ($i = 0; $i < count($vet_profissoes); $i++) {
      if($i == (count($vet_profissoes)-1)){
        $sql .= "(NULL, '".$vet_profissoes[$i]."');";
      }else{
        $sql .= "(NULL, '".$vet_profissoes[$i]."'), ";
      }
    }

    return $sql;
  }
