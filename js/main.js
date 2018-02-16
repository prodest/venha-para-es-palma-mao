$(document).ready(function() {

    /**
     * Desabilitar campo de texto (Firefox)
    */ 
    $("#txtPesquisa").prop('disabled', true);
    
    /**
     * Desabilitar radio button (Firefox)
    */ 
    $("input[name='escolha']").prop('checked', false);
    
    /**
     * Apaga qualquer texto que esteja armazenado na caixa de texto (Firefox)
    */
    $("#txtPesquisa").val("");

    /**
    *  Manter a msg 'aguarde...' escondida.
    */
    $("#divCarregando").hide();

    /**
     * Determina quanto aparece o botao de retornar ao topo
     */
    $(window).scroll(function(){ 
        // Se estiver 100px ou mais abaixo da página, o botão aparece.
        if ($(this).scrollTop() > 100) {
            $("#scrollup").fadeIn();
        } else {
            // Caso contrário, desaparece.
            $("#scrollup").fadeOut(); 
        }
    });

    /**
     * Executa ação de retornar ao topo
     */
    $("#scrollup").click(function(){
        //retorna ao topo da pagina
        $("html, body").animate({scrollTop: 0 }, 'slow');
        return false;
    });

    /**
     * Altera de forma dinâmica o placeholder do campo de texto de acordo com o
     * que for marcado dentre as opções 'candidato' ou 'concurso' e aplica a
     * máscara para o cpf, caso seja candidato, e personalizada para o código do concurso.
     */
    $("input[name='escolha']").change(function(){

        $("#txtPesquisa").prop('disabled', false);
        $("#txtPesquisa").val("");
        $("#txtPesquisa").attr("placeholder", $("input[name='escolha']:checked").attr("data-placeholder"));

        var escolha = $("input[name='escolha']:checked").val();
        
        if (escolha == "candidato") {
            // Mascara para o CPF
            $("#txtPesquisa").mask('000.000.000-00');
        }else if (escolha == "concurso") {
            // Mascara para o código do concurso
            // Resaltar que foi realizado o tratamento para o zero à esquerda, na qual é desconsiderado.
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
        
        //Limpa a div onde fica as informações do candidato ou concurso informado pelo usuário
        $("#info").empty();

        //Limpa a div onde é montada a tela para receber novas informações
        $("#tabela").empty();

        //Exibe a msg de 'Aguarde...' enquanto é gerado a tabela com os dados
        $("#divCarregando").show();

        //Pega o valor marcado pelo usuário ('candidato' ou 'concurso')
        var escolha = $("input[name='escolha']:checked").val();

        //Verifica o valor e toma a descisão a partir disso        
        if (escolha == "candidato") {
            //Busca os dados do arquivo candidatos.json
            $.get("dados/arq_json/candidatos.json", {  }, function(data){
                console.log(data);

                //Pega o CPF informado completo e apenas os numeros para fazer as devidas análises
                var CPF_Completo = $("#txtPesquisa").val();
                var CPF_Clean = $("#txtPesquisa").cleanVal();

                /** 
                 * OBS: Analisando a fonte candidatos.txt foi detectado que
                 * os CPFs registrados não passavam no teste da soma dos numeros.
                 * Logo estamos considerando CPF válido aquele que possui 11 digitos.
                */ 
                if(CPF_Clean.length < 11) { //Verifica se o CPF 'é valido'
                    alert("CPF inválido! Tente novamente.");
                    location.reload(true);
                } else {
                    var achou = false;
                    var i = 0;
                    var lst_profissoes = [];
                    var verificaCod = []; // array criada para evitar duplicidades na tabela
                    
                    /**
                     * Caso o CPF seja 'válido', será verificado a existência nos registros
                     * e, caso seja encontrado, é pego a relação de profissões daquele canditato
                    */  
                    while ((i < data.length)&&(!achou)) {
                        
                        if (CPF_Completo == data[i].cpf) {
                            achou = true;
                            lst_profissoes = data[i].profissoes;                 
                        }else{
                            i++;
                        }
                    }

                    //Caso não encontre candidato com o CPF informado, retorna msg de 'Não encontrado'
                    if (!achou) {
                        alert("Não foi encontrado nenhum candidato com esse CPF. =(");
                        location.reload(true);  
                    }else{
                        $("#info").empty();
                        $("#info").append("<h3>Dados do candidato:</h3>"+
                                                "<p>Nome: "+data[i].nome+"</p>"+
                                                "<p>Data de nascimento: "+data[i].dt_nasc+"</p>"+
                                                "<p>Trabalha como: "+data[i].profissoes+"</p>");
                        /**
                         * Uma vez passado pelos tratamentos, começa-se a montar a estrutura da 
                         * table para armazenar os dados que serão exibidos
                        */
                        $("#tabela").append("<table border=\"1\">" +
                                                "<thead>"+
                                                    "<tr>"+
                                                        "<th>Orgão</th>"+
                                                        "<th>Cód. Concurso</th>"+
                                                        "<th>Edital</th>"+
                                                    "</tr>"+
                                                "</thead>"+
                                                "<tbody></tbody>"+
                                            "</table>");
                        //Pega os dados de concurso.json para comparar com o perfil do candidato
                        $.get("dados/arq_json/concursos.json", {  }, function(data_conc){
                            for(i = 0; i < (lst_profissoes.length); i++) {
                                for (j = 0; j < data_conc.length; j++) {
                                    /**
                                     * Para que a informação seja exibida na tabela é
                                     * verificado se a profissão existe na listagem de vagas
                                     * do concurso e se o código do concurso ainda não foi
                                     * adicionado no array 'verificaCod'. Caso exista o código
                                     * no 'verificaCod' ou a profissão não esteja relacionada na
                                     * lista de vagas, a informação não é adicionada a tabela. 
                                    */
                                    if (($.inArray(lst_profissoes[i], data_conc[j].lista_vagas) !== -1)&&($.inArray(data_conc[j].cod_concurso, verificaCod) == -1)) {
                                        verificaCod.push(data_conc[j].cod_concurso);
                                        var tabela = $("#tabela").children();
                                        tabela.append("<tr>"+
                                                        "<td>"+data_conc[j].orgao+"</td>"+
                                                        "<td>"+data_conc[j].cod_concurso+"</td>"+
                                                        "<td>"+data_conc[j].edital+"</td>"+
                                                    "</tr>");	
                                    }
                                }
                            }
                            /**
                             * Também verficamos com o 'verificaCod' se não foi localizado
                             * nenhum registro. Caso a array esteja zerada, é retornado a 
                             * mensagem de que não foi localizado concursos para esse perfil.
                            */
                            if (verficaCod.length == 0) {
                                $("#tabela").empty();
                                $("#tabela").append("<table border=\"1\">"+
                                                        "<tbody>"+
                                                            "<tr>"+
                                                                "<td>Não existem concursos "+
                                                                "destinados ao perfil desse "+
                                                                "candidato.</td>"+
                                                            "</tr>"+
                                                        "</tbody>"+
                                                    "</table>");
                            }
                        }, "json");
                        //Efeito para 'subir' com a tela, mostrando o resultado obtido.
                        $('html, body').animate({
                            scrollTop: $("#info").offset().top
                        }, 'slow');                            
                    }
                }
            }, "json");        
        } else if (escolha == "concurso") { //Caso a opção escolhida seja 'concurso'
            //pega os dados de concurso.json
            $.get("dados/arq_json/concursos.json", {  }, function(data){
                console.log(data);

                //Pegando código informado pelo usuário
                var cod_Completo = $("#txtPesquisa").val();

                /**
                 * Tratamento para caso o usuário tente pesquisar sem informar 
                 * o código de concurso. 
                */
                if (cod_Completo.length == 0) {
                    alert("Informe o código do concurso.");
                    location.reload(true);                    
                } else {
                    var achou = false;
                    var i = 0;
                    var lst_vagas = [];
                    var verificaCPF = []; //Array para evitar duplicidade na tabela

                    /**
                     * Verifica se existe esse código na relação de concursos e pega
                     * a listagem de vagas concurso, caso encontrado.
                    */
                    while ((i < data.length)&&(!achou)) {
                        if (cod_Completo == data[i].cod_concurso) {
                            achou = true;
                            lst_vagas = data[i].lista_vagas;                 
                        }else{
                            i++;
                        }
                    }

                    //Retorna msg de 'Não encontrado' caso não tenha localizado código
                    if (!achou) {
                        alert("Não foi encontrado nenhum concurso com esse código. =(");
                        location.reload(true);  
                    }else{
                        $("#info").empty();
                        $("#info").append("<h3>Dados do concurso:</h3>"+
                                                "<p>Orgão: "+data[i].orgao+"</p>"+
                                                "<p>Edital: "+data[i].edital+"</p>"+
                                                "<p>Vagas para: "+data[i].lista_vagas+"</p>");
                        //Começa a montar a tabela para a relação de candidatos
                        $("#tabela").append("<table border=\"1\">"+
                                                "<thead>"+
                                                    "<tr>"+
                                                        "<th>Nome</th>"+
                                                        "<th>Dt. de Nasc.</th>"+
                                                        "<th>CPF</th>"+
                                                    "</tr>"+
                                                "</thead>"+
                                                "<tbody></tbody>"+
                                            "</table>");
                        //Pega os dados de candidatos.json
                        $.get("dados/arq_json/candidatos.json", {  }, function(data_cand){
                            for(i = 0; i < (lst_vagas.length); i++) {
                                for (j = 0; j < data_cand.length; j++) {
                                    /**
                                     * Para que a informação seja exibida na tabela é
                                     * verificado se a vaga bate com a profissão do
                                     * candidato e se o CPF do candidato ainda não foi
                                     * adicionado no array 'verificaCPF'. Caso exista o CPF
                                     * no 'verificaCPF' ou a vaga não esteja relacionada a(s)
                                     * profissão(ões) do candidato,
                                     * a informação não é adicionada a tabela. 
                                    */
                                    if (($.inArray(lst_vagas[i], data_cand[j].profissoes) !== -1)&&($.inArray(data_cand[j].cpf, verificaCPF) == -1)) {
                                        verificaCPF.push(data_cand[j].cpf);
                                        var tabela = $("#tabela").children();
                                        tabela.append("<tr>"+
                                                        "<td>"+data_cand[j].nome+"</td>"+
                                                        "<td>"+data_cand[j].dt_nasc+"</td>"+
                                                        "<td>"+data_cand[j].cpf+"</td>"+
                                                    "</tr>");    
                                    }                                    
                                }                                
                            }
                            /**
                             * Também verficamos com o 'verificaCPF' se não foi localizado
                             * nenhum registro. Caso a array esteja zerada, é retornado a 
                             * mensagem de que não foi localizado candidato para esse concurso.
                            */
                            if (verificaCPF.length == 0) {
                                $("#tabela").empty();
                                $("#tabela").append("<table border=\"1\">"+
                                                        "<tbody>"+
                                                            "<tr>"+
                                                                "<td>Não existem candidatos com "+
                                                                "perfil para esse concurso.</td>"+
                                                            "</tr>"+
                                                        "</tbody>"+
                                                    "</table>");
                            }
                        }, "json");
                        //Efeito para 'subir' com a tela, mostrando o resultado obtido.
                        $('html, body').animate({
                            scrollTop: $("#info").offset().top
                        }, 'slow');
                    }
                }
            }, "json");  
        }
        //Causa o efeito de desaparecimento da msg de 'aguarde...'
        $("#divCarregando").fadeOut("slow");     
    });
});