/*
Esta procedure agiliza a inserção de Candidatos e suas profissões selecionadas
*/

DELIMITER $
CREATE PROCEDURE InsereProfissaoDoCandidato (IN IdCand INT, IN prof VARCHAR(45))
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
CREATE PROCEDURE InsereProfissaoDoConcurso (IN IdConc INT, IN prof VARCHAR(45))
BEGIN
INSERT INTO ListaDeVagas (IdConcursoPublico, IdProfissao) VALUES
(
(IdCand),
(SELECT IdProfissao FROM Profissao WHERE Nome = prof)
);
END$
DELIMITER ;
