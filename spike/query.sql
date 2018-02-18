/* WARM UP */
/* Pega o id de um determinado candidato de acordo com seu cpf */
SELECT candidato.candidato_id FROM `candidato`
WHERE candidato.candidato_cpf_varchar = "12345678901";

/* busca o ide de uma profissao ou vaga */
SELECT vaga_profissao.vaga_profissao_id FROM `vaga_profissao`
WHERE vaga_profissao.vaga_profissao_descrição_varchar = "carpinteiro";

/* Todos os candidatos com suas respectivas profissoes */
SELECT candidato.candidato_nome_varchar,vaga_profissao.vaga_profissao_descricao_varchar
FROM candidato INNER JOIN candidato_profissao INNER JOIN vaga_profissao
WHERE candidato.candidato_id = candidato_profissao.candidato_id
AND candidato_profissao.vaga_profissao_id = vaga_profissao.vaga_profissao_id;

/* Todos os mecanicos */
SELECT candidato.candidato_nome_varchar,vaga_profissao.vaga_profissao_descricao_varchar
FROM candidato INNER JOIN candidato_profissao INNER JOIN vaga_profissao
WHERE candidato.candidato_id = candidato_profissao.candidato_id
AND candidato_profissao.vaga_profissao_id = vaga_profissao.vaga_profissao_id
AND vaga_profissao.vaga_profissao_id =
(SELECT Vaga.vaga_profissao_id FROM vaga_profissao AS Vaga
  WHERE Vaga.vaga_profissao_descricao_varchar = "mecanico");

/* Todos os programadores e mecanicos */
SELECT candidato.candidato_nome_varchar,vaga_profissao.vaga_profissao_descricao_varchar
FROM candidato INNER JOIN candidato_profissao INNER JOIN vaga_profissao
WHERE candidato.candidato_id = candidato_profissao.candidato_id
AND candidato_profissao.vaga_profissao_id = vaga_profissao.vaga_profissao_id
AND (vaga_profissao.vaga_profissao_id =
  (SELECT Vaga.vaga_profissao_id FROM vaga_profissao AS Vaga
    WHERE Vaga.vaga_profissao_descricao_varchar = "programador")
OR  vaga_profissao.vaga_profissao_id =
  (SELECT Vaga.vaga_profissao_id FROM vaga_profissao AS Vaga
    WHERE Vaga.vaga_profissao_descricao_varchar = "mecanico"));

/* SESSAO UTIL */

/* Dado um CPF exibe todas as profissoes do candidato */
SELECT vaga_profissao.vaga_profissao_descricao_varchar
FROM vaga_profissao JOIN candidato_profissao JOIN candidato
WHERE candidato.candidato_id = candidato_profissao.candidato_id
AND vaga_profissao.vaga_profissao_id = candidato_profissao.vaga_profissao_id
AND candidato.candidato_cpf_varchar = "12345678913";

/* Todos os concuros com a seguinte vaga: "programador" ou "mecanico" (orgao, codigo e edital)*/
SELECT DISTINCT concurso.concurso_orgao_varchar, concurso.concurso_codigo_varchar, concurso.concurso_edital_varchar
FROM concurso JOIN concurso_vaga JOIN vaga_profissao
WHERE concurso.concurso_id=concurso_vaga.concurso_vaga_id
AND concurso_vaga.vaga_profissao_id=vaga_profissao.vaga_profissao_id
AND (vaga_profissao.vaga_profissao_descricao_varchar = "programador"
  OR vaga_profissao.vaga_profissao_descricao_varchar = "mecanico");


/* Dado um codigo de concurso exibe todas as vagas disponiveis */
SELECT vaga_profissao.vaga_profissao_descricao_varchar
FROM vaga_profissao JOIN concurso JOIN concurso_vaga
WHERE vaga_profissao.vaga_profissao_id = concurso_vaga.vaga_profissao_id
AND concurso.concurso_id=concurso_vaga.concurso_id
AND concurso.concurso_codigo_varchar = "123456";

/* Todos os candidatos que tem como profissao "engenheiro" ou "programador" ou "mecanico" sem repetição*/
SELECT DISTINCT candidato.candidato_nome_varchar, candidato.candidato_nascimento_date, candidato.candidato_cpf_varchar
FROM candidato JOIN candidato_profissao JOIN vaga_profissao
WHERE candidato.candidato_id = candidato_profissao.candidato_id
AND candidato_profissao.vaga_profissao_id = vaga_profissao.vaga_profissao_id
AND (vaga_profissao.vaga_profissao_descricao_varchar = "engenheiro"
  OR vaga_profissao.vaga_profissao_descricao_varchar = "programador"
  OR vaga_profissao.vaga_profissao_descricao_varchar = "mecanico");
