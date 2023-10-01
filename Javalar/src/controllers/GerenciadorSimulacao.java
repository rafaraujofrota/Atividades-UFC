package controllers;

import java.util.Random;

import classes.Planeta;
import classes.Plano;
import utils.Astro;
import utils.Vetor;

public class GerenciadorSimulacao {
	private Planeta[] planetasAtivos;
	private Plano plano;
	
	// Dados sobre os planetas
	private int[] distanciaPercorrida;
	private int[] tempoSobrevivido;
	private int[] colisoesGlitchs;
	private int[] colisoesDevs;
	
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
	
	public int PegarNumeroExecucoes() {
		return numeroExecucoes;
	}
	
	public int[] PegarColisoesDevs() {
		return colisoesDevs;
	}
	
	public int[] PegarColisoesGlitchs() {
		return colisoesGlitchs;
	}
	
	public Planeta[] PegarPlanetasAtivos() {
		return planetasAtivos;
	}
	
	public int[] PegarVelocidadesMedia() {
		int[] velocidades = new int[planetasAtivos.length];
		
		for(int i = 0; i < velocidades.length; i++) {
			int tempo = tempoSobrevivido[i];
			velocidades[i] = tempo > 0 ? distanciaPercorrida[i] / tempo : 0;
		}
		
		return velocidades;
	}
	
	public void DefinirPlanetas(Planeta[] lista) {
		int espacos = plano.PegarCentro();
		
		if(lista.length > espacos || lista == null) {
			System.out.println("Tem mais planetas que deveria ou eh nulo. Max: " + espacos);
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
			
			colisoesDevs = new int[lista.length];
			colisoesGlitchs = new int[lista.length];
			tempoSobrevivido = new int[lista.length];
			distanciaPercorrida = new int[lista.length];
		}
	}
	
	public void PassaTempo(int tempo) {
		if(tempo < 0) {
			System.out.println("Tempo tem que ser maior ou igual 0");
			return;
		}
		
		numeroExecucoes++;
		unidadesTotais += tempo;
		
		if(tempo == 0) return;
		
		for(int i = 0; i < planetasAtivos.length; i++) {
			Planeta p = planetasAtivos[i];
			
			if(p == null || p.velocidade <= 0) continue;
			
			// Atualizar Dados
				distanciaPercorrida[i] += (p.velocidade * tempo);
				tempoSobrevivido[i] += tempo;
			//
			
			plano.Remover(p.pegarPosicao());
			Vetor novaPosicao = p.PegarProximaPosicao(tempo);
			Astro AstroVelho = plano.PegarAstro(novaPosicao);
			
			if(AstroVelho != null) {
				Enum<Astro.categorias> tipo = AstroVelho.pegarTipo();
				
				if(tipo == Astro.categorias.DEV) {
					p.velocidade++;
					colisoesDevs[i] += 1;
				};
				if(tipo == Astro.categorias.GLITCH) {
					p.velocidade--;
					colisoesGlitchs[i] += 1;
				}
				
				System.out.println(p.pegarNome() + " Teve a velocidade alterada para " + p.velocidade);
			}
			
			if(p.velocidade <= 0) {
				System.out.println(p.pegarNome() + " Foi jogar no Vasco");
				plano.Remover(novaPosicao);
			} else {
				p.definirPosicao(novaPosicao);
				plano.Adicionar(p);
			}
			
		}
	}
	
	public void AdicionarAstro(int nGlitchs, int nDevs) {
		if(nGlitchs < 0 || nDevs < 0) {
			System.out.println("Não é possível adicionar quantidade negativa");
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
			
			System.out.println("GLITCH CRIADO NA POSICAO " + vet.PegarTextoVetor());
		}
		
		for(int i = 0; i < nDevs; i++) {
			int x = Sorteador.nextInt(tamanho);
			int y = Sorteador.nextInt(tamanho);
			
			Astro Dev = new Astro(Astro.categorias.DEV, '$');
			Vetor vet = new Vetor(x, y);
			Dev.definirPosicao(vet);
			plano.Adicionar(Dev);
			
			System.out.println("DEV CRIADO NA POSICAO " + vet.PegarTextoVetor());
		}
	}
}
