using avaliacao_prodest.Enums;
using avaliacao_prodest.Models;
using System.Web.Mvc;

namespace avaliacao_prodest.Controllers
{
    /// <summary>
    /// Controller da página principal.
    /// </summary>
    public class HomeController : Controller
    {
        /// <summary>
        /// Classe que contém os dados usados na página principal.
        /// </summary>
        public HomeViewModel Model { get; set; }

        /// <summary>
        /// Construtor da classe.
        /// </summary>
        /// <remarks>
        /// Inicializando a HomeViewModel.
        /// </remarks>
        public HomeController()
        {
            Model = new HomeViewModel();
        }
        
        /// <summary>
        /// Método principal da controller.
        /// </summary>
        /// <returns>Retorna a página principal.</returns>
        public ActionResult Index()
        {
            return View(Model);
        }

        /// <summary>
        /// Método que redireciona a busca para a controller mais adequada.
        /// </summary>
        /// <param name="model">Classe que contém os dados da página principal.</param>
        /// <returns>Retorna a Action mais adequada.</returns>
        public ActionResult Buscar(HomeViewModel model)
        {            
            if (model.TipoBusca == TipoBuscaEnum.Candidato)
                return RedirectToAction("Index", "Candidato", new { busca = model.Busca });
            else
                return RedirectToAction("Index", "Concurso", new { busca = model.Busca });
        }

        /// <summary>
        /// Método que redireciona a atualização da tabela no banco de dados para a controller mais adequada.
        /// </summary>
        /// <param name="model">Recebe a classe que contém os dados da página principal.</param>
        /// <returns>Retorna a Action mais adequada para atualização do banco de dados.</returns>
        public ActionResult AtualizarDb(HomeViewModel model)
        {
            if (model.TipoBusca == TipoBuscaEnum.Candidato)
                return RedirectToAction("AtualizarCandidatosDB", "Candidato", model);
            else
                return RedirectToAction("AtualizarConcursosDB", "Concurso", model);
        }
    }
}