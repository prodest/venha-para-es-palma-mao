var express = require('express');
var app = express();
var db = require('.././bd');
var bodyParser = require('body-parser');

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({
    extended: true
}));

app.route('/')
    .get(function (req, resp) {
        resp.render('index');
    });

app.route('/search')
    .get(function (req, resp) {
        var arrayRows = [];
        resp.render('search', { data: arrayRows });
    })
    .post(function (req, resp) {
        listCourses(req, resp);
    });

function listCourses(req, resp) {
    try {
        var opt = req.body.opcao;
        var termo = req.body.termo;
        var sqlParams = [termo];
        if (opt == 1) {
            db.query(db.viaCPF, sqlParams, function (err, res) {
                if (err) {
                    console.error(err);
                } else {
                    var arrayRows = res.rows;
                    resp.render('search', { data: arrayRows, opcao: opt });
                }
            });
        } else {
            db.query(db.viaCOD, sqlParams, function (err, res) {
                if (err) {
                    console.error(err);
                } else {
                    var arrayRows = res.rows;
                    resp.render('search', { data: arrayRows, opcao: opt });
                }
            });
        }

    } catch{
        resp.render('index');
    }
}

module.exports = app;