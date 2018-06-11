/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testprodest.cgt;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import testprodest.cdp.Candidato;
import testprodest.cgd.CanconDAO;
import testprodest.cgd.CandidatoDAO;
import testprodest.cgd.ConcursoDAO;
import testprodest.db.SQLite;

/**
 *
 * @author ISM
 */
public class Apl {

    CandidatoDAO candidatoDao = new CandidatoDAO();
    ConcursoDAO concursoDao = new ConcursoDAO();
    CanconDAO canconDao = new CanconDAO();
    SQLite sql = new SQLite();

    public Apl() {
        try {
            sql.initDB();
        } catch (SQLException ex) {
            Logger.getLogger(Apl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Apl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Apl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String buscarPorCOD(String codigo) {
        return canconDao.getCandidato(codigo);
    }

    public String buscarPorCPF(String cpf) throws SQLException {
        return canconDao.getConcurso(cpf);
    }

    public boolean existeCPF(String cpf) throws SQLException {
        return canconDao.verificaCPF(cpf);
    }

    public boolean existeCOD(String cod) throws SQLException {
        return canconDao.verificaCOD(cod);

    }

    public String resultado(String codigo, String cpf) throws SQLException {
        String a = null;
        if ((!cpf.equals("")) && (codigo.equals(""))) {
            if (existeCPF(cpf)) {
                a = buscarPorCPF(cpf);

                if (a.isEmpty()) {
                    a = "Não há concursos para esse candidato!";
                } else {
                    a = "Concursos que se encaixam no perfil do candidato: <br>" + a;
                }
            } else {
                a = "Candidato não cadastrado em nosso banco!";
            }
        } else if ((cpf.equals("")) && (!codigo.equals(""))) {

            if (existeCOD(codigo)) {
                a = buscarPorCOD(codigo);
                if (a.isEmpty()) {
                    a = "Não há candidatos para esse concurso!";
                } else {
                    a = "Candidatos que se encaixam no perfil do concurso: <br>" + a;
                }
            } else {
                a = "Concurso não encontrado!";
            }
        } else if (a == null) {
            a = "Informe um e somente um dos campo ao lado!";
        }
        return a;
    }

    public List<Candidato> leArquivo() throws FileNotFoundException, IOException, ParseException {
        List<Candidato> lista_candidato = new ArrayList<>();
        FileInputStream stream = new FileInputStream("..\\Prodest\\src\\main\\resources\\candidatos.txt");
        InputStreamReader reader = new InputStreamReader(stream);
        BufferedReader br = new BufferedReader(reader);
        String linha = br.readLine();
        int i = 0;
        while (linha != null) {
            String nome = linha.substring(0, (linha.indexOf("/") - 3));
            String data1 = linha.substring(linha.indexOf("/") - 2, linha.lastIndexOf("/") + 5);
            String cpf = linha.substring(linha.lastIndexOf("/") + 6, linha.indexOf(" ["));
            String profissoes = linha.substring(linha.indexOf("[") + 1, linha.indexOf("]"));

            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date data = formato.parse(data1);

            Candidato candidato = new Candidato(i, nome, data, cpf, profissoes);
            lista_candidato.add(candidato);
            i++;
            linha = br.readLine();
        }
        return lista_candidato;
    }
}
