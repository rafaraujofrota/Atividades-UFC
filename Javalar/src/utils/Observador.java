package utils;

import java.util.ArrayList;

import classes.Planeta;

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
			
			double voltas;
			int tempoPassado = tempo <= 0 ? p.tempoSobrevivido : tempo;
			
			double horas = p.pegarRotacao() * tempoPassado;
			int dias = 0;
			
			while(horas >= 24) {
				horas -= 24;
				dias++;
			}
			
			System.out.print("Em " + p.pegarNome() + " se passou " + dias + " dias e " + horas + " horas. ");
				
			if(tempo > 0) voltas = (double)(p.velocidade * tempoPassado) / p.pegarMovimentosVolta();
			else voltas = (double) p.distanciaPercorrida / p.pegarMovimentosVolta();
			
			double voltaArredondada = Math.round(voltas * 100.0)/100.0;
			
			System.out.println("Foram " + voltaArredondada + " voltas (anos)");
		}
		System.out.println();
	}
	
	public void imprimirVelocidade(boolean mostrarRotacao, boolean apenasVivos) {	
		System.out.println("\nVelocidades");
		
		Planeta[] planetas = apenasVivos ? pegarPlanetasVivos() : lista;
		
		for(Planeta p : planetas) {
			if(p == null) continue;
			
			System.out.print(p.pegarNome() + " => Velocidade Atual = " + p.velocidade);
			System.out.print(", Media = " + p.velocidadeMedia);
			if(mostrarRotacao) {
				System.out.print(", Rotacao = " + p.pegarRotacao());
			}
			System.out.println();
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
		if(norte.size() > 0) {
			for (int i = 0; i < norte.size(); i++) {
	            System.out.print(norte.get(i));
	            if (i < norte.size() - 1) System.out.print(", ");
	        }
		} else System.out.print("Nenhum");
        
        System.out.print("\nPlanetas no Sul: ");
        if(sul.size() > 0) {
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
		
		Vetor[] vertices = new Vetor[planetas.length];
		
		for(int i = 0; i < planetas.length; i++) {
			vertices[i] = planetas[i].pegarPosicao();
		}
		
		for(int i = 0; i < planetas.length - 1; i++) {
			System.out.println("De " + planetas[i].pegarNome() + " Ate " + planetas[i + 1].pegarNome());
			
			System.out.println("Area = " + Vetor.AreaQuadrado(vertices[i], vertices[i + 1]));
			System.out.println("Distancia = " + Vetor.Distancia(vertices[i], vertices[i + 1]));
		}
		
		System.out.println();
		
		if(planetas.length < 3) {
			System.out.println("Nao eh possivel formar um Poligono com os Planetas Disponiveis");
		} else System.out.println("Area do Poligono = " + Vetor.AreaPoligonoGauss(vertices));
		
	}	
	
	public void imprimirRelatorioFinal() {
		imprimirVelocidade(true, false);
		System.out.println("Dados sobre a Rotacao\n");
		imprimirHoras(0, false);
		
		ArrayList<Planeta> mortos = new ArrayList<>();
		
		System.out.println();
		for(Planeta p : lista) {
			if(p == null) continue;
			if(p.velocidade <= 0) mortos.add(p);
			
			System.out.println(p.descricao);
			System.out.println("Colisoes Dev: " + p.colisoes[0]);
			System.out.println("Colisoes Glitchs: " + p.colisoes[1]);
			System.out.println();
		}
		
		System.out.println("Planetas Destruidos");
		if(mortos.size() > 0) {
			for(Planeta p : mortos) {
				System.out.println(p.pegarNome());
			}
		} else System.out.println("Nenhum");
		
	}
}
