<?php 
echo '<h1>Lista de vagas:</h1>';

require_once './controles/connect.php';


$result = conecta("select * from concurso 
where vaga in (select curso from candidato where cpf = '182.845.084-34')");

echo '<ul class="collection section scrollspy">';
while ($linha = pg_fetch_array($result)){
    echo '<li class="collection-item" id="lista" >';
    echo 'Órgão: '.$linha['nome'] .' Código: '.$linha['codigo'].' Edital: '. $linha['edital'].'<br>';
    echo '</li>';
}
echo '</ul>';