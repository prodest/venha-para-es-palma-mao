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
package CONTROLE.CONSULTAS;

import CONTROLE.DAO.ConnectionFactory;
import ENTIDADES.Candidato;
import ENTIDADES.ConcursoPublico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mgarcia
 */
public class Concursos {

    /*
    *   
    *   Esta classe cuida das consultas referentes a buscas complexas de 
    *   dados de Concursos.
    *   Todas as querys complexas que rodarão no banco buscando dados de 
    *   Concursos devem ser inseridas aqui e processadas somente aqui.
    *
     */
    public static ArrayList<ConcursoPublico> Problema1(String CPF) throws SQLException {
        /*
        *   Este método retorna uma lista de concursos publicos compatíveis
        *   com um candidato buscando pelo seu CPF, que deve ser fornecido
        *   via parâmetro para este método
         */
        StringBuilder query = new StringBuilder(557);
        /*
            usarei um stringbuilder para formar a query completa por questões 
            de desempenho. o Java trata uma soma de strings como varias strings
            separadas, o que causa impacto no desempenho ao se somar muitas 
            strings. o stringbuilder com tamanho pré definido pelo conhecimento
            do tamanho da query reduz o impacto no consumo de memória
         */
        query.append("SELECT Orgao, CodConcurso, EditalNum, EditalAno FROM ");
        query.append("ConcursoPublico WHERE IdConcursoPublico IN ");
        query.append("(SELECT ConcursoPublico.IdConcursoPublico FROM");
        query.append(" ConcursoPublico JOIN ListaDeVagas ON ");
        query.append("ListaDeVagas.IdConcursoPublico = ConcursoPublico");
        query.append(".IdConcursoPublico WHERE ListaDeVagas.IdProfissao IN ");
        query.append("(SELECT Profissao.IdProfissao FROM Profissao ");
        query.append("JOIN CandidatoXProfissao ON Profissao.IdProfissao");
        query.append(" = CandidatoXProfissao.IdProfissao ");
        query.append("JOIN Candidato ON CandidatoXProfissao.IdCandidato ");
        query.append("= Candidato.IdCandidato WHERE Candidato.CPF = ? )) ");
        query.append("ORDER BY ConcursoPublico.EditalAno Desc, Orgao");
        ArrayList<ConcursoPublico> concursos = new ArrayList();

        Connection con = new ConnectionFactory().getConnection();
        PreparedStatement ps = con.prepareStatement(query.toString());
        ps.setString(1, CPF);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            ConcursoPublico c = new ConcursoPublico();
            c.setOrgao(rs.getString(1));
            c.setCodConcurso(rs.getString(2));
            c.setEditalNum(rs.getInt(3));
            c.setEditalAno(rs.getInt(4));
            concursos.add(c);
        }
        System.out.println("O CPF inserido é inválido");
        rs.close();
        con.close();
        return concursos;
    }

}
