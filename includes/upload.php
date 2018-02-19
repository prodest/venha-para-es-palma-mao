<?php


  $target_dir = "../txt/";
  $target_file = $target_dir . basename($_FILES["arquivo"]["name"]);

  if (move_uploaded_file($_FILES["arquivo"]["tmp_name"], $target_file)) {
        echo "O arquivo ". basename( $_FILES["fileToUpload"]["name"]). " foi carregado com sucesso!.";
  } else {
        echo "Desculpe, ocorreu um erro ao carregar o arquivo.";
  }
