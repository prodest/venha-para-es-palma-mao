using avaliacao_prodest.Database;
using System;
using System.Collections.Generic;

namespace avaliacao_prodest.Models
{
    /// <summary>
    /// Classe que contém as informações a serem armazenadas do candidato.
    /// </summary>
    public class CandidatoViewModel
    {
        public string Nome { get; set; }
        public DateTime DataNascimento { get; set; }
        public string CPF { get; set; }
        public List<string> Profissoes { get; set; }
        public List<Concursos> VagasConcurso { get; set; }

        public CandidatoViewModel()
        {
            VagasConcurso = new List<Concursos>();
            Profissoes = new List<string>();
        }        
    }
}