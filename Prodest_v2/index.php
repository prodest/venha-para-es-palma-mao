<?php
	require 'lib/core_prodest.php';


	//Array de candidatos e de concursos BD
	$concursos_candidatos= array();
	$candidatos=array();
	$concursos=array();

	//Campo de busca
	$codigo="";

	//Contadores
	$inicio=0;
	$inicio2=0;
	$total=0;


	$erro= false;


	//Buscar Candidatos
	
	if (post('submit')) {
		
		// Verifica se o POST tem algum valor
		if ( !isset( $_POST ) || empty( $_POST ) ) {
			$erro = 'Nada foi postado.';
		}

		// Cria as variáveis dinamicamente
		foreach ( $_POST as $chave => $valor ) {
			// Remove todas as tags HTML
			// Remove os espaços em branco do valor
			$chave = trim( strip_tags( $valor ) );
			
			// Verifica se tem algum valor nulo
			if ( empty ( $valor ) ) {
				$erro = 'Existem campos em branco.';
			}
		}

		//$codigo recebe o valor pesquisado
		$codigo = post('codigo');

		// Verifica se $codigo realmente existe e se é um número. 
		// Também verifica se não existe nenhum erro anterior

		if ( ( ! isset( $codigo ) || ! is_numeric( $codigo ) ) && !$erro ) {
			$erro = 'O código deve ser um número.';
		}

		// Verifica se $codigo tem 11 números. 
		// Também verifica se não existe nenhum erro anterior
		if ( ( ! isset( $codigo ) ||  (strlen($codigo)!=11 )) && !$erro ) {
			$erro = 'O código deve possuir 11 números.';
		}


	
		 else {
			// Senão há erro, faz a pesquisa no Banco de Dados
	
			//Procura codigo do concurso no BD
			$concursos=  db::find('all', "SELECT * FROM concurso co INNER JOIN concurso_vaga cv ON co.id_concurso=cv.id_concurso INNER JOIN vaga v ON v.id_vaga=cv.id_vaga AND co.codigo = $codigo ORDER BY co.orgao");


			
			//Busca candidatos para cruzar com os dados da vaga
			$candidatos=  db::find('all', "SELECT * FROM candidato c INNER JOIN candidato_profissao cp ON c.id_candidato=cp.id_candidato INNER JOIN profissao p ON p.id_profissao=cp.id_profissao ORDER BY c.nome");

		

			//Busca candidatos
			foreach ($concursos as $concursos_vagas) {
				foreach ($candidatos as $candidatos_profissao) {
					if($concursos_vagas['nome_vaga'] == $candidatos_profissao['nome_profissao']){
						
						//Array de candidatos habilitados para o código buscado
						$concursos_candidatos[]=$candidatos_profissao;

						//Número de registros encontrados
						$total++;			
					}
				}
			}

			

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
        	<!--Div row-fluid 2-->
            <div class="row-fluid">
            	
            	<!--Menu lateral-->
                <div class="span3" id="sidebar">
                    <ul class="nav nav-list bs-docs-sidenav nav-collapse collapse">
                        <li class="active"> <a href="index.php"><i class="icon-chevron-right"></i>Consulta concurso</a>    </li>     <li> <a href="consulta_prodest_cpf.php"><i class="icon-chevron-right"></i>Consulta candidato</a>   </li>
                       
                    </ul>
                </div><!--/Menu lateral-->
                
                <<!--Conteúdo-->
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
								
										<!--Busca concurso-->
										<table cellpadding="0" cellspacing="0" border="0" class="table table-bordered " id="example">
											<form class="form-horizontal" method="post" >			
												<fieldset>
													
													<tr>	
														<td>Código do concurso: 

															<input type="text" class="span6" placeholder="Exemplo: 99999999999" id="codigo" name="codigo"> 
															<input name="submit" class="btn btn-primary" type="submit" value="Buscar">
															
															 <? if ($erro): ?>
															<strong> <?= $erro ?></strong>
															</div> <? endif ?>
														</td>
														

													</tr>

													
												</fieldset>
											</form>
													
										</table>

										<!--Resultado dados do concurso-->
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
												<?php $cont=$inicio; foreach ($concursos as $leconcurso):?>
													<tr>
														<td>	<?= ++$cont ?>  </td>
														<td>	<?= $leconcurso['orgao'] ?>	  </td>
														<td><?= $leconcurso['edital'] ?></td>
														<td><?= $leconcurso['codigo'] ?></td>
														<td><?= $leconcurso['nome_vaga'] ?></td>	
													</tr>

												<?php endforeach ?>
											</tbody>
										</table>	
													
									</div>
								</div>
                        </div> <!-- /block -->
                       
                    </div> <!-- /row fluid -->
                    
                  

                    <!-- row fluid - Resultado candidato-->
                    <div class="row-fluid">
                        
                            <!-- block -->
                            <div class="block">
                                <div class="navbar navbar-inner block-header">
									<div class="muted pull-left"> 
										Resultado da busca <?=$codigo?>
										
									</div>
									<div class="pull-right"><span class="label label-info"><?=$total?></span>	</div>
								</div>
								<div class="block-content collapse in">
									<div class="span12">

										<!--Dados dos candidatos habilitados-->
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
												<?php $cont=$inicio2; foreach ($concursos_candidatos as $usuario):?>
													<tr>
														<td>	<?= ++$cont ?>  </td>
														<td>	<?= $usuario['nome'] ?>	  </td>
														<td><?= $usuario['cpf'] ?></td>
														<td><?= $usuario['data_nascimento'] ?></td>
														<td><?= $usuario['nome_profissao'] ?></td>
													</tr>
														
												<?php endforeach ?>
																						</tbody>
										</table>
										
									</div> <!-- /span12 -->
								</div><!-- /block-content -->
							</div>
                        <!-- /block -->
                    </div> <!--/Row fluid Candidato-->
                    
				</div><!--/content 3-->
                    
            </div><!--/Div row-fluid 2-->
            <hr>
            <footer>
              	
            </footer>

        </div> <!--/Div container-fluid 1-->
       
	</body>
</html>