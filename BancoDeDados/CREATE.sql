/*
*	Script desenvolvido para gerar a estrutura 
*	de dados do Projeto no Banco de Dados MySQL
*	Autor: Mateus Garcia
*/
DROP SCHEMA IF EXISTS Concursos;
CREATE SCHEMA Concursos;
USE Concursos;

CREATE TABLE Profissao (
IdProfissao INT NOT NULL AUTO_INCREMENT,
Nome VARCHAR(30) NOT NULL,
PRIMARY KEY(IdProfissao)
);


CREATE TABLE Candidato (
IdCandidato INT NOT NULL AUTO_INCREMENT,
Nome VARCHAR(128) NOT NULL,
DataNasc DATETIME NOT NULL,
CPF VARCHAR(14) UNIQUE NOT NULL,
PRIMARY KEY(IdCandidato)
);


/*      
*
* Um candidato pode ter varias opções de profissão
* Uma profissão pode ser escolhida por vários candidatos
* portanto, cria-se um registro CandidatoXProfissao
* para cada opção que o candidato selecionar
*
*/


CREATE TABLE CandidatoXProfissao (
IdCandidato INT NOT NULL,
IdProfissao INT NOT NULL,
PRIMARY KEY(IdCandidato,IdProfissao),

CONSTRAINT CandidatoXProfissao_Candidato
FOREIGN KEY(IdCandidato)
REFERENCES Candidato(IdCandidato)
ON DELETE RESTRICT
ON UPDATE CASCADE,

CONSTRAINT CandidatoXProfissao_Profissao
FOREIGN KEY(IdProfissao)
REFERENCES Profissao(IdProfissao)
ON DELETE RESTRICT
ON UPDATE CASCADE
);



CREATE TABLE ConcursoPublico (
IdConcursoPublico INT NOT NULL AUTO_INCREMENT,
Orgao VARCHAR(25)NOT NULL,
EditalNum INT(3) NOT NULL,
EditalAno INT(5) NOT NULL,
CodConcurso VARCHAR(11) NOT NULL,
UNIQUE (EditalNum,EditalAno,CodConcurso),
PRIMARY KEY(IdConcursoPublico)
);


/*      
*
* Um concurso pode ter varias profissões na lista de vagas
* Uma profissão pode estar listada em varios concursos
* portanto, cria-se um registro ConcursoxProfissao,
* no caso, tal registro se define como sendo a própria 
* lista de vagas, por isso o nome ListaDeVagas, facilitando
* a legibilidade dos códigos relacionados.
*
*/


CREATE TABLE ListaDeVagas (
IdConcursoPublico INT NOT NULL,
IdProfissao INT NOT NULL,
PRIMARY KEY(IdConcursoPublico,IdProfissao),

CONSTRAINT ListaDeVagas_Profissao
FOREIGN KEY(IdProfissao)
REFERENCES Profissao(IdProfissao)
ON DELETE RESTRICT
ON UPDATE CASCADE,

CONSTRAINT ListaDeVagas_ConcursoPublico
FOREIGN KEY(IdConcursoPublico)
REFERENCES ConcursoPublico(IdConcursoPublico)
ON DELETE RESTRICT
ON UPDATE CASCADE
);

/*
	STORED PROCEDURE PARA AGILIZAR INSERÇÕES DE CANDIDATOS
	E SUAS PROFISSOES ESCOLHIDAS, DE FORMA A UTILIZAR MENOS
	O BANCO DE DADOS NA APLICAÇÃO, FAVORECENDO O DESEMPENHO.
*/

/*
Esta procedure agiliza a inserção de Candidatos e suas profissões selecionadas
*/

DELIMITER $
CREATE PROCEDURE InsereCandidatoXProfissao (IN IdCand INT, IN prof VARCHAR(45))
BEGIN
INSERT INTO CandidatoXProfissao (IdCandidato, IdProfissao) VALUES
(
(IdCand),
(SELECT IdProfissao FROM Profissao WHERE Nome = prof)
);
END$
DELIMITER ;


/*
Esta procedure agiliza a inserção de Concursos Publicos e suas profissões oferecidas
*/

DELIMITER $
CREATE PROCEDURE InsereListaDeVagas (IN IdConc INT, IN prof VARCHAR(45))
BEGIN
INSERT INTO ListaDeVagas (IdConcursoPublico, IdProfissao) VALUES
(
(IdConc),
(SELECT IdProfissao FROM Profissao WHERE Nome = prof)
);
END$
DELIMITER ;

/*      
*
* INSERÇÕES DAS PROFISSÕES BÁSICAS
*
*/

INSERT INTO Profissao (Nome) VALUES
('carpinteiro'),
('marceneiro'),
('assistente administrativo'),
('analista de sistemas'),
('professor de matemática'),
('inspetor penitenciário'),
('estagiário');

