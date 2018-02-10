/*
 * Copyright (C) 2018 mgarcia
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package CONTROLE.DAO;

import CONTROLE.UTIL.Arquivo;
import com.mysql.jdbc.exceptions.MySQLSyntaxErrorException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mgarcia
 */
public class ConnectionFactory {

    /*
    *   Esta classe se encarrega de abrir e entregar as conexões com
    *   o banco de dados MySQL para a aplicação
    *   Autor: Mateus Garcia
    *   github.com/NaturesProphet
     */
    private String host;
    private int port;
    private String user;
    private String password;
    private String db;
    private String url;

    public ConnectionFactory() throws Exception {
        host = Arquivo.getLineByCode("mysql.conf", "@ip");
        port = Integer.parseInt(Arquivo.getLineByCode("mysql.conf", "@port"));
        user = Arquivo.getLineByCode("mysql.conf", "@user");
        password = Arquivo.getLineByCode("mysql.conf", "@password");
        db = Arquivo.getLineByCode("mysql.conf", "@schema");
        url = "jdbc:mysql://" + host + ":" + port + "/" + db;

    }

    public Connection getConnection() {

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            return (DriverManager.getConnection(url, user, password));
        } catch (Exception e) {
            System.out.println("ConnectinFactory: "
                    + "Erro ao tentar abrir a conexão" + e);
            return null;
        }
    }

}
