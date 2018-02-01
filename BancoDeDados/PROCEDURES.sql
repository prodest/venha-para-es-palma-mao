DELIMITER $

CREATE PROCEDURE InsereProfissaoDoCandidato (IN IdCand INT, IN prof VARCHAR(45))

INSERT INTO CandidatoXProfissao (IdCandidato, IdProfissao) VALUES
(
	(IdCand),
	(SELECT IdProfissao FROM Profissao WHERE Nome = prof)
);
END $

DELIMITER ;
