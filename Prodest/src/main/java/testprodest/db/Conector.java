package testprodest.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Helen
 */
public class Conector {

    protected Connection con;

    protected Connection openConnection() throws SQLException, ClassNotFoundException {

        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(
                    "jdbc:postgresql://stampy.db.elephantsql.com:5432/tvmkxjez", "tvmkxjez", "4b2biiih_bkCr5O_lHrUKnc7FXZRsEsc");

            return con;
        } catch (SQLException e) {
            System.out.println("E:" + e.toString());
        }
        return null;
    }

    protected void closeConnection(Connection con) throws SQLException {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao fechar conexão: " + ex);
        }
    }
}
