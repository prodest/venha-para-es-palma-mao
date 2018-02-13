<!DOCTYPE html>
<html>
    <?php include ("head.html") ?>
    <body>
        <?php include ("navBar.html"); ?>
        <div class="container">
            <div class="row">
                <p class="flow-text">Entre com o <b>CPF</b> do candidato para procurar os concursos que
                    encaixam no seu perfil</p>
            </div>
            <div class="row">
                <form action="candidatosTable.php" method="GET">
                    <div class="input-field col s6">
                        <i class="material-icons prefix">credit_card</i>
                        <input id="cpf" type="text" class="validate" name="cpf" required>
                        <label for="cpf">CPF do candidato</label>
                    </div>
                </div>
                <div class="row">
                    <button class="btn waves-effect waves-light" type="submit" name="entidade" value="candidato">Procurar
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