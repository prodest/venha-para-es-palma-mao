/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testprodest.cgd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import testprodest.cdp.*;
import testprodest.db.Conector;

/**
 *
 * @author Helen
 */
public class CanconDAO extends Conector {

    private static final String SELECT_0 = "SELECT cpf FROM candidato WHERE cpf = ?;";
    private static final String SELECT_1 = "SELECT vagas FROM concurso WHERE codigo = ? ;";
    private static final String SELECT_2 = "SELECT profissoes FROM candidato WHERE cpf = ? ;";
    private static final String SELECT_3 = "SELECT codigo FROM concurso WHERE codigo = ?;";
    private static final String SELECT_CAN = "SELECT * FROM candidato";
    private static final String SELECT_CON = "SELECT * FROM concurso";

    public boolean verificaCPF(String cpf) throws SQLException, ClassNotFoundException {

        try (Connection connection = this.openConnection();
                PreparedStatement statement = connection.prepareStatement(SELECT_0)) {

            statement.setString(1, cpf);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                if (!result.getString("cpf").equals("")) {
                    return true;
                }
            }

        } finally {
            this.closeConnection(con);
        }
        return false;
    }

    public boolean verificaCOD(String codigo) throws SQLException, ClassNotFoundException {

        try (Connection connection = this.openConnection();
                PreparedStatement statement = connection.prepareStatement(SELECT_3)) {
            statement.setString(1, codigo);

            ResultSet result = statement.executeQuery();
            while (result.next()) {
                if (!result.getString("codigo").equals("")) {
                    return true;
                }
            }

        } finally {
            this.closeConnection(con);
        }
        return false;
    }

    public String[] getConcurso(String codigo) throws ClassNotFoundException, SQLException {
        String[] resposta;
        try (Connection connection = this.openConnection();
                PreparedStatement statement = connection.prepareStatement(SELECT_1)) {
            statement.setString(1, codigo);

            ResultSet result = statement.executeQuery();

            result.next();
            resposta = result.getString("vagas").split(",");

        } finally {
            this.closeConnection(con);
        }
        return resposta;
    }

    public String[] getCandidato(String cpf) throws ClassNotFoundException, SQLException {
        String[] split;

        try (Connection connection = this.openConnection();
                PreparedStatement statement = connection.prepareStatement(SELECT_2)) {
            statement.setString(1, cpf);

            ResultSet result = statement.executeQuery();

            result.next();
            split = result.getString("profissoes").split(",");

        } finally {
            this.closeConnection(con);
        }
        return split;
    }

    public List<Candidato> selectCan() throws ClassNotFoundException, SQLException {
        List<Candidato> candidatos = new ArrayList<>();

        try (Connection connection = this.openConnection();
                PreparedStatement statement = connection.prepareStatement(SELECT_CAN);
                ResultSet result = statement.executeQuery()) {
            Candidato candidato;
            while (result.next()) {
                candidato = new Candidato();
                candidato.setNome(result.getString("nome"));
                candidato.setCpf(result.getString("cpf"));

                candidato.setDataNascimento(result.getString("dataNascimento"));
                candidato.addProfissao(result.getString("profissoes"));
                candidatos.add(candidato);
            }
        } finally {
            this.closeConnection(con);
        }
        return (List<Candidato>) candidatos;
    }

    public List<Concurso> selectCon() throws ClassNotFoundException, SQLException {
        List<Concurso> concursos = new ArrayList<>();

        try (Connection connection = this.openConnection();
                PreparedStatement statement = connection.prepareStatement(SELECT_CON);
                ResultSet result = statement.executeQuery()) {
            Concurso concurso;
            while (result.next()) {
                concurso = new Concurso();
                concurso.setOrgao(result.getString("orgao"));
                concurso.setEdital(result.getString("edital"));

                concurso.setCodigo(result.getString("codigo"));
                concurso.addVaga(result.getString("vagas"));
                concursos.add(concurso);
            }
        } finally {
            this.closeConnection(con);
        }
        return (List<Concurso>) concursos;
    }

}
