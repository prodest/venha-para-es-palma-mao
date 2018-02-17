<?php
    require_once('Candidato.php');
    require_once('Concurso.php');

    /*Classe responsavél por colocar na tabela os dados correspondentes à pesquisa (E dentro da paginação)*/
    class Paginacao{

        private $concursoResult;        //Armazenará os resultados da consulta dos concursos
        private $candidatoResult;       //Armazenará os resultados da consulta dos candidatos
        private $entidade;              //Define em quem será 'controlado'
        private $pagina;                //Pagina atual
        private $inicio;                //Indice de inicio da listagem na tabela (De algum dos resultados)
        private $limite;                //Indice de final da listagem na tabela (De algum dos resultados)
        private $intervalo = 5;         //Quantidade máxima de linhas que a tabela terá

        function __construct($candidatoResult, $concursoResult, $entidade, $pagina){
            $this->candidatoResult = $candidatoResult;
            $this->concursoResult = $concursoResult;
            $this->entidade = $entidade;
            $this->pagina = $pagina;

            /*Dessa forma se define o inicio e final da listagem dependendo da pagina que está*/
            $this->inicio = ($pagina-1) * $this->intervalo;
            $this->limite = $this->inicio + $this->intervalo;
        }

        public function getIntervalo(){
            return $this->intervalo;
        }

        /*Principal funcao de controle, depende da entidade. Cada entidade chamará uma funcao de controle*/
        public function setPagination(){
            switch ($this->entidade){
                case "candidato":
                    $this->setCandidatoPagination();
                    break;
                case "concurso":
                    $this->setConcursoPagination();
                    break;
            }
        }

        /*Exibe dentro de uma tabela e por paginas definidas por um limite os concursos achados para o canditato*/
        private function setCandidatoPagination(){
            
            foreach ($this->candidatoResult->getProfissoes() as $prof){
                $i = $this->inicio;
                $j = $i;
                while ($i < $this->limite && $j < count($this->concursoResult)){
    
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

        /*Exibe dentro de uma tabela e por paginas definidas por um limite os candidatos achados para um concurso*/
        private function setConcursoPagination(){

            foreach ($this->concursoResult->getListaDeVagas() as $vaga){
                $i = $this->inicio;
                $j = $i;
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