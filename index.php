<?php

  header("Content-Type: text/html; charset=utf-8",true);
  ini_set('default_charset', 'UTF-8');

  require_once('includes/functions.php');
  require_once('includes/mysqli.php');
  require_once('includes/functionsBusca.php');

  require_once('includes/head.php');
  require_once('busca.php');

  global $MySQLi;

  if(!empty($_POST['busca'])){

    if(!strcmp($_POST['tipo'], "cpf")){
      $cpf = somente_numeros($_POST['busca']);
      $vet_profissoes = retorna_vet_profissoes_cpf($cpf);
      $sql_concurso = sql_busca_concursos_por_profissoes($vet_profissoes);

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

      echo $tabela;

    }else if(!strcmp($_POST['tipo'], "codigo")){

      $codigo = somente_numeros($_POST['busca']);

      $vet_vagas = retorna_vet_vagas_codigo($codigo);

      $sql_candidatos = "SELECT DISTINCT candidato.candidato_nome_varchar, candidato.candidato_nascimento_date, candidato.candidato_cpf_varchar
      FROM candidato JOIN candidato_profissao JOIN vaga_profissao
      WHERE candidato.candidato_id = candidato_profissao.candidato_id
      AND candidato_profissao.vaga_profissao_id = vaga_profissao.vaga_profissao_id
      AND (";

      foreach ($vet_vagas as $vaga) {
        $sql_candidatos .= "vaga_profissao.vaga_profissao_descricao_varchar = \"".$vaga."\" OR ";
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

      echo $tabela;
    }
  }

  require_once('includes/footer.php');
