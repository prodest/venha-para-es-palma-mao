using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using System.Text;
using System.Web;

namespace avaliacao_prodest.Helpers
{
    /// <summary>
    /// Classe extensão para String
    /// </summary>
    public static class StringExtension
    {
        /// <summary>
        /// Método para identificar a substring na posição indicada.
        /// </summary>
        /// <param name="input">Recebe a própria string.</param>
        /// <param name="index">Recebe a posição a ser pesquisada.</param>
        /// <param name="separador">Recebe o character separador da string.</param>
        /// <returns>Retorna a substring na posição indicada.</returns>
        public static string Posicao(this string input, int index, char separador = ' ')
        {
            return input.Split(separador)[index];
        }
        
        /// <summary>
        /// Método para remover caracteres da string.
        /// </summary>
        /// <param name="input">Recebe a própria string</param>
        /// <param name="caracteres">Recebe um número ilimitado de caracteres a serem removidos.</param>
        /// <returns></returns>
        public static string Remover(this string input, params string[] caracteres)
        {
            foreach(var caracter in caracteres)
            {
                input = input.Replace(caracter, string.Empty);
            }

            return input;
        }
    }
}