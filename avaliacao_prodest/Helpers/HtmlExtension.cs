using avaliacao_prodest.Database;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace avaliacao_prodest.Helpers
{
    /// <summary>
    /// Classe extensão para cshtml
    /// </summary>
    public static class HtmlHelpers
    {
        /// <summary>
        /// Metódo para exibir a lista de concursos do candidato em formato de tópico.
        /// </summary>
        /// <param name="htmlHelper">Recebe o próprio HtmlHelper.</param>
        /// <param name="lista">Recebe uma Lista de string.</param>
        /// <param name="maximoItens">Recebe o máximo de itens a serem exibidos antes de esconder o resto do conteúdo.</param>
        /// <param name="idLista">Recebe o id da lista.</param>
        /// <returns>Retorna uma IHtmlString com os dados da lista formatados em tópicos.</returns>
        public static IHtmlString ExibirListaTopico(this HtmlHelper htmlHelper, List<Concursos> lista, int maximoItens, int idLista)
        {
            if (!lista.Vazia())
            {
                string result = "";

                if (lista.Count > maximoItens)
                {
                    var tagDiv = new TagBuilder("div");
                    tagDiv.Attributes.Add("id", ("texto-" + idLista));
                    tagDiv.AddCssClass("table-list collapse");

                    var tagExpandir = new TagBuilder("a");
                    tagExpandir.AddCssClass("expandir-lista");
                    tagExpandir.Attributes.Add("data-toggle", "collapse");
                    tagExpandir.Attributes.Add("href", ("#texto-" + idLista));
                    tagExpandir.InnerHtml = "Ver mais";

                    int count = 0;

                    foreach (var item in lista)
                    {
                        if (count < maximoItens)
                            result += "<a data-value='" + item.Codigo + "'class='table-link' href='#'>" + item.IdConcurso.Replace("/", " - ") + (item == lista.Last() ? "</a></p>" : ",</a></p>");
                        else
                            tagDiv.InnerHtml = "<a data-value='" + item.Codigo + "'class='table-link' href='#'>" + item.IdConcurso.Replace("/", " - ") + (item == lista.Last() ? " </a></p>" : ",</a></p>");

                        count++;
                    }

                    result += tagDiv;
                    result += tagExpandir;
                }
                else
                {
                    foreach (var item in lista)
                    {
                        result += "<a data-value='" + item.Codigo + "'class='table-link' href='#'>" + item.IdConcurso.Replace("/", " - ") + (item == lista.Last() ? " </a></p>" : ",</p></p>");
                    }
                }

                return new MvcHtmlString(result);

            }

            return new MvcHtmlString(string.Empty);
        }

        /// <summary>
        /// Metódo para exibir a lista de candidatos do concurso em formato de tópico.
        /// </summary>
        /// <param name="htmlHelper">Recebe o próprio HtmlHelper.</param>
        /// <param name="lista">Recebe uma Lista de candidatos.</param>
        /// <param name="maximoItens">Recebe o máximo de itens a serem exibidos antes de esconder o resto do conteúdo.</param>
        /// <param name="idLista">Recebe o id da lista.</param>
        /// <returns>Retorna uma IHtmlString com os dados da lista formatados em tópicos.</returns>
        public static IHtmlString ExibirListaTopico(this HtmlHelper htmlHelper, List<Candidatos> lista, int maximoItens, int idLista)
        {
            if (!lista.Vazia())
            {
                string result = "";

                if (lista.Count > maximoItens)
                {
                    var tagDiv = new TagBuilder("div");
                    tagDiv.Attributes.Add("id", ("texto-" + idLista));
                    tagDiv.AddCssClass("table-list collapse");

                    var tagExpandir = new TagBuilder("a");
                    tagExpandir.AddCssClass("expandir-lista");
                    tagExpandir.Attributes.Add("data-toggle", "collapse");
                    tagExpandir.Attributes.Add("href", ("#texto-" + idLista));
                    tagExpandir.InnerHtml = "Ver mais";

                    int count = 0;

                    foreach (var item in lista)
                    {
                        if (count < maximoItens)
                            result += "<p><a data-value='" + item.CPF + "'class='table-link' href='#'>" + item.Nome + (item == lista.Last() ? "</a></p>" : ",</a></p>");
                        else
                            tagDiv.InnerHtml = "<a data-value='" + item.CPF + "'class='table-link' href='#'>" + item.Nome + (item == lista.Last() ? "</a></p>" : ",</a></p>");

                        count++;
                    }

                    result += tagDiv;
                    result += tagExpandir;
                }
                else
                {
                    foreach (var item in lista)
                    {
                        result += "<a data-value='" + item.CPF + "'class='table-link' href='#'>" + item.Nome + (item == lista.Last() ? "</a></p>" : ",</p></p>");
                    }
                }

                return new MvcHtmlString(result);

            }

            return new MvcHtmlString(string.Empty);
        }
    }
}