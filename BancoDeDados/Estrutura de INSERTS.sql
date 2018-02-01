/*
*	ESTE SCRIPT SERVE APENAS DE EXEMPLO PARA MOSTRAR 
*	A ESTRUTURA DE INSERT DE CADA TABELA
*	Autor: Mateus Garcia
*/


/*      
*
* INSERÇÕES DE PROFISSÕES
*
*/

INSERT INTO Profissao (Nome) VALUES
('carpinteiro'),
('marceneiro'),
('assistente administrativo'),
('analista de sistemas'),
('professor de matemática');



INSERT INTO Candidato (Nome,DataNasc,CPF) VALUES
('Lindsey Craft', '1976-05-19', '182.845.084-34'),
('Jackie Dawson', '1970-08-14','311.667.973-47'),
('Cory Mendoza','1957-02-11','565.512.353-92');



INSERT INTO CandidatoXProfissao (IdCandidato, IdProfissao) VALUES
(
	(SELECT IdCandidato FROM Candidato WHERE CPF = '182.845.084-34'),
	(SELECT IdProfissao FROM Profissao WHERE Nome = 'carpinteiro')
),

(
	(SELECT IdCandidato FROM Candidato WHERE CPF = '311.667.973-47'),
	(SELECT IdProfissao FROM Profissao WHERE Nome = 'marceneiro')
),

(
	(SELECT IdCandidato FROM Candidato WHERE CPF = '311.667.973-47'),
	(SELECT IdProfissao FROM Profissao WHERE Nome = 'assistente administrativo')
),

(
	(SELECT IdCandidato FROM Candidato WHERE CPF = '565.512.353-92'),
	(SELECT IdProfissao FROM Profissao WHERE Nome = 'carpinteiro')
),

(
	(SELECT IdCandidato FROM Candidato WHERE CPF = '565.512.353-92'),
	(SELECT IdProfissao FROM Profissao WHERE Nome = 'marceneiro')
);


/*	
*
* INSERÇÕES DE CONCURSOS PÚBLICOS
*
*/

INSERT INTO ConcursoPublico (Orgao,EditalNum,EditalAno,CodConcurso) VALUES 
('SEDU',9,2016,'61828450843'),
('SEJUS',15,2017,'61828450843'),
('SEJUS',17,2017,'95655123539');



/*primeiro*/
INSERT INTO ListaDeVagas (IdConcursoPublico,IdProfissao) VALUES
(
(
SELECT IdConcursoPublico FROM ConcursoPublico WHERE 
CodConcurso = '61828450843' AND EditalNum = 9 AND EditalAno = 2016
),
(
SELECT IdProfissao FROM Profissao WHERE Nome = 'analista de sistemas'
)
),
(
(
SELECT IdConcursoPublico FROM ConcursoPublico WHERE 
CodConcurso = '61828450843' AND EditalNum = 9 AND EditalAno = 2016
),
(
SELECT IdProfissao FROM Profissao WHERE Nome = 'marceneiro'
)
);

/*segundo*/
INSERT INTO ListaDeVagas (IdConcursoPublico,IdProfissao) VALUES
(
(
SELECT IdConcursoPublico FROM ConcursoPublico WHERE 
CodConcurso = '61828450843' AND EditalNum = 15 AND EditalAno = 2017
),
(
SELECT IdProfissao FROM Profissao WHERE Nome = 'carpinteiro'
)
),
(
(
SELECT IdConcursoPublico FROM ConcursoPublico WHERE 
CodConcurso = '61828450843' AND EditalNum = 15 AND EditalAno = 2017
),
(
SELECT IdProfissao FROM Profissao WHERE Nome = 'professor de matematica'
)
),
(
(
SELECT IdConcursoPublico FROM ConcursoPublico WHERE 
CodConcurso = '61828450843' AND EditalNum = 15 AND EditalAno = 2017
),
(
SELECT IdProfissao FROM Profissao WHERE Nome = 'assistente administrativo'
)
);

/*terceiro*/
INSERT INTO ListaDeVagas (IdConcursoPublico,IdProfissao) VALUES
(
(
SELECT IdConcursoPublico FROM ConcursoPublico WHERE 
CodConcurso = '95655123539' AND EditalNum = 17 AND EditalAno = 2017
),
(
SELECT IdProfissao FROM Profissao WHERE Nome = 'professor de matematica'
)
);

