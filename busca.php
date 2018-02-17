

          <form class="formulario" action="index.php" method="post">
            <div class="form-group">
              <input class="form-control" type="text" id="busca" name="busca" placeholder="CPF / Código do concurso">
            </div>

            <div class="form-check form-check-inline">
              <input class="form-check-input" type="radio" name="tipo" id="cpf" value="cpf" checked>
              <label class="form-check-label" for="cpf">CPF</label>
            </div>
            <div class="form-check form-check-inline">
              <input class="form-check-input" type="radio" name="tipo" id="codigo" value="codigo">
              <label class="form-check-label" for="inlineRadio2">Código do Concurso</label>
            </div>
            <br><button type="submit" class="btn btn-primary">Buscar</button>
          </form>
