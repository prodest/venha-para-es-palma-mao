
<form class="formulario" action="converte.php" method="post" enctype="multipart/form-data">

  <fieldset>
   <legend>Carregue seu arquivo para o banco de dados aqui</legend>
   <p>
      <label>Tipo do arquivo: </label>
      <input type = "radio"
             name = "tipo"
             id = "candidato"
             value = "candidato"
             checked = "checked" />
      <label for = "candidato">Candidato</label>
      <input type = "radio"
             name = "tipo"
             id = "concurso"
             value = "concurso" />
      <label for = "concurso">Concurso</label>
    </p>


  <div>
    <p>
      <label for="arquivo">Arquivo: </label>
      <input type="file" id="arquivo" name="arquivo"/>
    </p>
  </div>

  <div class="button">
    <p>
      <button type="submit">Enviar sua mensagem</button>
    </p>
  </div>

  </fieldset>
</form>
