$(document).ready(function() {

    // Manter a msg 'aguarde...' escondida.
    $("#divCarregando").hide();

    /**
     * Determina quanto aparece o botao para retornar ao topo
     */
    $(window).scroll(function(){ 
         if ($(this).scrollTop() > 100) { // Se estivermos 100px ou mais abaixo da página, o botão aparece
              $("#scrollup").fadeIn();
         } else {
              $("#scrollup").fadeOut(); // Caso contrário, desaparece -->
         }
    });

    /**
     * Executa ação de retornar ao topo ao clicar no botão
     */
    $("#scrollup,.back_top").click(function(){ 
         $("html, body").animate({scrollTop: 0 }, 'slow');
         return false;
    });


    /**
     * Altera de forma dinâmica o placeholder do campo de texto de acordo com o
     * que for marcado dentre as opções 'candidato' ou 'concurso' e aplicar a
     * máscara para o cpf, caso seja candidato, e personalizada para o código do concurso.
     */
    $("input[name='escolha']").change(function(){

        $("#txtPesquisa").prop('disabled', false);
        $("#txtPesquisa").attr("placeholder", $("input[name='escolha']:checked").attr("data-placeholder"));

        var escolha = $("input[name='escolha']:checked").val();
        
        if (escolha == "candidato") {
            $("#txtPesquisa").mask('000.000.000-00');
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
        $("#divCarregando").fadeOut("slow");

        var escolha = $("input[name='escolha']:checked").val();
        
        if (escolha == "candidato") {
            $.get("dados/arq_json/candidatos.json", {  }, function(data){
                console.log(data);

                var CPF_Completo = $("#txtPesquisa").val();
                var CPF_Clean = $("#txtPesquisa").cleanVal();

                if(CPF_Clean.length < 11) {
                    alert("CPF inválido! Tente novamente.");
                    location.reload(true);
                } else {
                    var achou = false;
                    var i = 0;
                    var lst_profissoes = [];
                    var verficaCod = [];

                    while ((i < data.length)&&(!achou)) {
                        
                        if (CPF_Completo == data[i].cpf) {
                            achou = true;
                            lst_profissoes = data[i].profissoes;                 
                        }
                        i++;
                    }

                    if (!achou) {
                        alert("Não foi encontrado nenhum candidato com esse CPF. =(");
                        location.reload(true);  
                    }else{
                        $("#tabela").append("<table border=\"1\"><thead><tr><th>Orgão</th><th>Cód. Concurso</th><th>Edital</th></tr></thead><tbody></tbody></table>");
                        $.get("dados/arq_json/concursos.json", {  }, function(data_conc){
                            for(i = 0; i < (lst_profissoes.length); i++) {
                                for (j = 0; j < data_conc.length; j++) {
                                    if (($.inArray(lst_profissoes[i], data_conc[j].lista_vagas) !== -1)&&($.inArray(data_conc[j].cod_concurso, verficaCod) == -1)) {
                                        verficaCod.push(data_conc[j].cod_concurso);
                                        var tabela = $("#tabela").children();
                                        tabela.append("<tr><td>"+data_conc[j].orgao+"</td><td id='cod_Conc'>"+data_conc[j].cod_concurso+"</td><td>"+data_conc[j].edital+"</td></tr>");	
                                    }
                                }
                            }
                            if (verficaCod.length == 0) {
                                $("#tabela").empty();
                                $("#tabela").append("<table border=\"1\"><tbody><tr><td>Não existem concursos destinados à esse candidato.</td></tr></tbody></table>");
                            }
                        }, "json");
                        $('html, body').animate({
                            scrollTop: $("#tabela").offset().top
                        }, 'slow');                            
                    }
                }
            }, "json");        
        } else if (escolha == "concurso") {
            $.get("dados/arq_json/concursos.json", {  }, function(data){
                console.log(data);

                var cod_Completo = $("#txtPesquisa").val();
                var cod_Clean = $("#txtPesquisa").cleanVal();

                if (cod_Clean.length == 0) {
                    alert("Informe o código do concurso.");
                    location.reload(true);                    
                } else {
                    var achou = false;
                    var i = 0;
                    var lst_vagas = [];
                    var verificaCPF = [];

                    while ((i < data.length)&&(!achou)) {
                        
                        if (cod_Completo == data[i].cod_concurso) {
                            achou = true;
                            lst_vagas = data[i].lista_vagas;                 
                        }
                        
                        i++;
                    }

                    if (!achou) {
                        alert("Não foi encontrado nenhum concurso com esse código. =(");
                        location.reload(true);  
                    }else{
                        $("#tabela").append("<table border=\"1\"><thead><tr><th>Nome</th><th>Dt. de Nasc.</th><th>CPF</th></tr></thead><tbody></tbody></table>");
                        $.get("dados/arq_json/candidatos.json", {  }, function(data_cand){
                            for(i = 0; i < (lst_vagas.length); i++) {
                                for (j = 0; j < data_cand.length; j++) {
                                    if (($.inArray(lst_vagas[i], data_cand[j].profissoes) !== -1)&&($.inArray(data_cand[j].cpf, verificaCPF) == -1)) {
                                        verificaCPF.push(data_cand[j].cpf);
                                        var tabela = $("#tabela").children();
                                        tabela.append("<tr><td>"+data_cand[j].nome+"</td><td>"+data_cand[j].dt_nasc+"</td><td>"+data_cand[j].cpf+"</td></tr>");    
                                    }                                    
                                }                                
                            }
                            if (verificaCPF.length == 0) {
                                $("#tabela").empty();
                                $("#tabela").append("<table border=\"1\"><tbody><tr><td>Não existem candidatos para esse concurso.</td></tr></tbody></table>");
                            }
                        }, "json");
                        $('html, body').animate({
                            scrollTop: $("#tabela").offset().top
                        }, 'slow');
                    }
                }
            }, "json");  
        }     
    });
});