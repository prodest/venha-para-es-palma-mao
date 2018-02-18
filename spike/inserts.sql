INSERT INTO `candidato`
(`candidato_id`, `candidato_nome_varchar`, `candidato_nascimento_date`, `candidato_cpf_varchar`)
VALUES (NULL, 'candidato', '2018-02-13', '13596133742');

INSERT INTO `vaga_profissao`
(`vaga_profissao_id`, `vaga_profissao_descricao_varchar`)
VALUES (NULL, 'profissao');

INSERT INTO `candidato_profissao` (`candidato_profissao_id`, `candidato_id`, `vaga_profissao_id`)
VALUES (NULL, '1', '1'), (NULL, '1', '2');

/* INSERT sem repetições */
INSERT INTO `vaga_profissao`(`vaga_profissao_id`, `vaga_profissao_descricao_varchar`)
    SELECT NULL, 'tecnico de futebol'
    FROM DUAL
    WHERE NOT EXISTS(SELECT `vaga_profissao_descricao_varchar` FROM `vaga_profissao`
    WHERE `vaga_profissao_descricao_varchar` = 'tecnico de futebol');
