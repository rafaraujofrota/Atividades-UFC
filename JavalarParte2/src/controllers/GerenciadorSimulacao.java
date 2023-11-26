package controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

import classes.Planeta;
import classes.Plano;
import utils.Astro;
import utils.Vetor;

public class GerenciadorSimulacao {
	private Planeta[] planetasAtivos;
	private Plano plano;
	
	private BufferedReader ArquivoInstantes;
	private String nomeArquivo;
	private String linhaAtual;
	
	// s�o usados somente no modo console
	private int unidadesTotais;
	private int numeroExecucoes;
	
	public GerenciadorSimulacao(Plano plano) {
		this.plano = plano;
		unidadesTotais = 0;
		numeroExecucoes = 0;
	}
	
	public int PegarUnidadesTotais() {
		return unidadesTotais;
	}
	
	public Plano PegarPlano() {
		return plano;
	}
	
	public String PegarNomeArquivo() {
		return nomeArquivo;
	}
	
	public void DefinirPlano(Plano plano) {
		this.plano = plano;
	}
	
	public int PegarNumeroExecucoes() {
		return numeroExecucoes;
	}
	
	public Planeta[] PegarPlanetasAtivos() {
		return planetasAtivos;
	}
	
	public void DefinirPlanetas(Planeta[] lista) throws Exception {
		int espacos = plano.PegarCentro();
		
		if(lista.length > espacos || lista == null) {
			throw new Exception("Tem mais planetas que deveria ou � nulo. Max: " + espacos);
		} else {
			int centro = plano.PegarCentro();
			int y = centro;
			
			for(int i = 0; i < lista.length; i++) {
				y--;
				if(lista[i] == null) continue;
				
				lista[i].definirInicio(new Vetor(centro, y));
				lista[i].definirDistancia(3 + (i * 2));
				plano.Adicionar(lista[i]);
			}
			
			planetasAtivos = lista; 
		}
	}
	
	public void povoarComPlanetasBasicos() {
		Planeta[] lista = new Planeta[7];
		
		// Definindo os dados b�sicos dos planetas de acordo com a ordem
		// Se alguma posi��o do Array for nula aquela posi��o no plano ficar� vazia
		lista[0] = new Planeta("Python", 'P', 4, 24, "Python: uma linguagem de alto n�vel simples, f�cil de ler e muito voltada para manipula��o de dados,\ntornando-a �tima para IA, mas fazendo-a mais lenta que as demais");
		lista[1] = new Planeta("Javascript", 'J', 3, 10, "Javascript: a linguagem padr�o dos navegadores e websites, � conhecida pelo formato JSON e sua tipagem fraca.\nPor ser extremamente famosa diversos frameworks s�o feitos regurlamente");
		lista[2] = new Planeta("Ruby", 'R', 2, 48, "Ruby: uma liguagem orientada a objetos com uma sintaxe extremamente �nica e que d� bastante liberdade.\nSua popularidade aumentou gra�as ao Rails, um framework para constru��o de aplicativos web");
		lista[3] = new Planeta("PHP", 'H', 2, 60, "PHP: a linguagem que revolucionou a web, tornando a cria��o de websites muito mais acess�vel.\nEla � utilizada nos servidores e foi muito �til por ser integrada junto a HTML.\nApesar de n�o ser t�o popular ela ainda � uma das mais utilizadas, principalmente junto ao Wordpress");
		lista[4] = new Planeta("CSharp", '#', 1, 4, "C#: a linguagem usada no framework .NET do windows, al�m de ser popular na cria��o de jogos, principalmente por conta da Engine Unity.\nFoi criada pela Microsoft inspirada no C++ e tem uma m�quina virtual semelhante a do Java chamada Common Language Runtime.");
		lista[5] = new Planeta("CMais", '+', 2, 0.5, "C++: uma linguagem geral OOP, com recursos de alto e baixo n�vel, sendo uma das mais populares.\nMuitas vezes � considerado um Super conjunto de C, ou seja c�digos do C normalmente podem ser compilados em C++");
		lista[6] = new Planeta("C", 'C', 10, 1, "C: uma linguagem antiga mas ainda muito importante, respons�vel por grandes ferramentas atuais, como os Kernels do Linux, Windows e MacOS.\nTamb�m Banco de dados como MySQL e interpretadores de outras linguagem como Python.\nSua sintaxe e recursos se tornaram um padr�o para todas as outras linguagens");
		
		try {
			DefinirPlanetas(lista);
		} catch (Exception e) {
			System.out.println("O Plano atual n�o suporta essa lista de planetas");
		}
	}
	
