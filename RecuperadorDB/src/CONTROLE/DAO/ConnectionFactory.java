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

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author mgarcia
 */
public class ConnectionFactory {

    private final String host = "192.168.1.109";
    private final int port = 3306;
    private final String user = "root";
    private final String password = "123";
    private final String db = "Concursos";
    private final String url = "jdbc:mysql://" + host + ":" + port + "/" + db;

    public Connection getConnection() {

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            return (DriverManager.getConnection(url, user, password));
        } catch (Exception e) {
            System.out.println("Erro " + e);
            JOptionPane.showMessageDialog(null, "Não foi possível conectar.\n" + e);
            return null;
        }
    }

}
