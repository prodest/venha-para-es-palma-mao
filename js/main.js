/* Funcao que verifica a opcao marcada e altera o texto do placeholder
pertencente ao input search (txtPesquisa) */
function exibeMsg(valor){
    if (valor == 'candidato') {
        document.getElementById('txtPesquisa').placeholder = "Informe o CPF do candidato..."
    } else {
        if (valor == 'concurso') {
            document.getElementById('txtPesquisa').placeholder = "Informe o Código do concurso..."
        }   
    }
}