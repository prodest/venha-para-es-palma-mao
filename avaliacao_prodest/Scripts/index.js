/**
 * 
 * @summary index
 * @description Javascript principal do aplicativo. Responsável pela fluidez e funções da página principal.
 * @file index.js
 * @author Lucas Negreiros Coradine - 07.06.18
 *  
 **/

//Ao carregar a página
$(document).ready(function () {

    $('.loader').hide();

    /* MODAL */
    //Esconde os elementos da modal ao carregar a página
    $('#msg-sucesso').hide();
    $('.modal-loader').hide();

    //Ao iniciar uma busca
    $('#result-form').on('submit', function () {
        $('.conteudo').hide();
        $('.loader').show();
    });

    alterarBusca();

    //Adiciona a classe 'active' ao filtro selecionado e altera o valor do tipo de busca
    $('.filtros li:not(".atualizar-db") a').on('click', function () {
        $('.filtros li a.active').removeClass('active');
        $(this).addClass('active');

        alterarBusca();
    });

    //Não permite a digitação de outros caracteres além de números na barra de pesquisa
    $('.searchbar').on('keypress', function (e) {
        var charCode = (e.which) ? e.which : e.keyCode;
        if (charCode != 46 && charCode > 31 && (charCode < 48 || charCode > 57)) {
            return false;
        }

        return true;
    });

    //Abre a modal ao clicar no link
    $('.atualizar-db').on('click', function () {
        $('#modal-atualizar').modal('show');
    });

    //Altera o valor do tipo de busca de acordo com o filtro selecionado
    function alterarBusca() {
        var $filtroAtivo = $('.filtros li a.active');
        var $searchbar = $('.searchbar');
        var $tipoBusca = $('#tipo_busca, #tipo_busca_modal');
        var $nomeTabela = $('#nome-tabela'); // Texto da modal

        if ($filtroAtivo.text() == 'Candidatos') {
            $searchbar.attr('placeholder', 'Digite o CPF do candidato...');
            $nomeTabela.text("Candidatos");
            $tipoBusca.val(1); //TipoBuscaEnum - Candidato = 1
        } else {
            $searchbar.attr('placeholder', 'Digite o código do concurso...');
            $nomeTabela.text("Concursos");
            $tipoBusca.val(2); // TipoBuscaEnum - Concurso = 2
        }
    }     

    /* MODAL */
    //Esconde os elementos desnecessários e mostra os necessários ao iniciar a atualização
    $('#modal-atualizar-db').on('submit', function () {
        $('#botao-continuar').hide();
        $('.modal-body').hide();
        $('.modal-loader').show();
    });
});

//Função chamada ao carregar a tabela com sucesso
function onSuccess() {

    //Verifica a width da tabela para saber se é de um dispositivo móvel.
    //Se for adiciona a classe 'table-responsive' para uma melhor experiência.
    if ($(window).width() < 450)
        $('.tabela').addClass('table-responsive');

    //Inicia o elemento como DataTable, principalmente para poder alterar a ordem das colunas.
    $('.table').DataTable({
        info: false,
        lengthChange: false,
        pageLength: 10,

        "language": {
            "search": "Buscar:",
            "paginate": {
                "next": "Próximo",
                "previous": "Voltar"
            }
        }
    });

    //Mostra o conteúdo da tabela
    $('.loader').fadeOut();
    $('.conteudo').delay(400).fadeIn();    

    //Função para alterar o texto ao expandir e retrair o conteúdo escondido da lista.
    function expandirListaTexto($tableList, event) {
        var idLista = $tableList.attr('id');
        $expandirLista = $('.expandir-lista[href = "#' + idLista + '"]');

        switch (event.type) {
            case 'show':
                $expandirLista.text('Ver menos');
                break;
            case 'hide':
                $expandirLista.text('Ver mais');
                break;
        }
    }

    //Chamada ao expandir conteúdo da lista
    $('.table').on('show.bs.collapse', ".table-list", function (event) {
        expandirListaTexto($(this), event);
    });

    //Chamada ao retrair conteúdo da lista
    $('.table').on('hide.bs.collapse', ".table-list", function (event) {
        expandirListaTexto($(this), event);
    });

    $('.table-link').on('click', function () {
        if ($("#tipo_busca").val() == 1)
            $('#filtro-concursos a').click();
        else
            $('#filtro-candidatos a').click();

        $('.searchbar').val($(this).data('value'));
        $('#result-form').submit();
    });
}

//Função chamada ao completar a atualização da tabela no banco de dados
function modalOnComplete() {

    //Remodela a modal com uma mensagem de sucesso ou erro, escondendo elementos desnecessários
    $('#msg-sucesso').show();
    $('#msg-atualizar').hide();
    $('.modal-loader').fadeOut();
    $('#modal-conteudo').delay(400).fadeIn();
    $('#botao-cancelar').text('Fechar');
    $('#botao-cancelar').removeClass('btn-danger');

    //Recarrega a modal com a sua configuração inicial
    $('#modal-atualizar').on('hidden.bs.modal', function () {
        $('#msg-sucesso').hide();
        $('#msg-atualizar').show();
        $('#botao-cancelar').addClass('btn-danger');
        $('#botao-cancelar').text('Cancelar');
        $('#botao-continuar').show();
    });
}