package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import classes.Planeta;
import classes.Plano;
import classes.Relatorio;
import utils.PlanetaDadosRelatorio;

public class RelatorioDAO {
	private Conexao conexao;
	
	public RelatorioDAO() {
		conexao = new Conexao();
	}
	
	public void PegarTodos() throws Exception {
		Connection con = conexao.PegarConexao();
		PreparedStatement comando = con.prepareStatement("SELECT * from javalar");
		ResultSet dados = comando.executeQuery();
		
		ArrayList<Relatorio> listaDados = new ArrayList<Relatorio>();
		
		while(dados.next()) {
			PlanetaDadosRelatorio[] dadosPlaneta = new PlanetaDadosRelatorio[7];
			int contador = 0;
			
			for(String nome: PlanetaDadosRelatorio.planetasDisponiveis) {
				PlanetaDadosRelatorio planeta = new PlanetaDadosRelatorio(nome);
				planeta.colisaoBugs = dados.getInt("bug_" + nome);
				planeta.colisaoDevs = dados.getInt("dev_" + nome);
				planeta.horasTotais =  dados.getInt("d_" + nome);
				planeta.velocidade =  dados.getInt("v_" + nome);
				planeta.anosTotais =  dados.getInt("a_" + nome);
				
				dadosPlaneta[contador] = planeta;
				contador++;
			}
			
			Relatorio relatorio = new Relatorio(dados.getInt("id"), dados.getString("nome"), dados.getString("matricula"));
			relatorio.nomeArquivo = dados.getString("nome_arquivo");
			relatorio.dadosPlaneta = dadosPlaneta;
			int[] bugQuadrante = new int[4];
			int[] devQuadrante = new int[4];
			
			for(int i = 1; i <= 4; i++) {
				bugQuadrante[i - 1] = dados.getInt("bug_q" + i);
				devQuadrante[i - 1] = dados.getInt("dev_q" + i);
			}
			
			relatorio.bugsPorQuadrante = bugQuadrante;
			relatorio.devsPorQuadrante = devQuadrante;
			
			listaDados.add(relatorio);
		}
		
		Relatorio.ListaRelatorios = listaDados;
		
		conexao.Desconectar();
	}
	
	public void Criar(Planeta[] planetas, Plano plano, String arquivo) throws Exception {
		String sql = "INSERT INTO javalar (nome, matricula, nome_arquivo";
		int argumento = 1;
		
		for(Planeta p: planetas) {
			if(p == null) continue; // Usando os 7 planetas padrão nunca vai ser null
			
			String nome = p.pegarNome().toLowerCase();
			sql += ", " + "dev_" + nome + ", " + "bug_" + nome + ", " + "v_" + nome + ", " + "d_" + nome + ", " + "a_" + nome;
		}
		
		sql += ", bug_q1, bug_q2, bug_q3, bug_q4, dev_q1, dev_q2, dev_q3, dev_q4)";
		sql += " VALUES ('Rafael Araujo Frota', '554587', ?";
		
		for(Planeta p: planetas) {
			if(p == null) continue;
			sql += ", ?, ?, ?, ?, ?"; 
		}
		
		sql += ", ?, ?, ?, ?, ?, ?, ?, ?)"; // Quadrantes
		
		Connection con = conexao.PegarConexao();
		PreparedStatement comando = con.prepareStatement(sql);
		comando.setString(argumento, arquivo);
			
		for(Planeta p: planetas) {
			if(p == null) continue;
			
			comando.setInt(argumento + 1, p.colisoes[0]);
			comando.setInt(argumento + 2, p.colisoes[1]);
			comando.setInt(argumento + 3, p.velocidade);
			comando.setInt(argumento + 4, (int) Math.floor(p.pegarHoras()));
			comando.setInt(argumento + 5, (int) Math.floor(p.pegarAnos()));
			
			argumento += 5;
		}
			
		int[] quadrantesBug = plano.PegarModificadoresPorQuadrante(true);
		int[] quadrantesDev = plano.PegarModificadoresPorQuadrante(false);
			
		for(int bugs: quadrantesBug) {
			argumento++;
			comando.setInt(argumento, bugs);
		}
			
		for(int devs: quadrantesDev) {
			argumento++;
			comando.setInt(argumento, devs);
		}
			
		comando.executeUpdate();
		conexao.Desconectar();
	}
		
}
