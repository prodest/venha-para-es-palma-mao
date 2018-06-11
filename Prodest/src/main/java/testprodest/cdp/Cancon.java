/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testprodest.cdp;

/**
 *
 * @author ISM
 */
public class Cancon {
    
    String edital_concurso;
    String cpf_candidato;
    String codigo;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    

    public Cancon(String edital_concurso, String cpf_candidato,String codigo) {
        this.edital_concurso = edital_concurso;
        this.cpf_candidato = cpf_candidato;
        this.codigo = codigo;
    }

    public String getEdital_concurso() {
        return edital_concurso;
    }

    public void setEdital_concurso(String edital_concurso) {
        this.edital_concurso = edital_concurso;
    }

    public String getCpf_candidato() {
        return cpf_candidato;
    }

    public void setCpf_candidato(String cpf_candidato) {
        this.cpf_candidato = cpf_candidato;
    }
    
    
}
