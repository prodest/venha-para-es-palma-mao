/**
 * 
 * @version 2018.06.08
 * @author Luiz Henrique A. A. Lima
 */
package persistencia;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author luizk
 */
public class Arquivo {
	
	private String nome;
	private String linha;
	private FileReader leitor;
	private BufferedReader buffer;
	
	public Arquivo(){
		this.nome = "";
		this.linha = "";
		this.leitor = null;
		this.buffer = null;
	}
	
	public void abrir(String nome) throws FileNotFoundException{
		this.setNome(nome);
		this.leitor = new FileReader(this.getNome());
		this.buffer = new BufferedReader(this.leitor);
	}
	
	public void fechar() throws IOException{
		this.setNome("");
		this.leitor.close();
		this.buffer.close();
	}
	
	public void setNome(String nome){
		this.nome = nome;
	}
	public String getNome(){
		return this.nome;
	}
	
	/**
	 * Retorna a ultima linha do arquivo que foi lida.
	 * 
	 * @return
	 */
	public String getLinhaAtual(){
		return this.linha;
	}
	
	/**
	 * Le uma linha do arquivo e passa para a proxima. A string resultante pode
	 * ser acessada posteriormente chamando o metodo getLinhaAtual. Retorna NULL
	 * se for fim do arquivo e não houverem mais linhas a serem lidas. 
	 * 
	 * @return
	 * @throws IOException 
	 */
	public Object lerLinha() throws IOException {
		return (this.linha = this.buffer.readLine());
	}
	
}
