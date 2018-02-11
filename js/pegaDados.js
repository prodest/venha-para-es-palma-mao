/*$(function() {
	
    $.get("dados/arq_json/candidatos.json", {  }, function(data){
        console.log(data);
        $("#nome").html(data[0].nome);
        $("#email").html(data[0].cpf);
    }, "json");
    
});*/

$(function() {

    $("#tabela").append("<table border=\"1\"></table>");
	
    $.get("dados/arq_json/concursos.json", {  }, function(data){
        console.log(data);
        for(i = 0; i < (data.length); i++) {
            var tabela = $("#tabela").children();
            tabela.append("<tr><td>"+data[i].cod_concurso+"</td><td>"+data[i].orgao+"</td><td>"+data[i].lista_vagas+"</td></tr>");	
        }
    }, "json");
    
});