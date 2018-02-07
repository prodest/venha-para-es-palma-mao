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
import CONTROLE.UTIL.Data;
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
public class Candidatos {

    /*
    *   
    *   Esta classe cuida das consultas referentes a buscas complexas de dados 
    *   de Candidato.
    *   Todas as querys complexas que rodarão no banco buscando dados de 
    *   candidatos devem ser inseridas aqui e processadas somente aqui.
    *
     */
    public static ArrayList<Candidato> Problema2(String codconcurso) throws Exception {
        /*
        *   Este método retorna uma lista de concursos publicos compatíveis
        *   com um candidato buscando pelo seu CPF, que deve ser fornecido
        *   via parâmetro para este método
         */
        ArrayList<Candidato> candidatos = new ArrayList();
        StringBuilder query = new StringBuilder(367);
        /*
            usarei um stringbuilder para formar a query completa por questões 
            de desempenho. o Java trata uma soma de strings como varias strings
            separadas, o que causa impacto no desempenho ao se somar muitas 
            strings. o stringbuilder com tamanho pré definido pelo conhecimento
            do tamanho da query reduz o impacto no consumo de memória
         */
        query.append("SELECT DISTINCT Nome, DataNasc, CPF FROM Candidato JOIN ");
        query.append("CandidatoXProfissao ON Candidato.IdCandidato = ");
        query.append("CandidatoXProfissao.IdCandidato JOIN ListaDeVagas ON ");
        query.append("CandidatoXProfissao.IdProfissao = ListaDeVagas.IdProfissao");
        query.append(" JOIN ConcursoPublico ON ListaDeVagas.IdConcursoPublico");
        query.append(" = ConcursoPublico.IdConcursoPublico WHERE ");
        query.append("ConcursoPublico.CodConcurso = ");
        query.append("? ORDER BY Candidato.Nome");

        Connection con = new ConnectionFactory().getConnection();
        PreparedStatement ps = con.prepareStatement(query.toString());
        ps.setString(1, codconcurso);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Candidato c = new Candidato();
            c.setNome(rs.getString(1));
            c.setDataNasc(Data.getDataAsUtil(rs.getDate(2)));
            c.setCPF(rs.getString(3));
            candidatos.add(c);
        }
        rs.close();
        ps.close();
        con.close();
        return candidatos;
    }
}
