package controllers;

import java.util.ArrayList;

import classes.Planeta;
import utils.Vetor;

public final class Observador {
	private Planeta[] lista;
	
	public Observador(Planeta[] lista) {
		this.lista = lista;
	}
	
	public Planeta[] pegarPlanetasVivos() {
		ArrayList<Planeta> vivos = new ArrayList<>();
		
		for(Planeta p: lista) {
			if(p == null) continue;	
			if(p.velocidade > 0) vivos.add(p); 
		}
		
		Planeta[] vivosArray = new Planeta[vivos.size()];
		return vivos.toArray(vivosArray);
	}
	
	public void imprimirHoras(int tempo, boolean apenasVivos) {
		Planeta[] planetas = apenasVivos ? pegarPlanetasVivos() : lista;
		
		for(Planeta p : planetas) {
			if(p == null) continue;
			
			int dias =  (int) Math.floor((p.pegarRotacao() * tempo) / 24);
			int horas = (int) ((p.pegarRotacao() * tempo) % 24);
			System.out.println("Em " + p.pegarNome() + "Se passou " + dias + " dias e " + horas + " horas");
			
			double voltas = (p.velocidade * tempo) / p.pegarMovimentosVolta();
			System.out.println(p.pegarNome() + "Passou " + voltas + " anos / voltas");
		}
	}
	
	public void imprimirVelocidade() {	
		System.out.println("\nVelocidades");
		for(Planeta p : pegarPlanetasVivos()) {
			System.out.println(p.pegarNome() + " = " + p.velocidade);
		}
		System.out.println();
	}
	
	public void imprimirHemisferio(int centro) {
		ArrayList<String> norte = new ArrayList<>();
		ArrayList<String> sul = new ArrayList<>();
		
		for(Planeta p : pegarPlanetasVivos()) {	
			Vetor posicao = p.pegarPosicao();
			
			if(posicao.y < centro) norte.add(p.pegarNome());
			else if(posicao.y > centro) sul.add(p.pegarNome());
		}
		
		System.out.print("Planetas no Norte: ");
		if(norte.size() > 1) {
			for (int i = 0; i < norte.size(); i++) {
	            System.out.print(norte.get(i));
	            if (i < norte.size() - 1) System.out.print(", ");
	        }
		} else System.out.print("Nenhum");
        
        System.out.print("\nPlanetas no Sul: ");
        if(sul.size() > 1) {
        	for (int i = 0; i < sul.size(); i++) {
                System.out.print(sul.get(i));
                if (i < sul.size() - 1) System.out.print(", ");
            }
        } else System.out.print("Nenhum");
        
	}
	
	public boolean estaAlinhado(int centro) {
		Planeta[] planetas = pegarPlanetasVivos();
		
		int total = planetas.length;
		if(total == 0) return false;
		
		int alinhadosX = 0;
		int alinhadosY = 0;
		
		for(Planeta p: planetas) {
			Vetor pos = p.pegarPosicao();
			
			if(pos.x == centro) alinhadosX++;
			else if(pos.y == centro) alinhadosY++;
		}
		
		if(alinhadosX == total || alinhadosY == total) return true;
		
		int alinhadosDiagonalP = 0;
		int alinhadosDiagonalS = 0;
		
		for(Planeta p: lista) {
			Vetor pos = p.pegarPosicao();
			
			if(pos.x == pos.y) alinhadosDiagonalP++;
			else if(pos.x + pos.y == total - 1) alinhadosDiagonalS++;
		}
		
		if(alinhadosDiagonalP == total || alinhadosDiagonalS == total) return true;
		
		return false;
	}
	
	public void imprimirDistancias() {
		
		Planeta[] planetas = pegarPlanetasVivos();
		
		if(planetas.length < 2) {
			System.out.println("Poucos Planetas para checar");
			return;
		}
		
		for(int i = 0; i < planetas.length - 1; i++) {
			System.out.println("De " + planetas[i].pegarNome() + " Ate " + planetas[i + 1].pegarNome());
			
			Vetor posAtual = planetas[i].pegarPosicao();
			Vetor posSeguinte = planetas[i + 1].pegarPosicao();
			
			System.out.println("Area = " + Vetor.AreaQuadrado(posAtual, posSeguinte));
			System.out.println("Distancia = " + Vetor.Distancia(posAtual, posSeguinte));
		}
		
		System.out.println();
		
		if(planetas.length < 3) {
			System.out.println("Nao eh possivel formar um Poligono com os Planetas Sobreviventes");
		} else {
			Vetor[] vertices = new Vetor[planetas.length];
			
			for(int i = 0; i < planetas.length; i++) {
				vertices[i] = planetas[i].pegarPosicao();
			}
			System.out.println("Area do Poligono = " + Vetor.AreaPoligonoGauss(vertices));
		}
		
	}	
}
