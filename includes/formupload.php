<form class="form-inline" action="./includes/formupload.php" method="post" enctype="multipart/form-data">
    <div class="form-check form-check-inline form-control form-control-sm">
      <input class="form-check-input" type="radio" name="tipo" id="cpf" value="cpf" checked>
      <label class="form-check-label" for="cpf">candidatos</label>
    </div>
    <div class="form-check form-check-inline form-control form-control-sm">
      <input class="form-check-input" type="radio" name="tipo" id="codigo" value="codigo">
      <label class="form-check-label" for="inlineRadio2">concursos</label>
    </div>
    <div class="form-group form-control form-control-sm">
      <input type="file" class="form-control-file" id="arquivo" name="arquivo">
    </div>
    <button type="submit" class="btn btn-primary">carregar</button>
</form>

<?php

  if(!empty($_FILES["arquivo"])){
    $target_dir = "../txt/";
    $target_file = $target_dir . basename($_FILES["arquivo"]["name"]);

    if (move_uploaded_file($_FILES["arquivo"]["tmp_name"], $target_file)) {
          echo "<br><div class=\"alert alert-success\" role=\"alert\">
                  O arquivo ". basename( $_FILES["fileToUpload"]["name"]). " foi carregado com sucesso!.
                </div>";
    } else {
          echo "<br><div class=\"alert alert-success\" role=\"alert\">
                  Desculpe, ocorreu um erro ao carregar o arquivo 
                </div>";
    }
  }
