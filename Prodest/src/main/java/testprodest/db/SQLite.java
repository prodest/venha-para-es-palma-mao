/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testprodest.db;

import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import testprodest.cdp.Cancon;
import testprodest.cdp.Candidato;
import testprodest.cdp.Concurso;
import testprodest.cgd.CanconDAO;
import testprodest.cgd.CandidatoDAO;
import testprodest.cgd.ConcursoDAO;
import testprodest.cgt.Apl;

public class SQLite {

    public static Connection getConnectionSQLite(String arquivo) throws SQLException {

        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection("jdbc:sqlite:" + arquivo);

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        //  this.stm = (Statement) this.conn.createStatement();
    }

    public void initDB() throws SQLException, ParseException, IOException {
        Connection con = SQLite.getConnectionSQLite("prodestbd.db");

        try {
            // Remove e cria a tabela a cada execução. Mero exemplo
            PreparedStatement statement = con.prepareStatement("DROP TABLE IF EXISTS candidato");
            statement.executeUpdate();
            closeConnection(con);

            Connection cone = SQLite.getConnectionSQLite("prodestbd.db");
            statement = cone.prepareStatement("CREATE TABLE candidato"
                    + " (id_candidato int PRIMARY KEY NOT NULL,"
                    + "nome varchar(70),"
                    + "dataNascimento Date,"
                    + "cpf varchar,"
                    + "profissoes varchar(150));");
            statement.executeUpdate();
            closeConnection(cone);

            Connection conexao = SQLite.getConnectionSQLite("prodestbd.db");
            statement = conexao.prepareStatement("DROP TABLE IF EXISTS concurso");
            statement.executeUpdate();
            closeConnection(conexao);

            Connection conex = SQLite.getConnectionSQLite("prodestbd.db");
            statement = conex.prepareStatement("CREATE TABLE concurso"
                    + " (id_concurso int PRIMARY KEY NOT NULL,"
                    + "orgao varchar,"
                    + "edital varchar,"
                    + "codigo varchar,"
                    + "vagas varchar(150));");
            statement.executeUpdate();
            closeConnection(conex);

            Connection conecta = SQLite.getConnectionSQLite("prodestbd.db");
            statement = conecta.prepareStatement("DROP TABLE IF EXISTS candidato_concurso");
            statement.executeUpdate();
            closeConnection(conecta);

            Connection co = SQLite.getConnectionSQLite("prodestbd.db");
            statement = co.prepareStatement("CREATE TABLE candidato_concurso"
                    + " (edital_concurso varchar,"
                    + " cpf_candidato varchar,"
                    + " codigo carchar);");
            statement.executeUpdate();
            closeConnection(co);

            carregaBanco();

        } catch (SQLException e) {
            System.out.println("DEU M: " + e.toString());
        }

    }

