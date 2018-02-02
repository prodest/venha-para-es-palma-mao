
/* Esta query mostra as profissoes que o candidato selecionou */
SELECT Profissao.IdProfissao FROM Profissao JOIN CandidatoXProfissao ON Profissao.IdProfissao = CandidatoXProfissao.IdProfissao
JOIN Candidato ON CandidatoXProfissao.IdCandidato = Candidato.IdCandidato WHERE Candidato.CPF = '120.164.449-15';

/* Esta query mostra os concursos disponiveis por profissao */

