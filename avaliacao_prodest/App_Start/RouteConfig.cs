using System.Web.Mvc;
using System.Web.Routing;

namespace avaliacao_prodest
{
    /// <summary>
    /// Classe para a configuração das rotas do applicativo.
    /// </summary>
    public class RouteConfig
    {
        /// <summary>
        /// Registra as rotas/caminhos da aplicação.
        /// </summary>
        /// <param name="routes">Recebe uma coleção de rotas.</param>
        public static void RegisterRoutes(RouteCollection routes)
        {
            routes.IgnoreRoute("{resource}.axd/{*pathInfo}");

            routes.MapRoute(
                name: "Default",
                url: "{controller}/{action}/{id}",
                defaults: new { controller = "Home", action = "Index", id = UrlParameter.Optional }
            );
        }
    }
}
