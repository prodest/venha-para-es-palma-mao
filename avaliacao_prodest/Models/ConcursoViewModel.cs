using avaliacao_prodest.Database;
using System.Collections.Generic;

namespace avaliacao_prodest.Models
{
    /// <summary>
    /// Classe que contém as informações a serem armazenadas do concurso.
    /// </summary>
    public class ConcursoViewModel
    {
        public string IdConcurso { get; set; }
        public string Orgao { get; set; }
        public string Edital { get; set; }
        public string Codigo { get; set; }
        public List<string> Vagas { get; set; }
        public List<Candidatos> Candidatos { get; set; }

        public ConcursoViewModel()
        {
            Candidatos = new List<Candidatos>();
            Vagas = new List<string>();
        }
    }
}