/*NESSE ARQUIVO FICARAM TODAS AS MINHAS FUNCOES USADAS NO MAIN.C*/
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>
#include "biblioteca.h"

//################FUNCOES################

//
int leQtdLinhasArq(char* arquivo){
	FILE* arq;
	char ch;
	int cont = 0;

	arq = fopen(arquivo, "r");
	if(arq == NULL){
		printf("Error: abrir arquivo para ler linhas\n");
		exit(1);
	}

	while((ch = fgetc(arq)) != EOF){
		if(ch == '\n'){
			cont++;
		}
	}

	fclose(arq);
	return cont;
}

//
void imprimeCandidatos(candidatos* Candidato, int qtd){
	int i = 0;

	for(i = 0; i < qtd; i++){
		printf("nome_candidato [%d]: %s\t%ld\n", i+1, Candidato[i].nome, strlen(Candidato[i].nome));
		printf("data_candidato [%d]: %s\t%ld\n", i+1, Candidato[i].data, strlen(Candidato[i].data));
		printf("cpf_candidato [%d]: %s\t%ld\n", i+1, Candidato[i].cpf, strlen(Candidato[i].cpf));
		printf("profissoes_candidato [%d]: %s\t%ld\n\n", i+1, Candidato[i].profissoes, strlen(Candidato[i].profissoes));
	}
}

//
void imprimeProfissaoCandidatoPorPartes(profissoes* Profissao, int qtd){
	int i = 0;

	for(i = 0; i < qtd; i++){
		printf("Candidato [%d]:\nprof1: %s\ttam: %ld\n", i+1, Profissao[i].prof1, strlen(Profissao[i].prof1));
		printf("prof2: %s\ttam: %ld\n", Profissao[i].prof2, strlen(Profissao[i].prof2));
		printf("prof3: %s\ttam: %ld\n\n", Profissao[i].prof3, strlen(Profissao[i].prof3));
	}
}

//
void imprimeProfissaoConcursosPorPartes(profissoes_concursos* Prof_Concursos, int qtd){
	int i = 0;

	for(i = 0; i < qtd; i++){
		printf("Concurso [%d]\nprof_concurso1: %s\t %ld\n", i+1, Prof_Concursos[i].prof_concurso1, strlen(Prof_Concursos[i].prof_concurso1));
		printf("prof_concurso2: %s\t %ld\n", Prof_Concursos[i].prof_concurso2, strlen(Prof_Concursos[i].prof_concurso2));
		printf("prof_concurso3: %s\t %ld\n\n", Prof_Concursos[i].prof_concurso3, strlen(Prof_Concursos[i].prof_concurso3));
	}
}

//
void imprimeConcursos(concursos* Concurso, int qtd){
	int i = 0;

	for(i = 0; i < qtd; i++){
		printf("nome_concurso [%d]: %s\t%ld\n", i+1, Concurso[i].nome_concurso, strlen(Concurso[i].nome_concurso));
		printf("data_concurso [%d]: %s\t%ld\n", i+1, Concurso[i].data_concurso, strlen(Concurso[i].data_concurso));
		printf("num_concurso [%d]: %s\t%ld\n", i+1, Concurso[i].num_concurso, strlen(Concurso[i].num_concurso));
		printf("profissoes [%d]: %s\t%ld\n\n", i+1, Concurso[i].profissoes_concurso, strlen(Concurso[i].profissoes_concurso));
	}
}

//
void limpaCandidatos(candidatos* Candidato, int qtd){
	int i = 0;

	for(i = 0; i < qtd; i++){
		free(Candidato[i].nome);
		free(Candidato[i].data);
		free(Candidato[i].cpf);
		free(Candidato[i].profissoes);
		Candidato[i].nome = NULL;
		Candidato[i].data = NULL;
		Candidato[i].cpf = NULL;
		Candidato[i].profissoes = NULL;
	}
	free(Candidato);
}

//
void limpaConcursos(concursos* Concurso, int qtd){
	int i = 0;

	for(i = 0; i < qtd; i++){
		free(Concurso[i].nome_concurso);
		free(Concurso[i].data_concurso);
		free(Concurso[i].num_concurso);
		free(Concurso[i].profissoes_concurso);
		Concurso[i].nome_concurso = NULL;
		Concurso[i]. data_concurso = NULL;
		Concurso[i].num_concurso = NULL;
		Concurso[i].profissoes_concurso = NULL;
	}
	free(Concurso);
}

