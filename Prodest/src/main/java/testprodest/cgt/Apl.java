/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testprodest.cgt;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import testprodest.cdp.Candidato;
import testprodest.cdp.Concurso;
import testprodest.cgd.CanconDAO;

/**
 *
 * @author Helen
 */
public class Apl {

    CanconDAO canconDao = new CanconDAO();
    List<Concurso> listaConcurso = new ArrayList<>();
    List<String> listVagas = new ArrayList<>();
    List<Candidato> listaCandidato = new ArrayList<>();
    List<String> listProfissoes = new ArrayList<>();

    public Apl() {
        //Construtor
    }

    public String[] buscarPorCOD(String codigo) throws ClassNotFoundException, SQLException {
        return canconDao.getConcurso(codigo);
    }

    public String[] buscarPorCPF(String cpf) throws SQLException, ClassNotFoundException {
        return canconDao.getCandidato(cpf);
    }

    public boolean existeCPF(String cpf) throws SQLException, ClassNotFoundException {
        return canconDao.verificaCPF(cpf);
    }

    public boolean existeCOD(String cod) throws SQLException, ClassNotFoundException {
        return canconDao.verificaCOD(cod);
    }

    public String resultado(String codigo, String cpf) throws SQLException, ClassNotFoundException {
        String a = "true";
       
        if ((!cpf.equals("")) && codigo.equals("") && (existeCPF(cpf))) {
            a = buscaConcursos(cpf);
            
            if (a.isEmpty()) {
                return "Não há concursos para esse candidato!";
            } else if (!a.isEmpty()) {
                return "Concursos que se encaixam no perfil do candidato: <br>" + a;
            }
        } else if ((cpf.equals("")) && (!codigo.equals("")) && (existeCOD(codigo))) {
            a = buscaCandidatos(codigo);
           
            if (a.isEmpty()) {
                return "Não há candidatos para esse concurso!";
            } else if (!a.isEmpty()) {
                return "Candidatos que se encaixam no perfil do concurso: <br>" + a;
            }
        } else if (a.equals(true)) {
            return "Informe um e somente um dos campo ao lado!";
        }
        return null;
    }

    public String buscaConcursos(String cpf) throws ClassNotFoundException, SQLException {

        String[] profissoes;
        listaConcurso = canconDao.selectCon();
        profissoes = buscarPorCPF(cpf);
        StringBuilder builder = new StringBuilder();

        for (Concurso edital : listaConcurso) {
            listVagas = edital.getListaVagas();
            for (String profissao : profissoes) {

                if (listVagas.contains(profissao)) {
                    builder.append("Órgão: ").append(edital.getOrgao()).append(" | Código: ").append(edital.getCodigo()).append(" | Edital: ").append(edital.getEdital()).append("<br><br>");
                    break;
                }
            }
        }

        return builder.toString();
    }

    public String buscaCandidatos(String cpf) throws ClassNotFoundException, SQLException {

        String a = "<br>";
        String[] vagas;
        listaCandidato = canconDao.selectCan();
        StringBuilder builder = new StringBuilder();
        vagas = buscarPorCOD(cpf);

        for (Candidato candidato : listaCandidato) {
            listProfissoes = candidato.getListaProfissoes();
            for (String vaga : vagas) {
                if (listProfissoes.contains(vaga)) {
                   builder.append("Nome: ").append(candidato.getNome()).append(" | Data de Nascimento: ").append(candidato.getDataNascimento()).append(" | CPF: ").append(candidato.getCpf()).append("<br><br>");
                    break;
                }
            }
        }

        return a;
    }
}
