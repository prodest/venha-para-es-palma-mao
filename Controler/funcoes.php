<?php
function multiexplode($delimiters, $string)
{

    $ready = str_replace($delimiters, $delimiters[0], $string);
    $launch = explode($delimiters[0], $ready);
    return $launch;
}

function limpaCPF_CNPJ($valor)
{
    $valor = trim($valor);
    $valor = str_replace(".", "", $valor);
    $valor = str_replace(",", "", $valor);
    $valor = str_replace("-", "", $valor);
    $valor = str_replace("/", "", $valor);
    return $valor;
}

function ordenaArrayProf($array)
{
    $cont = 0;
    for ($pos = 4; $pos < count($array); $pos++) {
        if ($array[$pos] != "") {
            $novoarray[$pos] = $array[$pos];
            $novoArraOrder[$cont] = $novoarray[$pos];
            $cont++;
        }

    }

    return $novoArraOrder;
}

function vereficaProfissoes($profArray)
{
    $cont = 0;
    for ($i = 0; $i < count($profArray); $i++) {

        if (strcasecmp($profArray[$i], "assistente") == 0) {

            $assistente = $profArray[$i] . " " . $profArray[$i + 1];
            $newArrayProfAjust[$cont] = $assistente;

        } elseif (strcasecmp($profArray[$i], "professor") == 0) {

            $prof = $profArray[$i] . " " . $profArray[$i + 1] . " " . $profArray[$i + 2];
            $newArrayProfAjust[$cont] = $prof;

        } elseif (strcasecmp($profArray[$i], "analista") == 0) {

            $analista = $profArray[$i] . " " . $profArray[$i + 1] . " " . $profArray[$i + 2];
            $newArrayProfAjust[$cont] = $analista;

        } elseif (strcasecmp($profArray[$i], "inspetor ") == 0) {

            $inspetor = $profArray[$i] . " " . $profArray[$i + 1];
            $newArrayProfAjust[$cont] = $inspetor;

        } elseif (strcasecmp($profArray[$i], "carpinteiro") == 0 || strcasecmp($profArray[$i], "marceneiro") == 0 || strcasecmp($profArray[$i], "estagiário") == 0) {

            $newArrayProfAjust[$cont] = $profArray[$i];
        }

        $cont++;
    }

    return $newArrayProfAjust;
}


function maskCPFCNPJ($val, $mask)
{

    $maskared = '';

    $k = 0;

    for ($i = 0; $i <= strlen($mask) - 1; $i++) {

        if ($mask[$i] == '#') {

            if (isset($val[$k])) {
                $maskared .= $val[$k++];
            }

        } else {

            if (isset($mask[$i])) {
                $maskared .= $mask[$i];
            }

        }

    }

    return $maskared;

}


	 



