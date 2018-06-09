/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;
import java.util.List;


public class Concurso {
    
    private String orgao;
    private String edital;
    private String codigo;
    private List<String> vagas;

    @Override
    public String toString() {
        return "Concurso{" + "orgao=" + orgao + ", edital=" + edital + ", codigo=" + codigo + ", vagas=" + vagas + '}';
    }

    public Concurso() {
        
        vagas = new ArrayList<>();
    }

    public String getOrgao() {
        return orgao;
    }

    public void setOrgao(String orgao) {
        this.orgao = orgao;
    }

    public String getEdital() {
        return edital;
    }

    public void setEdital(String edital) {
        this.edital = edital;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public List<String> getVagas() {
        return vagas;
    }

    public void setVagas(List<String> vagas) {
        this.vagas = vagas;
    }

    
}
