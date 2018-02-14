<?php

    function redirectMsg($msg, $time, $url){
        echo "<b style='color:red'>{$msg}</b>";
        echo "<meta http-equiv='refresh' content='{$time}; url={$url}'>";
    }