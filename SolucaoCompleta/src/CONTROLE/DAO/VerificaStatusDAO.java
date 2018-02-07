/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLE.DAO;

import CONTROLE.UTIL.Arquivo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author mgarcia
 */

/*
*   Esta classe é um DAO que pergunta para o banco se os arquivos 
*   ja foram processados e armazenados no banco, através da consulta
*   ao numero de registros nas tabelas de candidatos e concursos.
 */
public class VerificaStatusDAO {

    //
    public static boolean ArquivosProcessados() throws Exception {
        boolean ForamProcessados = false;

        String sql = "SELECT COUNT(Candidato.IdCandidato), "
                + "(SELECT COUNT(ConcursoPublico.IdCOncursoPublico) "
                + "FROM ConcursoPublico) FROM Candidato;";

        Connection con = new ConnectionFactory().getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int c1, c2;
            c1 = rs.getInt(1);
            c2 = rs.getInt(2);
            if (c1 >= 1000 && c2 >= 1000) {
                ForamProcessados = true;
            }

        }
        rs.close();
        con.close();
        return ForamProcessados;
    }

    //este método verifica se o banco existe atraves da tentativa de abrir
    //a conexão com os parâmetros configurados no mysql.conf, e também
    //fazendo uma contagem simples do numero de tabelas presentes no schema.
    public static boolean BancoExiste() {
        String host;
        int port;
        String user;
        String password;
        String db;
        String url;
        Connection conexao;

        try {
            host = Arquivo.getLineByCode("mysql.conf", "@ip");
            port = Integer.parseInt(Arquivo.getLineByCode("mysql.conf", "@port"));
            user = Arquivo.getLineByCode("mysql.conf", "@user");
            password = Arquivo.getLineByCode("mysql.conf", "@password");
            db = Arquivo.getLineByCode("mysql.conf", "@schema");
            url = "jdbc:mysql://" + host + ":" + port + "/" + db;
        } catch (Exception e) {
            return false;
        }
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conexao = (DriverManager.getConnection(url, user, password));
        } catch (Exception e) {
            return false;
        }
        String sql = "SHOW TABLES";
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            int contador = 0;
            while (rs.next()) {
                contador++;
            }
            if (contador >= 5) {
                conexao.close();
                return true;
            }
            return false;
        } catch (Exception e) {
            try {
                conexao.close();
            } catch (SQLException ex) {
                //faz nada, ja eh falso
            }
            return false;
        }
    }

    //verifica se o arquivo de configuração mysql.conf é válido
    public static boolean TheConfIsValid() {
        boolean ConfigValida = true;
        if (!Arquivo.AreYouHere("mysql.conf")) {
            JOptionPane.showMessageDialog(null, "O Arquivo de configuração "
                    + "mysql.conf não foi encontrado. certifique-se de que "
                    + "ele esteja na pasta raiz da aplicação antes de tentar "
                    + "novamente");
            System.exit(0);
        } else {

            try {
                if (Arquivo.getLineByCode("mysql.conf", "@ip").length() < 7
                        || Arquivo.getLineByCode("mysql.conf", "@ip").length() > 14) {
                    ConfigValida = false;
                    System.out.print("configure @ip. ");
                    System.out.println(Arquivo.getLineByCode("mysql.conf", "@ip").length());
                }
                if ((Arquivo.getLineByCode("mysql.conf", "@user").isEmpty())) {
                    ConfigValida = false;
                    System.out.print("configure @user. ");
                    System.out.println(Arquivo.getLineByCode("mysql.conf", "@user"));
                }
                if ((Arquivo.getLineByCode("mysql.conf", "@password").isEmpty())) {
                    ConfigValida = false;
                    System.out.print("configure @password. ");
                    System.out.println(Arquivo.getLineByCode("mysql.conf", "@password"));
                }
                if ((Arquivo.getLineByCode("mysql.conf", "@schema").isEmpty())) {
                    ConfigValida = false;
                    System.out.print("configure @schema. ");
                    System.out.println(Arquivo.getLineByCode("mysql.conf", "@schema"));
                }
                if (Integer.parseInt(Arquivo.getLineByCode("mysql.conf", "@port")) <= 0
                        || Integer.parseInt(Arquivo.getLineByCode("mysql.conf", "@port")) > 65535) {
                    ConfigValida = false;
                    System.out.print("configure @port. ");
                    System.out.println(Arquivo.getLineByCode("mysql.conf", "@port"));
                }

            } catch (Exception e) {
                ConfigValida = false;
                System.out.println("Erro de verificação em TheConfIsValid()" + e);
            }
        }
        return ConfigValida;
    }

    //este método reseta o arquivo mysql.conf na raiz do sistema
    public static void ResetConfigs() {
        if (!Arquivo.AreYouHere("mysql.conf")) {
            JOptionPane.showMessageDialog(null, "O Arquivo de configuração "
                    + "mysql.conf não foi encontrado. certifique-se de que "
                    + "ele esteja na pasta raiz da aplicação antes de tentar "
                    + "novamente");
            System.exit(0);
        } else {
            try {
                Arquivo.setLineByCode("mysql.conf", "@ip", "");
                Arquivo.setLineByCode("mysql.conf", "@user", "");
                Arquivo.setLineByCode("mysql.conf", "@password", "");
                Arquivo.setLineByCode("mysql.conf", "@port", "");
                Arquivo.setLineByCode("mysql.conf", "@port", "");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro resetando o arquivo "
                        + "mysql.conf\n" + e);
            }
        }
    }
}
