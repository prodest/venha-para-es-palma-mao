<?php 
/*
monta a pagina
*/
include_once 'cabeca.php';
echo '<h1>Lista de candidatos:</h1>';
require_once 'connect.php';
listarCandidato();
include_once 'pe.php';

/**
 * funções especificas desta pagina
 */
function listarCandidato(){
if (!empty($_POST["codigo"])) {
    $codigo = $_POST["codigo"];
    /**
     * query que confere se os arrays dos candidatos e dos cursos possuem algum item em comum.
     */
    $query = "select * from candidato 
    where curso && 
    (select vaga from concurso where codigo = '$codigo')";
    /**
     * funcao de conexao com o banco do arquivo connect.php
     */
    $result = conecta($query);

    /**
     * impressao do conteudo na tela já formatado.
     */
    echo "Candidatos para : $codigo <br>".pg_num_rows($result). " resultados";
    if (pg_num_rows($result) > 0) {
        echo '<ul class="collection section scrollspy">';
        while ($linha = pg_fetch_array($result)){
            echo '<li class="collection-item" id="lista" >';
            echo '<span id="negrito">Nome:</span> '.$linha['nome'] .' <span id="negrito">Data de nasc.: </span>'.$linha['nascimento'].' <span id="negrito">Cpf: </span>'. $linha['cpf'].'<br>';
            echo '</li>';
            }
            echo '</ul>';
    } else {
       echo'<br>Codigo inexistente ou sem candidatos a vaga ;( <br> ';
    }
    

} else {
   echo "Digite um Codigo valido.";
}}