<?php 

echo '<h1>Lista de vagas:</h1>';

require_once './controles/connect.php';


$result = conecta("select * from concurso 
where vaga in (select curso from candidato where cpf = '182.845.084-34')");

echo '<ul class="collection section scrollspy">';
while ($linha = pg_fetch_array($result)){
    echo '<li class="collection-item" id="lista" >';
    echo '<span id="negrito">Órgão: </span>'.$linha['nome'] .'<span id="negrito"> Código: </span>'.$linha['codigo'].'<span id="negrito"> Edital: </span>'. $linha['edital'].'<br>';
    echo '</li>';
}
echo '</ul>';