    public static void carregaBanco() throws ParseException, SQLException, IOException {
        
        System.out.println("FOI!");
        CandidatoDAO candidatoDao = new CandidatoDAO();
        ConcursoDAO concursoDao = new ConcursoDAO();
        CanconDAO canconDao = new CanconDAO();
        List<Concurso> lista_concurso = new ArrayList<>();
        List<Candidato> lista_candidato = new ArrayList<>();
        List<String> profissoes = new ArrayList<>();

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date data = formato.parse("19/05/1976");
        java.util.Date data1 = formato.parse("14/08/1970");
        java.util.Date data2 = formato.parse("11/02/1957");
        java.util.Date data3 = formato.parse("20/12/1979");
        java.util.Date data4 = formato.parse("21/03/1955");
        java.util.Date data5 = formato.parse("03/09/1979");
        java.util.Date data6 = formato.parse("23/09/1970");
        java.util.Date data7 = formato.parse("24/05/1969");
        java.util.Date data8 = formato.parse("11/01/1977");
        java.util.Date data9 = formato.parse("06/04/1981");

        Candidato candidato1 = new Candidato(0, "Lindsey Craft", data, "182.845.084-34", "carpinteiro");
        Candidato candidato2 = new Candidato(1, "Jackie Dawson", data1, "311.667.973-47", "marceneiro,assistente administrativo");
        Candidato candidato3 = new Candidato(2, "Cory Mendoza", data2, "565.512.353-92", "carpinteiro,marceneiro");
        Candidato candidato4 = new Candidato(3, "Devin Gilmore", data3, "679.734.709-14", "marceneiro, professor de matemática");
        Candidato candidato5 = new Candidato(4, "Miranda Stokes", data4, "551.235.392-12", "carpinteiro, assistente administrativo, professor de matemática");
        Candidato candidato6 = new Candidato(5, "Kay Elliott", data5, "423.976.557-33", "carpinteiro");
        Candidato candidato7 = new Candidato(6, "Alisha Dunn", data6, "116.964.794-53", "professor de matemática");
        Candidato candidato8 = new Candidato(7, "Myron Blanchard", data7, "700.587.955-96", "carpinteiro, professor de matemática");
        Candidato candidato9 = new Candidato(8, "Katelyn Ellis", data8, "395.634.184-22", "carpinteiro");
        Candidato candidato10 = new Candidato(9, "Erin Stafford", data9, "313.500.572-52", "professor de matemática, assistente administrativo");

        lista_candidato.add(candidato1);
        lista_candidato.add(candidato2);
        lista_candidato.add(candidato3);
        lista_candidato.add(candidato4);
        lista_candidato.add(candidato5);
        lista_candidato.add(candidato6);
        lista_candidato.add(candidato7);
        lista_candidato.add(candidato8);
        lista_candidato.add(candidato9);
        lista_candidato.add(candidato10);

        Concurso concurso1 = new Concurso(0, "SEDU", "9/2016", "61828450843", "analista de sistemas,marceneiro");
        Concurso concurso2 = new Concurso(1, "SEJUS", "15/2017", "61828450843", "carpinteiro,professor de matemática,assistente administrativo");
        Concurso concurso3 = new Concurso(2, "SEJUS", "17/2017", "95655123539", "professor de matemática");
        Concurso concurso4 = new Concurso(3, "SETADES", "17/2016", "66797347091", "carpinteiro, assistente administrativo");
        Concurso concurso5 = new Concurso(4, "IASES", "17/2017", "56551235392", "carpinteiro, analista de sistemas");
        Concurso concurso6 = new Concurso(5, "IASES", "14/2016", "74078423976", "professor de matemática");
        Concurso concurso7 = new Concurso(6, "SETADES", "1/2016", "13749470058", "inspetor penitenciário, estagiário");
        Concurso concurso8 = new Concurso(7, "SECULT", "8/2016", "24704233956", "carpinteiro, assistente administrativo");

        lista_concurso.add(concurso1);
        lista_concurso.add(concurso2);
        lista_concurso.add(concurso3);
        lista_concurso.add(concurso4);
        lista_concurso.add(concurso5);
        lista_concurso.add(concurso6);
        lista_concurso.add(concurso7);
        lista_concurso.add(concurso8);

        candidatoDao.insert(candidato1);
        candidatoDao.insert(candidato2);
        candidatoDao.insert(candidato3);
        candidatoDao.insert(candidato4);
        candidatoDao.insert(candidato5);
        candidatoDao.insert(candidato6);
        candidatoDao.insert(candidato7);
        candidatoDao.insert(candidato8);
        candidatoDao.insert(candidato9);
        candidatoDao.insert(candidato10);

        concursoDao.insert(concurso1);
        concursoDao.insert(concurso2);
        concursoDao.insert(concurso3);
        concursoDao.insert(concurso4);
        concursoDao.insert(concurso5);
        concursoDao.insert(concurso6);
        concursoDao.insert(concurso7);
        concursoDao.insert(concurso8);

        for (Candidato pessoa : lista_candidato) {
            profissoes = pessoa.getLista_profissoes();

            for (String profissao : profissoes) {
                //System.out.println(profissao);
                for (Concurso edital : lista_concurso) {
                    //System.out.println(edital.getLista_vagas()+"\n");
                    if (edital.getLista_vagas().contains(profissao)) {
                        canconDao.insert(new Cancon(edital.getEdital(), pessoa.getCpf(), edital.getCodigo()));
                    }
                }
            }
        }

    }

    public static void closeConnection(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao fechar conexão: " + ex);
        }
    }

    public static Connection getConnectionSQLite() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
