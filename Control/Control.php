<?php 
    include ("../Model/BancoDAO.php");

    $db = new BancoDAO();
    $db->executeQuery("SELECT * FROM concursos");
?>