using System.ComponentModel;

namespace avaliacao_prodest.Enums
{
    /// <summary>
    /// Enum para o tipo de busca.
    /// </summary>
    public enum TipoBuscaEnum
    {
        [Description("Candidatos")]
        Candidato = 1,

        [Description("Concursos")]
        Concurso = 2
    }
}