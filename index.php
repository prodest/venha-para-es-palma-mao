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
      if(existe_candidato($cpf)){
        $vet_profissoes = retorna_vet_profissoes_cpf($cpf);
        $sql_concurso = sql_busca_concursos_por_profissoes($vet_profissoes);
        $vet_candidato = retorna_vet_candidato($cpf);
        //preparando o html
        $i = 1;
        $tabela = "<br><table class=\"table table-striped\">
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
      }else{
        echo "<br><div class=\"alert alert-danger\" role=\"alert\">
                candidato de cpf ".$cpf." não encontrado!
              </div>";
      }

    }else if(!strcmp($_POST['tipo'], "codigo")){

      $codigo = somente_numeros($_POST['busca']);

      if(existe_concurso($codigo)){
        $vet_vagas = retorna_vet_vagas_codigo($codigo);

        $sql_candidatos = "SELECT DISTINCT candidato.candidato_nome_varchar, DATE_FORMAT(candidato.candidato_nascimento_date,'%d/%m/%Y'), candidato.candidato_cpf_varchar
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
        $tabela = "<br><table class=\"table table-striped\">
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
          $cpf_candidato = mascara_cpf($candidato[2]);
          $tabela .= "<tr>
                        <th scope=\"row\">".$i."</th>
                        <td>".$candidato[0]."</td>
                        <td>".$candidato[1]."</td>
                        <td>".$cpf_candidato."</td>
                      </tr>";
          $i++;
        }
        $tabela .= " </tbody> </table>";

        echo $tabela;
      }else{
        echo "<br><div class=\"alert alert-danger\" role=\"alert\">
                Concurso de código ".$codigo." não encontrado!
              </div>";
      }
    }
  }

  require_once('includes/footer.php');
