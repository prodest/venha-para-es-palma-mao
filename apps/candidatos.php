<?php 
echo '<h1>Lista de candidatos:</h1>';

require_once './controles/connect.php';


$result = conecta("select * from candidato 
where curso in (select vaga from concurso where codigo = '70224262380')");

echo '<ul class="collection section scrollspy">';
while ($linha = pg_fetch_array($result)){
    echo '<li class="collection-item" id="lista" >';
    echo '<span id="negrito">Nome:</span> '.$linha['nome'] .' <span id="negrito">Data de nasc.: </span>'.$linha['nascimento'].' <span id="negrito">Cpf: </span>'. $linha['cpf'].'<br>';
    echo '</li>';
}
echo '</ul>';