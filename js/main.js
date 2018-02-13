$(document).ready(function() {

    $("#divCarregando").hide();

    /**
     * Altera de forma dinâmica o placeholder do campo de texto de acordo com o
     * que for marcado dentre as opções 'candidato' ou 'concurso' e aplicar a
     * máscara para o cpf, caso seja candidato, e personalizada para o código do concurso.
     */
    $("input[name='escolha']").change(function(){
        $("#txtPesquisa").attr("placeholder", $("input[name='escolha']:checked").attr("data-placeholder"));
        
        var escolha = $("input[name='escolha']:checked").val();
        
        if (escolha == "candidato") {
            $("#txtPesquisa").mask('000.000.000.-00');
        }else if (escolha == "concurso") {
            $("#txtPesquisa").mask('x9999999999',  {
                                                        translation: {
                                                            'x': {
                                                                pattern: /[^(0+)]/
                                                            }
                                                        }
                                                    });
        }
    });

    /**
     * Faz o carregamento dos arquivos JSON e retorna uma tabela com os valores
     * de acordo com o que for passado pelo usuário.
     */
    $("#btnBusca").click(function() {

        $("#tabela").empty();

        $("#divCarregando").show();
        $('#divCarregando').fadeOut('slow');

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