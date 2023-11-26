package classes;

import java.util.ArrayList;

import utils.PlanetaDadosRelatorio;

public class Relatorio {
	public static ArrayList<Relatorio> ListaRelatorios;
	
	public PlanetaDadosRelatorio[] dadosPlaneta;
	public int[] bugsPorQuadrante;
	public int[] devsPorQuadrante;
	
	public int id;
	public String nomeAluno;
	public String nomeArquivo;
	public String matricula;
	
	public Relatorio(int id, String nome, String matricula) {
		this.id = id;
		this.nomeAluno = nome;
		this.matricula = matricula;
	}

}
