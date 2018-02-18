CREATE TABLE candidato
(
  nome varchar(50),
  nascimento varchar(50),
  cpf varchar(50),
  curso varchar(50)[]
);
CREATE TABLE concurso
(
  nome varchar(50),
  edital varchar(50),
  codigo bigint,
  vaga varchar(50)[]
);
