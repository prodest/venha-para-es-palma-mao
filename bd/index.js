const { Pool } = require('pg')

const configdb = require('./configdb')

var pool = new Pool(configdb);

module.exports = {
    query: (text, params, callback) => {
        return (pool.query(text, params, callback));
    },
    viaCPF: ' Select distinct C.Orgao, C.Codigo, C.Edital from Concurso C '
        + ' inner join Concurso_Profissao CP on CP.IdConcurso = C.IdConcurso '
        + ' where '
        + ' CP.IDProfissao in '
        + ' (select PP.IdProfissao from Pessoa P '
        + ' inner join Pessoa_Profissao PP on PP.IdPessoa = P.IdPessoa and '
        + ' P.Cpf like $1 ) ',
    viaCOD: ' Select P.nome, P.datanascimento, P.cpf from Pessoa P '
        + ' inner join Pessoa_Profissao PP on PP.IdPessoa = P.IdPessoa '
        + ' where PP.IdProfissao in '
        + ' (Select CP.IdProfissao from Concurso C '
        + ' inner join Concurso_Profissao CP on CP.IdConcurso = C.IdConcurso '
        + ' where C.Codigo like $1 ) '
        + ' order by P.nome '
}