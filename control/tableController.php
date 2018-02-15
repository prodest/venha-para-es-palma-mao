<?php

    /*Arquivo que que cria as variaveis para serem exibidas na paginação*/ 
    require_once("../model/Concurso.php");
    require_once("../model/BancoDAO.php");
    require_once("../model/Candidato.php");
    require_once('../model/Paginacao.php');

    $db = new BancoDAO();
    $concursosResult = null;
    $candidatosResult = null;
    $candidato = null;
    $concurso = null;
    $entidade = $_GET['entidade'];

    if (!isset($_GET['pg'])){
        $paginaAtual = 1;
    }
    else{
        $paginaAtual = $_GET['pg'];
    }

    switch ($entidade){

        case "candidato":
            if ($_GET['cpf'] == "" || $_GET['cpf'] == null){
                echo "<br><b>Volte e preencha o campo de busca corretamente !</b><br>";
            }
            else{
                $concursosResult = $db->executeQuery("SELECT * FROM concurso");
                $candidatosResult = $db->executeQuery("SELECT * FROM candidato WHERE cpf = '{$_GET['cpf']}'");
                if(count($candidatosResult)){
                    $candidato = new Candidato($candidatosResult['nome'], $candidatosResult['cpf'], 
                    $candidatosResult['data_nascimento'], $candidatosResult['profissoes']);
            
                    echo "<br>Candidato: <b>{$candidato->getNome()} </b><br>";
                    echo "CPF: <b>{$candidato->getCpf()} </b><br>";
                    echo "Data de nascimento: <b>{$candidato->getDataNascimento()}</b><br>";
                    echo "Profissões: <b>{$candidato->getLProfissoesString()}</b><br>";
            
                }
                else{
                    echo "<b><br>Nenhum candidato foi encontrado com esse CPF !</b><br>";
                }
            }
            $paginacao = new Paginacao($candidato, $concursosResult, $entidade, $paginaAtual);
            break;

        case "concurso":
            if ($_GET['cod_concurso'] == "" || $_GET['cod_concurso'] == null){
                echo "<br><b>Volte e preencha o campo de busca corretamente !</b><br>";
            }
            else{
                $concursosResult = $db->executeQuery("SELECT * FROM concurso WHERE cod_concurso = '{$_GET['cod_concurso']}'");
                $candidatosResult = $db->executeQuery("SELECT * FROM candidato");
                if(count($concursosResult)){
                    $concurso = new Concurso($concursosResult['orgao'], $concursosResult['edital'], 
                    $concursosResult['cod_concurso'], $concursosResult['lista_de_vagas']);

                    echo "<br>Orgão: <b>{$concurso->getOrgao()}</b><br>";
                    echo "Edital: <b>{$concurso->getEdital()}</b><br>";
                    echo "Código do concurso: <b>{$concurso->getCodigo()}</b><br>";
                    echo "Lista de vagas: <b>{$concurso->getListaDeVagasString()}</b><br>";

                }
                else{
                    echo "<b><br>Nenhum concurso foi encontrado com esse código !</b><br>";
                }
            }
            $paginacao = new Paginacao($candidatosResult, $concurso, $entidade, $paginaAtual);
            break;
    }
    $paginacao->setPagination();
    