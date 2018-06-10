using System.Web.Mvc;

namespace avaliacao_prodest
{
    /// <summary>
    /// Classe para filtrar requisições
    /// </summary>
    public class FilterConfig
    {
        /// <summary>
        /// Registra os filtros.
        /// </summary>
        /// <param name="filters">Recebe uma coleção GlobalFilterCollection</param>
        public static void RegisterGlobalFilters(GlobalFilterCollection filters)
        {
            filters.Add(new HandleErrorAttribute());
        }
    }
}
