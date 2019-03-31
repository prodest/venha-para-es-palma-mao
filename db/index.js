const { Pool } = require('pg')

const configdb = require('./configdb')

var pool = new Pool(configdb);

module.exports = {
    query: (text,params,callback) => {
        return (pool.query(text,params,callback));
    }
}