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
public class Candidato {

    private String nome;
    private String dataNascimento;
    private String cpf;
    private List<String> lista_profissoes = new ArrayList<>();

    public Candidato(String nome, String dataNascimento, String cpf, String profissoes) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        String[] profissao = profissoes.split(",");
        this.lista_profissoes.addAll(Arrays.asList(profissao));
    }

    public Candidato() {
        //Construtor
    }


    public String getNome() {
        return nome;
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
