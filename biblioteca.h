/*TAD BIBLIOTECA.H*/

//ESTRUTURA PARA ALOCAR CANDIDATOS
typedef struct{
	char* nome;
	char* data;
	char* cpf;
	char* profissoes;
} candidatos;

//ESTRUTURA PARA ALOCAR CONCURSOS
typedef struct{
	char* nome_concurso;
	char* data_concurso;
	char* num_concurso;
	char* profissoes_concurso;
} concursos;

//ESTRUTURA PARA ALOCAR CADA PROFISSAO DE CADA CONCURSO SEPARADAMENTE
typedef struct{
	char* prof1;
	char* prof2;
	char* prof3;
} profissoes;

//ESTRUTURA PARA ALOCAR PROFISSOES DOS CONCURSOS SEPARADAMENTE
typedef struct{
	char* prof_concurso1;
	char* prof_concurso2;
	char* prof_concurso3;
} profissoes_concursos;

//CABECALHOS DAS FUNCOES
//FUNCAO QUE RETORNA A QUANTIDADE DE LINHAS DE UM ARQUIVO
int leQtdLinhasArq(char* arquivo);

//SEPARA NOME DOS CANDIDATOS
candidatos* separaNomeCandidatos(char* string, candidatos* Candidato, int j);

//SEPARA DATA DOS CANDIDATOS
candidatos* separaDataCandidatos(char* string, candidatos* Candidato, int j);

//SEPARA CPF DOS CANDIDATOS
candidatos* separaCpfCandidatos(char* string, candidatos* Candidato, int j);

//SEPARA PROFISSOES POR LINHA INTEIRA DOS CANDIDATOS
candidatos* separaProfissoesCandidatos(char* string, candidatos* Candidato, int j);

//ALOCA CANDIDATOS NA ESTRUTURA
candidatos* alocaCandidatos(candidatos* Candidato, int qtd);

//ALOCA CONCURSOS  NA ESTRUTURA
concursos* alocaConcursos(concursos* Concurso, int qtd);

//ALOCA PROFISSOES NA ESTRUTURA
profissoes* alocaProfissoes(profissoes* Profissao, int qtd);

//ALOCA PROFISSOES NA ESTRUTURA
profissoes_concursos* alocaProfissoesConcursos(profissoes_concursos* Prof_Concurso, int qtd);

//SEPARA NOME DOS CONCURSOS
concursos* separaNomeConcursos(char* string, concursos* Concurso, int j);

//SEPARA DATA DE VIGENCIA DOS CONCURSOS
concursos* separaDataConcursos(char* string, concursos* Concurso, int j);

//SEPARA NUMERO DE IDENTIFICACAO DOS CONCURSOS
concursos* separaNumConcursos(char* string, concursos* Concurso, int j);

//SEPARA PROFISSOES DOS CONCURSOS POR LINHA
concursos* separaProfissoesConcursos(char* string, concursos* Concurso, int j);

//SEPARA CADA PROFISSAO DOS CANDIDATOS SEPARADAMENTE
profissoes* separaProfissoesPorPartes(profissoes* Profissao, candidatos* Candidato, int pont);

//SEPARA CADA PROFISSAO DOS CONCURSOS SEPARADAMENTE
profissoes_concursos* separaConcursoPorPartes(profissoes_concursos* Prof_Concursos, concursos* Concurso, int pont);

//LIBERA ESTRUTURA ALOCADA CANDIDATOS
void limpaCandidatos(candidatos* Candidato, int qtd);

//LIBERA ESTRUTURA ALOCADA CONCURSOS
void limpaConcursos(concursos* Concurso, int qtd);

//LIBERA ESTRUTURA ALOCADA PROFISSOES
void limpaProfissoes(profissoes* Profissao, int qtd);

//LIBERA ESTRUTURA ALOCADA PROFISSOES DOS CONCURSOS
void limpaProfissoesConcursos(profissoes_concursos* Prof_Concurso, int qtd);

//IMPRIME CASO SEJA PRECISO TODOS OS CANDIDATOS E SEUS DADOS ALOCADOS
void imprimeCandidatos(candidatos* Candidato, int qtd);

//IMPRIME CASO SEJA PRECISO TODOS OS CONCURSOS E OS DADOS ALOCADOS
void imprimeConcursos(concursos* Concurso, int qtd);

//IMPRIME CASO SEJA PRECISO CADA PROFISSAO DE CADA CANDIDATO SEPARADAMENTE
void imprimeProfissaoCandidatoPorPartes(profissoes* Profissao, int qtd);

//IMPRIME CASO SEJA PRECISO CADA PROFISSAO DE CADA CONCURSO SEPARADAMENTE
void imprimeProfissaoConcursosPorPartes(profissoes_concursos* Prof_Concursos, int qtd);

//BUSCA SE O CPF ESTA NA BASE DE DADOS
int busca_cpf(candidatos* Candidato, concursos* Concurso, char* cpf, int qtd_candidatos);

//COMPARA STRING CPF
int comparaString(char* string1, char* string2);

//COMPARA PROFISSOES
int comparaProfissoes(candidatos* Candidato, concursos* Concurso, profissoes_concursos* Prof_Concursos, profissoes* Profissao, int flag, int qtd);

//BUSCA CODIGO DO CONCURSO
int buscaCodigoConcurso(concursos* Concurso, char* num_concurso, int qtd);
