<!DOCTYPE html>

<html>
    
<head>
        <meta charset="UTF-8">  
        <title>ES palma da mão</title>
        <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
        <!-- Compiled and minified CSS -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css">
        <!-- Compiled and minified JavaScript -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>

        <!--Import Google Icon Font-->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <!--Import materialize.css-->   
        <!--Let browser know website is optimized for mobile-->
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <link rel="stylesheet" type="text/css" href="/apps/estaticos/base.css">
    </head>

    <body>
    <div>
    <nav>
    <div class="nav-wrapper">
      <a href="#" class="brand-logo">ES na palma da mão</a>
      <ul id="nav-mobile" class="right hide-on-med-and-down">
        <li><a id="candidatos" href="#">Candidatos</a></li>
        <li><a id="vagas" href="#">Vagas</a></li>
      </ul>
    </div>
  </nav>
    </div>
    <main>
    <?php 
    require_once './apps/controles/conexao.php';
    require_once './apps/controles/listacandidato.php';
    require_once './apps/controles/connect.php';
    

    ?>
    <div id="corpo" class="container">

    </div>
    </main>
    </body>
    <script type="text/javascript" src="/apps/estaticos/base.js"></script>
</html>
