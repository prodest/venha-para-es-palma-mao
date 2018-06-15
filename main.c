/*NOME: PABLO DOS SANTOS GARAJAU
NUMERO DE INSCRIÇÃO: 1031854*/

//
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>
#include "biblioteca.h"

int main(void){
	int i = 0;
	int qtd_linhas_candidatos = 0;
	int qtd_linhas_concursos = 0;
	int tam_cpf_busca = 20;
	int tam_cod_concurso = 25;
	int opcao = 0;
	char linha[500];
	char* cpf_busca = "";
	char* cod_concurso = "";
	FILE* arq;

	qtd_linhas_candidatos = leQtdLinhasArq("candidatos.txt");
	qtd_linhas_concursos = leQtdLinhasArq("concursos.txt");

	candidatos* Candidato = (candidatos*)malloc(qtd_linhas_candidatos * sizeof(candidatos));
	concursos* Concurso = (concursos*)malloc(qtd_linhas_concursos * sizeof(concursos));
	profissoes* Profissao = (profissoes*)malloc(qtd_linhas_candidatos * sizeof(profissoes));
	profissoes_concursos* Prof_Concursos = (profissoes_concursos*)malloc(qtd_linhas_concursos * sizeof(profissoes_concursos));

	alocaCandidatos(Candidato, qtd_linhas_candidatos);
	alocaConcursos(Concurso, qtd_linhas_concursos);
	alocaProfissoes(Profissao, qtd_linhas_candidatos);
	alocaProfissoesConcursos(Prof_Concursos, qtd_linhas_concursos);

	arq = fopen("candidatos.txt", "r");
	if(arq == NULL){
		printf("Erro ao abrir o arquivo candidatos.txt!\n");
		exit(1);
	}
	i = 0;
	while( fgets(linha, sizeof(linha), arq)!= NULL && i < qtd_linhas_candidatos ){
		separaNomeCandidatos(linha, Candidato, i);
		separaDataCandidatos(linha, Candidato, i);
		separaCpfCandidatos(linha, Candidato, i);
		separaProfissoesCandidatos(linha, Candidato, i);
		separaProfissoesPorPartes(Profissao, Candidato, i);
		i++;
	}
	fclose(arq);

	i = 0;
	arq = fopen("concursos.txt", "r");
	if(arq == NULL){
		printf("Erro ao abrir arquivo concursos.txt!\n");
		exit(1);
	}
	while( fgets(linha, sizeof(linha), arq)!= NULL && i < qtd_linhas_concursos ){
		separaNomeConcursos(linha, Concurso, i);
		separaDataConcursos(linha, Concurso, i);
		separaNumConcursos(linha, Concurso, i);
		separaProfissoesConcursos(linha, Concurso, i);
		separaConcursoPorPartes(Prof_Concursos, Concurso, i);
		i++;
	}

	printf(" - Pesquisar por CPF digite 1\n - Pesquisar por número do concurso digite 2\nDigite a opção desejada:");
	scanf("%d", &opcao);

	if(opcao == 1){
		cpf_busca = (char*)malloc(tam_cpf_busca * sizeof(char));
		printf("Digite o cpf do candidato(ex: 177.666.000-14): ");
		scanf(" %[^\n]", cpf_busca);
		i = busca_cpf(Candidato, Concurso, cpf_busca, qtd_linhas_candidatos);
		if(i == -1){
			printf("Cpf não encontrado!\n");
		}else{
			printf("Nome: %s\n", Candidato[i].nome);
			printf("Data de Nascimento: %s\n", Candidato[i].data);
			printf("CPF: %s\n", Candidato[i].cpf);
			printf("Profissões: [%s]\n\n", Candidato[i].profissoes);
			comparaProfissoes(Candidato, Concurso, Prof_Concursos, Profissao, i, qtd_linhas_concursos);
			// free(cpf_busca);
		}
	}else if(opcao == 2){
		cod_concurso = (char*)malloc(tam_cod_concurso * sizeof(char));
		printf("Digite o codigo do concurso: ");
		scanf("%s", cod_concurso);
		i = buscaCodigoConcurso(Concurso, cod_concurso, qtd_linhas_concursos);
		if(i == -1){
			printf("Código do concurso não localizado!\n");
		}else{
			printf("Órgão: %s\n", Concurso[i].nome_concurso);
			printf("Edital: %s\n", Concurso[i].data_concurso);
			printf("Código: %s\n", Concurso[i].num_concurso);
			printf("Vagas: [%s]\n", Concurso[i].profissoes_concurso);
			// free(cod_concurso);
		}
	}

	limpaConcursos(Concurso, qtd_linhas_concursos);
	limpaCandidatos(Candidato, qtd_linhas_candidatos);
	limpaProfissoes(Profissao, qtd_linhas_candidatos);
	limpaProfissoesConcursos(Prof_Concursos, qtd_linhas_concursos);
	fclose(arq);
	return 0;
}
