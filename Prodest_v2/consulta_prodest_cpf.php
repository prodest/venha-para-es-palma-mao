<?php
	require 'lib/core_prodest.php';

	
	//Array de candidatos e de concursos BD
	$candidatos_concursos=array();
	$candidatos=array();
	$concursos=array();

	//Campo de busca
	$cpf ="";

	//Contadores
	$inicio=0; 
	$inicio2=0;
	$total=0;
	$erro=false;
	

	//Buscar Concurso
	if (POST('submit2')) {
    
        //$cpf recebe o valor pesquisado
        $cpf= $_POST["cpf"];
         
         // Verifica se o POST tem algum valor
		if ( isset($cpf) &&!empty($cpf)) {              
            if(strlen($cpf)==14)
            {
                     //Senão há erro, faz a pesquisa no Banco de Dados
                    //Procura cpf no BD
                    $candidatos=  db::find('all', "SELECT * FROM candidato c INNER JOIN candidato_profissao cp ON c.id_candidato=cp.id_candidato INNER JOIN profissao p ON p.id_profissao=cp.id_profissao AND c.cpf LIKE '%".$cpf."%' ORDER BY c.nome");


                    //Busca concursos para cruzar os dados da profissão
                    $concursos=  db::find('all', "SELECT * FROM concurso co INNER JOIN concurso_vaga cv ON co.id_concurso=cv.id_concurso INNER JOIN vaga v ON v.id_vaga=cv.id_vaga ORDER BY co.orgao");

                    //Busca vagas
                    foreach ($candidatos as $candidatos_profissao) {
                        foreach ($concursos as $concursos_vagas) {
                            if($candidatos_profissao['nome_profissao']==$concursos_vagas['nome_vaga']){

                                //Array de concursos com vagas para o cpf buscado
                                $candidatos_concursos[]=$concursos_vagas;

                                //Número de registros encontrados
                                $total++;
                            }
                        }
                    }
            }
            else {
                $erro = 'CPF inválido! Digite o cpf conforme o exemplo: 999.999.999-99';
            }       

          }
          else{
              $erro = 'Existem campos em branco.';
             
          }
		}
	
?>


<!DOCTYPE html>
<html lang="en">
    <head>
		<?php	include("cabecalho_prodest.php");?>
		
    </head>
	
    <body>
		<!--Div container-fluid 1-->
        <div class="container-fluid">
        	<!--Div row-fluid 1-->
            <div class="row-fluid">

            	<!-- Menu lateral-->
                <div class="span3" id="sidebar">
                    <ul class="nav nav-list bs-docs-sidenav nav-collapse collapse">
                        <li > <a href="index.php"><i class="icon-chevron-right"></i>Consulta concurso</a>   </li>
                        <li class="active"> <a href="consulta_prodest_cpf.php"><i class="icon-chevron-right"></i>Consulta candidato</a>   </li>
                       
                    </ul>
                </div><!--/Menu lateral-->
                
                <!--Conteúdo-->
                <div class="span9" id="content">
                                        
                  
                  <!-- row fluid - Pesquisa-->
                    <div class="row-fluid">
                        
                            <!-- block -->
                            <div class="block">                       	
                                <div class="navbar navbar-inner block-header">
									<div class="muted pull-left">Consulta</div>
								</div>

								<div class="block-content collapse in">
									<div class="span12">
										
										<!--Busca candidato-->
										<table cellpadding="0" cellspacing="0" border="0" class="table table-bordered" id="example">
											<form class="form-horizontal" method="post" >			
												<fieldset>
													<tr>
														<td>CPF do candidato:
															<input type="text" class="span6" placeholder="Exemplo: 999.999.999-99" name="cpf" id="cpf">

															<input name="submit2" class="btn btn-primary" type="submit" value="Buscar">

															 <? if ($erro): ?>
															<strong> <?= $erro ?></strong>
															</div> <? endif ?>
														</td>	
													</tr>
												</fieldset>
											</form>										
										</table>
										
										<!--Resultado dados do candidato-->
										<table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered" id="example">
											<thead>
												<tr>
													<th width="15px" >Item</th>
													<th >Nome</th>
													<th >Cpf</th>
													<th >Nascimento</th>
													<th >Profissão</th>
														</tr>
											</thead>
											<tbody>
												<?php $cont=$inicio; foreach ($candidatos as $usuario):?><!--Lygia-->
													<tr>
														<td>	<?= ++$cont ?>  </td>
														<td>	<?= $usuario['nome'] ?>	  </td>
														<td><?= $usuario['cpf'] ?></td>
														<td><?= $usuario['data_nascimento'] ?></td>
														<td><?= $usuario['nome_profissao'] ?></td>
												<?php endforeach ?>
																						</tbody>
										</table>

									</div>
								</div>
                        </div> <!-- /block -->
                       
                    </div> <!-- /row fluid -->
                    
                   

                     <!-- row fluid - Resultado Concurso-->
                    <div class="row-fluid">
                        
                            <!-- block -->
                            <div class="block">
                                <div class="navbar navbar-inner block-header">
									<div class="muted pull-left">Concursos disponíveis para o CPF: <?= $cpf  ?>  </div>
									<div class="pull-right"><span class="label label-info"><?=$total?></span>	</div>
								</div>
								<div class="block-content collapse in">
									<div class="span12">

										<!--Dados dos dos concursos com vagas-->
										<table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered" id="example">
											<thead>
												<tr>
													<th width="15px" >Item</th>
													<th >Orgao</th>
													<th >Edital</th>
													<th >Codigo</th>
													<th >Vagas</th>
												</tr>
											</thead>
											<tbody>
												<?php $cont=$inicio2; foreach ($candidatos_concursos as $leconcurso):?>																<tr>
														<td>	<?= ++$cont ?>  </td>
														<td>	<?= $leconcurso['orgao'] ?>	  </td>
														<td><?= $leconcurso['edital'] ?></td>
														<td><?= $leconcurso['codigo'] ?></td>
														
														<td><?= $leconcurso['nome_vaga'] ?></td>
														
													</tr>
														
												<?php endforeach ?>
											</tbody>
										</table>	
									</div> <!-- /span12 -->
								</div><!-- /block-content -->
							</div><!-- /block -->         
                    </div> <!--/Row fluid-->
                    
				</div><!--/Conteúdo-->
                    
            </div><!--/Div row-fluid 1-->
            <hr>
            <footer>
               
            </footer>

        </div> <!--/Div container-fluid 1-->
       
	</body>
</html>