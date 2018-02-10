<!DOCTYPE html>
<html>
    <?php include ("head.html") ?>
    <body>
        <?php include ("navBar.html"); ?>
        <div class="container">
            <div class="row">
            <table class="bordered highlight">
                <thead>
                <tr>
                    <th>Órgão</th>
                    <th>Edital</th>
                    <th>Código do Concurso</th>
                    <th>Lista de vagas</th>
                </tr>
                </thead>
                <tbody>
                    <?php include ("../control/tableController.php"); ?>
                </tbody>
             </table>
            </div>
        </div>
    </body>
    <?php include ("footer.html"); ?>
</html> 