//
void limpaProfissoes(profissoes* Profissao, int qtd){
	int i = 0;

	for(i = 0; i < qtd; i++){
		free(Profissao[i].prof1);
		free(Profissao[i].prof2);
		free(Profissao[i].prof3);
		Profissao[i].prof1 = NULL;
		Profissao[i].prof2 = NULL;
		Profissao[i].prof3 = NULL;
	}
	free(Profissao);
}

//
void limpaProfissoesConcursos(profissoes_concursos* Prof_Concurso, int qtd){
	int i = 0;

	for(i = 0; i < qtd; i++){
		free(Prof_Concurso[i].prof_concurso1);
		free(Prof_Concurso[i].prof_concurso2);
		free(Prof_Concurso[i].prof_concurso3);
		Prof_Concurso[i].prof_concurso1 = NULL;
		Prof_Concurso[i].prof_concurso2 = NULL;
		Prof_Concurso[i].prof_concurso3 = NULL;
	}
	free(Prof_Concurso);
}

//
candidatos* separaNomeCandidatos(char* string, candidatos* Candidato, int j){
	int i = 0;

	while(!isdigit(string[i])){
		Candidato[j].nome[i] = string[i];
		i++;
	}

	Candidato[j].nome[i] ='\0';

	return Candidato;
}

//
concursos* separaNomeConcursos(char* string, concursos* Concurso, int j){
	int i = 0;

	while(!isdigit(string[i])){
		Concurso[j].nome_concurso[i] = string[i];
		i++;
	}

	Concurso[j].nome_concurso[i] ='\0';

	return Concurso;
}

//
candidatos* separaDataCandidatos(char* string, candidatos* Candidato, int j){
	int cont = 0;
	int i = 0;
	int k = 0;

	while(cont < 3){
		if(string[i] == ' '){
			cont++;
		}
		if(cont == 2){
			Candidato[j].data[k] = string[i];
			k++;
		}
		i++;
	}
	Candidato[j].data[k]='\0';

	return Candidato;
}

//
concursos* separaDataConcursos(char* string, concursos* Concurso, int j){
	int cont = 0;
	int i = 0;
	int k = 0;

	while(string[i] != '\0'){
		if(string[i] == ' '){
			cont++;
		}
		i++;
		if(cont == 1){
			if(string[i] != ' '){
				Concurso[j].data_concurso[k] = string[i];
			k++;
			}
		}
	}
	Concurso[j].data_concurso[k]='\0';

	return Concurso;
}

//
candidatos* separaCpfCandidatos(char* string, candidatos* Candidato, int j){
	int cont = 0;
	int i = 0;
	int k = 0;

	while(cont < 4){
		if(string[i] == ' '){
			cont++;
		}
		if(cont == 3){
			Candidato[j].cpf[k] = string[i];
			k++;
		}
		i++;
	}
	Candidato[j].cpf[k]='\0';

	return Candidato;
}

//
concursos* separaNumConcursos(char* string, concursos* Concurso, int j){
	int cont = 0;
	int i = 0;
	int k = 0;

	while(string[i] != '\0'){
		if(string[i] == ' '){
			cont++;
		}
		i++;
		if(cont == 2){
			if(string[i] != ' '){
				Concurso[j].num_concurso[k] = string[i];
			k++;
			}
		}
	}
	Concurso[j].num_concurso[k]='\0';

	return Concurso;
}

//
candidatos* separaProfissoesCandidatos(char* string, candidatos* Candidato, int j){
	int cont = 0;
	int i = 0;
	int k = 0;

	while(string[i] != ']'){
		if(string[i] == ' '){
			cont++;
		}
		if(cont > 3){
			if(string[i] != '['){
				Candidato[j].profissoes[k] = string[i];
				k++;
			}
		}
		i++;
	}
	Candidato[j].profissoes[k] = '\0';

	return Candidato;
}

//
concursos* separaProfissoesConcursos(char* string, concursos* Concurso, int j){
	int cont = 0;
	int i = 0;
	int k = 0;

	while(string[i] != ']'){
		if(string[i] == ' '){
			cont++;
		}
		if(cont > 2){
			if(string[i] != '['){
				Concurso[j].profissoes_concurso[k] = string[i];
				k++;
			}
		}
		i++;
	}
	Concurso[j].profissoes_concurso[k] = '\0';

	return Concurso;
}

