//ivonildo bispo dos santos 
// CI�CIA DA COMPUTA��O 
// faesa  4� periodo noturno
package Teste;

import java.io.BufferedReader; //importa�oes de algumas classes exclusivas do  java
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class MainProjetoES { // Classe principal

	static Scanner scan = new Scanner(System.in); // entrada de dados externo pelo metodo scanner

	public static void main(String[] args) { // metodo principal de testes

		ArrayList<Candidatos> novosCandidatos = new ArrayList<>();// arrayList da classe Candidatos
		ArrayList<Concursos> novoConcurso = new ArrayList<>(); // arrayList da classe Concursos

		// Atributos da classe candidatos inicializadas vazia
		String nome = "";
		String dataNascimento = "";
		String cpf = "";
		String profissoes = "";

		// Atributos da classe concursos inicializadas vazia
		String orgao = "";
		String edital = "";
		String codConcurso = "";
		String listaVagas = "";

		// variaveis auxiliares
		String aux = "";
		int cont = 0;

		String scan = JOptionPane.showInputDialog("Informe um CPF:");// entrada de dados no painel
		
        
		try {// tratamento de erros

			while (scan.length() != 14) { // VERIFICA��O DAS QUATIDADES DE STRINGS DO CPF ussando la�o while

				// Deve digitar os numeros,pontos e tra�inho do cpf que seja igual ao arquivo
				// CANDIDATOS.txt (nao colar,s� d�gitar)
				JOptionPane.showMessageDialog(null, "Digite qualquer CPF do arquivo Candidato.txt com 14 d�gitos \n"
						+ "       considerando ponto (.) e tra�o (-) \n EX:   111.222.333-44", "ATE��O", 0);

				scan = JOptionPane.showInputDialog("Informe um CPF:");

			} // fim do la�o while

		} catch (Exception e) {

		}

		InputStreamReader isr = null;
		try {// tratamento de erros

			InputStream is = new FileInputStream("C:\\Users\\ivonildo\\Desktop\\candidatos.txt");// Caminho do arquivo
																									// candidato.txt

			isr = new InputStreamReader(is);// instanciando arquivo
			BufferedReader br = new BufferedReader(isr);
			String s = br.readLine(); // lendo linha do arquivo

			while (s != null) { // la�o para ler o arquivo candidato.txt

				nome = s.split(" ")[0].trim();// tratando o arquivo e separando nome
				nome += " " + s.split(" ")[1].trim();

				dataNascimento = s.split(" ")[2].trim();// tratando o arquivo e separando data de nascimento

				cpf = s.split(" ")[3].trim();// tratando o arquivo e separando cpf

				profissoes = s.substring(s.indexOf("[") + " ".length(), s.indexOf("]")); // tratando o arquivo e
																							// separando profissoes

				novosCandidatos.add(new Candidatos(nome, dataNascimento, cpf, profissoes)); // inserindo os dados no
																							// arrayList

				s = br.readLine(); // linha do arquivo

			}
			br.close(); // fechando arquivo
			isr.close(); // fechando arquivo

		} catch (IOException e) {// fim do tratamento de erros

			System.out.println("Vamos tentar novamente:");// msg amigavel
		}

		InputStreamReader imput = null;
		try {

			InputStream arq = new FileInputStream("C:\\Users\\ivonildo\\Desktop\\concursos.txt");// CAMINHO DO ARQUIVO
																									// com nome do
																									// Arquivo

			imput = new InputStreamReader(arq);// instanciando o arquivo
			BufferedReader br = new BufferedReader(imput);

			String s = br.readLine(); // lendo a linha do arquivo

			while (s != null) {
				orgao = s.split(" ")[0].trim(); // 1� String da linha antes do espa�o

				edital = s.split(" ")[1].trim(); // 2� String da linha antes do espa�o

				codConcurso = s.split(" ")[2].trim(); // 3� String da linha antes do espa�o

				listaVagas = s.substring(s.indexOf("[") + " ".length(), s.indexOf("]")); // todas as Strings ente o
																							// simbolo [ ]

				novoConcurso.add(new Concursos(orgao, edital, codConcurso, listaVagas)); // inserindo dados no arraylist
																							// novoConcurso

				s = br.readLine();

			}
			br.close();// fecha o arq
			imput.close();// fecha o arq
		} catch (IOException e) {
			System.out.println(" Vamos tentar novamente :");// msg amigavel de erro
		}

		for (Candidatos a : novosCandidatos) { // la�o do arraylist dos candidatos

			if (a.getCpf().equals(scan)) { // Busca do CPF do candidato dentro do arrayList

				aux = a.getProfissao(); // variavel auxiliar para receber as profissoes

				JOptionPane.showMessageDialog(null, a, "CANDIDATO(A)", 1); // Painel com os dados do candidato
			}
           
		}
		
		if(aux.length()==0) {//CASO O CPF NAO EXISTA
			JOptionPane.showMessageDialog(null, "CPF n�o existe : Favor informar do arquivo candidato.txt","ATEN�AO",0);
		}

		if (aux.length() != 0) {// se nao for informado nenhum valor ao cpf

			try {// tratamento de erros

				String aux1 = aux.split(",")[0].trim();// captura a 1� string da linha antes da virgula
				String aux2 = aux.split(" ")[0].trim();// captura a 1� string da linha antes do espa�o vazio

				for (Concursos x : novoConcurso) { // la�o do arraylist do arquivo concurso.txt

					if (x.getListaVagas().contains(aux) || // se o concurso conter apenas uma profiss�o do CPF
							x.getListaVagas().contains(aux1) || // � suficiente para retorna verdadeiro imprimindo toda
																// posi�ao do array
							x.getListaVagas().contains(aux2)) {

						cont++; // conta quantidade de concursos

						System.out.println(x);// imprimindo a posi�ao do array que seja verdadeira a condi��o
					}

				}
				JOptionPane.showMessageDialog(null, "Existem " + cont + " concursos disponiveis para esse CPF \n "
						+ "Ser� exibido no CONSOLE uma lista de Concursos com O perfil profissional desse CPF:  ");
			} catch (Exception e) {

				
			}
		}
	}

}
