/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testprodest.cdp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ISM
 */
public class Candidato {

    private int id;
    private String nome;
    private Date dataNascimento;
    private String cpf;
    private List<String> lista_profissoes = new ArrayList<>();

    public Candidato(int id,String nome, Date dataNascimento, String cpf, String profissoes) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        String[] profissao = profissoes.split(",");
        this.lista_profissoes.addAll(Arrays.asList(profissao));
    }
    
    public Candidato(){
        
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<String> getLista_profissoes() {
        return lista_profissoes;
    }

    public void addProfissao(String profissao) {
        this.lista_profissoes.add(profissao);
    }

    @Override
    public String toString() {
        return "Nome: " + this.getNome() + ", Data de Nascimento: "
                + this.getDataNascimento() + ", CPF: " + this.getCpf();
    }
}
