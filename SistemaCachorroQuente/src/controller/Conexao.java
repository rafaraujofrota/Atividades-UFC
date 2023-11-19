package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	
	private Connection conexao;
	private String endereco = "test_db.mysql.dbaas.com.br:3306";
	private String banco = "test_db";
	private String nome = "test_db";
	private String senha = "TecProg23-2!@";

	public Conexao() {
	    try {
	      String url = "jdbc:mysql://" + endereco + "/" + banco;
	      conexao = DriverManager.getConnection(url, nome, senha);
	      System.out.println("Conectou no banco de dados.");
	    } catch (SQLException ex) {
	      ex.printStackTrace();
	    } 
	}

	public void Desconectar() {
	   try {
	      conexao.close();
	      System.out.println("Desconectou do banco de dados.");
	   } catch (SQLException ex) {
	      System.out.println("Não conseguiu desconectar do BD.");
	   }
	}
	
}