//
candidatos* alocaCandidatos(candidatos* Candidato, int qtd){
	int i = 0;
	int tam_nome = 30;
	int tam_data = 20;
	int tam_cpf = 20;
	int tam_profissoes = 80;

	for(i = 0; i < qtd; i++){
		Candidato[i].nome = (char*)malloc(tam_nome * sizeof(char));
		Candidato[i].data = (char*)malloc(tam_data * sizeof(char));
		Candidato[i].cpf = (char*)malloc(tam_cpf * sizeof(char));
		Candidato[i].profissoes = (char*)malloc(tam_profissoes * sizeof(char));
	}

	return Candidato;
}

//
profissoes* alocaProfissoes(profissoes* Profissao, int qtd){
	int i = 0;
	int tam = 80;

	for(i = 0; i < qtd; i++){
		Profissao[i].prof1 = (char*)malloc(tam * sizeof(char));
		Profissao[i].prof2 = (char*)malloc(tam * sizeof(char));
		Profissao[i].prof3 = (char*)malloc(tam * sizeof(char));
	}

	return Profissao;
}

//
profissoes_concursos* alocaProfissoesConcursos(profissoes_concursos* Prof_Concursos, int qtd){
	int i = 0;
	int tam = 80;

	for(i = 0; i < qtd; i++){
		Prof_Concursos[i].prof_concurso1 = (char*)malloc(tam * sizeof(char));
		Prof_Concursos[i].prof_concurso2 = (char*)malloc(tam * sizeof(char));
		Prof_Concursos[i].prof_concurso3 = (char*)malloc(tam * sizeof(char));
	}

	return Prof_Concursos;
}

//
concursos* alocaConcursos(concursos* Concurso, int qtd){
	int i = 0;
	int tam_nome_concurso = 10;
	int tam_data_concurso = 10;
	int tam_num_concurso = 15;
	int tam_profissoes_concurso = 80;

	for(i = 0; i < qtd; i++){
		Concurso[i].nome_concurso = (char*)malloc(tam_nome_concurso * sizeof(char));
		Concurso[i].data_concurso = (char*)malloc(tam_data_concurso * sizeof(char));
		Concurso[i].num_concurso = (char*)malloc(tam_num_concurso * sizeof(char));
		Concurso[i].profissoes_concurso = (char*)malloc(tam_profissoes_concurso * sizeof(char));
	}

	return Concurso;
}

//
int busca_cpf(candidatos* Candidato, concursos* Concurso, char* cpf, int qtd_candidatos){
	int i = 0;
	int cont = 0;
	int flag = 0;

	for(i = 0; i < qtd_candidatos; i++){
		if( (comparaString(Candidato[i].cpf, cpf)) == 14){
			cont++;
			flag = i;
			break;
		}
	}

	if(cont == 0){
		return -1;
	}else{
		return flag;
	}
}

//
int comparaString(char* string1, char* string2){
	int i = 0;
	int j = 0;
	int cont = 0;

	for(i = 0; string1[i] != '\0'; i++){
		if(string1[i] == string2[j]){
			cont++;
			j++;
		}
	}

	return cont;
}

//
int comparaProfissoes(candidatos* Candidato, concursos* Concurso, profissoes_concursos* Prof_Concursos, profissoes* Profissao, int flag, int qtd){
	int i = 0;
	int cont = 0;
	int lin = 0;

	printf("Concursos Vigentes:\n");
	printf("|Órgãos\t\t|Código\t\t|Editais\n");
	for(i = 0; i < qtd; i++){
		if( (strcmp(Profissao[flag].prof1, Prof_Concursos[i].prof_concurso1)) == 0){
			cont++;
		}else if( (strcmp(Profissao[flag].prof1, Prof_Concursos[i].prof_concurso2)) == 0 ){
			cont++;
		}else if( (strcmp(Profissao[flag].prof1, Prof_Concursos[i].prof_concurso3)) == 0 ){
			cont++;
		}else if( (strcmp(Profissao[flag].prof2, Prof_Concursos[i].prof_concurso1)) == 0 ){
			cont++;
		}else if( (strcmp(Profissao[flag].prof2, Prof_Concursos[i].prof_concurso2)) == 0 ){
			cont++;
		}else if( (strcmp(Profissao[flag].prof2, Prof_Concursos[i].prof_concurso3)) == 0 ){
			cont++;
		}else if( (strcmp(Profissao[flag].prof3, Prof_Concursos[i].prof_concurso1)) == 0 ){
			cont++;
		}else if( (strcmp(Profissao[flag].prof3, Prof_Concursos[i].prof_concurso2)) == 0 ){
			cont++;
		}else if( (strcmp(Profissao[flag].prof3, Prof_Concursos[i].prof_concurso3)) == 0 ){
			cont++;
		}

		if(cont > 0){
			printf("%.9s   \t%.9s   \t%.15s\n", Concurso[i].nome_concurso, Concurso[i].data_concurso, Concurso[i].num_concurso);
			cont = 0;
			lin++;
		}else{
			cont = 0;
		}
	}
	printf("\nQuantidade de concursos: %d\n", lin);

	return cont;
}

