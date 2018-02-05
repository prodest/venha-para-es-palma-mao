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
package CONTROLE;

import CONTROLE.UTILS.Data;
import ENTIDADES.Candidato;
import ENTIDADES.ConcursoPublico;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mgarcia
 */
/*
* Esta classe Le os dados das linhas dos arquivos txt fornecidos e devolve a
* entdade correspondente instanciada e pronta para ser manipulada.
 */
public class LeitorDeDados {

    public static Candidato LeCandidatos(String linha) {
        Candidato candidato = new Candidato();

        StringBuilder nome = new StringBuilder(100);
        StringBuilder data = new StringBuilder(10);
        StringBuilder cpf = new StringBuilder(14);
        StringBuilder prof = new StringBuilder(45);

        ArrayList<String> lista = new ArrayList();

        int t = linha.length();
        /*
        *  os booleans vão definir qual dado está sendo processado
        *  naquela posição da linha que está sendo lida,
        *  ao terminar de processar o dado, o boolean é setado em
        *  false, e o boolean do próximo dado a ser lido sobe pra true.
        *  o boolean de nome começa em true pois é o primeiro dado na linha
         */
        boolean isnome = true;
        boolean isdata = false;
        boolean iscpf = false;
        boolean isprof = false;
        /*
        *  este contador representa o tamanho de uma string de data (10)
        *  sua finalidade é fazer com que que o loop saiba quando ele chegou no 
        *  começo da data e quando ele saiu dela.
         */
        int contagemdata = 10;
        //idem para cpf
        int contagemcpf = 14;

        for (int i = 0; i < t; i++) {
            char c = linha.charAt(i);
            //primeira fase. capturar o nome
            if (isnome) {
                if (Character.isLetter(c) || c == ' ') {
                    nome.append(c);
                }
            }
            //este ponto define o fim do nome e o começo da data
            if (Character.isDigit(c) && contagemdata == 10) {
                isdata = true;
                isnome = false;
                nome.deleteCharAt(nome.length() - 1); //remove o espaço residual do fim

            }
            //segunda fase. capturar a data
            //1 - dia
            if (isdata) {
                if (contagemdata > -1 && isdata) {
                    if (contagemdata != 0) {
                        data.append(linha.charAt(i));
                    }
                    contagemdata--; //sinaliza a posição do cursor na string de data
                    if (contagemdata == -1) {
                        isdata = false; //fim da informação de data na linha
                        iscpf = true; //começará a informação do cpf em frente
                    }
                }
            }
            if (iscpf) {
                if (c == ' ') { //ignora o espaço vazio no caminho
                    continue;
                } else {
                    cpf.append(linha.charAt(i));
                    contagemcpf--;
                    if (contagemcpf == 0) {
                        iscpf = false;
                    }
                }
            }

            //chegou nos cochetes, entao é prof.
            if (linha.charAt(i) == '[') {
                isprof = true;
            }
            /*
            * Neste ponto faço algumas verificações lógicas um pouco mais complexas.
            * se o cursor estiver sob algum dos cochetes, nada será feito e o laço
            * deverá liberar a execução para próxima iteração.
            * se o cursor estiver sob uma virgula, significa que o loop terminou
            * de ler uma profissão, e portanto, deve armazena-la na lista de 
            * profissões do candidato.
            * se o cursor estiver sobre um espaço vazio q está imediatamente
            * a frente de uma virgula, significa que este é um espaço que
            * não deve ser computado (existem espaços que devem ser computados,
            * mas se for um depois da virgula, não é um dos casos).
             */

            if (isprof) {
                if (linha.charAt(i) != '[' && !(linha.charAt(i) == ' '
                        && linha.charAt(i - 1) == ',')) {
                    if (linha.charAt(i) == ',' || linha.charAt(i) == ']') {
                        candidato.addProfissao(prof.toString());
                        prof.delete(0, prof.length()); // limpa a string
                    } else {
                        prof.append(linha.charAt(i));

                    }

                } else {
                    continue;
                }
            }

        }

        candidato.setNome(nome.toString());
        candidato.setCPF(cpf.toString());
        try {
            candidato.setDataNasc(Data.getDataFromStringDMY(data.toString()));
        } catch (ParseException ex) {
            Logger.getLogger(LeitorDeDados.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro ao converter a data. verifique o formato" + ex);
        }

        return candidato;
    }

    //este método terá menos comentários pois segue a mesma idéia do primeiro.
    public static ConcursoPublico LeConcursos(String linha) {
        ConcursoPublico concurso = new ConcursoPublico();
        StringBuilder orgao = new StringBuilder(25);
        StringBuilder editalnum = new StringBuilder(3);
        StringBuilder editalano = new StringBuilder(4);
        StringBuilder codconcurso = new StringBuilder(11);
        StringBuilder profissoes = new StringBuilder(45);

        int t = linha.length();
        boolean isorgao = true;
        boolean iseditalnum = false;
        boolean iseditalano = false;
        boolean iscodconcurso = false;
        boolean isprof = false;

        for (int i = 0; i < t; i++) {

            if (isorgao) {
                if (linha.charAt(i) != ' ') {
                    orgao.append(linha.charAt(i));
                } else {
                    isorgao = false;
                    iseditalnum = true;
                }
            }

            if (iseditalnum) {
                if (linha.charAt(i) == ' ') {
                    continue;
                } else if (linha.charAt(i) == '/') {
                    iseditalnum = false;
                    iseditalano = true;
                } else {
                    editalnum.append(linha.charAt(i));
                }
            }

            if (iseditalano) {
                if (linha.charAt(i) == '/') {
                    continue;
                } else if (linha.charAt(i) == ' ') {
                    iseditalano = false;
                    iscodconcurso = true;
                } else {
                    editalano.append(linha.charAt(i));
                }
            }

            if (iscodconcurso) {
                if (linha.charAt(i) == ' ' && linha.charAt(i + 1) == '[') {
                    iscodconcurso = false;
                    isprof = true;
                } else if (linha.charAt(i) == ' ') {
                    continue;
                } else {
                    codconcurso.append(linha.charAt(i));
                }
            }

            if (isprof) {
                if (linha.charAt(i) == ' ' && linha.charAt(i + 1) == '[' || linha.charAt(i) == '[') {
                    continue;
                } else if (linha.charAt(i) == ',') {
                    concurso.addProfissao(profissoes.toString());
                    profissoes.delete(0, profissoes.length());//limpa pilha
                } else if (linha.charAt(i) == ' ' && linha.charAt(i - 1) == ',') {
                    continue;
                } else if (linha.charAt(i) == ']') {
                    concurso.addProfissao(profissoes.toString());
                    profissoes.delete(0, profissoes.length());//limpa pilha
                } else {
                    profissoes.append(linha.charAt(i));
                }

            }

        }
        concurso.setOrgao(orgao.toString());
        concurso.setEditalNum(Integer.parseInt(editalnum.toString()));
        concurso.setEditalAno(Integer.parseInt(editalano.toString()));
        concurso.setCodConcurso(codconcurso.toString());
        return concurso;
    }

}
