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
        String a = null;
        String b = null;
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
        } else if (a.equals(b)) {
            return "Informe um e somente um dos campo ao lado!";
        }
        return null;
    }

    public String buscaConcursos(String cpf) throws ClassNotFoundException, SQLException {
        List<Concurso> listaConcurso = new ArrayList<>();
        List<String> vagas = new ArrayList<>();
        String a = "<br>";
        String[] profissoes;
        listaConcurso = canconDao.selectCon();
        profissoes = buscarPorCPF(cpf);

        for (Concurso edital : listaConcurso) {
            vagas = edital.getLista_vagas();
            for (String profissao : profissoes) {

                if (vagas.contains(profissao)) {
                    a = a + "Órgão: " + edital.getOrgao() + " | Código: " + edital.getCodigo() + " | Edital: " + edital.getEdital() + "<br><br>";
                    break;
                }
            }
        }

        return a;
    }

    public String buscaCandidatos(String cpf) throws ClassNotFoundException, SQLException {
        List<Candidato> listaCandidato = new ArrayList<>();
        List<String> profissoes = new ArrayList<>();
        String a = "<br>";
        String[] vagas;
        listaCandidato = canconDao.selectCan();
        vagas = buscarPorCOD(cpf);

        for (Candidato candidato : listaCandidato) {
            profissoes = candidato.getLista_profissoes();
            for (String vaga : vagas) {
                if (profissoes.contains(vaga)) {
                    a = a + "Nome: " + candidato.getNome() + " | Data de Nascimento: " + candidato.getDataNascimento() + " | CPF: " + candidato.getCpf() + "<br><br>";
                    break;
                }
            }
        }

        return a;
    }
}
