package model;

@Entity
@Table( name = "TB_CONCURSOS" )
public class Concurso {
	
	@Id
	@Column(name = "cdConcurso")
	private String codigoConcurso;
	
	@Column(name = "edital")
	private String edital;
	
	@Column(name = "orgao")
	private String orgao;

	public Concurso() {
		
	}

	public String getCodigoConcurso() {
		return codigoConcurso;
	}

	public void setCodigoConcurso(String codigoConcurso) {
		this.codigoConcurso = codigoConcurso;
	}

		public String getEdital() {
		return edital;
	}

	public void setEdital(String edital) {
		this.edital = edital;
	}

	public String getOrgao() {
		return orgao;
	}

	public void setOrgao(String orgao) {
		this.orgao = orgao;
	}


}
