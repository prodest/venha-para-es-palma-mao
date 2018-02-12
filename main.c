#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>

//definindo um tamanho máximo para os testes
#define TAM 5

//estrutura para armazenar meus dados lidos do arquivo candidatos.txt
typedef struct{
	char* nome;
	char* data;
	char* cpf;
	char* profissoes;
} candidatos;

//estrutura para armazenar dados lidos do arquivo concursos.txt
typedef struct{
	char* nome_concurso;
	char* data_concurso;
	char* num_concurso;
	char* profissoes_concurso;
} concursos;

//cabecalho das minhas funcoes
int leQtdLinhasArq(char* arquivo);
candidatos* separaNome(char* string, candidatos* Candidato, int j);
candidatos* separaData(char* string, candidatos* Candidato, int j);
candidatos* separaCpf(char* string, candidatos* Candidato, int j);
candidatos* separaProfissoes(char* string, candidatos* Candidato, int j);
candidatos* alocaCandidatos(candidatos* Candidato, int qtd);
concursos* alocaConcursos(concursos* Concurso, int qtd);
void limpaCandidatos(candidatos* Candidato, int qtd);
void limpaConcursos(concursos* Concurso, int qtd);
void imprime(candidatos* Candidato, int qtd);

//funcao principal
int main(void){
	int i = 0;
	int qtd_linhas_candidatos = 0;
	int qtd_linhas_concursos = 0;
	char linha[500];
	FILE* arq;

	qtd_linhas_candidatos = TAM;
	qtd_linhas_concursos = TAM;
	// qtd_linhas_candidatos = leQtdLinhasArq("candidatos.txt");
	// qtd_linhas_concursos = leQtdLinhasArq("concursos.txt");

	//alocando espaço para a minha estrutura que ira armazenar os dados do arquivo candidatos.txt
	candidatos* Candidato = (candidatos*)malloc(qtd_linhas_candidatos * sizeof(candidatos));
	concursos* Concurso = (concursos*)malloc(qtd_linhas_concursos * sizeof(concursos));

	//alocando os espaços na memoria para dados dos candidatos
	alocaCandidatos(Candidato, qtd_linhas_candidatos);

	//alocando os espaços na memoria para dados dos concursos
	alocaConcursos(Concurso, qtd_linhas_concursos);

	//abrindo arquivo candidatos.txt
	arq = fopen("candidatos.txt", "r");
	if(arq == NULL){
		printf("Erro ao abrir o arquivo para leitura!\n");
		exit(1);
	}
	//zerando o 'i' pois usarei a variavel novamente, ja que nao precisarei mais dela
	i = 0;

	//separando os dados do arquivo para a minha estrutura
	while( fgets(linha, sizeof(linha), arq)!=NULL && i < qtd_linhas_candidatos ){
		separaNome(linha, Candidato, i);
		separaData(linha, Candidato, i);
		separaCpf(linha, Candidato, i);
		separaProfissoes(linha, Candidato, i);
		// CadaProf[i].prof1 = separaProfissoesPorParte(Candidato[i].profissoes);
		i++;
	}

	// printf("qtd_linhas_candidatos: %d\n", leQtdLinhasArq("candidatos.txt"));
	// printf("qtd_linhas_concursos: %d\n", leQtdLinhasArq("concursos.txt"));

	//imprimindo os dados coletados do arquivo e armazenados na struct candidatos.txt
	imprime(Candidato, qtd_linhas_candidatos);

	//dando um free na memoria apos o uso
	limpaConcursos(Concurso, qtd_linhas_concursos);
	limpaCandidatos(Candidato, qtd_linhas_candidatos);
	fclose(arq);
	return 0;
}

//FUNCOES
//essa funcao le a qtd de linhas de qualquer arquivo
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

//funcao sem retorno para imprimir os dados armazenados na minha struct
void imprime(candidatos* Candidato, int qtd){
	int i = 0;

	for(i = 0; i < qtd; i++){
		printf("nome [%d]: %s\t%ld\n", i+1, Candidato[i].nome, strlen(Candidato[i].nome));
		printf("data [%d]: %s\t%ld\n", i+1, Candidato[i].data, strlen(Candidato[i].data));
		printf("cpf [%d]: %s\t%ld\n", i+1, Candidato[i].cpf, strlen(Candidato[i].cpf));
		printf("profissoes [%d]: %s\t%ld\n\n", i+1, Candidato[i].profissoes, strlen(Candidato[i].profissoes));
		// printf("separaProfissoesPorParte: %s\n\n", separaProfissoesPorParte(Candidato[i].profissoes));
	}
}

//funcao que libera espaco de memoria apos o uso
void limpaCandidatos(candidatos* Candidato, int qtd){
	int i = 0;

	for(i = 0; i < qtd; i++){
		free(Candidato[i].nome);
		free(Candidato[i].data);
		free(Candidato[i].cpf);
		free(Candidato[i].profissoes);
	}
	free(Candidato);
}

void limpaConcursos(concursos* Concurso, int qtd){
	int i = 0;

	for(i = 0; i < qtd; i++){
		free(Concurso[i].nome_concurso);
		free(Concurso[i].data_concurso);
		free(Concurso[i].num_concurso);
		free(Concurso[i].profissoes_concurso);
	}
	free(Concurso);
}

candidatos* separaNome(char* string, candidatos* Candidato, int j){
	int i = 0;

	while(!isdigit(string[i])){
		Candidato[j].nome[i] = string[i];
		i++;
	}

	//adicionando caracter que indica o fim de uma string
	Candidato[j].nome[i] ='\0';

	return Candidato;
}

//funcao para separar a data para a struct
candidatos* separaData(char* string, candidatos* Candidato, int j){
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
	Candidato[j].data[i]='\0';

	return Candidato;
}

//funcao para separar o cpf para a struct
candidatos* separaCpf(char* string, candidatos* Candidato, int j){
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

//funcao para separar a linha de profissoes para a struct
candidatos* separaProfissoes(char* string, candidatos* Candidato, int j){
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

//funcao que separa as linha de profissões por parte para cada candidato do arquivo candidatos.txt
char* separaProfissoesPorParte(char* string, char* aux){
	int i = 0;
	int tam = 50;
	int cont = 0;
	char* prof = (char*)malloc(tam * sizeof(char));

	// printf("teste> %s\n", string);

	for(i = 0; i < strlen(string); i++){
		if(string[i] != ','){
			prof[i] = string[i];
		}else{
			cont++;
		}
	}
	prof[i] = '\0';
	// printf("cont: %d\n", cont);

	return prof;
}
