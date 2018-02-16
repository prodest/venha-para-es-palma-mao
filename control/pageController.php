<?php 
    /* Todas as variaveis que estão sendo usadas mas não que foram criadas nesse arquivo, foram criadas em
    'tableController.php', isso acontece ele foi chamado antes desse arquivo. É como se todos os aqruivos
    PHP fossem apenas um único e grande script */

    //Definindo as duas variaveis que depende da entidade
    switch ($entidade){
        case "candidato":
            $totalPaginas = ceil(count($concursosResult)/$paginacao->getIntervalo());
            $urlAtual = "?cpf=".$_GET['cpf']."&entidade=".$entidade;
            break;
        case "concurso":
            $totalPaginas = ceil(count($candidatosResult)/$paginacao->getIntervalo());
            $urlAtual = "?cod_concurso=".$_GET['cod_concurso']."&entidade=".$entidade;
            break;
    }

    $proximo = $paginaAtual + 1;    //Vai definir pra onde será encaminhado ao clicar na seta 'proximo'
    $anterior = $paginaAtual - 1;   //Vai definir pra onde será encaminhado ao clicar na seta 'anterior'
    $setaEsquerda = "";             //Vai definir a aparencia das setas esquerdas
    $setaDireita = "";              //Vai definir a aparencia das setas direitas
    $lowLimit;                      //Vai definir o primeiro botao da paginacao
    $highLimit;                     //Vai definir o último botao da paginacao
    
    /* Defindo tamanho e aparencia dos botoes da paginaçao*/ 
    if($paginaAtual == 1 || $anterior < 1){
        $anterior = 1;
        $setaEsquerda = "disabled";
    }
    if($paginaAtual == $totalPaginas || $proximo > $totalPaginas){
        $proximo = $totalPaginas;
        $setaDireita = "disabled";
    }

    if($paginaAtual <= 4){
        $lowLimit = 1;
    }

    else{
        $lowLimit = $paginaAtual - 4;
        if($paginaAtual+4 >= $totalPaginas){
            $lowLimit = $totalPaginas - 8;
        }
    }
    
    if(($paginaAtual+4) <= $totalPaginas){
        $highLimit = $paginaAtual + 4;
        if($paginaAtual >= 1 && $paginaAtual < 5 && $highLimit+4 <= $totalPaginas){
            $highLimit+=(4-$paginaAtual);
        }
    }
    else{
        $highLimit = $totalPaginas;
    }    
    
    /*Imprimindo as setas e botoes da paginação*/
    echo "<li class='waves-effect waves-green {$setaEsquerda}'><a href=".$urlAtual."&pg=1"."><i class='material-icons'>chevron_left</i></a></li>";
    echo "<li class='waves-effect waves-green {$setaEsquerda}'><a href=".$urlAtual."&pg=".$anterior."><i class='material-icons'>chevron_left</i></a></li>";
    for($i = $lowLimit; $i <= $highLimit; $i++){
        if ($paginaAtual == $i){
            $active = "pactive";
        }
        else {
            $active = "";
        }
        echo "<li class='waves-effect waves-green {$active}'><a href=".$urlAtual."&pg=".$i.">{$i}</a></li>";
    }
    echo "<li class='waves-effect waves-green {$setaDireita}'><a href=".$urlAtual."&pg=".$proximo."><i class='material-icons'>chevron_right</i></a></li>";
    echo "<li class='waves-effect waves-green {$setaDireita}'><a href=".$urlAtual."&pg=".$totalPaginas."><i class='material-icons'>chevron_right</i></a></li>";
