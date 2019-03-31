/**
 * Author: Tarcisio Bruni Rangel
 */
var express = require('express');
var app = require('./controller/index.js');
var port = process.env.PORT || 3000;

//Setando a variavel que representa o diretorio de views;
app.set('views', './views');

//Definindo a engine view como Pug
app.set('view engine', 'pug');

//Tornando publico uso do diretorio pela aplicacao para captura de arquivos
app.use('/public', express.static('public'));

app.listen(
    port,
    function () {
        console.log('server on!!');
    });