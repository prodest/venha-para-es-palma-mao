package dao;


import java.util.ArrayList;

import model.Candidato;
import model.Concurso;

public class ConcursoDAO {

	public ConcursoDAO() {
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<Concurso> pesquisarConcursos(String cpf) {
		
		/*Listar os órgãos, códigos e editais dos concursos públicos que encaixam no perfil do candidato tomando como base o CPF do candidato ;*/
		
		@Inject
		private Session session;
		
		String sql = new String();
		
		sql = "SELECT "
				+ " conc.orgao,"
				+ " conc.cdConcurso, "
				+ " conc.edital"
				+ " FROM tb_concursos conc"
				+ " WHERE"
				+ " conc.cdConcurso in("
				    + " SELECT"
				    + " pcand.cpf"
				    + " FROM tb_profissoes_concurso pconc, tb_profissoes_candidatos pcand"
				    + " WHERE"
				    + " pcand.cpf = '"+cpf+"'"
				    + " and pconc.idProf = pcand.idProf";
		
		return  session.createSQLQuery( sql ).addEntity( Concurso.class ).list();


	}

}
