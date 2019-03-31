CREATE TABLE Concurso (
idconcurso int not null PRIMARY KEY,
codigo VARCHAR(100) not null,
edital VARCHAR(100) not null,
orgao VARCHAR(100) not null
);

CREATE TABLE Concurso_Profissao (
id int not null PRIMARY KEY,
idprofissao int not null,
idconcurso int not null,
FOREIGN KEY(idconcurso) REFERENCES Concurso (idconcurso)
);

CREATE TABLE Profissao (
idprofissao int not null PRIMARY KEY,
descricao VARCHAR(100) not null
);

CREATE TABLE Pessoa_Profissao (
id int not null PRIMARY KEY,
idpessoa int not null,
idprofissao int not null,
FOREIGN KEY(idprofissao) REFERENCES Profissao (idprofissao)
);

CREATE TABLE Pessoa (
idpessoa int not null PRIMARY KEY,
datanascimento DATE not null,
cpf VARCHAR(100) not null,
nome VARCHAR(100) not null
);

ALTER TABLE Concurso_Profissao ADD FOREIGN KEY(idprofissao) REFERENCES Profissao (idprofissao);
ALTER TABLE Pessoa_Profissao ADD FOREIGN KEY(idpessoa) REFERENCES Pessoa (idpessoa)
