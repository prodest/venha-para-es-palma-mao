<?php

  header("Content-Type: text/html; charset=utf-8",true);
  ini_set('default_charset', 'UTF-8');

  require_once('index.php');
  require_once('txt/functions.php');
  require_once('includes/mysqli.php');

  global $MySQLi;

  if(!strcmp($_POST['tipo'], "cpf")){

    $cpf = somente_numeros($_POST['busca']);

    /* Dado um CPF exibe todas as profissoes do candidato */
    $sql = "SELECT vaga_profissao.vaga_profissao_descricao_varchar
    FROM vaga_profissao JOIN candidato_profissao JOIN candidato
    WHERE candidato.candidato_id = candidato_profissao.candidato_id
    AND vaga_profissao.vaga_profissao_id = candidato_profissao.vaga_profissao_id
    AND candidato.candidato_cpf_varchar = ".$cpf.";";

    $sql_concurso = "SELECT DISTINCT concurso.concurso_orgao_varchar, concurso.concurso_codigo_varchar, concurso.concurso_edital_varchar
    FROM concurso JOIN concurso_vaga JOIN vaga_profissao
    WHERE concurso.concurso_id=concurso_vaga.concurso_vaga_id
    AND concurso_vaga.vaga_profissao_id=vaga_profissao.vaga_profissao_id
    AND (";// vaga_profissao.vaga_profissao_descricao_varchar = "programador"
         //OR vaga_profissao.vaga_profissao_descricao_varchar = "mecanico");";

    //realizando a busca das profissoes do candidato
    $resultado = $MySQLi->query($sql) OR trigger_error($MySQLi->error, E_USER_ERROR);
    //capturando o resultado
    while($profissao = mysqli_fetch_row($resultado)){
      $sql_concurso .= "vaga_profissao.vaga_profissao_descricao_varchar = \"".$profissao[0]."\" OR ";
    }
    for ($i=(strlen($sql_concurso)-4); $i < strlen($sql_concurso); $i++) {
      if($i==(strlen($sql_concurso)-4)){
        $sql_concurso[$i] = ')';
      }else {
        $sql_concurso[$i] = ' ';
      }
    }

    //preparando o html
    $i = 1;
    $tabela = "<table class=\"table table-striped\">
                <thead>
                  <tr>
                    <th scope=\"col\">#</th>
                    <th scope=\"col\">Órgão</th>
                    <th scope=\"col\">Código</th>
                    <th scope=\"col\">Edital</th>
                  </tr>
                </thead>
                <tbody>";

    //realizando a busca
    $resultado = $MySQLi->query($sql_concurso) OR trigger_error($MySQLi->error, E_USER_ERROR);
    //capturando o resultado
    while($concurso = mysqli_fetch_row($resultado)){
      $tabela .= "<tr>
                    <th scope=\"row\">".$i."</th>
                    <td>".$concurso[0]."</td>
                    <td>".$concurso[1]."</td>
                    <td>".$concurso[2]."</td>
                  </tr>";
      $i++;
    }
    $tabela .= " </tbody> </table>";

    $html = "<div class=\"container text-center\">
              <div class=\"row\">
                <div class=\"col-sm-2\">
                </div>
                <div class=\"col-sm-8\">
                ".$tabela."
                </div>
              </div>
            </div>";



    echo $html;
  }else if(!strcmp($_POST['tipo'], "codigo")){
    $codigo = somente_numeros($_POST['busca']);

    $sql = "SELECT vaga_profissao.vaga_profissao_descricao_varchar
    FROM vaga_profissao JOIN concurso JOIN concurso_vaga
    WHERE vaga_profissao.vaga_profissao_id = concurso_vaga.vaga_profissao_id
    AND concurso.concurso_id=concurso_vaga.concurso_id
    AND concurso.concurso_codigo_varchar = ".$codigo.";";

    $sql_candidatos = "SELECT DISTINCT candidato.candidato_nome_varchar, candidato.candidato_nascimento_date, candidato.candidato_cpf_varchar
    FROM candidato JOIN candidato_profissao JOIN vaga_profissao
    WHERE candidato.candidato_id = candidato_profissao.candidato_id
    AND candidato_profissao.vaga_profissao_id = vaga_profissao.vaga_profissao_id
    AND (";
    //vaga_profissao.vaga_profissao_descricao_varchar = "engenheiro"
    //OR vaga_profissao.vaga_profissao_descricao_varchar = "programador"
    //OR vaga_profissao.vaga_profissao_descricao_varchar = "mecanico");"

    //realizando a busca das vagas do concurso
    $resultado = $MySQLi->query($sql) OR trigger_error($MySQLi->error, E_USER_ERROR);
    //capturando o resultado
    while($vaga = mysqli_fetch_row($resultado)){
      $sql_candidatos .= "vaga_profissao.vaga_profissao_descricao_varchar = \"".$vaga[0]."\" OR ";
    }
    for ($i=(strlen($sql_candidatos)-4); $i < strlen($sql_candidatos); $i++) {
      if($i==(strlen($sql_candidatos)-4)){
        $sql_candidatos[$i] = ')';
      }else {
        $sql_candidatos[$i] = ' ';
      }
    }

    //preparando o html
    $i = 1;
    $tabela = "<table class=\"table table-striped\">
                <thead>
                  <tr>
                    <th scope=\"col\">#</th>
                    <th scope=\"col\">Nome</th>
                    <th scope=\"col\">Data de Nascimento</th>
                    <th scope=\"col\">CPF</th>
                  </tr>
                </thead>
                <tbody>";

    //realizando a busca
    $resultado = $MySQLi->query($sql_candidatos) OR trigger_error($MySQLi->error, E_USER_ERROR);
    //capturando o resultado
    while($candidato = mysqli_fetch_row($resultado)){
      $tabela .= "<tr>
                    <th scope=\"row\">".$i."</th>
                    <td>".$candidato[0]."</td>
                    <td>".$candidato[1]."</td>
                    <td>".$candidato[2]."</td>
                  </tr>";
      $i++;
    }
    $tabela .= " </tbody> </table>";

    $html = "<div class=\"container text-center\">
              <div class=\"row\">
                <div class=\"col-sm-2\">
                </div>
                <div class=\"col-sm-8\">
                ".$tabela."
                </div>
              </div>
            </div>";



    echo $html;
  }
