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

import CONTROLE.DAO.CandidatoDAO;
import CONTROLE.DAO.ConcursoPublicoDAO;
import CONTROLE.DAO.VerificaStatusDAO;
import VISAO.AppStart;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author mgarcia
 */

/*
    *  Esta classe le os dados contidos nos arquivos, formatando-os nos seus
    *  respectivos formatos e os salvando no banco de dados respeitando a 
    *  integridade dos dados atraves dos autocommits desabilitados. cada entidade
    *  e suas profissoes só tem seus inserts commitados ao final de cada iteração
    *  finalizada sem erros.
 */
public class RodarRestauracao {

    // o construtor recebe os Paths para os arquivos já tratados
    public RodarRestauracao(String[] arquivos) throws Exception {
        //faz a verificação se o banco existe e se so arquivos ja foram processados antes
        //caso as condições estejam como esperado, executa a restauração
        if (VerificaStatusDAO.BancoExiste()) {
            if (!VerificaStatusDAO.ArquivosProcessados()) {

                String candidatostxt = arquivos[0];
                String concursostxt = arquivos[1];

                // esta é uma lista de todas as linhas que serão lidas do arquivo
                ArrayList<String> candidatos;

                try {
                    JOptionPane.showMessageDialog(null, "Os arquivos serão processados "
                            + "AGORA!\nAguarde, isso pode demorar um ou dois minutos...");
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
                    } catch (Exception ex) {
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
                        JOptionPane.showMessageDialog(null,"Dados recuperados com êxito!\n"
                                + "Agora você já pode trabalhar com as "
                                + "consultas sempre que quiser");
                        
                        
                        /*
                        aqui temos um probleminha que eu resolvi usando um
                        recurso técnico que fiz devido ao uso de Thread 
                        em SeletorDeArquivos na hora de invocar esta classe.
                        se eu não reinvocar o main do AppStart daqui, a primeira
                        inicialização da aplicação não chegará ao ultimo estágio
                         */
                        AppStart.main(null); //deal with it..!
                        
                        
                        
                        
                        
                    } catch (Exception ex) {
                        Logger.getLogger(RodarRestauracao.class.getName()).log(Level.SEVERE, null, ex);
                        System.out.println("Erro ao tentar salvar Concursos\n" + ex);
                    }

                } catch (IOException ex) {
                    Logger.getLogger(RodarRestauracao.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("Erro ao ler o arquivo\n" + ex);
                }

            } else {
                JOptionPane.showMessageDialog(null, "Os arquivos ja foram processados"
                        + "antes e não precisam ser processados novamente");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Banco de dados invalido!");
        }
    }

}
