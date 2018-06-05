package Teste;

public class Candidatos {//Classe

	private String nome;          //         ''
	private String dataNascimento;//Atributos privados do tipo String
	private String cpf;          //           ''
	private String profissao;    //           ''
	
	public Candidatos() {}//Construtor sem passagens de parametros
	
	//Construtor com passagens de parametros
	public Candidatos(String nome, String dataNascimento, String cpf, String profissao) {
		
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.cpf = cpf;
		this.profissao = profissao;
	}
	
	//Gets e Sets para leitura e acesso de dados
    public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getProfissao() {
		return profissao;
	}
	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	@Override
	public String toString() {// ToString para exibição de dados no console
		return "                    Nome = " + nome + "\n"
		     + "DataNascimento = " + dataNascimento + "\n"
		     + "                        CPF = " + cpf + "\n "
	         + "  Profissão(ões) = " + profissao +"\n" ;
	}
	
		
		
	}	

	

