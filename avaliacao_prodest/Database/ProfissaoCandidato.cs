//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated from a template.
//
//     Manual changes to this file may cause unexpected behavior in your application.
//     Manual changes to this file will be overwritten if the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

namespace avaliacao_prodest.Database
{
    using System;
    using System.Collections.Generic;
    
    public partial class ProfissaoCandidato
    {
        public int Id { get; set; }
        public string CPFCandidato { get; set; }
        public string Profissao { get; set; }
    
        public virtual Candidatos Candidatos { get; set; }
    }
}
