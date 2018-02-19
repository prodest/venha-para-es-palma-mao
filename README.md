O sistema foi desenvolvido na IDE NetBeans8.2 sobre a plataforma Linux Debian 9 "Stretch" 64 bits

OBSERVA��O IMPORTANTE: por alguma raz�o o driver do MySQL n�o est� funcionando diretamente ao rodar a aplica��o em outra maquina. a resolu��o � simples: importe o Driver .jar do MySQL que est� na pasta LIBs para as bibliotecas da aplica��o no netbeans.

SE O SERVIDOR ESTIVER EM OUTRA MAQUINA, CERTIFIQUE-SE QUE O SERVIDOR MYSQL ESTEJA ACEITANDO CONEX�ES EXTERNAS

1) Documenta��o da Solu��o:

A Pasta Documenta��o possui alguns dos diagramas de funcionamento b�sico do sistema que podem ajudar a esclarescer seu comportamento

Primeira parte:
Simplesmente rode o script BancoDeDados/CREATE.sql dentro de um Servidor de Banco de Dados MySQL. Este script gerar� um Schema chamado "Concursos" contendo toda a estrutura de dados e informa��es b�sicas para o funcionamento da aplica��o.
anote o usuario do banco, a senha, o IP do servidor e a porta, a aplica��o ir� solicitar estes dados para sua primeira inicializa��o

Segunda parte: 
Ap�s ter executado o script CREATE.sql no seu MySQL, basta que execute o Projeto SolucaoCompleta e siga as instru��es do programa.

###########################################################

2) Diferenciais implementados:

Uso de Banco de Dados: MySQL

utiliza CleanCode

utiliza o Padr�o de Programa��o MVC (padr�o do java)

##########################################################

3)Link do Projeto no WakaTime: https://wakatime.com/@2be09e86-5ee9-4fdd-8deb-23ce1dc5c766/projects/hpruoavnlu?start=2018-02-01&end=2018-02-07

Observe que o WakaTime est� computando somente o tempo em que trabalhei no projeto SolucaoCompleta.
a maior parte do tempo eu gastei para fazer os outros dois projetos, os quais eu fundi neste acima citado para formar um s�, por�m eu ainda n�o estava utilizando o WakaTime naquele momento, pois s� tomei conhecimento do servi�o ap�s o primeiro pull request.

##########################################################

Sobre minhas implementa��es:

O primeiro desafio consistiu em transformar os dados crus que est�o nos arquivos txt em representa��es confiaveis das informa��es para serem registradas no banco de dados. Para executar esta miss�o, eu desenvolvi uma aplica��o java, nomeando esse projeto "RecuperadorDB". O projeto consistia em duas classes principais, encarregadas de ler cada arquivo e transformar os dados contidos em cada linha em uma entidade �ntegra (Candidato, ConcursoPublico e suas rela��es CandidatoXProfissao e e ConcursoPublicoXProfissao). 

Criei as 2 classes das entidades envolvidas, seus respectivos DAOs, uma fabrica de conex�es com o banco, as 2 classes principais que cont�m os m�todos para fazer isso ser poss�vel, e a classe com o metodo main dentro do pacote VISAO, que cuida de executar todo o backend devidamente e mostrar o resultado no console da IDE. A IDE utilizada para desenvolver a solu��o foi o NetBeans 8.2, rodando sobre a plataforma Linux Debian 9 'Stretch'

O Banco de dados que escolhi para a tarefa foi o MySQL, devido a sua robustez e simplicidade equilibrados e sem custos de licen�as.

Ap�s criar o Projeto para recuperar os dados dos arquivos, criei o projeto principal, chamado "TesteProdest", que era a solu��o propriamente dita do problema principal. Este projeto continha os DAOS e as Entidades do primeiro, adicionados das funcionalidades demandadas pelo problema.


#############################################################

AP�S TESTAR OS PROJETOS, EU CHEGUEI A FAZER UM PULL REQUEST

#############################################################

Por�m, eu percebi que eu poderia unificar os 2 projetos em um s� e simplificar muito mais esta solu��o, atrav�s de arquivos de configura��o e fluxos de dados mais inteligentes na classe principal.

#############################################################

PORTANTO, FECHEI O PULL REQUEST ANTERIOR E COME�EI A TRABALHAR NESTA ID�IA

#############################################################

CRIEI O PROJETO JAVA CHAMADO "SolucaoCompleta" QUE � O QUE O TITULO DIZ, UMA SOLU��O COMPLETA PARA OS PROBLEMAS DE ARQUIVOS, BANCO DE DADOS, CONFIGURA��ES E FINALMENTE DAS CONSULTAS.

