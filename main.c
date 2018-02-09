#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>

#define TAM 5

//estrutura para armazenar meus dados lidos dos arquivos
typedef struct{
	char* nome;
	char* profissoes;
	char* data;
	char* cpf;
} candidatos;

//cabecalho das minhas funcoes
int leQtdLinhasArq(char* arquivo);
void limpa(candidatos* Candidato, int qtd);
char* separaNome(char* string);
char* separaData(char* string);
char* separaCpf(char* string);

//funcao principal
int main(void){
	int i = 0, qtd_linhas_arq = 0;
	char linha[500];
	FILE* arq;

	qtd_linhas_arq = TAM;

	candidatos* Candidato = (candidatos*)malloc(qtd_linhas_arq * sizeof(candidatos));
	for(i = 0; i < qtd_linhas_arq; i++){
		Candidato[i].nome = (char*)malloc(sizeof(char));
		Candidato[i].data = (char*)malloc(sizeof(char));
		Candidato[i].cpf = (char*)malloc(sizeof(char));
		Candidato[i].profissoes = (char*)malloc(sizeof(char));
	}

	arq = fopen("candidatos.txt", "r");
	if(arq == NULL){
		printf("Erro ao abrir o arquivo para leitura!\n");
		exit(1);
	}
	i = 0;

	while( fgets(linha, sizeof(linha), arq)!=NULL && i < TAM ){
		// printf("%s", linha);
		i++;
		printf("retorno separaNome: %s\n", separaNome(linha));
		printf("retorno separaData: %s\n", separaData(linha));
		printf("retorno separaData: %s\n", separaCpf(linha));
	}
	printf("%d\n", i);

	fclose(arq);
	limpa(Candidato, qtd_linhas_arq);
	return 0;
}

//funcoes
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

void limpa(candidatos* Candidato, int qtd){
	int i;

	for(i = 0; i < qtd; i++){
		free(Candidato[i].nome);
		free(Candidato[i].data);
		free(Candidato[i].cpf);
		free(Candidato[i].profissoes);
	}
	free(Candidato);
}

char* separaNome(char* string){
	int i = 0, tam = 50;
	char* aux = (char*)malloc(tam * sizeof(char));

	while(!isdigit(string[i])){
		aux[i] = string[i];
		i++;
	}
	//adicionando caracter que indica o fim de uma string
	aux[i]='\0';
	return aux;
}

char* separaData(char* string){
	int cont = 0, i = 0, j = 0, tam = 30;
	char* aux = (char*)malloc(tam * sizeof(char));

	while(cont < 3){
		if(string[i] == ' '){
			cont++;
		}
		if(cont == 2){
			aux[j] = string[i];
			j++;
		}
		i++;
	}
	//adicionando caracter que indica o fim de uma string
	aux[j]='\0';

	return aux;
}

char* separaCpf(char* string){
	int cont = 0, i = 0, j = 0, tam = 30;
	char* aux = (char*)malloc(tam * sizeof(char));

	while(cont < 4){
		if(string[i] == ' '){
			cont++;
		}
		if(cont == 3){
			aux[j] = string[i];
			j++;
		}
		i++;
	}
	//adicionando caracter que indica o fim de uma string
	aux[j]='\0';

	return aux;
}
