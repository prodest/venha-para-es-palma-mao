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

import CONTROLE.UTILS.Data;
import ENTIDADES.Candidato;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author mgarcia
 */
public class CandidatoDAO {

    public void salvar(Candidato c) throws SQLException {
        Connection con = new ConnectionFactory().getConnection();
        String sql = "INSERT INTO Candidato VALUES (NULL,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, c.getNome());
        ps.setDate(2, Data.getDataAsSQL(c.getDataNasc()));
        ps.setString(3, c.getCPF());

        ps.execute();
        System.out.println("Candidato registrado: " + c.getNome());
        ps.close();
        con.close();
    }

    public ArrayList<Candidato> getAll() throws SQLException {
        ArrayList<Candidato> lista = new ArrayList();
        Connection con = new ConnectionFactory().getConnection();
        String sql = "SELECT * FROM Candidato";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Candidato c = new Candidato();
            c.setidCandidato(rs.getInt(1));
            c.setNome(rs.getString(2));
            c.setDataNasc(Data.getDataAsUtil(rs.getDate(3)));
            c.setCPF(rs.getString(4));

            lista.add(c);
        }
        rs.close();
        ps.close();
        con.close();

        return lista;
    }
}
