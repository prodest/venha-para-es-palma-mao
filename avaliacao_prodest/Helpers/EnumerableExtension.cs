using System.Collections.Generic;
using System.Linq;

namespace avaliacao_prodest.Helpers
{
    /// <summary>
    /// Classe extensão para IEnumerable.
    /// </summary>
    public static class EnumerableExtension 
    {
        /// <summary>
        /// Verifica se a lista está vazia.
        /// </summary>
        /// <param name="list">Recebe o IEnumerable a ser consultado.</param>
        /// <returns>Retorna um booleano que indica se a lista está vazia ou não.</returns>
        public static bool Vazia<T>(this IEnumerable<T> list)
        {
            return list == null || list.Count() == 0;
        }
    }
}