<?php
session_start();
include "funcoes.php";
include_once "../Model/conexao.php";

try {
    if ($_POST['enviar']) {

        $arqTemp = $_FILES['arquivo']['tmp_name'];
        $arqname = $_FILES['arquivo']['name'];
    
        $ArquivoConcurso = file($arqTemp);
        $conta = 0;
        foreach ($ArquivoConcurso as $linha) {
            
            $linha = trim($linha);
            $ArquivoConcurso = multiexplode(array(" ", "[", ",", "]"), $linha);
            $nomeOrgao = $ArquivoConcurso[0];
            $Edital = $ArquivoConcurso[1];
            $codConcurso = $ArquivoConcurso[2];
            $arrayProf = ordenaArrayProf($ArquivoConcurso);
            $profCertas = vereficaProfissoes($arrayProf);
            $prof = $profCertas[0];
    
            $result_usu = "INSERT INTO concursos (orgao, edital, codConcurso) VALUES('$nomeOrgao', '$Edital', '$codConcurso')";
            $conn->query($result_usu);
    
            $_SESSION['msgConcurso'] = "<p style='color: green'>Dados carregados com sucesso!</p>";
            header("Location: ../View/arquivos.php"); 
    
        }
    
    
    }else {
    
        $_SESSION['msgConcurso'] = "<p style='color: green'>Sem dados para carregar!</p>";
        header("Location: ../View/arquivos.php");
        
    }
    
} catch (Exception $e) {
    echo 'Exceção capturada: ',  $e->getMessage(), "\n";
}

