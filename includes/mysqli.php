<?php

// Dados de acesso ao servidor MySQL
$MySQL = array(
  'servidor' => '127.0.0.1',  				// Endereço do servidor
  'usuario' => 'root',    						// Usuário
  'senha' => '',       								// Senha
  'banco' => 'es_na_palma_da_mao'   	// Nome do banco de dados
);
$MySQLi = new MySQLi($MySQL['servidor'], $MySQL['usuario'], $MySQL['senha'], $MySQL['banco']);

// Verifica se ocorreu um erro e exibe a mensagem de erro
if ($conn = mysqli_connect_errno()){
    trigger_error(mysqli_connect_error(), E_USER_ERROR);
}else {
  mysqli_set_charset($MySQLi,"utf8");
}
