<!DOCTYPE html>
<html lang="pt-br">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Venha para ES</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

     <link rel="stylesheet" type="text/css" href="../css/styles.css">
  </head>


    <body>

      <br><br>
      <ul class="nav justify-content-center fixed-top navbar navbar-primary bg-primary">
        <li class="nav-item">
          <button type="nav-button" class="btn btn-light"><?php echo total_candidatos(); ?> Candidatos</button>
          <button type="nav-button" class="btn btn-light"><?php echo total_concursos(); ?> Concursos</button>
          <button type="nav-button" class="btn btn-light"><?php echo total_profissoes(); ?> Profissões</button>
        </li>
      </ul>
      <div class="container text-center">

        <div class="row">
          <div class="col-sm-2">

          </div>
          <div class="col-sm-8">

            <img src="img/es-palma-mao.jpg" class="img-fluid" alt="Responsive image">
            <br><br>
