<?php

    require_once("../model/Concurso.php");
    require_once("../model/BancoDAO.php");
    require_once("../model/Candidato.php");

    $db = new BancoDAO();
    $concursosResult = null;
    $candidatosResult = null;


    if($_GET['cpf'] == "" || $_GET['cpf'] == null){
        echo "<br><b>Volte e preencha o campo de busca corretamente !</b><br>";
    }
    else{

        $concursosResult = $db->executeQuery("SELECT * FROM concursos");
        $candidatosResult = $db->executeQuery("SELECT * FROM candidatos WHERE cpf = '{$_GET['cpf']}'");
        
        $candidato = new Candidato($candidatosResult['nome'], $candidatosResult['cpf'], 
        $candidatosResult['data_nascimento'], $candidatosResult['profissoes']);

        echo "<br>Candidato: <b>{$candidato->getNome()} </b><br>";
        echo "CPF: <b>{$candidato->getCpf()} </b><br>";
        echo "Data de nascimento: <b>{$candidato->getDataNascimento()}</b><br>";
        echo "Profissões: <b>{$candidato->getLProfissoesString()}</b><br>";

        foreach ($candidato->getProfissoes() as $prof){
            foreach ($concursosResult as $line){
                $concurso = new Concurso($line['orgao'], $line['edital'], $line['cod_concurso'], $line['lista_de_vagas']);
                foreach($concurso->getListaDeVagas() as $vaga){
                    if($prof == $vaga){
                        echo "<tr>";
                            echo "<td>".$concurso->getOrgao()."</td>";
                            echo "<td>".$concurso->getEdital()."</td>";
                            echo "<td>".$concurso->getCodConcurso()."</td>";
                            echo "<td>".$concurso->getListaDeVagasString()."</td>";
                        echo "</tr>";
                    }
                }
            }
        }
    }