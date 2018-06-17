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
        if ((!cpf.equals("")) && (codigo.equals(""))) {
            if (existeCPF(cpf)) {
                a = buscaConcursos(cpf);

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
                a = buscaCandidatos(codigo);
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

    public String buscaConcursos(String cpf) throws ClassNotFoundException, SQLException {
        List<Concurso> lista_concurso = new ArrayList<>();
        List<String> vagas = new ArrayList<>();
        String a = "<br>";
        String[] profissoes;
        lista_concurso = canconDao.selectCon();
        profissoes = buscarPorCPF(cpf);

        for (Concurso edital : lista_concurso) {
            vagas = edital.getLista_vagas();
            for (String profissao : profissoes) {

                if (vagas.contains(profissao)) {
                    a += "Órgão: " + edital.getOrgao() + " | Código: " + edital.getCodigo() + " | Edital: " + edital.getEdital() + "<br><br>";
                    break;
                }
            }
        }

        return a;
    }

    public String buscaCandidatos(String cpf) throws ClassNotFoundException, SQLException {
        List<Candidato> lista_candidato = new ArrayList<>();
        List<String> profissoes = new ArrayList<>();
        String a = "<br>";
        String[] vagas;
        lista_candidato = canconDao.selectCan();
        vagas = buscarPorCOD(cpf);

        for (Candidato candidato : lista_candidato) {
            profissoes = candidato.getLista_profissoes();
            for (String vaga : vagas) {
                if (profissoes.contains(vaga)) {
                    a += "Nome: " + candidato.getNome() + " | Data de Nascimento: " + candidato.getDataNascimento() + " | CPF: " + candidato.getCpf() + "<br><br>";
                    break;
                }
            }
        }

        return a;
    }
}
