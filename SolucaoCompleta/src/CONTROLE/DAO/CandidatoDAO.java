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

import CONTROLE.UTIL.Data;
import ENTIDADES.Candidato;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author mgarcia
 */
public class CandidatoDAO {

    public void salvar(Candidato c) throws SQLException, Exception {
        ArrayList<String> Profissoes = c.getProfissoes();
        Connection con = new ConnectionFactory().getConnection();
        con.setAutoCommit(false);
        String sql = "INSERT INTO Candidato VALUES (NULL,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, c.getNome());
        ps.setDate(2, Data.getDataAsSQL(c.getDataNasc()));
        ps.setString(3, c.getCPF());
        ps.execute();
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            c.setidCandidato(rs.getInt(1));
        }
        ps.close();

        for (int i = 0; i < Profissoes.size(); i++) {
            String callsql = "call InsereCandidatoXProfissao (?,?);";
            CallableStatement pcall = con.prepareCall(callsql);
            pcall.setInt(1, c.getIdCandidato());
            pcall.setString(2, Profissoes.get(i));
            pcall.executeUpdate();
            pcall.close();
        }
        con.commit();
        System.out.println("Candidato registrado: " + c.getNome()
                + ". ID: " + c.getIdCandidato());
        con.close();
    }

    public ArrayList<Candidato> getAll() throws SQLException, Exception {
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

    //retorna um Candidato buscado pelo seu CPF 
    public Candidato getByCPF(String CPF) throws SQLException, Exception {
        Connection con = new ConnectionFactory().getConnection();
        String sql = "SELECT * FROM Candidato WHERE CPF = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, CPF);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Candidato c = new Candidato();
            c.setidCandidato(rs.getInt(1));
            c.setNome(rs.getString(2));
            c.setDataNasc(Data.getDataAsUtil(rs.getDate(3)));
            c.setCPF(rs.getString(4));
            rs.close();
            con.close();
            return c;
        }
        rs.close();
        con.close();
        return null;
    }
}
