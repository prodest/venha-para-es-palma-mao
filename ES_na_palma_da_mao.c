#include <stdio.h>
#include <stdlib.h>
#include <string.h>
//------------------------------------------------------------------------------
int tipo_busca(char busca[])
{
  int tipo;
  char *verificar;

  verificar=(char*) memchr (busca, '-', strlen(busca));//verifica se há o caracter '-'
  if(verificar)
    tipo=0; //busca por cpf
  else
    tipo=1; //busca por concurso

  return tipo;
}
//------------------------------------------------------------------------------
void pesquisar_2(char dados[], int tipo)
{
  FILE *arquivo;
  char *imprimir;
  char  pesquisa[100];
  char  profissao[5][50];
  int   i=2,j=0,k=0;

  for( i; i < strlen(dados); i++,k++)
  {
    if((dados[i])==',')//separa a string onde tem virgula
    {
      profissao[j][k]='\0';
      j++;
      k=0;
      i=i+2;
    }
    if((dados[i])!=']')//termina quando encontra ']'
      profissao[j][k]=dados[i];//armazena na matriz
    else
      profissao[j][k]='\0';
   }

  if(tipo)//abre o arquivo referente ao tipo de busca
    arquivo = fopen("candidatos.txt", "r");
  else
    arquivo = fopen("concursos.txt", "r");

  if(!arquivo)
  {
    printf ("Erro na abertura do arquivo!\n\n");
    exit(1);
  }
  k=0;
  while(fgets(pesquisa, sizeof(pesquisa), arquivo))//leitura do aruivo
  {
    i=0;
    while(i<=j)
    {
      if(strstr(pesquisa, profissao[i]))//verifica se resultado da pesquisa é compatível
      {
        imprimir= strtok(pesquisa, "[");//retira a parte entre colchetes da string
        printf("%s\n", imprimir);
        imprimir=NULL;
        k++;
        break;
       }
      else
        i++;
    }
  }
  fclose(arquivo);
  if(tipo)
    printf(" \n%d Candidatos compatíveis\n",k);
  else
    printf("\n %d concursos disponíveis\n",k );
}
//------------------------------------------------------------------------------
void pesquisar(char busca[],int tipo)
{
 FILE *arquivo;
 char dados[100];
 int i=0;

 if (!tipo)
  arquivo = fopen("candidatos.txt", "r");
 else
  arquivo = fopen("concursos.txt", "r");
 if (!arquivo)
 {
   printf ("Erro na abertura do arquivo!\n\n");
   exit(1);
 }
 while((fscanf(arquivo,"%s",dados)!=EOF)) //leitura do arquivo
 {
   if(!(strcmp(busca,dados)))//compara com os dados de busca
   {
     fgets(dados, sizeof(dados), arquivo);//se encontrado captura a profissoes
     i=1;
     break;
    }
 }
  fclose(arquivo);
  if(i)
    pesquisar_2(dados,tipo);//pesquisar os concursos ou candidatos compatíveis
  else
    printf("\nNão consta em arquivo!\n\n");

}
//------------------------------------------------------------------------------
void main()
{
 char busca[50];
 int t;

 while(1)
 {
   printf("Buscar por CPF ou Código do Concurso: ");
   gets(busca);
   if(!(strcmp(busca,"sair")))
   break;

   t=tipo_busca(busca);//identifica se a busca é por cpf ou concurso
   pesquisar(busca,t); //pesquisa candidato/concurso correspondente em arquivo
  }
}
