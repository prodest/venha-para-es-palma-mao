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

import ENTIDADES.ConcursoPublico;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author mgarcia
 */
public class ConcursoPublicoDAO {

    //registra um ConcursoPublico no banco de dados
    public void salvar(ConcursoPublico c) throws SQLException {
        Connection con = new ConnectionFactory().getConnection();
        con.setAutoCommit(false);
        String sql = "INSERT INTO ConcursoPublico "
                + "(Orgao,CodConcurso,EditalNum,EditalAno) VALUES (?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, c.getOrgao());
        ps.setString(2, c.getCodConcurso());
        ps.setInt(3, c.getEditalNum());
        ps.setInt(4, c.getEditalAno());
        ps.execute();
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            c.setIdConcursoPublico(rs.getInt(1));
        }
        //laço para registrar as profissoes 1 por 1
        for (int i = 0; i < c.getProfissoes().size(); i ++) {
            String callsql = "call InsereCandidatoXProfissao (?,?)";
            CallableStatement call = con.prepareCall(callsql);
            call.setInt(1, c.getIdConcursoPublico());
            call.setString(2, c.getProfissao(i));
            call.execute();
            call.close();
        }
        
        con.commit();
        System.out.println("Concurso Publico registrado! ID: "+c.getIdConcursoPublico());
        rs.close();
        ps.close();
        con.close();
    }

}
