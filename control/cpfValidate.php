<?php

    include ("functions.php");
    /*Código que fará a validação da entrada de dados do usuário*/ 

    /*Funcao que normaliza o cpf. Aceita o formato padrao e o formato de apenas numeros*/
    function normalizarCPF($cpf){
        if (strlen($cpf) == 14){
            return $cpf;
        }
        else if (strlen($cpf) == 11){
            $initialString = substr($cpf, 0, 9);
            $initialString = chunk_split($initialString, 3, '.');
            $initialString[11] = '-';
            $finalString = substr($cpf, 9);
            return $initialString.$finalString;
        }
    }

    if(isset($_GET['cpf'])){

        $count = 0;         //Contador que servira para fazer contagem de caracter correto
        $cpf = $_GET['cpf'];

        if (strlen($cpf) == 11 || strlen($cpf) == 14){ 
            for($i=0; $i<strlen($cpf);$i++){
                if (($i == 3 || $i == 7) && $cpf[$i] == '.'){
                    $count++;
                }
                else if ($i == 11 && $cpf[$i] == '-'){
                    $count++;
                }
                else if ($cpf[$i] >= '0' && $cpf[$i] <= '9'){
                    $count++;
                }
                else{
                    break;      // Se algum caracter nao for dentro do padrao, parar operação.
                }
            }
            if($count == strlen($cpf)){
                $cpf = normalizarCPF($cpf);
                $url = "?cpf=".$cpf."&entidade=".$_GET['entidade'];     //Variavei que armazenará a url que será redirecionado 
                redirectMsg("",0,"candidatosTable.php{$url}");
            }
            else{
                redirectMsg("CPF INCORRETO",2,"candidatos.php");
            }
        }
        else{
            redirectMsg("CPF INCOMPLETO",2,"candidatos.php");
        }
    }

   