# Solução

**Lidando com os dados**

Para o desenvolvimento do programa proposto escolhi utilizar a linguagem Java com o apoio do NetBeans IDE. Com o intuito de organizar os dados, criei três classes: uma para armazenar as informações dos candidatos, uma para as informações dos concursos, e uma ultima para ligar as profissões a um numero de identificação (pensando nas consultas dos bancos de dados).

O software carrega os dados dos arquivos de candidatos e concursos, e separa linha por linha em 4 seções diferentes: no caso do arquivo pertinente as candidatos, o programa lê todos os caracteres da linha até encontrar o primeiro algarismo numerico, e salva a união desses caracteres (após retirada de espaços desnecessários nas extremidades) no atributo nome de um objeto da classe candidato. A partir dai, continua lendo os caracteres até o primeiro espaço, salvando a data de nascimento, e o mesmo é feito para o CPF. Por fim, le os caracteres restantes, retira os colchetes, divide (split) a string resultante usando as vírgulas como parametro, retira os espaços redundantes e os salva no objeto de classe Cargos (referente as profissões), salvando no objeto Candidato apenas uma lista de inteiros com o id de cada profissão.

Para os concursos é feito um processo bem similar, lendo os caracteres do Órgão, depois do edital, depois o código e por fim o mesmo processo das profissões é feito nas vagas.

**Interface com o usuário**

Para o programa ser utilizado foi criada uma interface simples através de JFrames com uma janela, 2 tabelas, 2 campos de pesquisa (um para pesquisar pelo cpf, outro para pesquisar por codigo de concurso) e dois botões associados com os campos de pesquisa. Se o botão é clicado sem nada digitado no campo, a lista completa de todos os candidatos (ou todos os concursos) é preenchida na tabela. Se é inserido um valor que não está presente no arquivo, nenhum valor é preenchido na tabela, e o mesmo ocorre se nenhum "match" é dado entre candidado e possíveis concursos. Se um código (ou cpf) correto é inserido, a respectiva tabela é atualizada com as entradas de candidatos disponíveis para um concurso (ou concursos disponíveis para um candidato).

**Diferenciais**

Após a implementação lidando apenas com a persistencia em arquivos, escrevi uma classe para tratar do banco de dados SQLite com criação das tabelas e resgate de dados salvos nelas. No entanto, a funcionalidade de salvar os dados tomou bastante tempo de execução e precisei finalizar o procedimento antes de terminar, portanto receio que precisaria de mais tempo para corrigir os problemas que vieram com essa feature.

Tentei seguir o que pude dos padrões de código limpo como o uso de nomes descritivos e relevantes, funções na maioria de tamanho reduzido, maioria das funções documentadas mais ou menos de acordo com os padrões da linguagem, entre outros, mas pode ser eu tenha deixado passar algums detalhes, e vou ficar devendo os testes unitários com JUnit. 

Luiz H. A. A. de Lima - Inscricao 1031947

# Links

Link do WakaTime: https://wakatime.com/@a671e39b-ab96-4b0c-9ab8-25f432ef8b6d/projects/gtuskpwtsa?start=2018-06-11&end=2018-06-17
OBS: alguns dados do dia 16 não apareceram.

PS: Link abaixo desnecessário após upload no git, mas manterei por backup.
Projeto (código dentro da pasta /ProdesteTeste/src/): https://drive.google.com/file/d/1cKodTq38UAsOzrH9ZwhUas3YorDw5XkF/view?usp=sharing



====================================================================================================
README Original:

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
