<html>
 <head>
    <title> Venha para ES na Palma da Mão </title>
  <meta http-equiv="Content-Type" content="text/html;charset=iso-8859-1" />
 </head>

 <body>
  <?php

    header("Content-Type: text/html; charset=ISO-8859-1",true);
    // Inclui o arquivo que faz a conexão ao banco de dados
    require_once('../includes/mysqli.php');
    $sql = "SELECT * FROM candidato AS Candidato;";

    $resultado = $MySQLi->query($sql) OR trigger_error($MySQLi->error, E_USER_ERROR);

    while ($candidato = $resultado->fetch_object()) {

      echo "<p>".
              "Nome: ".$candidato->candidato_nome_varchar.
              " CPF: ".$candidato->candidato_cpf_varchar .
              " Data de Nascimento: ".$candidato->candidato_nascimento_date.
          "</p>";

    } // while ($noticia = $resultado->fetch_object())
  ?>
  </body>

</html>
