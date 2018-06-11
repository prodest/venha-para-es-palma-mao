/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testprodest.cgd;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import testprodest.cdp.Candidato;
import testprodest.db.SQLite;

/**
 *
 * @author ISM
 */
public class CandidatoDAO<GenericType> implements GenericDAO<GenericType> {

    private final String INSERT = "INSERT INTO candidato (id_candidato,nome,dataNascimento,cpf,profissoes) VALUES(?,?,?,?,?);";
    private final String SELECT = "SELECT * FROM candidato";
    List<Candidato> candidatos = new ArrayList<>();
    List<String> profissoes = new ArrayList<>();

    public List<GenericType> select() {
        try (Connection connection = SQLite.getConnectionSQLite()) {
            try (PreparedStatement statement = connection.prepareStatement(SELECT)) {
                statement.execute();
                ResultSet result = statement.executeQuery();

                Candidato candidato = new Candidato();
                candidato.setNome(result.getString("nome"));
                candidato.setCpf(result.getString("cpf"));

                candidato.setDataNascimento(result.getDate("dataNascimento"));
                candidato.addProfissao(result.getString("profissoes"));
                candidatos.add(candidato);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CandidatoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (List<GenericType>) candidatos;
    }

    @Override
    public void insert(GenericType obj) {
        try (Connection connection = SQLite.getConnectionSQLite("prodestbd.db")) {
            try (PreparedStatement statement = connection.prepareStatement(INSERT)) {
                int id = ((Candidato) obj).getId();
                String nome = ((Candidato) obj).getNome();
                String cpf = ((Candidato) obj).getCpf();
                profissoes.addAll(((Candidato) obj).getLista_profissoes());
                java.util.Date dataNascimento = ((Candidato) obj).getDataNascimento();
                java.sql.Date date = new java.sql.Date(dataNascimento.getTime());

                statement.setInt(1, id);
                statement.setString(2, nome);

                statement.setDate(3, date);
                statement.setString(4, cpf);
                statement.setString(5, profissoes.toString());

                statement.execute();

            } catch (Exception ex) {
                System.out.println("DEU B.O." + ex.toString());
            }
        } catch (SQLException ex) {
            System.out.println(" EX: " + ex.toString());
        }
    }

    @Override
    public int getNextId() {
        int res = -0;
        String ORDER = "ORDER BY id_candidato ASC;";
        try (Connection connection = SQLite.getConnectionSQLite("prodestbd.db")) {
            try (PreparedStatement statement = connection.prepareStatement(SELECT + ORDER,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
                statement.execute();
                ResultSet result = statement.executeQuery();
                if (result.last()) {
                    res = result.getInt("id_candidato");
                    return res + 1;

                }
            } catch (SQLException ex) {
                Logger.getLogger(CandidatoDAO.class
                        .getName()).log(Level.SEVERE, null, ex);

            }

        } catch (SQLException ex) {
            Logger.getLogger(CandidatoDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }

}