	public void PassaTempo(int tempo) {
		if(tempo < 0) {
			System.out.println("Tempo tem que ser maior ou igual a 0");
			return;
		}
		
		numeroExecucoes++;
		unidadesTotais += tempo;
		
		if(tempo == 0) return;
		
		for(Planeta p: planetasAtivos) {
			if(p == null || p.velocidade <= 0) continue;
			
			// Atualizar Dados
				p.distanciaPercorrida += (p.velocidade * tempo);
				p.tempoSobrevivido += tempo;
				
				if(p.tempoSobrevivido <= 0) p.velocidadeMedia = 0;
				else p.velocidadeMedia = (double) p.distanciaPercorrida / p.tempoSobrevivido;
			//
			
			plano.Remover(p.pegarPosicao());
			Vetor novaPosicao = p.PegarProximaPosicao(tempo);
			Astro AstroVelho = plano.PegarAstro(novaPosicao);
			
			if(AstroVelho != null) {
				Enum<Astro.categorias> tipo = AstroVelho.pegarTipo();
				
				if(tipo == Astro.categorias.DEV) {
					p.velocidade++;
					p.colisoes[0] += 1;
				};
				if(tipo == Astro.categorias.GLITCH) {
					p.velocidade--;
					p.colisoes[1] += 1;
				}
				
				System.out.println(p.pegarNome() + " Teve a velocidade alterada para " + p.velocidade);
			}
			
			if(p.velocidade <= 0) {
				System.out.println(p.pegarNome() + " Faliu");
				plano.Remover(novaPosicao);
			} else {
				p.definirPosicao(novaPosicao);
				plano.Adicionar(p);
			}
			
		}
	}
	
	
	public void DefinirArquivo(String CaminhoArquivo) {
		try {
			this.ArquivoInstantes = new BufferedReader(new FileReader(CaminhoArquivo));
			this.nomeArquivo = new File(CaminhoArquivo).getName();
			ArquivoInstantes.readLine(); // Esse aqui � s� para pular a primeira linha;
			
			this.linhaAtual = ArquivoInstantes.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void ResetarPlano() {
		try {
			DefinirPlano(new Plano(7));
			povoarComPlanetasBasicos();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void PassaTempoArquivo() throws Exception {
		if(ArquivoInstantes == null) throw new Exception("Sem Arquivo"); 
		if(linhaAtual == null) throw new Exception("Fim do Arquivo");
		
		int qntPlanetas = planetasAtivos.length;
		
		String[] instantes = linhaAtual.split(",");
			
		for(int coluna = 1; coluna < instantes.length; coluna++) {
			
			int tempo = Integer.parseInt(instantes[coluna]);
			if(tempo == 0) continue;
			
			// Nessa parte o nome tempo n�o faz tanto sentido, j� que � a quantidade de Bugs/Devs
			if(coluna == qntPlanetas + 1) AdicionarAstro(tempo, 0);
			else if (coluna == qntPlanetas + 2) AdicionarAstro(0, tempo);
			
			if(coluna > qntPlanetas) continue;
			
			Planeta p = planetasAtivos[coluna - 1];
			if(p == null || p.velocidade <= 0) continue;
			
			// Atualizar Dados
			p.distanciaPercorrida += (p.velocidade * tempo);
			p.tempoSobrevivido += tempo;
				
			if(p.tempoSobrevivido <= 0) p.velocidadeMedia = 0;
			else p.velocidadeMedia = (double) p.distanciaPercorrida / p.tempoSobrevivido;
			//
			
			plano.Remover(p.pegarPosicao());
			Vetor novaPosicao = p.PegarProximaPosicao(tempo);
			Astro AstroVelho = plano.PegarAstro(novaPosicao);
			
			if(AstroVelho != null) {
				Enum<Astro.categorias> tipo = AstroVelho.pegarTipo();
				
				if(tipo == Astro.categorias.DEV) {
					p.velocidade++;
					p.colisoes[0] += 1;
				};
				if(tipo == Astro.categorias.GLITCH) {
					p.velocidade--;
					p.colisoes[1] += 1;
				}
				
			}
			
			if(p.velocidade <= 0) {
				plano.Remover(novaPosicao);
			} else {
				p.definirPosicao(novaPosicao);
				plano.Adicionar(p);
			}
		}
		
		linhaAtual = ArquivoInstantes.readLine();
	}
	
	public void AdicionarAstro(int nGlitchs, int nDevs) {
		if(nGlitchs < 0 || nDevs < 0) {
			System.out.println("N�o � poss�vel adicionar quantidade negativa");
			return;
		}

		Random Sorteador = new Random();
		int tamanho = plano.PegarTamanho();
		
		for(int i = 0; i < nGlitchs; i++) {
			int x = Sorteador.nextInt(tamanho);
			int y = Sorteador.nextInt(tamanho);
			
			Astro Glitch = new Astro(Astro.categorias.GLITCH, 'X');
			Vetor vet = new Vetor(x, y);
			Glitch.definirPosicao(vet);
			plano.Adicionar(Glitch);
			
			//System.out.println("GLITCH CRIADO NA POSI��O " + vet.PegarTextoVetor());
		}
		
		for(int i = 0; i < nDevs; i++) {
			int x = Sorteador.nextInt(tamanho);
			int y = Sorteador.nextInt(tamanho);
			
			Astro Dev = new Astro(Astro.categorias.DEV, '$');
			Vetor vet = new Vetor(x, y);
			Dev.definirPosicao(vet);
			plano.Adicionar(Dev);
			
			//System.out.println("DEV CRIADO NA POSI��O " + vet.PegarTextoVetor());
		}
	}
}
