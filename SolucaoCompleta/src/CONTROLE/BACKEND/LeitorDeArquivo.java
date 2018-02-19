/*
 * Copyright (C) 2018 mgarcia
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package CONTROLE.BACKEND;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author mgarcia
 */
/*
*  Esta classe le todas as linhas do arquivo e devolve uma lista de Strings
*  de acordo com o método acionado.
 */
public class LeitorDeArquivo {

    //este método lê as linhas do arquivo candidatos.txt
    //recebe o caminho para o arquivo como parâmetro
    public static ArrayList<String> LeArquivoCandidatos(String patch) throws FileNotFoundException, IOException {
        ArrayList<String> dados = new ArrayList();
        BufferedReader br = new BufferedReader(new FileReader(patch));
        String linha;
        while ((linha = br.readLine()) != null) {
            dados.add(linha);
        }
        br.close();
        return dados;
    }
}
