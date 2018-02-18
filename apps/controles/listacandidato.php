<?php 
/**
 * monta a pagina
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
     * query que confere se os arrays de cursos dos candidatos e das vagas dos cursos
     * possuem algum item em comum.
     */
    $query = "select * from candidato 
    where curso && 
    (select vaga from concurso where codigo = '$codigo')";
    $result = conecta($query);
    echo "Candidatos para : $codigo <br>".pg_num_rows($result). " resultados";
    imprimeResultado($result);
} else {
   echo "Digite um Codigo valido.";
}
}
function imprimeResultado($result){
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
}