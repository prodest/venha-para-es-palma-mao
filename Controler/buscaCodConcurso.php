<?php
include_once "../Model/conexao.php";
include_once "../Controler/funcoes.php";

try {
    if ($_POST['buscaCodConcurso']) {
        $cpf = $_POST['buscaCodConcurso'];
       
        $sql = "SELECT * FROM candidatos WHERE cpf LIKE '%$cpf%' ";
    
        $resultado = mysqli_query($conn, $sql);
    
        if (mysqli_num_rows($resultado) <= 0) {
            echo"<tr>";
                echo "<td>Sem dados</td>";
                echo "<td>Sem dados</td>";
                echo "<td>Sem dados</td>";
                echo"</tr>";
        } else {
            while ($rows = mysqli_fetch_assoc( $resultado)) {            
                echo"<tr>";
                echo "<td>".$rows['nome']."</td>";
                echo "<td>".date('d/m/Y', strtotime($rows['dataNasc']))."</td>";
                echo "<td>".maskCPFCNPJ($rows['cpf'],'###.###.###-##')."</td>";
                echo"</tr>";
    
            }
    
        }
        
    }
} catch (Exception $e) {
    echo 'Exceção capturada: ',  $e->getMessage(), "\n";
}

