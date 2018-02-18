# ES na palma da mão

## Documentação

### problema e solução
##### O problema
Dado o desafio de comparar dois arquivos de dados nos quais possuiam informações sobre candidatos e editais de concursos, no qual havia a necessidade encontrar candidados para a vaga do concurso especifico ou localizar vaga para o candidato, sendo pesquisado por seu código do concurso ou cpf respectivamente.
##### soluçao
para solucionar este desafio tentando usar o máximo de requisitos pensei em uma linguagem web e na captura dos dados em um banco, sendo um desafio de pouca complexidade também optei por usar programação procedural, pois orientação a objeto faria uma solução mais complexa do que o proprio dasafio proposto e utilizei também arquitetura MVC(Model View Control).

### tecnologias
* PHP
* Postgresql
* HTML
* JavaScript
* CSS

### Utilizando o projeto

##### instalações necessárias
 * php7.0
 * php7.0-pgsql
 * postgresql
 * pgadmin (Opcional)

##### Configurações iniciais
após clonar o projeto ```git clone git@github.com:icarodgl/venha-para-es-palma-mao.git``` será necessário importar os dados para o postgres, eles estão na pasta **dados** em formato **sql**, executando primeiro o arquivo **criabanco.sql**.

as configurações da conexão com o banco estão no arquivo ```/apps/controles/connect.php``` basta subistituir as informações.

agora que configuramos os dados basta abrir o terminal na raiz do projeto ```ctrl+alt+T``` e rodar o comando ``` php -S localhost:8080 ``` assim estará rodando em modo desenvolvimento sem a necessidade de configurar um servidor.



## lista de diferenciais
| Critério  | Valor | Feito? |
|---|---|---|
| Legibilidade do Código |  10  |X|
| Documentação do código|  10  |X|
| Documentação da solução|  10  |X|
| Tratamento de Erros| 10|X|
| Criar um [serviço](https://martinfowler.com/articles/microservices.html) com o problema |  30  |X|
| Utilizar banco de dados| 30|X|
| Implementar Clean Code |  20  |X|
| Implementar o padrão de programação da tecnologia escolhida |  20  |X|
| Qualidade de [Código com SonarQube](https://about.sonarcloud.io/) |  15  |-|
| Implementar testes unitários |  15  |X|
| Implementar testes comportamentais |  15  |X|
| Implementar integração com [Travis](https://travis-ci.org/)  |  10  |-|
| Implementar integração com Travis + SonarQube |  10  |-|
| Total| 140|-|
## link do WakaTime
[WakaTime](https://wakatime.com/@3412acbb-3482-45c8-b61b-9cbffbd7c31a/projects/iodhkmqpdz)



