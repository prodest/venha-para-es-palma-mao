var fs = require('fs');
const { Pool } = require('pg');

const configDB = {
    user: 'postgres',
    host: 'localhost',
    database: 'dbESPM',
    password: 'admin',
    port: 5432,
};

const pool = new Pool(configDB);

const dataProfissoes = {
    'analista de sistemas': 1,
    'marceneiro': 2,
    'carpinteiro': 3,
    'professor de matemática': 4,
    'assistente administrativo': 5,
    'inspetor penitenciário': 6,
    'estagiário': 7
}

//  var str = 'analista de sistemas';
// console.log(dataProfissoes[str]);
// console.log(dataProfissoes[str]===undefined)

//Leitura do Arquivo e Insercao dos Concursos e as profissoes associadas;
var data = fs.readFileSync("concursos.txt", "utf8");
var vet = data.split('\n');

vet.forEach(element => {
    var arrayInfo = element.split(' [');
    try {
        arrayInfo[0] = arrayInfo[0].split(' ');
        arrayInfo[1] = arrayInfo[1].replace('\r', '').replace(']', '').split(', ');
        // Expressao regular que captura as profissoes em cada linha :\[[a-zA-Z]*( [a-zA-Z]*)*\]
        pool.query('insert into concurso(orgao,edital,codigo) values ($1,$2,$3) RETURNING IDconcurso', arrayInfo[0]
            , (err, res) => {
                if (err) {
                    console.error(err);
                }
                else {
                    IdInserido = res.rows[0].idconcurso;
                    arrayInfo[1].forEach(element2 => {

                        pool.query('insert into Concurso_Profissao(idProfissao,idConcurso) values ($1,$2)', [dataProfissoes[element2], IdInserido]
                            , (err, res) => {
                                if (err) {
                                    console.error(err);
                                }
                            });

                    });
                }
            });
    } catch (err) {
        //console.error(err);
    }
});

//Leitura do Arquivo e Insercao dos Concursos e as profissoes associadas;
var data = fs.readFileSync("candidatos.txt", "utf8");
var vet = data.split('\n');

vet.forEach(element => {
    var arrayInfo = element.split(' [');
    try {
        arrayInfo[0] = arrayInfo[0].split(' ');
        arrayInfo[1] = arrayInfo[1].replace('\r', '').replace(']', '').split(', ');
        // Expressao regular que captura as profissoes em cada linha :\[[a-zA-Z]*( [a-zA-Z]*)*\]
        if (arrayInfo[0].length === 4) {
            var cpf = arrayInfo[0][3].replace('.', '').replace('.', '').replace('-', '');
            var nomePessoa = arrayInfo[0][0] + ' ' + arrayInfo[0][1];
            var dataNascimento = arrayInfo[0][2];
        } else {
            var cpf = arrayInfo[0][4].replace('.', '').replace('.', '').replace('-', '');
            var nomePessoa = arrayInfo[0][0] + ' ' + arrayInfo[0][1] + ' ' + arrayInfo[0][2];
            var dataNascimento = arrayInfo[0][3];
        }

        pool.query('insert into pessoa(nome,datanascimento,cpf) values ($1,$2,$3) RETURNING IDpessoa', [nomePessoa, dataNascimento, cpf]
            , (err, res) => {
                if (err) {
                    console.error(err);
                }
                else {
                    IdInserido = res.rows[0].idpessoa;
                    arrayInfo[1].forEach(element2 => {

                        pool.query('insert into Pessoa_Profissao(idProfissao,idPessoa) values ($1,$2)', [dataProfissoes[element2], IdInserido]
                            , (err, res) => {
                                if (err) {
                                    console.error(err);
                                }
                            });
                    });
                }
            });
    } catch (err) {
        //console.error(err);
    }
});