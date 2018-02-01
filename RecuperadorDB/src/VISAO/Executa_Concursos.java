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
package VISAO;

import CONTROLE.DAO.ConcursoPublicoDAO;
import CONTROLE.LeitorDeDados;
import ENTIDADES.ConcursoPublico;
import java.sql.SQLException;

/**
 *
 * @author mgarcia
 */
public class Executa_Concursos {

    public static void main(String[] args) {
        ConcursoPublico c = LeitorDeDados.LeConcursos("SEDU 9/2016 61828450843 [carpinteiro, analista de sistemas, marceneiro]");
    
    System.out.println(c.getEditalNum());
    System.out.println(c.getEditalAno());
    System.out.println(c.getOrgao());
    System.out.println(c.getCodConcurso());
    
    for (int i = 0; i < c.getProfissoes().size(); i++) {
        System.out.println(c.getProfissao(i));
    }
    
    
    }
    
}
