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
