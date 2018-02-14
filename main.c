/*PROGRAMA QUE LE DOIS ARQUIVOS, UM COM OS CANDIDATOS E OUTRO COM OS CONCURSOS E RECEBE O CPF DE
UM CANDIDATO RETORNANDO CASO ELE EXISTA NA BASE DE DADOS QUAIS OS CONCURSOS VIGENTES PARA O PERFIL
DO CANDIDATO, OU SEJA, DE ACORDO COM AS PROFISSOES POR ELE ALMEJADA*/

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
	char linha[500];
	char* cpf_busca = "";
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

	cpf_busca = (char*)malloc(tam_cpf_busca * sizeof(char));
	printf("Digite o cpf do candidato(ex: 177.666.000-14): ");
	scanf(" %[^\n]", cpf_busca);

	i = busca_cpf(Candidato, Concurso, cpf_busca, qtd_linhas_candidatos);
	if(i == -1){
		printf("Cpf não encontrado!\n");
	}else{
		printf("Nome: %s\n", Candidato[i].nome);
		printf("Data de Nascimento: %s\n", Candidato[i].data);
		printf("CPF: %s\n\n", Candidato[i].cpf);
		comparaProfissoes(Candidato, Concurso, Prof_Concursos, Profissao, i);
	}

	limpaConcursos(Concurso, qtd_linhas_concursos);
	limpaCandidatos(Candidato, qtd_linhas_candidatos);
	limpaProfissoes(Profissao, qtd_linhas_candidatos);
	limpaProfissoesConcursos(Prof_Concursos, qtd_linhas_concursos);
	free(cpf_busca);


	fclose(arq);
	return 0;
}
