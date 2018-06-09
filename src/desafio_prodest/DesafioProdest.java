/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desafio_prodest;

import static java.lang.System.exit;
import java.util.Scanner;
import servico.Servico;

public class DesafioProdest {

    public static void main(String[] args) {
       
        apresentaMenu();
        Scanner scan = new Scanner(System.in);
        Servico servico = new Servico();
        int opcao = scan.nextInt();
        while (opcao != 3)
        {       
            switch (opcao) {
                case 1:  servico.listaInformacoesConcursoPorCpfCandidato();
                         break;
                case 2:  servico.listaInformacoesCandidatoPorCodigoConcurso();
                         break;
                default: break;
            }
            
            apresentaMenu();
            opcao = scan.nextInt();
            
        }
        
        //sai do sistema caso a opção do usuário seja 3
        exit(1);
        
    }
    
        private static void apresentaMenu() {

        System.out.println();
        System.out.println("1-Listar informações de concursos por cpf do candidato");
        System.out.println("2-Listar candidatos por código do concurso");
        System.out.println("3-Sair");
    }
    
}
