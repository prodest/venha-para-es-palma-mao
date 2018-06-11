package view;

import java.util.ArrayList;
import java.util.Scanner;

import dao.CandidatoDAO;
import dao.ConcursoDAO;
import model.Candidato;
import model.Concurso;

public class executarPrograma {

	public executarPrograma() {
	}

	public static void main(String[] args) {

		@SuppressWarnings("resource")
		Scanner leitor = new Scanner(System.in);
		String menu;
		String cpf;
		String codigo;

		try {


			System.out.println("O que deseja pesquisar? \n"
					+ "- Pressione 1 para pesquisar candidatos disponiveis por codigo. \n"
					+ "- Pressione 2 para pesquisar concurso disponiveis por cpf. \n");
			menu = leitor.nextLine();

			if(menu.equals("1")) {

				System.out.println("Informe o Codigo do concurso: ");
				codigo = leitor.nextLine();

				CandidatoDAO  dao = new CandidatoDAO();
				ArrayList<Candidato> listaCandidatos = dao.pesquisarCandidatos(codigo);

				if(listaCandidatos != null) {

					System.out.println("#### CANDIDATOS DISPONIVEIS ###### \n");

					for (Candidato candidato : listaCandidatos) {

						System.out.println("* Nome: "+candidato.getNome());
						System.out.println("\n");
						System.out.println("* Data do Nascimento: "+candidato.getDataNascimento());
						System.out.println("\n");
						System.out.println("* CPF: "+candidato.getCpf());
					}

				}else {

					System.out.println("... Não há candidatos disponiveis!");
				}

			}else if(menu.equals("2")) {

				System.out.println("Informe o CPF do candidato: ");
				cpf = leitor.nextLine();

				ConcursoDAO  dao = new ConcursoDAO();
				ArrayList<Concurso> listaConcurso = dao.pesquisarConcursos(cpf);

				if(listaConcurso != null) {

					System.out.println("#### CONCURSOS DISPONIVEIS ###### \n");

					for (Concurso concurso : listaConcurso) {

						System.out.println("* Orgão: "+concurso.getOrgao());
						System.out.println("\n");
						System.out.println("* Código: "+concurso.getCodigoConcurso());
						System.out.println("\n");
						System.out.println("* Edital: "+concurso.getEdital());

					}

				}else {

					System.out.println("... Não há concursos disponiveis!");
				}

			}else {
				System.out.println("Escolha uma opção válida...");

			}

		} catch (Exception e) {
			
			e.printStackTrace();
		}



	}

}
