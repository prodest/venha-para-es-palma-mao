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
import java.util.Date;

/**
 *
 * @author mgarcia
 */
public class Candidato {
    private int IdCandidato;
    private String Nome;
    private Date DataNasc;
    private String CPF;
    private ArrayList<String> Profissoes;
    
    public Candidato() {
        this.Profissoes = new ArrayList();
    }
    
    public void addProfissao(String p) {
        this.Profissoes.add(p);
    }
    
    public String getProfissao(int index) {
        return this.Profissoes.get(index);
    }
    
    public ArrayList<String> getProfissoes() {
        return this.Profissoes;
    }
    
    public void setidCandidato(int i) {
        this.IdCandidato = i;
    }
    public int getIdCandidato() {
        return this.IdCandidato;
    }
    
    public void setNome(String n) {
        this.Nome = n;
    }
    public String getNome(){
        return this.Nome;
    }
    
    public void setDataNasc(Date d) {
        this.DataNasc = d;
    }
    public Date getDataNasc() {
        return this.DataNasc;
    }
    
    public void setCPF(String c) {
        this.CPF = c;
    }
    public String getCPF(){
        return this.CPF;
    }
    
}
