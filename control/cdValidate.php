<?php
    
    include ("functions.php");
    /*Código que validará a entrada de dados do Código de Concurso*/
    if(isset($_GET['cod_concurso'])){
        
        $cd = $_GET['cod_concurso'];
        $count = 0;

        for($i=0; $i<strlen($cd); $i++){
            if ($cd[$i] >= '0' && $cd[$i] <= '9'){
                $count++;
            }
            else{
                break;
            }
        }
        if($count == strlen($cd)){
            $url = "?cod_concurso=".$cd."&entidade=".$_GET['entidade'];
            redirectMsg("",0,"concursosTable.php{$url}");
        }
        else{
            redirectMsg("Código do concurso inválido",2,"concursos.php");
        }
    }