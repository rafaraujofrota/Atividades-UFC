package utils;

public class Astro {
	public enum categorias {
		GLITCH, DEV, PLANETA, ESTRELA
	}
	
	protected Vetor posicao;
	private Enum<categorias> tipo;
	private char icone;
	
	public Astro(categorias t, char c) {
		tipo = t;
		icone = c;
		posicao = new Vetor(0,0);
	}

	public Enum<categorias> pegarTipo() {
		return tipo;
	}

	public char pegarIcone() {
		return icone;
	}
	
	public Vetor pegarPosicao() {
		return posicao;
	}
	
	public void definirPosicao(Vetor vet) {
		posicao = vet;
	}
	
}
