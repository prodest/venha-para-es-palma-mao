/*
 * @version 2018.06.08
 * @author Luiz Henrique A. A. Lima
 */
package entidades;

/**
 *
 * @author luizk
 */
public class Candidato {

	private String nome;
	private String dataNasc; // TODO: O ideal seria um tipo Date, mas pra esse teste vai servir
	private String cpf;
	private int[] cargo;
	
	public Candidato(){
		this.setNome("");
		this.setDataNasc(null);
		this.setCpf("");
		this.setCargo(null);
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDataNasc() {
		return dataNasc;
	}
	public void setDataNasc(String dataNasc) {
		this.dataNasc = dataNasc;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public int[] getCargo() {
		return cargo;
	}
	public void setCargo(int[] cargo) {
		this.cargo = cargo;
	}
}
