# Documentação do teste para o projeto ES na Palma da Mão | [Veja a demonstração on-line](https://busca-vaga-espm.herokuapp.com/)
# Desafios
Implementar um sistema de busca de candidatos e vagas afins e concursos públicos e candidatos afins.

O primero desafio foi transformar os arquivos de dados originais, que estavam no formato '.txt' em algo que pudesse ser interpretado por alguma linguagem de programação. Escolhi convertê-los para o formato '.csv'. Para tal, utilizei os recursos de 'find & replace with regular expressions' do editor [Sublime Text](https://www.sublimetext.com/), onde facilmente, através de padrões, pude identificar e separar por ',' os campos e seus respectivos valores.
Com estes dois arquivos convertidos em mãos, pude criar uma tarefa de importação que fez todo o trabalho de leitura e cadastro dos 10.000 candidatos e dos 1.000 concursos no banco de dados.

Como minha intenção era criar um sistema web, optei por utilizar o Bootstrap, um conhecido franework web, com recursos para uma mínima experiência de usuário. isso para que o sistema pudesse paracer, ao máximo com algo real.

É importante informar que durante o processo de importação dos dados, a maior parte dos documentos informados foi considerada inválida. Tive que desabilitar a validação para concluir con sucesso este processo.

Para as buscas, também foi implementada a opção 'Auto Complemeto' que se dá através de:
- Nome ou CPF no caso de candidatos
- Órgão ou código no caso de concursos

Na pasta 'doc' se encontra o diagrama de classes dos projetos.
Nas classes e nos controllers utilizados, se encontram comentários sobre as funções e o que elas fazem

Ao descobrir que o SonarQube não mais suporta Ruby, resolvi não insistir com o seu uso.

Duas classes principais compõem o sistema. São elas:
  - Candidate: Representando os candidatos
  - PublicTender: Representando os concursos públicos

O banco de dados se encontra populado e operante, estando hospedado no SAAS (Software as a Service) Mongo Lab.
Para uma maior velocidade nas buscas, optei por definir dois índices:
- Candidate (Candidato): document_number
- Public Tender (Concurso Público): code

A biblioteca de conexão utilizada foi a [MongoID](https://docs.mongodb.com/mongoid/master/), que é oficialmente mantido pelo MongoDB.

Os modelos estão definidos na pasta 'app/models'.

As views estão definidas na pasta 'app/views'.

Os controllers estão definidos na pasta 'app/controllers'.

Os testes estão definidos na pasta 'spec'.

Todo o projeto foi desenvolvido utilizando o padrão MVC (Model View Controller) e OO (Orientação a Objetos).
# Ferramentas e Tecnologias
  - Linguagem/Framework: [Ruby on Rails](https://rubyonrails.org/)
  - Banco de Dados: [MongoDB](https://www.mongodb.com/)
  - Banco Hospedado em: [Mongo Lab](https://mlab.com/login/)
  - Framework Web: [Bootstrap v4.1.1](https://getbootstrap.com/)
  - Servidor Web: [Puma](https://puma.io/)
  - Biblioteca de Testes: [RSpec](https://rspec.info/)
  - Biblioteca JavaScript: [JQuery](https://jquery.com/)
  - Hospedado em: [Heroku](https://www.heroku.com/)

# Wakatime
[Link do projeto](https://wakatime.com/@05dd98fe-23df-4032-aadd-e313c1089137/projects/udllwfrygz)

# Meus dados
 - Nome: Dérick Hogan Pimenta
 - Número de Inscrição: 1032054
