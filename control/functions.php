<?php

    /*Arquivo com o proposito de guardar os funções que podem ser usadas em mais de um lugar*/
    
    function redirectMsg($msg, $time, $url){
        echo "<b style='color:red'>{$msg}</b>";
        echo "<meta http-equiv='refresh' content='{$time}; url={$url}'>";
    }

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