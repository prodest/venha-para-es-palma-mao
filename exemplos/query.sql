#-> Todos os candidatos com suas respectivas profissoes
SELECT candidato.candidato_nome_varchar,vaga_profissao.vaga_profissao_descricao_varchar
FROM candidato INNER JOIN candidato_profissao INNER JOIN vaga_profissao
WHERE candidato.candidato_id = candidato_profissao.candidato_id
AND candidato_profissao.vaga_profissao_id = vaga_profissao.vaga_profissao_id;

#-> Todos os mecanicos
SELECT candidato.candidato_nome_varchar,vaga_profissao.vaga_profissao_descricao_varchar
FROM candidato INNER JOIN candidato_profissao INNER JOIN vaga_profissao
WHERE candidato.candidato_id = candidato_profissao.candidato_id
AND candidato_profissao.vaga_profissao_id = vaga_profissao.vaga_profissao_id
AND vaga_profissao.vaga_profissao_id =
(SELECT Vaga.vaga_profissao_id FROM vaga_profissao AS Vaga
  WHERE Vaga.vaga_profissao_descricao_varchar = "mecanico");

#->Todos os programadores e mecanicos
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
