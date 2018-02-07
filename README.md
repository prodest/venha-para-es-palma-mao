1) Documentação da Solução:

A Pasta Documentação possui alguns dos diagramas de funcionamento básico do sistema que podem ajudar a esclarescer seu comportamento

Primeira parte:
Simplesmente rode o script BancoDeDados/CREATE.sql dentro de um Servidor de Banco de Dados MySQL. Este script gerará um Schema chamado "Concursos" contendo toda a estrutura de dados e informações básicas para o funcionamento das aplicações.
anote o usuario do banco, a senha, o IP do servidor e a porta, a aplicação irá solicitar estes dados para sua primeira inicialização

Segunda parte: 
Após ter executado o script CREATE.sql no seu MySQL, basta que execute o Projeto SolucaoCompleta e siga as instruções do programa.

###########################################################

2) Diferenciais implementados:

Uso de Banco de Dados: MySQL

utiliza CleanCode

utiliza o Padrão de Programação MVC (padrão do java)

##########################################################

3)Link do Projeto no WakaTime: https://wakatime.com/@2be09e86-5ee9-4fdd-8deb-23ce1dc5c766/projects/hpruoavnlu?start=2018-02-01&end=2018-02-07

##########################################################

Sobre minhas implementações:

O primeiro desafio consistiu em transformar os dados crus que estão nos arquivos txt em representações confiaveis das informações para serem registradas no banco de dados. Para executar esta missão, eu desenvolvi uma aplicação java, nomeando esse projeto "RecuperadorDB". O projeto consistia em duas classes principais, encarregadas de ler cada arquivo e transformar os dados contidos em cada linha em uma entidade íntegra (Candidato, ConcursoPublico e suas relações CandidatoXProfissao e e ConcursoPublicoXProfissao). 

Criei as 2 classes das entidades envolvidas, seus respectivos DAOs, uma fabrica de conexões com o banco, as 2 classes principais que contém os métodos para fazer isso ser possível, e a classe com o metodo main dentro do pacote VISAO, que cuida de executar todo o backend devidamente e mostrar o resultado no console da IDE. A IDE utilizada para desenvolver a solução foi o NetBeans 8.2, rodando sobre a plataforma Linux Debian 9 'Stretch'

O Banco de dados que escolhi para a tarefa foi o MySQL, devido a sua robustez e simplicidade equilibrados e sem custos de licenças.

Após criar o Projeto para recuperar os dados dos arquivos, criei o projeto principal, chamado "TesteProdest", que era a solução propriamente dita do problema principal. Este projeto continha os DAOS e as Entidades do primeiro, adicionados das funcionalidades demandadas pelo problema.


#############################################################

APÓS TESTAR OS PROJETOS, EU CHEGUEI A FAZER UM PULL REQUEST

#############################################################

Porém, eu percebi que eu poderia unificar os 2 projetos em um só e simplificar muito mais esta solução, através de arquivos de configuração e fluxos de dados mais inteligentes na classe principal.

#############################################################

PORTANTO, FECHEI O PULL REQUEST ANTERIOR E COMEÇEI A TRABALHAR NESTA IDÉIA

#############################################################

CRIEI O PROJETO JAVA CHAMADO "SolucaoCompleta" QUE É O QUE O TITULO DIZ, UMA SOLUÇÃO COMPLETA PARA OS PROBLEMAS DE ARQUIVOS, BANCO DE DADOS, CONFIGURAÇÕES E FINALMENTE DAS CONSULTAS.

Eu unifiquei os projetos anteriores, que compartilhavam muito backend similar, fiz as adaptações em todas as classes para que trabalhassem sem conflito, diminui as chances de ocorrerem erros inesperados através do tratamento das exceptions, e criei uma classe que concentra o fluxo do programa, a "VISAO.AppStart.java". esta classe contém o espírito do projeto dentro de sí e revela seu funcionamento de uma maneira muito clara e intuitiva, contendo comentários preciosos que guiarão o raciocínio de quem a lê.

Candidato: MATEUS GARCIA LOPES
Inscrição: 750838

# Teste para o projeto ES na Palma da mão

O desafio é desenvolver um programa que permita realizar as seguintes buscas: 
1. Listar os **órgãos, códigos e editais dos concursos públicos** que encaixam no perfil do candidato tomando como base o **CPF** do candidato ; 
2. Listar o **nome, data de nascimento e o CPF** dos candidatos que se encaixam no perfil do concurso tomando com base o **Código do Concurso** do concurso público;

O arquivo **candidatos.txt** contém as informações dos candidatos:

| Nome  | Data de Nascimento  | CPF |  Profissões|
|---|---|---|---|
| Lindsey Craft  |  19/05/1976  |  182.845.084-34  |  [carpinteiro]  | 
| Jackie Dawson  |  14/08/1970  |  311.667.973-47  |  [marceneiro, assistente administrativo]  |
| Cory Mendoza |   11/02/1957 |  565.512.353-92  |  [carpinteiro, marceneiro] |

O arquivo **concursos.txt** contém as informações dos concursos públicos:

| Órgão  | Edital  | Código do Concurso |  Lista de vagas|
|---|---|---|---|
| SEDU  | 9/2016  |  61828450843  |  [analista de sistemas, marceneiro]  | 
| SEJUS | 15/2017  |  61828450843  |  [carpinteiro,professor de matemática,assistente administrativo] |
| SEJUS | 17/2017 |  95655123539  |  [professor de matemática] |

**Escolha as tecnologias que você vai usar e tente montar uma solução completa para rodar a aplicação**.

Para enviar o resultado, basta realiazar um **Fork** deste repositório e **abra um Pull Request**, **com seu nome e o número de inscrição**.  

**É importante comentar que deve ser enviado apenas o código fonte. Não aceitaremos códigos compilados**.

Por fim, o candidato deve atualizar o Readme.md com as seguintes informações: 
1. Documentação da solução;
2. Lista dos diferenciais implementados
3. Link do projeto no [WakaTime](https://wakatime.com/). Veja um [exemplo](https://wakatime.com/@b142ebdf-4d65-4b92-bc14-567db7b72151/projects/zrxbwdmhtu?start=2018-01-25&end=2018-01-31).  

## Avaliação

O programa será avaliado levando em conta os seguintes critérios:

| Critério  | Valor | 
|---|---|
| Legibilidade do Código |  10  |
| Documentação do código|  10  |
| Documentação da solução|  10  |
| Tratamento de Erros| 10| 
| Total| 40|

A pontuação do candidato será a soma dos valores obtidos nos critérios acima.

## Diferenciais 

O candidato pode aumentar a sua pontuação na seleção implementando um ou mais dos itens abaixo:

| Item  | Pontos Ganhos | 
|---|---|
| Criar um [serviço](https://martinfowler.com/articles/microservices.html) com o problema |  30  |
| Utilizar banco de dados| 30|
| Implementar Clean Code |  20  |
| Implementar o padrão de programação da tecnologia escolhida |  20  |
| Qualidade de [Código com SonarQube](https://about.sonarcloud.io/) |  15  |
| Implementar testes unitários |  15  |
| Implementar testes comportamentais |  15  |
| Implementar integração com [Travis](https://travis-ci.org/)  |  10  |
| Implementar integração com Travis + SonarQube |  10  |
| Implementar usando Docker| 5|
| Total| 170|

A nota final do candidato será acrescido dos pontos referente ao item implementado corretamente.

## Penalizações

O candidato será desclassifiado nas seguintes situações:

1. Submeter um solução que não funcione; 
2. Não cumprir os critérios presentes no seção **Avaliação**
3. Plágio
