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

import CONTROLE.DAO.ConcursoPublicoDAO;
import CONTROLE.LeitorDeArquivo;
import CONTROLE.LeitorDeDados;
import ENTIDADES.ConcursoPublico;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mgarcia
 */
public class Executa_Concursos {

    public static void main(String[] args) {
        ArrayList<String> lista; // lista de linhas lidas do arquivo
        System.out.println("Aguarde, lendo o arquivo...");

        try {
            lista = LeitorDeArquivo.LeArquivoCandidatos("/home/mgarcia/"
                    + "GitProjects/venha-para-es-palma-mao/concursos.txt");
            ConcursoPublicoDAO dao = new ConcursoPublicoDAO();
            System.out.println("Iniciando as inserções no banco de dados...");
            try {
                //entra num loop e vai salvando os Concursos da lista 1 por 1
                for (int i = 0; i < lista.size(); i++) {
                    dao.salvar(LeitorDeDados.LeConcursos(lista.get(i)));
                }
                System.out.println("Inserções Concluídas com Êxito!");
            } catch (SQLException ex) {
                Logger.getLogger(Executa_Candidatos.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Erro ao tentar salvar Concursos\n" + ex);
            }

        } catch (IOException ex) {
            Logger.getLogger(Executa_Concursos.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro ao ler o arquivo\n" + ex);
        }

    }

}
