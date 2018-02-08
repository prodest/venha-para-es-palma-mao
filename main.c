/*nome:Pablo dos Santos Garajau
cpf: 16293627784*/
#include <stdio.h>
#include <string.h>
#include <stdlib.h>

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
void lerTudo();

//funcao principal
int main(void){
	int i;
	int qtd_linhas_arq;

	qtd_linhas_arq = leQtdLinhasArq("candidatos.txt");
	printf("O arquivo possui %d linhas\n", qtd_linhas_arq);

	candidatos* Candidato = (candidatos*)malloc(qtd_linhas_arq * sizeof(candidatos));
	for(i = 0; i < qtd_linhas_arq; i++){
		Candidato[i].nome = (char*)malloc(sizeof(char));
		Candidato[i].data = (char*)malloc(sizeof(char));
		Candidato[i].cpf = (char*)malloc(sizeof(char));
		Candidato[i].profissoes = (char*)malloc(sizeof(char));
	}

	lerTudo();
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

void lerTudo(){
	int i;
	FILE* arq;
	char linha[150];

	arq = fopen("candidatos.txt", "r");
	if(arq == NULL){
		printf("Error!\n");
		exit(1);
	}

	while( (fscanf(arq, "%[^\n]\n", linha)) != EOF){
		printf("%s\n", linha);
		i++;
		if(i == 10){
			break;
		}
	}

	fclose(arq);
}

candidatos* organiza(candidatos* Candidato){
	int cont = 0, i = 0;



	return Candidato;
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
