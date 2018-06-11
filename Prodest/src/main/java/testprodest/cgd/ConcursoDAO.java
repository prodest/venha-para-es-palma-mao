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
import java.util.logging.Level;
import java.util.logging.Logger;
import testprodest.cdp.Concurso;
import testprodest.db.SQLite;

/**
 *
 * @author ISM
 * @param <GenericType>
 */
public class ConcursoDAO<GenericType> implements GenericDAO<GenericType>  {
    
    private final String INSERT = "INSERT INTO concurso (id_concurso,orgao,edital,codigo,vagas) VALUES(?,?,?,?,?);";
    private final String SELECT = "SELECT * FROM concurso ";
    List<String> concursos = new ArrayList<>();

    /* @Override
    public List<GenericType> getAll() {
    try (Connection connection = Conector.getConnection()) {
    try (PreparedStatement statement = connection.prepareStatement(SELECT)) {
    statement.execute();
    ResultSet result = statement.executeQuery();
    
    Pessoa pessoa;
    while (result.next()) {
    pessoa = new Pessoa();
    pessoa.setNome(result.getString(this.NOME));
    pessoa.setEmail(result.getString(this.EMAIL));
    pessoa.setCpf(Long.parseLong((String) result.getString(this.CPF)));
    pessoa.setSenha(result.getString(this.SENHA));
    pessoa.setId(result.getInt(this.ID_CLIENTE));
    pessoas.add(pessoa);
    }
    } catch (SQLException ex) {
    Logger.getLogger(PessoaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    } catch (SQLException | ClassNotFoundException ex) {
    Logger.getLogger(PessoaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
    }
    return (List<GenericType>) pessoas;
    }*/
    
    @Override
    public void insert(GenericType obj) {
        try (Connection connection = SQLite.getConnectionSQLite("prodestbd.db")) {
            try (PreparedStatement statement = connection.prepareStatement(INSERT)) {
                int id = ((Concurso)obj).getId();
                String orgao = ((Concurso) obj).getOrgao();
                String edital = ((Concurso) obj).getEdital();
                String codigo = ((Concurso) obj).getCodigo();
                concursos.addAll(((Concurso) obj).getLista_vagas());
                
                statement.setInt(1, id);
                statement.setString(2, orgao);
                statement.setString(3, edital);
                statement.setString(4, codigo);
                statement.setString(5, concursos.toString());

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
