package Teste; 

public class Concursos { //Classe 
	
	private String orgao;//           ''
	private String edital;//           ''
	private String condigoConcurso;//Atributos do tipo String
	private String listaVagas;//       ''
	
	public Concursos() {}  //Construtor sem passagem de parametro
	
	
	//Construtor com passagens de parametros
	public Concursos(String orgao, String edital, String condigoConcurso, String listaVagas) {
		
		this.orgao = orgao;
		this.edital = edital;
		this.condigoConcurso = condigoConcurso;
		this.listaVagas = listaVagas;
	}
	//Gets e Sets para leitura e acesso de dados
	public String getOrgao() {
		return orgao;
	}
	public void setOrgao(String orgao) {
		this.orgao = orgao;
	}
	public String getEdital() {
		return edital;
	}
	public void setEdital(String edital) {
		this.edital = edital;
	}
	public String getCondigoConcurso() {
		return condigoConcurso;
	}
	public void setCondigoConcurso(String condigoConcurso) {
		this.condigoConcurso = condigoConcurso;
	}
	public String getListaVagas() {
		return listaVagas;
	}
	public void setListaVagas(String listaVagas) {
		this.listaVagas = listaVagas;
	}

	@Override  
	public String toString() {// ToString para exibição de dados no console
		return " Concursos orgao  = " + orgao + "\n"
			+ ",          edital = " + edital + "\n"
			+ ", condigoConcurso = " + condigoConcurso+"\n"
     		+ ",      listaVagas = " + listaVagas +"\n";
	}
	
	
	

}
