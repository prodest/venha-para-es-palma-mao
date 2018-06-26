/*
 * @version 2018.06.08
 * @author Luiz Henrique A. A. Lima
 */
package entidades;

/**
 *
 * @author luizk
 */
public class Cargo {
	public int id;
	public String nome;
	
	public Cargo(){
		this.id = 0;
		this.nome = "";
	}
	
	public Cargo(int id, String nome){
		this.id = id;
		this.nome = nome;
	}
}
