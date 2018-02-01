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
package VISAO;

import CONTROLE.DAO.CandidatoDAO;
import CONTROLE.DAO.ConcursoPublicoDAO;
import CONTROLE.LeitorDeArquivo;
import CONTROLE.LeitorDeDados;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mgarcia
 */
public class RodarRestauracao {

    /*
    *  Esta classe le os dados contidos nos arquivos, formatando-os nos seus
    *  respectivos formatos e os salvando no banco de dados respeitando a 
    *  integridade dos dados atraves dos autocommits desabilitados. cada entidade
    *  e suas profissoes só tem seus inserts commitados ao final de cada iteração
    *  finalizada sem erros.
     */
    public static void main(String[] args) {
        //aponte o caminho pro arquivo candidatos.txt aqui
        String candidatostxt = "/home/mgarcia/"
                + "GitProjects/venha-para-es-palma-mao/candidatos.txt";

        //aponte o caminho para o arquivo concursos.txt aqui
        String concursostxt = "/home/mgarcia/"
                + "GitProjects/venha-para-es-palma-mao/concursos.txt";

        ArrayList<String> candidatos; // lista de linhas lidas do arquivo

        try {
            System.out.println("Aguarde, lendo o arquivo candidatos.txt...");
            //instanciando uma lista de candidatos lida do arquivo
            candidatos = LeitorDeArquivo.LeArquivoCandidatos(candidatostxt);

            CandidatoDAO candao = new CandidatoDAO();
            System.out.println("Iniciando as inserções no banco de dados...");
            try {
                //entra num loop e vai salvando os candidatos da lista 1 por 1
                for (int i = 0; i < candidatos.size(); i++) {
                    candao.salvar(LeitorDeDados.LeCandidatos(candidatos.get(i)));
                }
                System.out.println("Inserções Concluídas com Êxito!");
            } catch (SQLException ex) {
                Logger.getLogger(RodarRestauracao.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Erro ao tentar salvar Candidatos\n" + ex);
            }

        } catch (IOException ex) {
            Logger.getLogger(RodarRestauracao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro ao ler o arquivo\n" + ex);
        }

        ArrayList<String> concursos; // lista de linhas lidas do arquivo
        System.out.println("Aguarde, lendo o arquivo concursos.txt...");

        try {
            concursos = LeitorDeArquivo.LeArquivoCandidatos(concursostxt);
            ConcursoPublicoDAO codao = new ConcursoPublicoDAO();
            System.out.println("Iniciando as inserções no banco de dados...");
            try {
                //entra num loop e vai salvando os Concursos da lista 1 por 1
                for (int i = 0; i < concursos.size(); i++) {
                    codao.salvar(LeitorDeDados.LeConcursos(concursos.get(i)));
                }
                System.out.println("Inserções Concluídas com Êxito!");
            } catch (SQLException ex) {
                Logger.getLogger(RodarRestauracao.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Erro ao tentar salvar Concursos\n" + ex);
            }

        } catch (IOException ex) {
            Logger.getLogger(RodarRestauracao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro ao ler o arquivo\n" + ex);
        }

    }

}
