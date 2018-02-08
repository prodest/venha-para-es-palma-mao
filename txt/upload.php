<?php

  require_once('../includes/head.php');

  $target_dir = "../uploads/";
  $target_file = $target_dir . basename($_FILES["arquivo"]["name"]);

  if (move_uploaded_file($_FILES["arquivo"]["tmp_name"], $target_file)) {
        echo "The file ". basename( $_FILES["fileToUpload"]["name"]). " has been uploaded.";
  } else {
        echo "Sorry, there was an error uploading your file.";
  }


  // echo "Tipo: " . $_POST["tipo"];
  // echo "<br>Arquivo: " . $_POST["arquivo"];
  // $arquivo = $_POST["arquivo"];
  // ABRE O ARQUIVO TXT
  // $ponteiro = fopen ($arquivo, "r");
  //
  // //LÊ O ARQUIVO ATÉ CHEGAR AO FIM
  // while (!feof ($ponteiro)) {
  //   //LÊ UMA LINHA DO ARQUIVO
  //   $linha = fgets($ponteiro);
  //   //IMPRIME NA TELA O RESULTADO
  //   echo $linha."<br>";
  // }//FECHA WHILE
  //
  // FECHA O PONTEIRO DO ARQUIVO
  // fclose ($ponteiro);


  require_once('../includes/footer.php');
