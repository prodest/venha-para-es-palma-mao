<?php
session_start();
include "funcoes.php";
include_once "../Model/conexao.php";

try {

    if ($_POST['enviar']) {

        $arqTemp = $_FILES['arquivo']['tmp_name'];
        $arqname = $_FILES['arquivo']['name'];
    
        $ArquivoCandidato = file($arqTemp);
    
        foreach ($ArquivoCandidato as $linha) {
    
            $linha = trim($linha);
            $dadosCandidato = multiexplode(array(" ", "[", ",", "]"), $linha);
            $nome = $dadosCandidato[0];
            $sobreNome = $dadosCandidato[1];
            $NomeCompleto = $nome . " " . $sobreNome;
            $datanascFor = date("Y-m-d", strtotime(str_replace('/', '-', $dadosCandidato[2])));
            $cpfFor = limpaCPF_CNPJ($dadosCandidato[3]);
            $arrayProf = ordenaArrayProf($dadosCandidato);
            $profCertas = vereficaProfissoes($arrayProf);
    
            $result_usu = "INSERT INTO candidatos (nome, datanasc, cpf) VALUES('$NomeCompleto', '$datanascFor', '$cpfFor')";
            $conn->query($result_usu);
    
            $_SESSION['msg'] = "<p style='color: green'>Dados carregados com sucesso!</p>";
            header("Location: ../View/arquivos.php");
    
        }
    
        
    
    } else {
    
        $_SESSION['msg'] = "<p style='color: green'>Sem dados para carregar!</p>";
        header("Location: ../View/arquivos.php");
    
    }
   
} catch (Exception $e) {
    echo 'Exceção capturada: ',  $e->getMessage(), "\n";
}




