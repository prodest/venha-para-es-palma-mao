
function gocandidato() {

    $.ajax({
        url: "/apps/candidatos.php",
        cache: false,
        success: function (result) {
            document.getElementById("corpo").innerHTML = result

        }
    })
}
function govagas() {

    $.ajax({
        url: "/apps/vagas.php",
        cache: false,
        success: function (result) {
            document.getElementById("corpo").innerHTML = result

        }
    })
}

window.onload = function () {
    document.getElementById("candidatos").addEventListener("click",function(){
        gocandidato()
    } )
    document.getElementById("vagas").addEventListener("click",function(){
        govagas()
    } )
}
