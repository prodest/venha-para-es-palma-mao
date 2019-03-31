const { Pool } = require('pg')

const configdb = require('./configdb')

var pool = new Pool(configdb);

module.exports = {
    query: (text,params,callback) => {
        return (pool.query(text,params,callback));
    },
    viaCPF: " SELECT * from CURSOS where 1 = $1::integer " +
    " " +
    " ",
    viaCOD: ""
}