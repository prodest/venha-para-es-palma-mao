$(function() {

    $("input[name='escolha']").change(function(){
        $("#txtPesquisa").attr("placeholder", $("input[name='escolha']:checked").attr("data-placeholder"));
    });

    $("#btnBusca").click(function() {

        $("#tabela").empty();

        $("#tabela").append("<table border=\"1\"></table>");

        var escolha = $("input[name='escolha']:checked").val();
        
        if (escolha == "candidato") {
            $.get("dados/arq_json/candidatos.json", {  }, function(data){
                console.log(data);
                for(i = 0; i < (data.length); i++) {
                    var tabela = $("#tabela").children();
                    tabela.append("<tr><td>"+data[i].cpf+"</td><td>"+data[i].nome+"</td><td>"+data[i].profissoes+"</td></tr>");	
                }
            }, "json");        
        } else if (escolha == "concurso") {
            $.get("dados/arq_json/concursos.json", {  }, function(data){
                console.log(data);
                for(i = 0; i < (data.length); i++) {
                    var tabela = $("#tabela").children();
                    tabela.append("<tr><td>"+data[i].cod_concurso+"</td><td>"+data[i].orgao+"</td><td>"+data[i].lista_vagas+"</td></tr>");	
                }
            }, "json");  
        }     
    });

});