//
profissoes* separaProfissoesPorPartes(profissoes* Profissao, candidatos* Candidato, int pont){
	int i = 0;
	int j = 0;
	int k = 0;
	int flag = 0;
	int cont = 0;

	for(i = 0; Candidato[pont].profissoes[i] != '\0'; i++){
		if(Candidato[pont].profissoes[i] == ','){
			cont++;
		}

		if(cont == 0){
			Profissao[pont].prof1[i] = Candidato[pont].profissoes[i];
			flag++;
		}else if(cont == 1){
			if(Candidato[pont].profissoes[i] != ',' && Candidato[pont].profissoes[i] != '\0'){
				Profissao[pont].prof2[j] = Candidato[pont].profissoes[i];
				j++;
			}
		}else if(cont == 2){
			if(Candidato[pont].profissoes[i] != ',' && Candidato[pont].profissoes[i] != '\0'){
				Profissao[pont].prof3[k] = Candidato[pont].profissoes[i];
				k++;
			}
		}
	}
		Profissao[pont].prof1[flag] = '\0';
		Profissao[pont].prof2[j] = '\0';
		Profissao[pont].prof3[k] = '\0';

	return Profissao;
}

//
profissoes_concursos* separaConcursoPorPartes(profissoes_concursos* Prof_Concursos, concursos* Concurso, int pont){
	int i = 0;
	int j = 0;
	int k = 0;
	int flag = 0;
	int cont = 0;

	for(i = 0; Concurso[pont].profissoes_concurso[i] != '\0'; i++){
		if(Concurso[pont].profissoes_concurso[i] == ','){
			cont++;
		}

		if(cont == 0){
			Prof_Concursos[pont].prof_concurso1[i] = Concurso[pont].profissoes_concurso[i];
			flag++;
		}else if(cont == 1){
			if(Concurso[pont].profissoes_concurso[i] != ',' && Concurso[pont].profissoes_concurso[i] != '\0'){
				Prof_Concursos[pont].prof_concurso2[j] = Concurso[pont].profissoes_concurso[i];
				j++;
			}
		}else if(cont == 2){
			if(Concurso[pont].profissoes_concurso[i] != ',' && Concurso[pont].profissoes_concurso[i] != '\0'){
				Prof_Concursos[pont].prof_concurso3[k] = Concurso[pont].profissoes_concurso[i];
				k++;
			}
		}
	}if(cont == 0){
		Prof_Concursos[pont].prof_concurso1[flag] = '\0';
		Prof_Concursos[pont].prof_concurso2[j] = 'a';
		Prof_Concursos[pont].prof_concurso2[j+1] = '\0';
		Prof_Concursos[pont].prof_concurso3[k] = 'b';
		Prof_Concursos[pont].prof_concurso3[k+1] = '\0';
	}
	if(cont == 1){
		Prof_Concursos[pont].prof_concurso1[flag] = '\0';
		Prof_Concursos[pont].prof_concurso2[j] = '\0';
		Prof_Concursos[pont].prof_concurso3[k] = 'b';
		Prof_Concursos[pont].prof_concurso3[k+1] = '\0';
	}
	if(cont == 2){
		Prof_Concursos[pont].prof_concurso1[flag] = '\0';
		Prof_Concursos[pont].prof_concurso2[j] = '\0';
		Prof_Concursos[pont].prof_concurso3[k] = '\0';
	}

	return Prof_Concursos;
}

//
int buscaCodigoConcurso(concursos* Concurso, char* num_concurso, int qtd){
	int i = 0;

	for(i = 0; i < qtd; i++){
		if(num_concurso[i] != ' '){
			if( (comparaString(Concurso[i].num_concurso, num_concurso)) >= 10){
				return i;
		}
		}else{
			return -1;
		}
	}
}
