<?php
include_once "../Model/conexao.php";
include_once "../Controler/funcoes.php";

try {
    if ($_POST['buscacpf']) {
        $CodConcurso = $_POST['buscacpf'];

        $sql = "SELECT * FROM concursos WHERE codConcurso  LIKE '%$CodConcurso%' ";

        $resultado = mysqli_query($conn, $sql);

        if (mysqli_num_rows($resultado) <= 0) {
            echo "<tr>";
            echo "<td>Sem dados</td>";
            echo "<td>Sem dados</td>";
            echo "<td>Sem dados</td>";
            echo "</tr>";
        } else {
            while ($rows = mysqli_fetch_assoc($resultado)) {
                echo "<tr>";
                echo "<td>" . $rows['orgao'] . "</td>";
                echo "<td>" . $rows['edital'] . "</td>";
                echo "<td>" . $rows['codConcurso'] . "</td>";
                echo "</tr>";

            }

        }

    }
} catch (Exception $e) {
    echo 'Exceção capturada: ', $e->getMessage(), "\n";
}
