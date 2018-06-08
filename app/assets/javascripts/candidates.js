// --- Select2
//= require select2/dist/js/select2

var searchBox = document.getElementById("search-box");
if (searchBox)
$("#search-box").select2({
    minimumInputLength: 1,
    placeholder: "Digite o nome ou cpf do candidato",
    theme: "bootstrap",
    tags: [],
    ajax: {
        url: "/candidatos/buscar",
        dataType: 'json',
        type: "GET",
        quietMillis: 50,
        id: function(obj) {
          return obj.id;
        },
        data: function (term) {
          return {
            q: term.term
          };
        },
        results: function (data) {
           return { results: data.results };
         }
    },
});

$('#search-box').on("select2:select", function(e) {
  window.location = "/candidatos/" + $(e.currentTarget).val() + "/concursos";
});

function hasNumber(string) {
  return /\d/.test(string);
}
