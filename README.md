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

---------------------------------------------------------------------------------------------


O sistema foi feito em PHP com uso de base de Dados Mysql
Usuário Banco de dados: root
Senha: 


Documento de Especificação de Requisitos

Projeto: Venha para o ES na Palma da mão
Responsável: Lygia Botelho


Histórico de Revisões
Data	Versão	Descrição	Autor
10/06/18	1.0	Versão inicial	Lygia Botelho
			


1.	Prefácio

A elaboração deste documento, em sua versão inicial, tem por objetivo principal documentar as fases de criação de um software visando dentre outros resguardar uma característica essencial, que é a manutenibilidade e está destinado em especial aos profissionais de TI.



2.	Introdução

Este documento apresenta a especificação dos requisitos do Sistema de Consulta de Concursos do teste para projeto ES na palma da Mão.  A atividade de análise foi conduzida aplicando-se técnicas de modelagem de casos de uso e modelagem de classes. Os modelos apresentados foram elaborados usando a UML.
Este documento está organizado da seguinte forma: a seção 03 apresenta o propósito do sistema; a seção 04 apresenta o diagrama de casos de uso, incluindo descrições de atores; a seção 05 apresenta a descrição dos casos de uso e a seção 06 apresenta o diagrama de classes do sistema.

3.	Descrição do propósito do Sistema

Desenvolver um programa que permita realizar as seguintes buscas:
1.	Listar os órgãos, códigos e editais dos concursos públicos que encaixam no perfil do candidato tomando como base o CPF do candidato;
2.	Listar o nome, data de nascimento e o CPF dos candidatos que se encaixam no perfil do concurso tomando como base o Código do Concurso do concurso público;


4.	Diagrama de Casos de Uso e descrição de atores

Este diagrama mostra atores (pessoas ou outros usuários do sistema), casos de
uso (os cenários onde eles usam o sistema), e seus relacionamentos. O ator identificado no contexto deste projeto está descrito na tabela 1.

Tabela 1 – Descrição dos atores
Nome	Função
Usuário	Corresponde a pessoa que realiza consultas por dados do candidato ou por dados do concurso
	



 
Figura 1 - Diagrama de Caso de Uso (Vide imagem Documentacao/DiagramadeCasosdeUso.png)





5.	Descrição dos Casos de Uso

A descrição do caso de uso pode descrever em uma sequência razoavelmente
completa de passos, todas as atividades que ocorrem em reação a um evento
acionador ao sistema e como o mesmo reagiria ao evento.
A seguir são apresentadas as descrições dos casos de uso do pacote Concurso.

Caso de Uso 01
Caso de uso: Buscar concursos do candidato
Descrição: Lista os órgãos, códigos e editais dos concursos públicos que encaixam no perfil de um candidato tomando como base o CPF do candidato.
Ator: usuário
Fluxo Principal de sucesso:
1.	O sistema apresenta o formulário para listar os concursos que se encaixam no perfil do candidato através do CPF do candidato.
2.	O usuário insere os dados no campo selecionado.
3.	O sistema valida o campo e faz a busca.
4.	O usuário confere os dados da consulta.
5.	O usuário fecha a janela.

Fluxo alternativo:
1.	Candidato não encontrado:
a.	O sistema alerta o usuário que os dados não foram encontrados.

2.	Campo em branco:
a.	O sistema alerta o usuário que o campo obrigatório está em branco.

3.	Campos com caracteres inválidos:
a.	O sistema alerta o usuário que existem campos com caracteres inválidos.

4.	Campos com caracteres incompletos:
a.	O sistema alerta o usuário que existem campos com caracteres incompletos.

5.	Nova pesquisa:
a.	Após conferir os dados da consulta o usuário pode fazer uma nova pesquisa.


Caso de Uso 02
Caso de uso: Buscar candidatos do concurso
Descrição: Listar o nome, data de nascimento e o CPF dos candidatos que se encaixam no perfil de um concurso tomando como base o Código do Concurso do concurso público;
Ator: usuário

Fluxo Principal de sucesso:
1.	O sistema apresenta o formulário para buscar os candidatos que se encaixam no perfil do concurso através do código do concurso.
2.	O usuário insere os dados no campo selecionado.
3.	O sistema valida o campo e faz a busca.
4.	O usuário confere os dados.
5.	O usuário fecha a janela.

Fluxo alternativo:
1.	Concurso não encontrado:
a.	O sistema alerta ao usuário que os dados não foram encontrados.

2.	Campo em branco:
a.	O sistema alerta ao usuário que o campo obrigatório está em branco.

3.	Campos com caracteres inválidos:
a.	O sistema alerta ao usuário que existem campos com caracteres inválidos.

4.	Campos com caracteres incompletos:
a.	O sistema alerta ao usuário que existem campos com caracteres incompletos.

5.	Nova pesquisa:
a.	Após conferir os dados da consulta o usuário pode fazer uma nova pesquisa.



6.	Diagrama de classes

O diagrama de classes mostra as classes e os relacionamentos entre elas, a
figura 2 representam o diagrama de classes utilizado:

 
Figura 2 - Diagrama de Classes (Vide imagem Documentacao/DiagramadeClasses.png)


---------------------------------------------------------------------------------------------


2. Lista dos diferenciais implementados


Utilização de Banco de Dados Mysql.


---------------------------------------------------------------------------------------------


3. Link do projeto no [WakaTime](https://wakatime.com/). Veja um [exemplo](https://wakatime.com/@b142ebdf-4d65-4b92-bc14-567db7b72151/projects/zrxbwdmhtu?start=2018-01-25&end=2018-01-31).  


https://wakatime.s3.amazonaws.com/coding-activity-exports/e73c2b01-1c7e-45d9-9fa0-9647c280fe04/wakatime-lygia_botelhooutlook.com-e73c2b011c7e45d99fa09647c280fe04.json?Signature=CK6pLtMu1Ea%2BKG9P6R57OYskRuk%3D&AWSAccessKeyId=AKIAIUAL6LCFUN5DDH2Q&Expires=1528721701

---------------------------------------------------------------------------------------------


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
