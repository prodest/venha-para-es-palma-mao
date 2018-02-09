<!DOCTYPE html>
<html>
    <?php include ("head.html") ?>
    <body>
        <?php include ("navBar.html"); ?>
        <div class="container">
            <div class="row">
                <p class="flow-text">Entre com o <b>código do concurso</b> para procurar os candidatos que
                    encaixam no perfil do concurso</p>
            </div>
            <div class="row">
                <form action="concursosTable.php" method="GET">
                    <div class="input-field col s6">
                        <i class="material-icons prefix">description</i>
                        <input id="cpf" type="text" class="validate">
                        <label for="cpf">CPF do candidato</label>
                    </div>
                </div>
                <div class="row">
                    <button class="btn waves-effect waves-light" type="submit" name="action">Procurar
                        <i class="material-icons right">send</i>
                    </button>
                </div>
            </form>
            <br>
            </div>
        </div>
    </body>
    <?php include ("footer.html"); ?>
</html> 