<?php

    require_once("../model/Concurso.php");
    require_once("../model/BancoDAO.php");
    require_once("../model/Candidato.php");

    $db = new BancoDAO();
    $concursosResult = null;
    $candidatosResult = null;


    if ($_POST['entidade'] == "candidato"){
        if ($_POST['cpf'] == "" || $_POST['cpf'] == null){
            echo "<br><b>Volte e preencha o campo de busca corretamente !</b><br>";
        }
        else{
            $concursosResult = $db->executeQuery("SELECT * FROM concursos");
            $candidatosResult = $db->executeQuery("SELECT * FROM candidatos WHERE cpf = '{$_POST['cpf']}'");
            if(count($candidatosResult)){
                $candidato = new Candidato($candidatosResult['nome'], $candidatosResult['cpf'], 
                $candidatosResult['data_nascimento'], $candidatosResult['profissoes']);
        
                echo "<br>Candidato: <b>{$candidato->getNome()} </b><br>";
                echo "CPF: <b>{$candidato->getCpf()} </b><br>";
                echo "Data de nascimento: <b>{$candidato->getDataNascimento()}</b><br>";
                echo "Profissões: <b>{$candidato->getLProfissoesString()}</b><br>";
        
                foreach ($candidato->getProfissoes() as $prof){
                    foreach ($concursosResult as $line){
                        $concurso = new Concurso($line['orgao'], $line['edital'], $line['cod_concurso'], $line['lista_de_vagas']);
                        foreach ($concurso->getListaDeVagas() as $vaga){
                            if ($prof == $vaga){
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
            else{
                echo "<b><br>Nenhum candidato foi encontrado com esse CPF !</b><br>";
            }
            
        }
    }
    else if ($_POST['entidade'] == "concurso"){
        if ($_POST['cod_concurso'] == "" || $_POST['cod_concurso'] == null){
            echo "<br><b>Volte e preencha o campo de busca corretamente !</b><br>";
        }
        else{
            $concursosResult = $db->executeQuery("SELECT * FROM concursos WHERE cod_concurso = '{$_POST['cod_concurso']}'");
            $candidatosResult = $db->executeQuery("SELECT * FROM candidatos");
            if(count($concursosResult)){
                $concurso = new Concurso($concursosResult['orgao'], $concursosResult['edital'], 
                $concursosResult['cod_concurso'], $concursosResult['lista_de_vagas']);

                echo "<br>Orgão: <b>{$concurso->getOrgao()}</b><br>";
                echo "Edital: <b>{$concurso->getEdital()}</b><br>";
                echo "Código do concurso: <b>{$concurso->getCodigo()}</b><br>";
                echo "Lista de vagas: <b>{$concurso->getListaDeVagasString()}</b><br>";

                foreach ($concurso->getListaDeVagas() as $vaga){
                    foreach ($candidatosResult as $line){
                        $candidato = new Candidato($line['nome'], $line['cpf'], $line['data_nascimento'], $line['profissoes']);
                        foreach ($candidato->getProfissoes() as $prof){
                            if($vaga == $prof){
                                echo "<tr>";
                                    echo "<td>".$candidato->getNome()."</td>";
                                    echo "<td>".$candidato->getDataNascimento()."</td>";
                                    echo "<td>".$candidato->getCpf()."</td>";
                                    echo "<td>".$candidato->getLProfissoesString()."</td>";
                                echo "</tr>";
                            }
                        }
                    }
                }

            }
            else{
                echo "<b><br>Nenhum concurso foi encontrado com esse código !</b><br>";
            }
        }
    }
    