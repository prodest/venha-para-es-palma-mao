<form class="form-inline" action="./includes/upload.php" method="post" enctype="multipart/form-data">
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
