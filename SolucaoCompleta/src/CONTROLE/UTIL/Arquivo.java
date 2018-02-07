/*
 * Copyright (C) 2018 mgarcia.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package CONTROLE.UTIL;

/**
 *
 * @author mgarcia
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Arquivo {

    /*
    *   Classe que fornece utilidades para manipulação de arquivos de texto puro
    *   Autor: Mateus Garcia
    *   github.com/NaturesProphet
     */
    //lê a primeira linha de um arquivo informado pelo parâmetro
    public static String getFirstLine(String path) throws IOException {
        if (AreYouHere(path)) {
            BufferedReader buffRead = new BufferedReader(new FileReader(path));
            String linha = buffRead.readLine();
            buffRead.close();
            return linha;
        } else {
            throw new FileNotFoundException("O programa buscou por um arquivo "
                    + "que não foi encontrado no local especificado");
        }
    }

    //escreve o conteúdo da String no arquivo, informados via parâmetro
    public static void setFileContentAsSingleLine(String path, String content)
            throws IOException {
        if (AreYouHere(path)) {
            BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path));
            buffWrite.append(content + "\n");
            buffWrite.close();
        } else {
            throw new FileNotFoundException("O programa buscou por um arquivo "
                    + "que não foi encontrado no local especificado");
        }
    }

    //este método lê as linhas de um arquivo e retorna uma lista de Strings
    //recebe o caminho para o arquivo como parâmetro
    public static ArrayList<String> getAllLines(String patch)
            throws FileNotFoundException, IOException {
        if (AreYouHere(patch)) {
            ArrayList<String> dados = new ArrayList();
            BufferedReader br = new BufferedReader(new FileReader(patch));
            String linha;
            Scanner scan = new Scanner(br);
            while (scan.hasNextLine()) {
                linha = scan.nextLine();
                dados.add(linha);
            }
            br.close();
            scan.close();
            return dados;
        } else {
            throw new FileNotFoundException("O programa buscou por um arquivo "
                    + "que não foi encontrado no local especificado");
        }
    }

    //retorna uma linha no arquivo que comece com a String informada.
    //a string retornada começa depois do final + 1 espaço do codigo
    public static String getLineByCode(String path, String codigo)
            throws FileNotFoundException, IOException, Exception {
        if (AreYouHere(path)) {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String linha; //linha atual de cada iteração
            int contador = 0; //total de ocorrências do codigo
            String saida = ""; //string que será retornada
            Scanner scan = new Scanner(br);

            while (scan.hasNextLine()) {
                linha = scan.nextLine();
                if (linha.indexOf(codigo) != -1) {
                    if (contador > 0) { // se houver mais de uma ocorrência
                        br.close();
                        throw new Exception("Util.Arquivo: O Programa tentou "
                                + "buscar no arquivo por uma referência que se "
                                + "repete mais de uma vez e por isso ele não sabe "
                                + "qual delas usar. Contate o Desenvolvedor"
                                + "Codigo redundante: "+codigo);
                    } else {
                        saida = linha;
                        contador++;
                    }
                }
            }
            br.close();
            scan.close();
            if (contador == 0) {
                return null;
            } else {
                saida = saida.substring(codigo.length() + 1, saida.length());
                //corta a substring desde o final do codigo mais 1 ate o final.
                // o +1 serve para pular o espaço vazio após o codigo
                return saida;
            }
        } else {
            throw new FileNotFoundException("O programa buscou por um arquivo "
                    + "que não foi encontrado no local especificado");
        }
    }

    // configura o texto de uma linha que comece com o codigo informado
    // oposto do método acima ^
    public static void setLineByCode(String path, String Code, String insert) throws IOException {
        if (AreYouHere(path)) {

            ArrayList<String> AllLines = getAllLines(path);
            boolean IsRedundant = false;

            for (int i = 0; i < AllLines.size(); i++) {
                if (IsAComent(AllLines.get(i), '#')) {
                    continue;
                } else {
                    if (Code.equals(getFirstStringFromLine(AllLines.get(i)))) {
                        if (!IsRedundant) {
                            AllLines.set(i, Code + " " + insert);
                            IsRedundant = true;
                        } else {
                            throw new IOException("O programa tentou alterar"
                                    + " uma linha do arquivo " + path
                                    + " mas o codigo " + Code + " foi encontrado"
                                    + "mais de uma vez, causando um conflito"
                                    + "Por segurança, nenhuma "
                                    + "alteração foi feita.");
                        }
                    }
                }
            }
            OverWrite(path, AllLines);
        } else {
            throw new FileNotFoundException("O programa buscou por um arquivo "
                    + "que não foi encontrado no local especificado");
        }
    }

    //verifica se o arquivo especificado existe
    public static boolean AreYouHere(String patch) {
        File file = new File(patch);
        return file.exists();
    }

    //verifica o primeiro caractere encontrado na linha
    //util para mecanismos de comentario em arquivo, para que o sistema
    //ignore as linhas que começam com um determinado caractere, 
    //como o '#', no caso do linux
    public static boolean IsAComent(String linha, char coment) {
        for (int i = 0; i < linha.length(); i++) {
            if (linha.charAt(i) != ' ') {
                return linha.charAt(i) == coment;
            }
        }
        return false;
    }

    //adiciona uma linha especificada no final do arquivo especificado
    public static void AddThisLineAtEOF(String path, String texto) throws IOException {
        if (AreYouHere(path)) {
            FileWriter fw = new FileWriter(path, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(texto);
            bw.newLine();
            bw.close();
        } else {
            throw new FileNotFoundException("O programa buscou por um arquivo "
                    + "que não foi encontrado no local especificado");
        }

    }

    //adiciona uma linha no começo do arquivo
    public static void AddThisLineAtBOF(String path, String texto) throws IOException {
        if (AreYouHere(path)) {
            ArrayList<String> conteudoanterior = getAllLines(path);
            setFileContentAsSingleLine(path, texto);
            for (int i = 0; i < conteudoanterior.size(); i++) {
                AddThisLineAtEOF(path, conteudoanterior.get(i));
            }

        } else {
            throw new FileNotFoundException("O programa buscou por um arquivo "
                    + "que não foi encontrado no local especificado");
        }
    }

    //este método retorna a primeira string sem espaços encontrada numa linha
    public static String getFirstStringFromLine(String linha) {
        StringBuilder sb = new StringBuilder(10);
        if (!linha.isEmpty()) {
            boolean PrimeiroEspaco = true;
            for (int i = 0; i < linha.length(); i++) {
                if (linha.charAt(i) == ' ' && PrimeiroEspaco) {
                    continue;
                } else {
                    PrimeiroEspaco = false;
                }
                if (!PrimeiroEspaco) {
                    if (linha.charAt(i) != ' ') {
                        sb.append(linha.charAt(i));
                    } else {
                        return sb.toString();
                    }
                }
            }
        }
        return sb.toString(); //se a linha for vazia retorna nada
    }

    //metodo para sobreescrever um arquivo com a lista de strings informada
    public static void OverWrite(String path, ArrayList<String> Content) throws IOException {
        if (AreYouHere(path)) {
            setFileContentAsSingleLine(path, Content.get(0));
            for (int i = 1; i < Content.size(); i++) {
                AddThisLineAtEOF(path, Content.get(i));
            }
        } else {
            throw new FileNotFoundException("O programa buscou por um arquivo "
                    + "que não foi encontrado no local especificado");
        }
    }
}