Eu unifiquei os projetos anteriores, que compartilhavam muito backend similar, fiz as adapta��es em todas as classes para que trabalhassem sem conflito, diminui as chances de ocorrerem erros inesperados atrav�s do tratamento das exceptions, e criei uma classe que concentra o fluxo do programa, a "VISAO.AppStart.java". esta classe cont�m o esp�rito do projeto dentro de s� e revela seu funcionamento de uma maneira muito clara e intuitiva, contendo coment�rios preciosos que guiar�o o racioc�nio de quem a l�.

Candidato: MATEUS GARCIA LOPES

Inscri��o: 750838

# Teste para o projeto ES na Palma da m�o

O desafio � desenvolver um programa que permita realizar as seguintes buscas: 
1. Listar os **�rg�os, c�digos e editais dos concursos p�blicos** que encaixam no perfil do candidato tomando como base o **CPF** do candidato ; 
2. Listar o **nome, data de nascimento e o CPF** dos candidatos que se encaixam no perfil do concurso tomando com base o **C�digo do Concurso** do concurso p�blico;

O arquivo **candidatos.txt** cont�m as informa��es dos candidatos:

| Nome  | Data de Nascimento  | CPF |  Profiss�es|
|---|---|---|---|
| Lindsey Craft  |  19/05/1976  |  182.845.084-34  |  [carpinteiro]  | 
| Jackie Dawson  |  14/08/1970  |  311.667.973-47  |  [marceneiro, assistente administrativo]  |
| Cory Mendoza |   11/02/1957 |  565.512.353-92  |  [carpinteiro, marceneiro] |

O arquivo **concursos.txt** cont�m as informa��es dos concursos p�blicos:

| �rg�o  | Edital  | C�digo do Concurso |  Lista de vagas|
|---|---|---|---|
| SEDU  | 9/2016  |  61828450843  |  [analista de sistemas, marceneiro]  | 
| SEJUS | 15/2017  |  61828450843  |  [carpinteiro,professor de matem�tica,assistente administrativo] |
| SEJUS | 17/2017 |  95655123539  |  [professor de matem�tica] |

**Escolha as tecnologias que voc� vai usar e tente montar uma solu��o completa para rodar a aplica��o**.

Para enviar o resultado, basta realiazar um **Fork** deste reposit�rio e **abra um Pull Request**, **com seu nome e o n�mero de inscri��o**.  

**� importante comentar que deve ser enviado apenas o c�digo fonte. N�o aceitaremos c�digos compilados**.

Por fim, o candidato deve atualizar o Readme.md com as seguintes informa��es: 
1. Documenta��o da solu��o;
2. Lista dos diferenciais implementados
3. Link do projeto no [WakaTime](https://wakatime.com/). Veja um [exemplo](https://wakatime.com/@b142ebdf-4d65-4b92-bc14-567db7b72151/projects/zrxbwdmhtu?start=2018-01-25&end=2018-01-31).  

## Avalia��o

O programa ser� avaliado levando em conta os seguintes crit�rios:

| Crit�rio  | Valor | 
|---|---|
| Legibilidade do C�digo |  10  |
| Documenta��o do c�digo|  10  |
| Documenta��o da solu��o|  10  |
| Tratamento de Erros| 10| 
| Total| 40|

A pontua��o do candidato ser� a soma dos valores obtidos nos crit�rios acima.

## Diferenciais 

O candidato pode aumentar a sua pontua��o na sele��o implementando um ou mais dos itens abaixo:

| Item  | Pontos Ganhos | 
|---|---|
| Criar um [servi�o](https://martinfowler.com/articles/microservices.html) com o problema |  30  |
| Utilizar banco de dados| 30|
| Implementar Clean Code |  20  |
| Implementar o padr�o de programa��o da tecnologia escolhida |  20  |
| Qualidade de [C�digo com SonarQube](https://about.sonarcloud.io/) |  15  |
| Implementar testes unit�rios |  15  |
| Implementar testes comportamentais |  15  |
| Implementar integra��o com [Travis](https://travis-ci.org/)  |  10  |
| Implementar integra��o com Travis + SonarQube |  10  |
| Implementar usando Docker| 5|
| Total| 170|

A nota final do candidato ser� acrescido dos pontos referente ao item implementado corretamente.

## Penaliza��es

O candidato ser� desclassifiado nas seguintes situa��es:

1. Submeter um solu��o que n�o funcione; 
2. N�o cumprir os crit�rios presentes no se��o **Avalia��o**
3. Pl�gio
