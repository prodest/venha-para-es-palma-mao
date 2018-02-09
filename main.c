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
char* separaProfissoes(char* string);
void imprime(candidatos* Candidato, int qtd);

//funcao principal
int main(void){
	int i = 0, qtd_linhas_candidatos = 0, qtd_linhas_concursos = 0;
	char linha[500];
	FILE* arq;

	qtd_linhas_candidatos = TAM;
	// qtd_linhas_candidatos = leQtdLinhasArq("candidatos.txt");
	// qtd_linhas_concursos = leQtdLinhasArq("concursos.txt");

	candidatos* Candidato = (candidatos*)malloc(qtd_linhas_candidatos * sizeof(candidatos));
	for(i = 0; i < qtd_linhas_candidatos; i++){
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

	while( fgets(linha, sizeof(linha), arq)!=NULL && i < qtd_linhas_candidatos ){
		Candidato[i].nome = separaNome(linha);
		Candidato[i].data = separaData(linha);
		Candidato[i].cpf = separaCpf(linha);
		Candidato[i].profissoes = separaProfissoes(linha);
		i++;
	}

	// printf("qtd_linhas_candidatos: %d\n", leQtdLinhasArq("candidatos.txt"));
	// printf("qtd_linhas_concursos: %d\n", leQtdLinhasArq("concursos.txt"));

	imprime(Candidato, qtd_linhas_candidatos);

	fclose(arq);
	limpa(Candidato, qtd_linhas_candidatos);
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

void imprime(candidatos* Candidato, int qtd){
	int i;

	for(i = 0; i < qtd; i++){
		printf("nome [%d]: %s\n", i+1, Candidato[i].nome);
		printf("data [%d]: %s\n", i+1, Candidato[i].data);
		printf("cpf [%d]: %s\n", i+1, Candidato[i].cpf);
		printf("profissoes [%d]: %s\n", i+1, Candidato[i].profissoes);
		printf("---------------------------------------\n");
	}
}

void limpa(candidatos* Candidato, int qtd){
	int i = 0;

	for(i = 0; i < qtd; i++){
		free(Candidato[i].nome);
		free(Candidato[i].data);
		free(Candidato[i].cpf);
		free(Candidato[i].profissoes);
	}
	free(Candidato);
}

char* separaNome(char* string){
	int i = 0, tam = 60;
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
	aux[j]='\0';

	return aux;
}

char* separaProfissoes(char* string){
	int cont = 0, i = 0, j = 0, tam = 100;
	char* aux = (char*)malloc(tam * sizeof(char));

	while(string[i] != ']'){
		if(string[i] == ' '){
			cont++;
		}
		if(cont > 3){
			if(string[i] != '['){
				aux[j] = string[i];
				j++;
			}
		}
		i++;
	}
	aux[j]='\0';

	return aux;
}

char* separaProfissoesPorParte(candidatos* Candidato){
	int i = 0;


}
