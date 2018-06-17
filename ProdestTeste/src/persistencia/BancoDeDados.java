/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import entidades.Candidato;
import entidades.Cargo;
import entidades.Concurso;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author luizk
 */
public class BancoDeDados {
	
	/**
	 * 
	 * @throws SQLException 
	 */
	public void criarTabelaCandidato() throws SQLException{
		String sql = "CREATE TABLE candidato"
			+ "(id INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ "nome CHAR(40),"
			+ "dataNasc CHAR(10),"
			+ "cpf CHAR(14) NOT NULL,"
			+ "cargos CHAR(20))";
		this.executeSQL(sql);
	}
	
	/**
	 * 
	 * @throws SQLException 
	 */
	public void criarTabelaCargo() throws SQLException{
		String sql = "CREATE TABLE cargo"
			+ "(id INTEGER PRIMARY KEY,"
			+ "nome CHAR(40))";
		this.executeSQL(sql);
	}
	
	/**
	 * 
	 * @throws SQLException 
	 */
	public void criarTabelaConcurso() throws SQLException{
		String sql = "CREATE TABLE concurso"
			+ "(id INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ "orgao CHAR(40),"
			+ "edital CHAR(40),"
			+ "codigo CHAR(40) NOT NULL,"
			+ "cargos CHAR(20))";
		this.executeSQL(sql);
	}
	
	/**
	 * 
	 * @param cand
	 * @throws SQLException 
	 */
	public void saveCandidato(Candidato cand) throws SQLException{
		String sql="INSERT INTO candidato (nome, dataNasc, cpf, cargos) "
			+ "values ("
			+ "'" + cand.getNome() + "',"
			+ "'" + cand.getDataNasc() + "',"
			+ "'" + cand.getCpf() + "',"
			+ "'" + cargoIdToString(cand.getCargo()) + "'"
			+ ")";
		this.executeSQL(sql);
	}
	
	/**
	 * 
	 * @param cargo
	 * @throws SQLException 
	 */
	public void saveCargo(Cargo cargo) throws SQLException{
		String sql="INSERT INTO cargo (id, nome) "
			+ "values ("
			+ cargo.id + ","
			+ "'" + cargo.nome + "'"
			+ ")";
		this.executeSQL(sql);
	}
	
	/**
	 * 
	 * @param conc
	 * @throws SQLException 
	 */
	public void saveConcurso(Concurso conc) throws SQLException{
		String sql="INSERT INTO concurso (orgao, edital, codigo, cargos) "
			+ "values ("
			+ "'" + conc.getOrgao() + "',"
			+ "'" + conc.getEdital() + "',"
			+ "'" + conc.getCodigo() + "',"
			+ "'" + cargoIdToString(conc.getCargo()) + "'"
			+ ")";
		this.executeSQL(sql);
	}
	
	/**
	 * 
	 * @param sql
	 * @throws SQLException 
	 */
	public void executeSQL(String sql) throws SQLException {
		Connection connection = null;
		Statement statement = null;
		try {
			Class.forName("org.sqlite.JDBC");
			
			connection = DriverManager.getConnection("jdbc:sqlite:prodestteste.db");
			statement = connection.createStatement();
			statement.executeUpdate(sql);
			statement.close();
			connection.close();
			
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw ex;
		}
	}

	/**
	 * 
	 * @return 
	 */
	public ArrayList recuperarCandidatos(){
		ArrayList<Candidato> lista = new ArrayList<>();
		Connection connection = null;
		Statement stament = null;
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:prodestteste.db");
			
			stament = connection.createStatement();
			ResultSet resultado = stament.executeQuery("SELECT * FROM candidato");
			
			while(resultado.next()){
				Candidato cand  = new Candidato();
				cand.setNome(resultado.getString("nome"));
				cand.setDataNasc(resultado.getString("dataNasc"));
				cand.setCpf(resultado.getString("cpf"));
				cand.setCargo(stringToCargoId(resultado.getString("cargos")));
				lista.add(cand);
			}
			stament.close();
			connection.close();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return lista;
	}
	
	/**
	 * 
	 * @return 
	 */
	public ArrayList recuperarCargos(){
		ArrayList<Cargo> lista = new ArrayList<>();
		Connection connection = null;
		Statement stament = null;
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:prodestteste.db");
			
			stament = connection.createStatement();
			ResultSet resultado = stament.executeQuery("SELECT * FROM cargo");
			
			while(resultado.next()){
				Cargo cargo  = new Cargo();
				cargo.id = Integer.parseInt(resultado.getString("id"));
				cargo.nome = resultado.getString("nome");
				lista.add(cargo);
			}
			stament.close();
			connection.close();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return lista;
	}

	/**
	 * 
	 * @return 
	 */
	public ArrayList recuperarConcursos(){
		ArrayList<Concurso> lista = new ArrayList<>();
		Connection connection = null;
		Statement stament = null;
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:prodestteste.db");
			
			stament = connection.createStatement();
			ResultSet resultado = stament.executeQuery("SELECT * FROM concurso");
			
			while(resultado.next()){
				Concurso conc  = new Concurso();
				conc.setOrgao(resultado.getString("orgao"));
				conc.setEdital(resultado.getString("edital"));
				conc.setCodigo(resultado.getString("codigo"));
				conc.setCargo(stringToCargoId(resultado.getString("cargos")));
				lista.add(conc);
			}
			stament.close();
			connection.close();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return lista;
	}
	
	/**
	 * 
	 * @param cargo
	 * @return 
	 */
	private String cargoIdToString(int[] cargo) {
		String str = "";
		if(cargo.length == 0){
			return str;
		}
		for(int num : cargo){
			str += num + ",";
		}
		str = str.substring(0, str.length()-1);
		return str;
	}

	/**
	 * 
	 * @param str
	 * @return 
	 */
	private int[] stringToCargoId(String str) {
		String[] strList = str.split(",");
		int[] nums = new int[strList.length];
		for(int i=0; i < nums.length; i++){
			nums[i] = Integer.parseInt(strList[i]);
		}
		return nums;
	}
}
