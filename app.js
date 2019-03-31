/**
 * Author: Tarcisio Bruni Rangel
 *
 * jshint app.js --config ./config.json (especificação de testes com JSHint)
 * 
 */
var express = require('express');
var bodyParser = require('body-parser');
var db = require('./db');
var app = express();
var port = process.env.PORT || 3000;

//Middleware responsavel por permitir receber json do cliente e a captura
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({
    extended: true
}));

//Setando a variavel que representa o diretorio de views;
app.set('views', './views');

//Definindo a engine view como Pug
app.set('view engine', 'pug');

//Tornando publico uso do diretorio pela aplicacao para captura de arquivos
app.use('/public', express.static('public'));

//Confguração de Rotas
app.route('/')
    .get(function (req, resp) {
        //listCourses(resp);
        var arrayRows = [];
        resp.render('index', { data: arrayRows });
    })
    .post(function (req, resp) {
        //listCourses(resp);
        var arrayRows = [];
        resp.render('index', { data: arrayRows });
    });

app.listen(
    port,
    function () {
        console.log('server on!!');
    });

function listCourses(resp) {
    var sqlStmt = 'SELECT * from CURSOS';
    var sqlParams = [];
    db.query(sqlStmt, sqlParams, function (err, res) {
        if (err) {
            console.error(err);
        } else {
            var arrayRows = res.rows;
            resp.render('index', { data: arrayRows });
        }
    });
}
