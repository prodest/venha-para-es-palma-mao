# TESTE PARA PRODEST 2018
### Instalação
O projeto deve ser executao em algum servidor que, de preferência, tenha um servidor apache. Caso não possua, basta baixar [esse](https://github.com/MatheusRBarbosa/webserver) servidor(local) e copiar a pasta desse repositório dentro do servidor que foi baixado. 
Após isso, renomeie a pasta copiada, que foi colocada dentro do servidor, para 'root'. Com isso, basta iniciar o servidor clicanco em 'usbwebserver.exe', e para abrir, basta clicar na aba 'general' e depois em localhost, ou digitar 'localhost:8080' na url do navegador

### Tecnologias escolhidas
- Back-end: foi escolhido **PHP**, pelo falo de ser uma aplicação pequena e simples. O PHP me serviu muito bem, por ser simples e versátil
- Font-end: HTML5 com [Materializecss](http://materializecss.com/)
- Persistência de dados: Estou utilizando [GearHost](https://www.gearhost.com/), um banco de dados MySQL gratuito e online. 


### Diferenciais implementados
  - Banco de dados
  - [Wakatime](https://wakatime.com/@44c37bfc-b7a8-45a2-9154-06fddd1c175f/projects/aylngysfdh?start=2018-02-11&end=2018-02-17)
  - Padrão de programação da tecnologia escolhida
  - Clean code

### Documentação
Dentro da pasta 'Documentação' existem dois PDF's

**Documentação técnica** Onde eu falo um pouco sobre o papel de cada classe e arquivo de controle. Nas classes e funções usadas eu descrevo seus parametros de entrada e seus retornos, quando existem. E falo um pouco do que o método/função faz.

**Teste funcional PRODEST** Onde eu faço um teste funcional, testando todas as funcionalidades da aplicação. Testo todas as possivels interações com o sistema e se essas interações correspondem com o esperado.

### O que foi feito
Primeiramente, foi preciso melhorar a padronização dos arquivos de dados. Pra isso, o **EXCEL** me ajudou bastante. Não queria fazer um código para fazer o que ele faz, com umas duas ferramentas do Excel, consegui padronizar os arquivos para um formato melhor. Com os arquivos padronizados, fiz a importação dos dados para o banco. (imagem do aqruivo)

Com o banco de dados pronto para uso, eu fiz todas as telas que seriam necessárias e testei a conexão com o banco via PHP.
Depois disso eu fiz os arquivos que controlariam as tabela.

Na hora de fazer a paginação eu dediquei um tempo especial, pois queria deixar ela da forma mais prática possível. Fiz um código que sempre exibirá a pagina que o usuário está no meio (imagem da paginação)

Por último fiz a validação da entrada de dados do usuário. No caso do cpf, em específico, fiz com que o código aceitasse o formato padrão nnn.nnn.nnn-nn ou apenas os números nnnnnnnnnnn, sendo 'n' um numero qualquer.

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
