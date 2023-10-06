package classes;

import utils.Astro;
import utils.Vetor;

public class Planeta extends Astro {
	public int velocidade;
	private Vetor inicio;
	
	// A cada unidade de tempo o planeta passa sua rotacao em horas, 24 = 1 dia do planeta
	private double rotacao;
	
	// distancia é o lado do quadrado que o planeta fará no seu movimento
	private int distancia;
	private int movimentosVolta;
	public String descricao;
	private String nome;
	
	// Atributos da simulação
	public double velocidadeMedia;
	public int distanciaPercorrida;
	public int tempoSobrevivido;
	public int[] colisoes;
	
	public Planeta(String nome, char icone, int velocidade, double rotacao, String descricao) {
		super(Astro.categorias.PLANETA, icone);
		
		this.inicio = this.posicao;
		this.velocidade = velocidade;
		this.rotacao = rotacao;
		this.nome = nome;
		this.descricao = descricao;
		
		// 0 = colisoesDev, 1 = colisoesGlitch
		this.colisoes = new int[2];
	}
	
	public String pegarNome() {
		return this.nome;
	}
	
	public double pegarRotacao() {
		return this.rotacao;
	}
	
	public int pegarMovimentosVolta() {
		return this.movimentosVolta;
	}
	
	public void definirDistancia(int distancia) {
		this.distancia = Math.abs(distancia); 
		this.movimentosVolta = (this.distancia * 2) + (2 * (this.distancia - 2));
	}
	
	public void definirInicio(Vetor vet) {
		this.inicio = vet;
		this.posicao = vet;
	}
	
	public Vetor PegarProximaPosicao(int tempo) {
		if(tempo <= 0 ) return this.posicao;
		
		int xBase = inicio.x;
		int yBase = inicio.y;
		
		// Distancia do centro até um canto;
		int dCentro = (distancia - 1) / 2;
		
		// checar se deu alguma volta para deixar o programa mais rápido
		int movimento = (tempo * velocidade) % (movimentosVolta);
		
		// Pega o y do lado de cima e baixo, e o x do lado esquerdo e direito;
		// Lembrar que o y = 0 é o topo, entao para abaixar precisa somar
		int[] cantos = {yBase, xBase - dCentro, yBase + distancia - 1, xBase + dCentro};
		
		int x = posicao.x;
		int y = posicao.y;
		
		for(int i = 0; i < movimento; i++) {
			if(y == cantos[0]) {
				if(x == cantos[1]) y++;
				else x--;
			}
			else if(x == cantos[1]) {
				if(y == cantos[2]) x++;
				else y++;
			}
			else if(y == cantos[2]) {
				if(x == cantos[3]) y--;
				else x++;
			}
			else y--;
		}
		
		return new Vetor(x, y);
	}
}
