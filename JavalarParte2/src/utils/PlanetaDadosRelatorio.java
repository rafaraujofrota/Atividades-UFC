package utils;

public class PlanetaDadosRelatorio {
	public int colisaoBugs;
	public int colisaoDevs;
	public int velocidade;
	public int horasTotais;
	public int anosTotais;
	public String nome;
	
	public static final String[] planetasDisponiveis = {"python", "javascript", "ruby", "php", "csharp", "cmais", "c"};
	
	public PlanetaDadosRelatorio(String nome) {
		this.nome = nome;
	}
}
