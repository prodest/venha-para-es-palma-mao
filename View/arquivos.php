<?php include_once("header.php");
session_start();
?>

<body>

    <div class="container">
        <div class="voltar">

            <a href="index.php" class="waves-effect waves-light btn-small">
                <i class="material-icons left">home</i>Inicio</a>
        </div>

        <div class="col s10">

            <h3>Importar arquivo Candidatos</h3>
            <!-- O tipo de encoding de dados, enctype, DEVE ser especificado abaixo -->
            <form enctype="multipart/form-data" action="../Controler/procDadosCandidatos.php" method="POST">
                <!-- MAX_FILE_SIZE deve preceder o campo input -->
                <input type="hidden" name="MAX_FILE_SIZE" value="300000" />
                <!-- O Nome do elemento input determina o nome da array $_FILES -->
                <label for="arqCandidato">Selecionar arquivo</label>
                <input id="arqCandidato" name="arquivo" type="file" />
                <input type="submit" value="enviar" name="enviar">
                <br>
                <br>
            </form>
            <?php 
            if(isset($_SESSION['msg'])){
            echo $_SESSION['msg'];
            unset($_SESSION['msg']);
            }
            ?>
        </div>

        <div class="col s10">
            <h3>Importar arquivo Concursos</h3>
            <!-- O tipo de encoding de dados, enctype, DEVE ser especificado abaixo -->
            <form enctype="multipart/form-data" action="../Controler/procDadosConcursos.php" method="POST">
                <!-- MAX_FILE_SIZE deve preceder o campo input -->
                <input type="hidden" name="MAX_FILE_SIZE" value="300000" />
                <!-- O Nome do elemento input determina o nome da array $_FILES -->
                <label for="arqCandidato">Selecionar arquivo</label>
                <input type="file" name="arquivo">
                <input type="submit" value="enviar" name="enviar">
                <br>
                <br>
            </form>
            <?php 
            if(isset($_SESSION['msgConcurso'])){
            echo $_SESSION['msgConcurso'];
            unset($_SESSION['msgConcurso']);
            }
            ?>

        </div>

    </div>

</body>

<?php include_once("footer.php")?>