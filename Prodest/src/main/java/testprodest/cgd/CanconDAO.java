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
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import testprodest.cdp.Cancon;
import testprodest.db.SQLite;

/**
 *
 * @author ISM
 * @param <GenericType>
 */
public class CanconDAO<GenericType> implements GenericDAO<GenericType> {

    private final String INSERT = "INSERT INTO candidato_concurso (edital_concurso,cpf_candidato,codigo) VALUES(?,?,?);";
    private final String SELECT = "SELECT * FROM candidato_concurso;";
    private final String SELECT_0 = "SELECT cpf_candidato FROM candidato_concurso WHERE cpf_candidato = ?;";
    private final String SELECT_1 = "SELECT DISTINCT nome, dataNascimento, cpf FROM candidato A INNER JOIN candidato_concurso B ON B.codigo = ? ;";

    private final String SELECT_2 = "SELECT * FROM candidato_concurso A INNER JOIN concurso B ON A.cpf_candidato = ? AND"
            + " B.edital = A.edital_concurso ;";
    private final String SELECT_3 = "SELECT codigo FROM concurso WHERE codigo = ?;";

    public boolean verificaCPF(String cpf) throws SQLException {

        try (Connection connection = SQLite.getConnectionSQLite("prodestbd.db")) {
            try (PreparedStatement statement = connection.prepareStatement(SELECT_0)) {
                statement.setString(1, cpf);
                statement.execute();

                ResultSet result = statement.executeQuery();
                while (result.next()) {
                    if (!result.getString("cpf_candidato").equals("")) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean verificaCOD(String codigo) throws SQLException {

        try (Connection connection = SQLite.getConnectionSQLite("prodestbd.db")) {
            try (PreparedStatement statement = connection.prepareStatement(SELECT_3)) {
                statement.setString(1, codigo);
                statement.execute();

                ResultSet result = statement.executeQuery();
                while (result.next()) {
                    if (!result.getString("codigo").equals("")) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public String getCandidato(String codigo) {
        String resposta = "";
        try (Connection connection = SQLite.getConnectionSQLite("prodestbd.db")) {
            try (PreparedStatement statement = connection.prepareStatement(SELECT_1)) {
                statement.setString(1, codigo);
                statement.execute();

                ResultSet result = statement.executeQuery();
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                while (result.next()) {
                    resposta += "<br> Nome: " + result.getString("nome") + " | "
                            + "Data de Nascimento: " + formato.format(result.getDate("dataNascimento")) + ""
                            + " | CPF: " + result.getString("cpf");
                }
            } catch (SQLException ex) {
                Logger.getLogger(CanconDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CanconDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resposta;
    }

    public String getConcurso(String cpf) {
        String resposta = "";
        try (Connection connection = SQLite.getConnectionSQLite("prodestbd.db")) {
            try (PreparedStatement statement = connection.prepareStatement(SELECT_2)) {
                statement.setString(1, cpf);
                statement.execute();

                ResultSet result = statement.executeQuery();

                while (result.next()) {
                    resposta += "<br> Órgao: " + result.getString("orgao") + ""
                            + " | Edital: " + result.getString("edital") + ""
                            + " | Código do Concurso: " + result.getString("codigo");
                }
            } catch (SQLException ex) {
                Logger.getLogger(CanconDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CanconDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resposta;
    }

    @Override
    public void insert(GenericType obj) {
        try (Connection connection = SQLite.getConnectionSQLite("prodestbd.db")) {
            try (PreparedStatement statement = connection.prepareStatement(INSERT)) {

                String edital_concurso = ((Cancon) obj).getEdital_concurso();
                String cpf_candidato = ((Cancon) obj).getCpf_candidato();
                String codigo = ((Cancon) obj).getCodigo();

                statement.setString(1, edital_concurso);
                statement.setString(2, cpf_candidato);
                statement.setString(3, codigo);

                statement.execute();

            }
        } catch (SQLException ex) {
            Logger.getLogger(CanconDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int getNextId() {
        int res = -0;
        String ORDER = "ORDER BY id_concurso ASC;";
        try (Connection connection = SQLite.getConnectionSQLite("prodestbd.db")) {
            try (PreparedStatement statement = connection.prepareStatement(SELECT + ORDER,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
                statement.execute();
                ResultSet result = statement.executeQuery();
                if (result.last()) {
                    res = result.getInt("id_concurso");
                    return res + 1;
                }
            } catch (SQLException ex) {
                Logger.getLogger(CandidatoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CandidatoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
}
