<?php

    /*Arquivo que que cria as variaveis para serem exibidas na paginação*/ 
    require_once("../model/Concurso.php");
    require_once("../model/BancoDAO.php");
    require_once("../model/Candidato.php");
    require_once('../model/Paginacao.php');

    $db = new BancoDAO();                   //Criação do objeto da classe responsável pelo banco de dados
    $concursosResult = null;                //Armazenará os dados extraido do banco de dados referente aos concursos
    $candidatosResult = null;               //Armazenará os dados extraidos do banco de dados referente aos candidatos
    $candidato = null;                      //Será o objeto da classe Candidato
    $concurso = null;                       //Será o objeto da classe Concurso
    $entidade = $_GET['entidade'];          //Armazena a entidade que indica 'quem' iremos manipular, candidatos ou concursos
    $finded = false;                        // Se achar resultado, seja candidatos ou concursos, será true

    /*Definição da página que o usuário se encontra*/
    if (!isset($_GET['pg'])){
        $paginaAtual = 1;
    }
    else{
        $paginaAtual = $_GET['pg'];
    }

    /*Verifica qual entidade foi disparada*/
    switch ($entidade){

        case "candidato":
            if ($_GET['cpf'] == "" || $_GET['cpf'] == null){
                echo "<br><b>Volte e preencha o campo de busca corretamente !</b><br>";
            }
            else{
                $concursosResult = $db->executeQuery("SELECT * FROM concurso");
                $candidatosResult = $db->executeQuery("SELECT * FROM candidato WHERE cpf = '{$_GET['cpf']}'");
                /*Se o count($candidatosResult) for 1 ou mais, significa que pelo menos um candidato foi achado*/
                if(count($candidatosResult)){
                    $candidato = new Candidato($candidatosResult['nome'], $candidatosResult['cpf'], 
                    $candidatosResult['data_nascimento'], $candidatosResult['profissoes']);
            
                    echo "<br>Candidato: <b>{$candidato->getNome()} </b><br>";
                    echo "CPF: <b>{$candidato->getCpf()} </b><br>";
                    echo "Data de nascimento: <b>{$candidato->getDataNascimento()}</b><br>";
                    echo "Profissões: <b>{$candidato->getLProfissoesString()}</b><br>";
                    
                    /*Cria o objeto de classe de paginação para a busca de concursos por cpf de candidato*/
                    $paginacao = new Paginacao($candidato, $concursosResult, $entidade, $paginaAtual);
                    $finded = true;     //Pelo menos um candidato foi achado
                }
                else{
                    echo "<b><br>Nenhum candidato foi encontrado com esse CPF !</b><br>";
                }
            }
            break;

        case "concurso":
            if ($_GET['cod_concurso'] == "" || $_GET['cod_concurso'] == null){
                echo "<br><b>Volte e preencha o campo de busca corretamente !</b><br>";
            }
            else{
                $concursosResult = $db->executeQuery("SELECT * FROM concurso WHERE cod_concurso = '{$_GET['cod_concurso']}'");
                $candidatosResult = $db->executeQuery("SELECT * FROM candidato");
                /*Se o count($concursoResult) for 1 ou mais, significa que pelo menos um concurso foi achado*/
                if(count($concursosResult)){
                    $concurso = new Concurso($concursosResult['orgao'], $concursosResult['edital'], 
                    $concursosResult['cod_concurso'], $concursosResult['lista_de_vagas']);

                    echo "<br>Órgão: <b>{$concurso->getOrgao()}</b><br>";
                    echo "Edital: <b>{$concurso->getEdital()}</b><br>";
                    echo "Código do concurso: <b>{$concurso->getCodigo()}</b><br>";
                    echo "Lista de vagas: <b>{$concurso->getListaDeVagasString()}</b><br>";
                    /*Cria o objeto de classe de paginação para a busca de candidatos por código de concurso*/
                    $paginacao = new Paginacao($candidatosResult, $concurso, $entidade, $paginaAtual);
                    $finded = true;     //Pelo menos um concurso foi achado
                }
                else{
                    echo "<b><br>Nenhum concurso foi encontrado com esse código !</b><br>";
                }
            }
            break;
    }
    if ($finded){
        $paginacao->setPagination();
    }
    