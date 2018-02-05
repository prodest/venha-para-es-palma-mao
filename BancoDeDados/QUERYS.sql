
/* Esta query mostra as profissoes que o candidato selecionou */
SELECT Profissao.IdProfissao FROM Profissao JOIN CandidatoXProfissao ON Profissao.IdProfissao = CandidatoXProfissao.IdProfissao
JOIN Candidato ON CandidatoXProfissao.IdCandidato = Candidato.IdCandidato WHERE Candidato.CPF = '551.235.392-12';

/* Esta query mostra os concursos disponiveis por profissao */
SELECT ConcursoPublico.IdConcursoPublico FROM ConcursoPublico JOIN ListaDeVagas ON
ListaDeVagas.IdConcursoPublico = ConcursoPublico.IdConcursoPublico WHERE ListaDeVagas.IdProfissao IN (SELECT Profissao.IdProfissao FROM Profissao JOIN CandidatoXProfissao ON Profissao.IdProfissao = CandidatoXProfissao.IdProfissao
JOIN Candidato ON CandidatoXProfissao.IdCandidato = Candidato.IdCandidato WHERE Candidato.CPF = '551.235.392-12');

##################################################################################################################################
SOLUÇÃO DO PROBLEMA 1
/* Listar Orgaos, Codigos e editais pelo CPF */
SELECT Orgao, CodConcurso, EditalNum, EditalAno FROM ConcursoPublico WHERE IdConcursoPublico IN
(SELECT ConcursoPublico.IdConcursoPublico FROM ConcursoPublico JOIN ListaDeVagas ON
ListaDeVagas.IdConcursoPublico = ConcursoPublico.IdConcursoPublico WHERE ListaDeVagas.IdProfissao IN (SELECT Profissao.IdProfissao FROM Profissao JOIN CandidatoXProfissao ON Profissao.IdProfissao = CandidatoXProfissao.IdProfissao
JOIN Candidato ON CandidatoXProfissao.IdCandidato = Candidato.IdCandidato WHERE Candidato.CPF = '684.284.486-15'))
ORDER BY EditalAno Desc, Orgao;
##################################################################################################################################
SOLUÇÃO DO PROBLEMA 2
/* Listar os candidatos compatíveis com um concurso baseado no codigo do concurso */
SELECT DISTINCT Nome, DataNasc, CPF FROM Candidato JOIN CandidatoXProfissao ON Candidato.IdCandidato = CandidatoXProfissao.IdCandidato
JOIN ListaDeVagas ON CandidatoXProfissao.IdProfissao = ListaDeVagas.IdProfissao 
JOIN ConcursoPublico ON ListaDeVagas.IdConcursoPublico = ConcursoPublico.IdConcursoPublico WHERE ConcursoPublico.CodConcurso = '80671076246' ORDER BY Candidato.Nome;
##################################################################################################################################
