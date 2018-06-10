using avaliacao_prodest.Database;
using avaliacao_prodest.Models;
using avaliacao_prodest.Helpers;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web.Mvc;
using System.Text;

namespace avaliacao_prodest.Controllers
{
    /// <summary>
    /// Controller do Candidato.
    /// </summary>
    public class CandidatoController : Controller
    {
        /// <summary>
        /// Método principal da controller.
        /// </summary>
        /// <remarks>
        /// Carrega a tabela de candidados na página principal.
        /// </remarks>
        /// <param name="busca">String que traz o CPF buscado pelo usuário.</param>
        /// <returns>Retorna uma PartialView, no caso, a tabela de candidados.</returns>
        /// <seealso cref="RecuperarCandidatos(string)"/>
        public ActionResult Index(string busca)
        {
            return PartialView("~/Views/Home/CandidatosDatagrid.cshtml", RecuperarCandidatos(busca));
        }

        /// <summary>
        /// Método para recuperar as informações dos candidados.
        /// </summary>
        /// <param name="busca">String que traz o CPF buscado pelo usuário.</param>
        /// <returns>Retorna uma lista contendo as informações dos usuários que se encaixam na pesquisa.</returns>
        /// <seealso cref="Index(string)"/>
        public List<CandidatoViewModel> RecuperarCandidatos(string busca)
        {
            using (var db = new AvaliacaoProdestDbEntities())
            {
                //Retorna os dados do candidato cujo CPF contém os números da busca
                if (!string.IsNullOrEmpty(busca))
                {
                    return db.Candidatos
                        .Where(x => x.CPF.StartsWith(busca))
                        .Select(o => new CandidatoViewModel()
                        {
                            Nome = o.Nome,
                            DataNascimento = o.DataNascimento,
                            CPF = o.CPF,
                            Profissoes = db.ProfissaoCandidato
                                .Where(p => p.CPFCandidato == o.CPF)
                                .Select(p => p.Profissao)
                                .ToList(),
                            VagasConcurso = db.VagaConcurso
                            .Where(vc => o.ProfissaoCandidato
                                .Any(pc => pc.Profissao == vc.Vaga)
                            ).Select(x => x.Concursos).ToList()
                        }).ToList();
                }

                return null;
            }
        }

        /// <summary>
        /// Atualiza, no banco de dados, a tabela de Candidatos e profissões do candidato de acordo com as informações do arquivo .txt
        /// </summary>
        /// <param name="model">Recebe o classe que contém os dados da página inicial.</param>
        /// <returns>Retorna uma PartialView contendo uma mensagem de sucesso ou de erro, dependendo do resultado.</returns>
        public ActionResult AtualizarCandidatosDB(HomeViewModel model)
        {
            using (var db = new AvaliacaoProdestDbEntities())
            {
                try
                {
                    var candidatos = MapearCandidatosTxt();
                    
                    foreach (var candidato in candidatos)
                    {
                        var candidatoOriginal = db.Candidatos.SingleOrDefault(c => c.CPF == candidato.CPF);

                        if (candidatoOriginal != null)
                        {
                            candidatoOriginal.DataNascimento = candidato.DataNascimento;
                            candidatoOriginal.Nome = candidato.Nome;

                            //Deleta as profissões já salvas desse candidato e insere as novas
                            db.ProfissaoCandidato.RemoveRange(candidatoOriginal.ProfissaoCandidato);
                            candidatoOriginal.ProfissaoCandidato = candidato.ProfissaoCandidato;
                        }
                        else
                        {
                            db.Candidatos.Add(candidato);
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
        /// Faz a leitura do arquivo 'candidatos.txt'
        /// </summary>
        /// <returns>Retorna uma lista de candidatos com suas respectivas informações</returns>
        public List<Candidatos> MapearCandidatosTxt()
        {
            var result = new List<Candidatos>();
            var path = AppDomain.CurrentDomain.BaseDirectory;

            string[] candidatos = System.IO.File.ReadAllLines(path + @"\candidatos.txt", Encoding.GetEncoding("iso-8859-1"));

            foreach (var candidato in candidatos)
            {
                if (!string.IsNullOrEmpty(candidato))
                {
                    var profissoesCandidato = new List<ProfissaoCandidato>();

                    int index = 0;
                    bool numero = char.IsDigit(candidato.Posicao(index).First()); //Verifica se o primeiro caractere da string é um número.
                    string nome = "";

                    while (!numero)
                    {
                        nome += (candidato.Posicao(index) + " ");
                        index++;
                        numero = char.IsDigit(candidato.Posicao(index).First());
                    }

                    DateTime dataNascimento = DateTime.Parse(candidato.Posicao(index++));
                    string cpf = candidato.Posicao(index++).Remover(".", "-");
                    string[] profissoes = candidato.Posicao(1, '[').Remover("]").ToUpper().Split(',').ToArray();

                    foreach (var profissao in profissoes)
                    {
                        profissoesCandidato.Add(new ProfissaoCandidato()
                        {
                            CPFCandidato = cpf,
                            Profissao = profissao.Trim()
                        });
                    }

                    result.Add(new Candidatos()
                    {
                        Nome = nome,
                        DataNascimento = dataNascimento,
                        CPF = cpf,
                        ProfissaoCandidato = profissoesCandidato
                    });
                }
            }

            return result;
        }
    }
}
