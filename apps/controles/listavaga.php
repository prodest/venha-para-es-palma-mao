<?php 
/**
 * montando a pagina
 */
include_once 'cabeca.php';
echo '<h1>Lista de vagas:</h1>';
require_once 'connect.php';
listavaga();
include_once 'pe.php';
/**
 * funcoes especificas desta pagina.
 */
function listavaga(){

    if (!empty($_POST['cpf']) ) {
        $cpf = $_POST['cpf'];
        /**
         * query que compara os arrays dos candidatos e dos concursos
         * selecionado os que possuem item em comum.
         */
        $query = "select * from concurso 
        where vaga && (select curso from candidato where cpf = '$cpf')";
        $result = conecta($query);

        /**
         * cria o conteúdo da tela.
         */
        echo "Vagas para : $cpf <br> ". pg_num_rows($result). " resultados ";
        if (!pg_num_rows($result) == 0) {
            echo '<ul class="collection section scrollspy">';
            while ($linha = pg_fetch_array($result)){
                echo '<li class="collection-item" id="lista" >';
                echo '<span id="negrito">Órgão: </span>'.$linha['nome'] .'<span id="negrito"> Código: </span>'.$linha['codigo'].'<span id="negrito"> Edital: </span>'. $linha['edital'].'<br>';
                echo '</li>';
            }
            echo '</ul>';
        } else {
            echo'<br>Cpf inexistente ou sem vagas para o candidato ;( <br>';
        }
    } else {
        echo 'Digite um CPF valido Ex: 182.845.084-34';
    }
}