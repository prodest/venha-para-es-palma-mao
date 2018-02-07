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
package ENTIDADES;

import java.util.ArrayList;

/**
 *
 * @author mgarcia
 */
public class ConcursoPublico {
    private int IdConcursoPublico;
    private String orgao;
    private String CodConcurso;
    private int EditalNum;
    private int EditalAno;
    private ArrayList<String> Profissoes;
    
    //o construtor inicializa o ArrayList
    public ConcursoPublico() {
        this.Profissoes = new ArrayList();
    }
    

    public void setIdConcursoPublico(int id) {
        this.IdConcursoPublico = id;
    }
    public int getIdConcursoPublico() {
        return this.IdConcursoPublico;
    }
    
    public void setOrgao(String orgao) {
        this.orgao = orgao;
    }
    public String getOrgao(){
        return this.orgao;
    }
    
    public void setCodConcurso(String cod) {
        this.CodConcurso = cod;
    }
    public String getCodConcurso() {
        return this.CodConcurso;
    }
    
    public void setEditalNum(int num) {
        this.EditalNum = num;
    }
    public int getEditalNum(){
        return this.EditalNum;
    }
    
    public void setEditalAno(int ano) {
        this.EditalAno = ano;
    }
    public int getEditalAno(){
        return this.EditalAno;
    }
    //adiciona uma profissão na lista de profissoes
    public void addProfissao(String prof) {
        this.Profissoes.add(prof);
    }
    //retorna o nome da profissao no indice indicado
    public String getProfissao(int index) {
        return this.Profissoes.get(index);
    }
    //retorna toda a lista de profissoes
    public ArrayList<String> getProfissoes(){
        return this.Profissoes;
    }
    
    //retorna o numero composto completo do edital como string
    public String getEdital() {
        return "" + this.EditalNum + "/" + this.EditalAno;
    }
}
