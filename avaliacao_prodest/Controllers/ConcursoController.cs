using avaliacao_prodest.Database;
using avaliacao_prodest.Helpers;
using avaliacao_prodest.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Web.Mvc;

namespace avaliacao_prodest.Controllers
{
    /// <summary>
    /// Controller do Concurso
    /// </summary>
    public class ConcursoController : Controller
    {
        /// <summary>
        /// Método principal da controller.
        /// </summary>
        /// <remarks>
        /// Carrega a tabela de concursos na página principal.
        /// </remarks>
        /// <param name="busca">String que traz o código pesquisado pelo usuário.</param>
        /// <returns>Retorna uma PartialView, no caso, a tabela de concursos.</returns>
        /// <see cref="RecuperarConcursos(string)"/>
        public ActionResult Index(string busca)
        {
            return PartialView("~/Views/Home/ConcursosDatagrid.cshtml", RecuperarConcursos(busca));
        }

        /// <summary>
        /// Método para recuperar as informações dos concursos.
        /// </summary>
        /// <param name="busca">String que traz o código pesquisado pelo usuário.</param>
        /// <returns>Retorna uma lista contendo as informações dos concursos que sem encaixam na pesquisa.</returns>
        public List<ConcursoViewModel> RecuperarConcursos(string busca)
        {
            using (AvaliacaoProdestDbEntities db = new AvaliacaoProdestDbEntities())
            {
                //Retorna os dados concurso cujo código contém os números da busca
                if (!string.IsNullOrEmpty(busca))
                {
                    return db.Concursos
                        .Where(x => x.Codigo.StartsWith(busca))
                        .Select(o => new ConcursoViewModel()
                        {
                            IdConcurso = o.IdConcurso,
                            Orgao = o.Orgao,
                            Edital = o.Edital,
                            Codigo = o.Codigo,
                            Vagas = db.VagaConcurso
                                .Where(p => p.IdConcurso == o.IdConcurso)
                                .Select(p => p.Vaga)
                                .ToList(),
                            Candidatos = db.ProfissaoCandidato
                            .Where(pc => o.VagaConcurso
                                .Any(vc => vc.Vaga == pc.Profissao)
                            ).Select(x => x.Candidatos).ToList()
                        }).ToList();
                }

                return null;
            }
        }

        /// <summary>
        /// Atualiza, no banco de dados, a tabela de Concursos e de vagas do concurso de acordo com as informações do arquivo .txt
        /// </summary>
        /// <param name="model">Recebe o classe que contém os dados da página inicial.</param>
        /// <returns>Retorna uma PartialView contendo uma mensagem de sucesso ou de erro, dependendo do resultado.</returns>
        public ActionResult AtualizarConcursosDB(HomeViewModel model)
        {
            using (var db = new AvaliacaoProdestDbEntities())
            {
                try
                {
                    var concursos = MapearConcursosTxt();
                    
                    foreach (var concurso in concursos)
                    {
                        var original = db.Concursos.SingleOrDefault(c => c.IdConcurso == concurso.IdConcurso);

                        if (original != null)
                        {
                            original.Orgao = concurso.Orgao;
                            original.Edital = concurso.Edital;
                            original.Codigo = concurso.Codigo;

                            //Deleta as vagas já salvas desse concurso e insere as novas
                            db.VagaConcurso.RemoveRange(original.VagaConcurso);
                            original.VagaConcurso = concurso.VagaConcurso;
                        }
                        else
                        {
                            db.Concursos.Add(concurso);
                        }
                    }

                    db.SaveChanges();
                }
                catch (Exception ex)
                {
                    model.Erro = (ex.InnerException == null ? ex.Message : ex.InnerException.Message);
                }                
            }

            return PartialView("~/Views/Home/_AtualizarDbMensagem.cshtml", model);
        }

        /// <summary>
        /// Faz a leitura do arquivo 'concursos.txt'
        /// </summary>
        /// <returns>Retorna uma lista de concursos com suas respectivas informações</returns>
        public List<Concursos> MapearConcursosTxt()
        {
            var result = new List<Concursos>();
            var path = AppDomain.CurrentDomain.BaseDirectory;

            string[] concursos = System.IO.File.ReadAllLines(path + @"\concursos.txt", Encoding.GetEncoding("iso-8859-1"));

            foreach (var concurso in concursos)
            {
                if (!string.IsNullOrEmpty(concurso))
                {
                    var vagasConcurso = new List<VagaConcurso>();

                    string orgao = concurso.Posicao(0);
                    string edital = concurso.Posicao(1);
                    string codigo = concurso.Posicao(2);
                    string[] vagas = concurso.Posicao(1, '[').Remover("]").ToUpper().Split(',').ToArray();
                    string idConcurso = orgao + "/" + codigo;

                    foreach (var vaga in vagas)
                    {
                        vagasConcurso.Add(new VagaConcurso()
                        {
                            IdConcurso = idConcurso,
                            Vaga = vaga.Trim()
                        });
                    }

                    result.Add(new Concursos()
                    {
                        IdConcurso = idConcurso,
                        Orgao = orgao,
                        Edital = edital,
                        Codigo = codigo,
                        VagaConcurso = vagasConcurso
                    });
                }
            }

            return result;
        }
    }
}