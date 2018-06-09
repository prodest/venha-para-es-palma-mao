/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;
import java.util.List;


public class Candidato {
    
    private String nome;
    private String dataNascimento;
    private String cpf;
     private List<String> profissoes;

    public Candidato() {
        
        profissoes = new ArrayList<>();
    }
   

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return "Candidato{" + "nome=" + nome + ", dataNascimento=" + dataNascimento + ", cpf=" + cpf + ", profissoes=" + profissoes + '}';
    }



    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<String> getProfissoes() {
        return profissoes;
    }

    public void setProfissoes(List<String> profissoes) {
        this.profissoes = profissoes;
    }



    
}
