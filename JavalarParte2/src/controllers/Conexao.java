package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	private Connection conexao;
	private String endereco = "da_java.mysql.dbaas.com.br";
	private String banco = "da_java";
	private String nome = "da_java";
	private String senha = "Tecnicas*2023@";
	
	public Conexao() {
	    try {
	      String url = "jdbc:mysql://" + endereco + "/" + banco;
	      conexao = DriverManager.getConnection(url, nome, senha);
	    } catch (SQLException ex) {
	      ex.printStackTrace();
	    } 
	}
	
	public Connection PegarConexao() {
		return conexao;
	}
	
	public void Desconectar() {
		try {
			conexao.close();
		} catch (SQLException ex) {
		    System.out.println("Não conseguiu desconectar do BD.");
		}
	}
	
}
