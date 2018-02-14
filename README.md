## Bem vindo ao repositório do projeto venha para o ES na palma da mão

### Introdução

Este repositório contém o código do sistema web, que foi desenvolvido em Python utilizando a framework Django, para a avaliação técnica do processo seletivo do projeto ES na palma da mão

### Pré-Requisitos do Sistema
* **Python >= 3.5.2**
* **Virtualenv >= 15.0.2**
* **Pip >= 8.1.2**


### Processo de Instalação

1. Faça o download do Código:
 * [Download Zip](https://github.com/lukasg18/venha-para-es-palma-mao.git)
 * Caso possua o git instalado, ao invés de baixar o zip, execute, em um terminal, o seguinte comnando na pasta onde desejar salvar o projeto:
      
		git clone https://github.com/lukasg18/venha-para-es-palma-mao.git

2. Abra um terminal e vá até a pasta onde o projeto se econtra. 
3. Crie um ambiente virtual na raiz do projeto executando o comando:

		virtualenv --python=python3.5 myvenv

4. Em seguida ative-o com o comando:

		source myvenv/bin/activate

6. Execute o comando para instalar as depedencias do projeto:

		pip install -r requirements.pip

7. Para a criação de um usuário com direitos administrativos no sistema, execute o comando:

		python3 manage.py createsuperuser 

### Executando o projeto

Para executar o projeto vá para o dirtorio raiz, com o ambiente virtual ativado, e execute o comando:

    python3 manage.py runserver
    
O servidor estará disponível no endereço:

    http://localhost:8000/


## Considerações 

### Arquitetura utilizada

O django utiliza a arquitetura MVT: Model, Template e View. As Models sao as classes, cada classe do modelo se compara a uma tabela do banco de dados, e as instâncias destas classes, representam os registros destas tabelas. A camada é reposnsavel pela comunicaçao com a Model e a Template, cadastrando e tratando as informações recebidas. Templates é a camada que retorna a visão para o usuário do programa. Essa camada é composta por, HTML,CSS, javascript.
* **models se encontram no diretorio: 'programa/models.py'** 
* **views em: 'programa/views.py'**  
* **templates em: 'programa/templates/'**


### Decisao do Banco de Dados

Para este projeto foi utilizado o banco PostgreSQL. O mesmo se encontra online, sendo possivel ser acessado em qualquer maquina atraves das configurações que se encontram no arquivo 'settings.py' dentro da pasta 'avaliacao'. Para a comunicação com o banco externo, foi utlizado o Psycopg, que é o adaptador de banco de dados PostgreSQL.

	'ENGINE': 'django.db.backends.postgresql_psycopg2'
Foi criado quatro tabelas no banco de dados, sendo elas: profissao, orgao, candidato e concurso. As tablas do banco de dados, foram criadas em cima das classes criadas dentro de 'models.py'(dito no topico anterior). Após a criação das classes, um comando é executado, sendo ele: 

	python3 manage.py migrate
o mesmo é responsavel por criar as tabelas no banco de dados em cima das classes que se encontram em 'models.py'

### Importando arquivos do arquivo para o banco de dados

Dois algoritmos foram criados para importar os dois arquivos .txt para o banco de dados, sendo eles: importCandidato.py e importConcurso.py, ambos encontrados no diretório raiz do projeto.

### Integrações Adicionais

Os diferenciais implementados no projeto são:
1. [Travis](https://travis-ci.org/lukasg18/venha-para-es-palma-mao)
2. [Qualidade do codigo SonnarQube](https://sonarcloud.io/dashboard?id=lukasg94)
3. Travis + SonnarQube
4. Padrão de programação da tecnologia escolhida
5. Utilizar Banco de Dados
6. Clean Code(exceto testes)
7. [Wakatime](https://wakatime.com/@c5c52eda-9bed-4a7a-b1fd-98544474d827/projects/zzrynnmtog?start=2018-02-01&end=2018-02-14)



###################################################################################

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
