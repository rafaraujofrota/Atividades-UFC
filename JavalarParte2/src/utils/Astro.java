package utils;

public class Astro {
	public enum categorias {
		GLITCH, DEV, PLANETA, ESTRELA
	}
	
	protected Vetor posicao;
	private Enum<categorias> tipo;
	private char icone;
	private String imagem;
	
	public Astro(categorias t, char c) {
		tipo = t;
		icone = c;
		posicao = new Vetor(0,0);
		
		if(t != categorias.PLANETA) {
			String arquivo = t.name().toLowerCase();
			definirImagem(arquivo);
		}
	}

	public Enum<categorias> pegarTipo() {
		return tipo;
	}

	public char pegarIcone() {
		return icone;
	}
	
	public String pegarImagem() {
		return imagem;
	}
	
	public void definirImagem(String nomeImagem) {
		this.imagem = "imagens/" + nomeImagem + ".png";
	}
	
	public Vetor pegarPosicao() {
		return posicao;
	}
	
	public void definirPosicao(Vetor vet) {
		posicao = vet;
	}
	
}
