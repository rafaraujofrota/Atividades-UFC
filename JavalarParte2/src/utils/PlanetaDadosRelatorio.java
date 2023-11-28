package utils;

public class PlanetaDadosRelatorio {
	public int colisaoBugs;
	public int colisaoDevs;
	public int velocidade;
	public int horasTotais;
	public int anosTotais;
	public String nome;
	
	// Nome que está no banco
	public static final String[] planetasDisponiveis = {"python", "javascript", "ruby", "php", "csharp", "cmais", "c"};
	
	// Nome comum
	public static final String[] nomesPlanetas = {"Python", "Javascript", "Ruby", "PHP", "C#", "C++", "C"};
	
	public PlanetaDadosRelatorio(String nome) {
		this.nome = nome;
	}
}
