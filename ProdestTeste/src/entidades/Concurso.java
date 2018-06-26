/*
 * @version 2018.06.08
 * @author Luiz Henrique A. A. Lima
 */
package entidades;

/**
 *
 * @author luizk
 */
public class Concurso {

	private String orgao;
	private String edital;
	private String codigo;
	private int[] cargo;
	
	public String getOrgao() {
		return orgao;
	}
	public void setOrgao(String organizacao) {
		this.orgao = organizacao;
	}
	public String getEdital() {
		return edital;
	}
	public void setEdital(String edicao) {
		this.edital = edicao;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public int[] getCargo() {
		return cargo;
	}
	public void setCargo(int[] cargo) {
		this.cargo = cargo;
	}
}
