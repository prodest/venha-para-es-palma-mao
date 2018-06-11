package dao;

import java.util.ArrayList;

import model.Candidato;
import org.hibernate.Query;
import org.hibernate.Session;


@RequestScoped
public class CandidatoDAO {

	public CandidatoDAO() {
	
	}
	
	public ArrayList<Candidato> pesquisarCandidatos(String codigoConcurso) {
				
		@Inject
		private Session session;
		
		ArrayList<Candidato> candidatos = null;
		String sql = new String();
		
		sql = "SELECT"
				+ " c.nome,"
				+ " c.dt_nasc,"
		        + " c.cpf"
		        + " FROM tb_candidatos c"
		        + " WHERE"
		        + " c.cpf in ("
		        + " SELECT "
		        + " pcand.cpf"
		        + " FROM  tb_profissoes_concurso pconc, tb_profissoes_candidatos pcand " 
		        + " WHERE"
		        + " pconc.cdConcurso = '"+codigoConcurso+"'"
		        + " and pcand.idProf = pconc.idProf  "          
		        + " )";
		
		return  session.createSQLQuery( sql ).addEntity( Candidato.class ).list();
		
		return candidatos;

	}
	
	
	
	

}
