// --- Select2
//= require select2/dist/js/select2

var searchBox = document.getElementById("search-box");
if (searchBox)
$("#search-box").select2({
    minimumInputLength: 1,
    placeholder: "Digite o órgão ou código do concurso",
    theme: "bootstrap",
    tags: [],
    ajax: {
        url: "/concursos/buscar",
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
  window.location = "/concursos/" + $(e.currentTarget).val() + "/candidatos";
});
