<?php

    /*Arquivo com o proposito de guardar os funções que podem ser usadas em mais de um lugar*/
    
    function redirectMsg($msg, $time, $url){
        echo "<b style='color:red'>{$msg}</b>";
        echo "<meta http-equiv='refresh' content='{$time}; url={$url}'>";
    }