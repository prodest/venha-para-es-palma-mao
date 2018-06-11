
#TRANSACTION para inserção da tabela  concursos e profConcursos
START TRANSACTION;
   INSERT concursos(orgao, edital, codConcurso) VALUES('SEDU', '12/2018', '12345678');
   INSERT INTO profconcursos(nomeProfissoes, concursos_idconcursos) 
   value('marceneiro',  (select LAST_INSERT_ID()));   
COMMIT;

#TRANSACTION para inserção da tabela  candidatos e profCandidatos
START TRANSACTION;
   INSERT candidatos(nome, dataNasc, cpf) VALUES('Paulo', '1991-05-26', '12345678');
   INSERT INTO profcandidatos(nomeprofissoes, candidatos_idcandidatos) 
   values('marceneiro', LAST_INSERT_ID());   
COMMIT;

# select para retonar  o concurso pelo CPF do candidato
SELECT c.orgao, c.edital, c.codConcurso FROM concursos c
join profconcursos pcon on pcon.concursos_idconcursos = c.idconcursos
join profcandidatos pcand on pcand.nomeprofissoes = pcon.nomeProfissoes
join candidatos cand on cand.idcandidatos = pcand.candidatos_idcandidatos
where cand.cpf like '%123%'; 



#select para retornar o candidato pelo codigo do cocurso
Select c.nome, c.dataNasc, c.cpf from candidatos c
join profcandidatos pcand on pcand.candidatos_idcandidatos = c.idcandidatos
join profconcursos pcon on pcon.nomeProfissoes = pcand.nomeprofissoes
join concursos con on con.idconcursos = pcon.concursos_idconcursos
where con.codConcurso like '%123%';

