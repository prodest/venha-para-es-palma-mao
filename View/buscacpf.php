<?php
include_once("header.php");

?>

<body>
    <div class="container">
        <div class="row">
            <div class="voltar">
                <a href="index.php" class="waves-effect waves-light btn-small">
                    <i class="material-icons left">home</i>Inicio</a>
            </div>
            <div class="buscaCpf col s6 offset-s6">
                <form action="" method="post">
                    <label for="busca">Digite o CPF</label>
                    <input type="text" id="busca" name="busca" placeholder="Digite o CPF">
                </form>
            </div>

            <table>
                <tr>
                    <tr>
                        <th>Orgão</th>
                        <th>Codigo Curso</th>
                        <th>Editais</th>
                    </tr>


                </tr>
                <tr class="cpfbuscado">

                </tr>
            </table>
        </div>

    </div>
</body>

<?php
include_once("footer.php");
?>