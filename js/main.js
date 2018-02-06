function exibeMsg(valor){
    if (valor == 'candidato') {
        document.getElementById('txtPesquisa').placeholder = "Informe o CPF do candidato..."
    } else {
        if (valor == 'concurso') {
            document.getElementById('txtPesquisa').placeholder = "Informe o numero do concurso..."
        }   
    }
}