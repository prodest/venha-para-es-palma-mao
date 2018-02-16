# Teste para o projeto ES na Palma da mão

**Autor**: Magno Macedo de Oliveira

**Nome do programa**: Morpheus System

**Ferramentas utulizadas**

- HTML5
- CSS3
- jQuery
- JSON
- NodeJS, apenas para:
    * Gerar um serviço HTTP:
        + Foi feito a instalação do http-server 'npm install http-server -g' (sem aspas), para rodar um serviço simples HTTP;
            - Para excutar o serviço é só digitar o seguinte comando 'http-server . -p 8000' (sem aspas);
    * E auxiliar na integração com o Travis:
        + Instalação do jshint 'npm install jshint --save-dev';
        + E configuração do '.travis.yml' e o 'package.json'.

**OBS**: A solução em si foi desenvolvida com o jQuery + JSON, utilizando do HTML5 e CSS3 para a criação e personalização da interface.

**Restrições**

- Necessário utilizar:
    * Chrome, recomenda-se versão 62.0.3202.94 ou superior;
    * Ou Firefox, recomenda-se versão 58.0.2 ou superior.
- Testes realizados com o Interner Explorer demostraram problemas no carregamento de elementos essenciais para a interface. 

**Como funciona**

A ideia foi criar uma tela onde o usuário escolhe se a pesquisa será feita através do candidato ou através do concurso. Uma vez selecionado a opção, é solicitado que ele informe o CPF do candidato ou o código do concurso, logo em seguida ele deve clicar no botão de pesquisa (lupa) e assim aguardar o sistema fazer o levantamento, e entrega, das informações requeridas.

A escolha por utilizar ferramentas voltas a web, em especial à front-end, foi devido a facilidade de uso e a possibilidade de montar uma interface mais convidativa ao usuário.

Destaca-se a utilização de jQuery que demostrou amplo e eficaz, tanto na interação da tela quanto na mamipulação dos dados, e o tratamento feito com os arquivos .txt para gerar os aquivos .json que facilitaram, bastante, na busca pelas informações contidas.

**OBS**: Analisando os CPFs dos candidatos registrados foi detectado que não passavam no teste da soma dos numeros. Logo está sendo considerado CPF válido qualquer sequência numérica que possuir 11 digitos.

- **Link do projeto no [WakaTime](https://wakatime.com/@0d7e8590-bea0-4c4e-837f-8b6284e3befc/projects/sunbofxesw?start=2018-02-09&end=2018-02-15).**  

## Diferenciais 

- **Integração com o Travis**
[![Build Status](https://travis-ci.org/magnoDev/venha-para-es-palma-mao.svg?branch=magno750781)](https://travis-ci.org/magnoDev/venha-para-es-palma-mao)