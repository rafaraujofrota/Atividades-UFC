package controllers;

import java.util.Random;

import classes.Planeta;
import classes.Plano;
import utils.Astro;
import utils.Vetor;

public class GerenciadorSimulacao {
	private Planeta[] planetasAtivos;
	private Plano plano;
	
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
	
	public Planeta[] PegarPlanetasAtivos() {
		return planetasAtivos;
	}
	
	public void DefinirPlanetas(Planeta[] lista) throws Exception {
		int espacos = plano.PegarCentro();
		
		if(lista.length > espacos || lista == null) {
			throw new Exception("Tem mais planetas que deveria ou é nulo. Max: " + espacos);
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
			
			System.out.println("GLITCH CRIADO NA POSIÇÃO " + vet.PegarTextoVetor());
		}
		
		for(int i = 0; i < nDevs; i++) {
			int x = Sorteador.nextInt(tamanho);
			int y = Sorteador.nextInt(tamanho);
			
			Astro Dev = new Astro(Astro.categorias.DEV, '$');
			Vetor vet = new Vetor(x, y);
			Dev.definirPosicao(vet);
			plano.Adicionar(Dev);
			
			System.out.println("DEV CRIADO NA POSIÇÃO " + vet.PegarTextoVetor());
		}
	}
}
