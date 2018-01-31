# Teste para o projeto ES na Palma da mão

O desafio é desenvolver um programa que permita realizar as seguintes buscas: 
1. Retornar o órgão, edital dos concursos públicos que encaixam no perfil do candidato tomando como base o **CPF** do candidato ; 
2. Retornar o nome, data de nascimento e o CPF dos candidatos que se encaixam no perfil do concurso tomando com base o **Código do Concurso** do concurso público;

O arquivo **candidatos.txt** contém as informações dos candidatos:

| Nome  | Data de Nascimento  | CPF |  Lista de habilidades do candidato|
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

Caso o candidato envie um código que não funcione e não cumpra com os requisitos supracitados, esse será desclassificado.

## Pontos de Avaliação


## Diferenciais 

| Critério  | Pontos Ganhos | 
|---|---|
| Criar um [microserviço ou Serviço](https://martinfowler.com/articles/microservices.html) com o problema |  10  |
| Implementar Clean Code  |  10  |
| Qualidade de [Código com SonarQube](https://about.sonarcloud.io/) |  10  |
| Implementar Teste |  10  |
| Implementar Integração com [Travis](https://travis-ci.org/)  |  10  |
| Implementar Integração com Travis + SonarQube |  10  |

