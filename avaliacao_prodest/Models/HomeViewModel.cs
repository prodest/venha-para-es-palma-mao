using avaliacao_prodest.Enums;

/// <summary>
/// Modelos
/// </summary>
namespace avaliacao_prodest.Models
{
    /// <summary>
    /// Classe que contém as informações necessárias para a busca.
    /// </summary>
    public class HomeViewModel
    {
        public string Busca { get; set; }
        public TipoBuscaEnum TipoBusca { get; set; }
        public string Erro { get; set; }
    }
}