# Solução

Para solucionar o problema proposto eu me direcionei para a criação de uma api web seguinto, de certa forma fiel, uma arquitetura REST padrão. Não foram desenvolvidos testes unitários e nem de integração por isso não fiz questão de integrar com SonarQube, porém foi adicionado um CI/CD com Travis e Heroku (https://espm-david-server.herokuapp.com/ping).

Para resolver o problema da entrada de dados (arquivo txt com um padrão definido) foi criado um script (`src/scripts/seed.ts`) que busca estes arquivos (`candidatos.txt` e `concursos.txt`) da pasta raíz do projeto e cadastra os itens no banco de dados diretamente, com a finalidade de economizar tempo.

## Tecnologias utilizadas

- NodeJS
- TypeScript
- NestJS (framework)
- MongoDB
- TravisCI
- Heroku

## Iniciando aplicação

1. Configure suas variáveis de ambient ou um arquivo `.env`, tomando como base o arquivo `.env.example`.
2. Execute `yarn build` para transpilar a aplicação para JavaScript ou execute em modo desenvolvimento com `yarn start:dev`.
3. Caso tenha feito o build e queira executar em modo produção execute: `yarn start:prod`

## Api

- Endpoint: https://espm-david-server.herokuapp.com

| Rota | Detalhes |
| ---- | -------- |
| GET [/candidate](https://espm-david-server.herokuapp.com/candidate) | Obtém todos candidatos cadastrados |
| POST [/candidate](https://espm-david-server.herokuapp.com/candidate) | Cadastra um candidato |
| PUT [/candidate/:id](https://espm-david-server.herokuapp.com/candidate) | Atualiza um candidato |
| DELETE [/candidate/:id](https://espm-david-server.herokuapp.com/candidate) | Apaga um candidato |
| GET [/candidate/suggestions/:cpf](https://espm-david-server.herokuapp.com/candidate) | Lista todos os concursos a qual o candidado com cpf informado se encaixa |
| GET [/concourse](https://espm-david-server.herokuapp.com/concourse) | Obtém todos concursos cadastrados |
| POST [/concourse](https://espm-david-server.herokuapp.com/concourse) | Cadastra um concurso |
| PUT [/concourse/:id](https://espm-david-server.herokuapp.com/concourse) | Atualiza um concurso |
| DELETE [/concourse/:id](https://espm-david-server.herokuapp.com/concourse) | Apaga um concurso |
| GET [/concourse/possible-candidates/:concourseCode](https://espm-david-server.herokuapp.com/concourse) | Lista todos candidatos que se encaixam no perfil do curso com código informado |

## Wakatime

https://wakatime.com/@davidpvilaca/projects/fdlqjcqgio

## Organização do código e padrões

O projeto foi dividido em módulos e segue a seguinte estrutura:

| candidates                 # Módulo de candidatos<br>
&nbsp;&nbsp;| candidate.controller.ts    # Controlador da rota de candidatos<br>
&nbsp;&nbsp;| candidate.schema.ts        # Schema do banco de dados de candidatos<br>
&nbsp;&nbsp;| candidates.module.ts       # Definição do módulo de candidatos e suas dependências<br>
&nbsp;&nbsp;| candidates.service.ts      # Serviço de acesso ao dado para candidatos especificamente<br>
&nbsp;&nbsp;| create-candidate.dto.ts    # Classe para tipagem da entrada de dados e futuras validações na criação de candidado<br>
| concourses                 # Módulo de concursos<br>
&nbsp;&nbsp;| ...{.ts}                   # Mesma organização de candidates, porém para concursos<br>
| config                     # Arquivos de configurações gerais<br>
| interfaces                 # Interfaces de maneira geral<br>
| providers                  # Serviços que tangenciam toda a aplicação horizontalmente<br>
| scripts                    # Scripts para ser executado no terminal de forma independente da aplicação<br>
| app.controller.ts          # Controlador principal (root)<br>
| app.module.ts              # Módulo principal (root) <br>
| app.service.ts             # Serviço principal da aplicação<br>
| main.ts                    # Arquivo para o bootstrap da api<br>

## Banco de dados e modelagem

Minha escolha para o banco de dados foi o MongoDB devido a sua simplicidade e facilidade de modelar com NodeJS e Mongoose, apesar de achar mais apropriada a resolução, devido a natureza do problema, com banco relacional (Postgres por exemplo).

Foram criadas duas Collections através do uso de schemas do Mongoose:

- Candidate: Para armazenar informações dos candidatos.
- Concourse: Para armazenar informações dos concursos.

As profissões, que são informações comuns às duas entidades, não foram criadas uma Collection para elas a fim de melhorar o desempenho nas buscas, pois bancos não relacionais como MongoDB não são muito velozes em fazer essa junção depois.

## Docker

Foi desenvolvido um Dockerfile da aplicação. A imagem pode ser criada e testada com os comandos:

```bash
$ docker build -t espmdavid:latest .  # Cria uma imagem da aplicação
$ docker run -d -e PORT=3000 -e DB_URI='mongodb://your-server/yourdb' -p 3000:3000 espmdavid:latest # Executa a web api e expõem na porta 3000
$ docker run --rm -it -e DB_URI='mongodb://your-server/yourdb' espmdavid:latest yarn seed # Executa o job de preencher o banco com as informações dos arquivos txt
```

## Linters e Configs

Segui muito do padrão Standard, porém adicionando algumas regras que acho interessante seguir, como por exemplo imports em ordem alfabética. Já as configurações de compilação do TypeScript não perdoa tipagem implícita do `any` e nem variáveis não utilizadas.

Também foi configurado o Commitlint para padronizar as mensagens de commit, juntamente com a verificação do código e formatação adequada antes do commit ser efetuado.

---

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
