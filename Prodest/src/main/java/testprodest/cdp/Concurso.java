/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testprodest.cdp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Helen
 */
public class Concurso {

    private String codigo;
    private String orgao;
    private String edital;
    private List<String> listaVagas = new ArrayList<>();

    public Concurso(String orgao, String edital, String codigo, String vagas) {
     
        this.codigo = codigo;
        this.orgao = orgao;
        this.edital = edital;
        String[] vaga = vagas.split(",");
        this.listaVagas.addAll(Arrays.asList(vaga));
    }

    public Concurso() {
        //Contrutor
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public List<String> getLista_vagas() {
        return listaVagas;
    }

    public void addVaga(String vaga) {
        this.listaVagas.add(vaga);
    }

    @Override
    public String toString() {
        return "Órgão: " + this.getOrgao() + ", Código: " + this.getCodigo() + ", Edital: " + this.getEdital();
    }

}
