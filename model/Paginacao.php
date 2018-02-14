<?php
    require_once('Candidato.php');
    require_once('Concurso.php');

    class Paginacao{

        private $concursoResult;  
        private $candidatoResult; 
        private $entidade;
        private $pagina;
        private $inicio;
        private $limite;
        private $intervalo = 10;

        function __construct($candidatoResult, $concursoResult, $entidade, $pagina){
            $this->candidatoResult = $candidatoResult;
            $this->concursoResult = $concursoResult;
            $this->entidade = $entidade;
            $this->pagina = $pagina;

            $this->inicio = ($pagina-1) * $this->intervalo;
            $this->limite = $this->inicio + $this->intervalo;
        }

        public function getIntervalo(){
            return $this->intervalo;
        }

        public function setPagination(){
            if ($this->entidade == "candidato"){
                $this->setCandidatoPagination();
            }
            else if ($this->entidade == "concurso"){
                $this->setConcursoPagination();
            }
        }

        private function setCandidatoPagination(){
            $i = $this->inicio;
            $j = $i;
        
            foreach ($this->candidatoResult->getProfissoes() as $prof){
                while ($i < $this->limite && $j < count($this->concursoResult)){
                    //Erro aparece apartir daqui
                    $concurso = new Concurso($this->concursoResult[$j]['orgao'], $this->concursoResult[$j]['edital'], 
                     $this->concursoResult[$j]['cod_concurso'], $this->concursoResult[$j]['lista_de_vagas']);
                    
                    foreach ($concurso->getListaDeVagas() as $vaga){
                        if ($prof == $vaga){
                            echo "<tr>";
                                echo "<td>".$concurso->getOrgao()."</td>";
                                echo "<td>".$concurso->getEdital()."</td>";
                                echo "<td>".$concurso->getCodigo()."</td>";
                                echo "<td>".$concurso->getListaDeVagasString()."</td>";
                            echo "</tr>";
                            $i++;
                            break;
                        }
                    }
                    $j++;
                }
            }
        }

        private function setConcursoPagination(){
            $i = $this->inicio;
            $j = $i;
            foreach ($this->concursoResult->getListaDeVagas() as $vaga){
                while ($i < $this->limite && $j < count($this->candidatoResult)){
                    
                    $candidato = new Candidato($this->candidatoResult[$j]['nome'], $this->candidatoResult[$j]['cpf'],
                     $this->candidatoResult[$j]['data_nascimento'], $this->candidatoResult[$j]['profissoes']);

                    foreach ($candidato->getProfissoes() as $prof){
                        if($vaga == $prof){
                            echo "<tr>";
                                echo "<td>".$candidato->getNome()."</td>";
                                echo "<td>".$candidato->getDataNascimento()."</td>";
                                echo "<td>".$candidato->getCpf()."</td>";
                                echo "<td>".$candidato->getLProfissoesString()."</td>";
                            echo "</tr>";
                            $i++;
                            break;
                        }
                    }
                    $j++;
                }
            }
        }

    }