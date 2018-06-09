/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servico;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Candidato;
import modelo.Concurso;


public class Servico {
    
    public List<Concurso> concursos;
    public List<Candidato> candidatos;

    public Servico() {
        
        concursos = leArquivoConcursos();
        candidatos = leArquivoCandidatos();
    }

    public void listaInformacoesConcursoPorCpfCandidato() {
        
        Scanner scan = new Scanner(System.in);
        System.out.println("CPF do candidato:");
        String cpf = scan.nextLine();
        
        Candidato candidato = buscaCandidato(cpf);
        System.out.println(candidato);
        System.out.println("Concursos compatíveis:");
        
        List<Concurso> concursos = retornaListaConcursosCompativeis(cpf);
        for (Concurso concurso : concursos)
        {
            System.out.println(concurso.toString());
        }    
    }

    public void listaInformacoesCandidatoPorCodigoConcurso() {
        
        Scanner scan = new Scanner(System.in);
        System.out.println("Código do concurso:");
        String codigo = scan.nextLine();
        
        Concurso concurso = buscaConcurso(codigo);
        System.out.println(concurso);
        System.out.println("Candidatos compatíveis:");
        
        List<Candidato> candidatos = retornaListaCandidatosCompativeis(codigo);
        for (Candidato candidato : candidatos)
        {
            System.out.println(candidato.toString());
        } 
    }

    private List<Concurso> retornaListaConcursosCompativeis(String cpf) {
        
        List<Concurso> concursosCompativeis = new ArrayList<>();
        Candidato candidato = buscaCandidato(cpf);
        for (Concurso concurso : concursos)
        {
            if(Compativel(candidato,concurso))
            {
                concursosCompativeis.add(concurso);
            }
        }
        return concursosCompativeis;
        
    }

    private List<Candidato> retornaListaCandidatosCompativeis(String codigo) {
        
        List<Candidato> candidatosCompativeis = new ArrayList<>();
        Concurso concurso = buscaConcurso(codigo);
        for (Candidato candidato : candidatos)
        {
            if(Compativel(candidato,concurso))
            {
                candidatosCompativeis.add(candidato);
            }
        }
        return candidatosCompativeis;
    }

    private List<Concurso> leArquivoConcursos() {

        String folderPath = "c:/";
        Path path = Paths.get(folderPath, "concursos.txt"); 
        Charset charset = Charset.forName("UTF-8");
        String line;
        List<Concurso> concursos = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(path , charset)) {
          while ((line = reader.readLine()) != null ) {
            String[] campos = line.split("\\[");
            String[] dadosBasicosConcurso = campos[0].split(" ");
            String[] vagas = campos[1].replace("]", "").split(",");
            Concurso concurso = new Concurso();
            concurso.setOrgao(dadosBasicosConcurso[0].trim());
            concurso.setEdital(dadosBasicosConcurso[1].trim());
            concurso.setCodigo(dadosBasicosConcurso[2].trim());
            for (int i = 0; i < vagas.length; i++) {
                concurso.getVagas().add(vagas[i].trim());
            }
            concursos.add(concurso);
          }
        } catch (IOException e) {
            System.err.println(e);
        }
        return concursos;
    }

    private List<Candidato> leArquivoCandidatos() {
        
        String folderPath = "c:/";
        Path path = Paths.get(folderPath, "candidatos.txt"); 
        Charset charset = Charset.forName("UTF-8");
        String line;
        List<Candidato> candidatos = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(path , charset)) {
          while ((line = reader.readLine()) != null ) {
              
            int indiceDataNascimento = line.indexOf("/")-2;
            String nome = line.substring(0, indiceDataNascimento);
            
            String[] DemaisCampos = line.substring(indiceDataNascimento, line.length()-1).split("\\["); 
            String[] dadosBasicosCandidato = DemaisCampos[0].split(" ");
            String[] profissoes = DemaisCampos[1].replace("]", "").split(",");
            Candidato candidato = new Candidato();
            candidato.setNome(nome);
            candidato.setDataNascimento(dadosBasicosCandidato[0]);
            candidato.setCpf(dadosBasicosCandidato[1]);
            for (int i = 0; i < profissoes.length; i++) {
                candidato.getProfissoes().add(profissoes[i].trim());
            }
            candidatos.add(candidato);
          }
        } catch (IOException e) {
            System.err.println(e);
        }
        return candidatos;
    }


    private Candidato buscaCandidato(String cpf) {
        
        for (Candidato candidato : candidatos)
        {
            if(candidato.getCpf().equalsIgnoreCase(cpf))
            {
                return candidato;
            }
        }
        return null;
    }

    private boolean Compativel(Candidato candidato, Concurso concurso) {
        
        for (String profissao : candidato.getProfissoes())
        {
            if(concurso.getVagas().contains(profissao))
            {
                return true;
            }   
        }
        return false;
    }

    private Concurso buscaConcurso(String codigo) {
                
        for (Concurso concurso : concursos)
        {
            if(concurso.getCodigo().equalsIgnoreCase(codigo))
            {
                return concurso;
            }
        }
        return null;
    }
    
}
