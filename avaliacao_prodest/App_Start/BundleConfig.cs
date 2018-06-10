using System.Web.Optimization;

/// <summary>
/// Namespace principal da solução.
/// </summary>
namespace avaliacao_prodest
{
    /// <summary>
    /// Classe utilizada para agrupar Scripts e arquivos CSS, facilitando importação.
    /// </summary>
    /// <remarks>
    /// Para obter mais informações sobre o agrupamento, visite https://go.microsoft.com/fwlink/?LinkId=301862.
    /// </remarks>
    public class BundleConfig
    {        
        /// <summary>
        /// Registra os agrupamentos.
        /// </summary>
        /// <param name="bundles">Recebe uma BundleCollection.</param>
        public static void RegisterBundles(BundleCollection bundles)
        {
            //Bundle Scripts
            bundles.Add(new ScriptBundle("~/Scripts").Include(
                        "~/Scripts/bootstrap/bootstrap.min.js",
                        "~/Scripts/datatables.min.js",
                        "~/Scripts/jquery/jquery-3.3.1.min.js"));

            //Bundle CSS
            bundles.Add(new StyleBundle("~/Content/css").Include(
                      "~/Content/Bootstrap/bootstrap.min.css",
                      "~/Content/datatables.css",
                      "~/Content/Site.css"));
        }
    }
}
