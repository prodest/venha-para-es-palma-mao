using System;
using System.ComponentModel;
using System.Linq;
using System.Reflection;

namespace avaliacao_prodest.Helpers
{
    /// <summary>
    /// Classe extensão do Enum
    /// </summary>
    public static class EnumExtension
    {
        /// <summary>
        /// Método que retorna a descrição do Enum.
        /// </summary>
        /// <param name="input">Recebe o Enum a ser consultado.</param>
        /// <returns>Retorna a descrição do Enum.</returns>
        public static string GetDescription(this Enum input)
        {
            return input.GetType().GetMember(input.ToString()).FirstOrDefault()?
                .GetCustomAttribute<DescriptionAttribute>()?.Description;
        }
    }
}