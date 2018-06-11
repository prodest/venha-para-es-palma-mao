$(function () {

    $("#busca").keyup(function () {
        console.log("teste");
        var cpf = $(this).val();

        //Verefica se a variavel 
        if (busca != '') {
            var dados = {
                buscacpf: cpf
            }
            $.post('../Controler/buscacpf.php', dados, function (retorna) {
                $('.cpfbuscado').html(retorna);               
                
            });
        } else {
            $('.cpfbuscado').html('');
            
        }
    });

    $("#buscaConcurso").keyup(function () {
        console.log("teste");
        var CodConcurso = $(this).val();

        //Verefica se a variavel 
        if (buscaConcurso != '') {
            var dados = {
                buscaCodConcurso: CodConcurso
            }
            $.post('../Controler/buscaCodConcurso.php', dados, function (retorna) {
                $('.cpfbuscado').html(retorna);               
                
            });
        } else {
            $('.cpfbuscado').html('');
            
        }
    });


});