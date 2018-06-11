<?php

function get($var, $default = false, $htmlspecialchars = true) {
	if (isset($_GET[$var])) {
		$value = $_GET[$var];
		if ($htmlspecialchars) {
			$value = htmlspecialchars($value, ENT_QUOTES);
		} else {
			$value = addslashes($value);
		}
		return  trim($value);
	} else {
		return $default;
	}
}

function post($var, $default = false, $htmlspecialchars = true, $addslashes = true) {
	if (isset($_POST[$var])) {
		$value = $_POST[$var];

		if (is_array($value)) {
			return $default;
		}
		
		if ($htmlspecialchars) {
			$value = htmlspecialchars($value, ENT_QUOTES);
		} else {
			$value = ($addslashes ? addslashes($value) : $value);
		}

		return trim($value);
	} else {
		return $default;
	}
}

function cpfValido($check) {
	$cpf = preg_replace('/[^0-9]/', '', $check);
	
	if (strlen($cpf) != 11) {
		return false;
	}

	for ($i = 0, $j = 10, $soma = 0; $i < 9; $i++, $j--) {
		$soma += $cpf{$i} * $j;
	}

	$resto = $soma % 11;

	if ($cpf{9} != ($resto < 2 ? 0 : 11 - $resto)) {
		return false;
	}

	for ($i = 0, $j = 11, $soma = 0; $i < 10; $i++, $j--) {
		$soma += $cpf{$i} * $j;
	}

	$resto = $soma % 11;

	return $cpf{10} == ($resto < 2 ? 0 : 11 - $resto);
}


/**
* Função para gerar senhas aleatórias
*
* @author    Thiago Belem <contato@thiagobelem.net>
*
* @param integer $tamanho Tamanho da senha a ser gerada
* @param boolean $maiusculas Se terá letras maiúsculas
* @param boolean $numeros Se terá números
* @param boolean $simbolos Se terá símbolos
*
* @return string A senha gerada
*/
function geraSenha($tamanho = 8, $maiusculas = true, $numeros = true, $simbolos = false)
{
$lmin = 'abcdefghijklmnopqrstuvwxyz';
$lmai = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ';
$num = '1234567890';
$simb = '!@#$%*-';
$retorno = '';
$caracteres = '';

$caracteres .= $lmin;
if ($maiusculas) $caracteres .= $lmai;
if ($numeros) $caracteres .= $num;
if ($simbolos) $caracteres .= $simb;

$len = strlen($caracteres);
for ($n = 1; $n <= $tamanho; $n++) {
$rand = mt_rand(1, $len);
$retorno .= $caracteres[$rand-1];
}
return $retorno;
}
