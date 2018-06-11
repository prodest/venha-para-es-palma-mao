package model;

import java.util.Date;


@Entity
@Table( name = "TB_CANDIDATOS" )
public class Candidato {
	
	@Id
	@Column(name = "cpf")
	private String cpf;
	
	@Column(name = "dt_nasc")
	private Date dataNascimento;
	
	@Column(name = "nome")
	private String nome;

	public Candidato() {
		
	}


	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	public Date getDataNascimento() {
		return dataNascimento;
	}


	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


}
