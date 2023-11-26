package classes;

import utils.Astro;
import utils.Vetor;

public class Plano {
	
	private Astro[][] pontos;
	private int centro;
	private int tamanho;
	
	public Plano(int quantidadeDePlanetas) throws Exception {
		if(quantidadeDePlanetas < 1) throw new Exception("Valor mínimo é 1");
		// O Mais 1 é para poder botar a Estrela
		int tamanho = (quantidadeDePlanetas * 2) + 1;
		this.tamanho = tamanho;
		// Posição central da Matriz é igual a quantidade porque array começa do 0
		centro = quantidadeDePlanetas;
		pontos = new Astro[tamanho][tamanho];
		
		Astro Estrela = new Astro(Astro.categorias.ESTRELA, '*');
		Estrela.definirPosicao(new Vetor(centro, centro));
		
		Adicionar(Estrela);
	}
	
	public int[] PegarModificadoresPorQuadrante(boolean bug) {
		Enum<Astro.categorias> tipo = bug ? Astro.categorias.GLITCH : Astro.categorias.DEV;
		int[] quantidade = new int[4];
		
		// Quadrante 1
		for(int i = 0; i < centro; i++) {
			for(int j = 0; j < centro; j++) {
				if(pontos[i][j] != null && pontos[i][j].pegarTipo() == tipo) quantidade[0] += 1;
			}
		}
		
		// Quadrante 2
		for(int i = 0; i < centro; i++) {
			for(int j = centro + 1; j < tamanho; j++) {
				if(pontos[i][j] != null && pontos[i][j].pegarTipo() == tipo) quantidade[1] += 1;		
			}
		}
		
		// Quadrante 3
		for(int i = centro + 1; i < tamanho; i++) {
			for(int j = 0; j < centro; j++) {
				if(pontos[i][j] != null && pontos[i][j].pegarTipo() == tipo) quantidade[2] += 1;		
			}
		}
				
		// Quadrante 4
		for(int i = centro + 1; i < tamanho; i++) {
			for(int j = centro + 1; j < tamanho; j++) {
				if(pontos[i][j] != null && pontos[i][j].pegarTipo() == tipo) quantidade[3] += 1;		
			}
		}
			
		return quantidade;
	}
	
	// nessas funcoes o y vem antes do x porque pontos é uma Matriz
	// então em pontos[i][j] i = linha e j = coluna
	
	public void Adicionar(Astro novoAstro) {
		Vetor vet = novoAstro.pegarPosicao();
		Astro antigo = PegarAstro(vet);
		
		// Se estão livre então adiciona sem checar
		if(antigo == null) {
			pontos[vet.y][vet.x] = novoAstro;
			return;
		}
		
		Enum<Astro.categorias> tipoNovo = novoAstro.pegarTipo();
		Enum<Astro.categorias> tipoAntigo = antigo.pegarTipo();
		
		if(tipoAntigo == Astro.categorias.PLANETA || tipoAntigo == Astro.categorias.ESTRELA) {
			return;
		}
		
		if(tipoNovo == Astro.categorias.PLANETA || tipoNovo == Astro.categorias.ESTRELA) {
			pontos[vet.y][vet.x] = novoAstro;
		} 
		
		// Bugs e devs só podem ser adicionados se o espaço for null
		// Planetas e Estrelas não podem ser sobescritos
	}
	
	public Astro PegarAstro(Vetor vet) {
		return pontos[vet.y][vet.x];
	}
	
	public void Remover(Vetor vet) {
		pontos[vet.y][vet.x] = null;
	}
	
	public int PegarCentro() {
		return centro;
	}
	
	public void RenderizarDebug() {	
		String Resetar = "\u001B[0m";  // Resetar para a cor padrão
	    String Vermelho = "\u001B[31m";  
	    String Verde = "\u001B[32m"; 
	    String Amarelo = "\u001B[33m";
	    String Azul = "\u001B[34m"; 
		
		for (int i = 0; i < pontos.length; i++) {
            for (int j = 0; j < pontos[i].length; j++) {
                if (pontos[i][j] != null) {
                	
                	// Cores. Pode dar problemas em algums terminais antigos
                	if(pontos[i][j].pegarTipo() == Astro.categorias.DEV) {
                		System.out.print(Verde);
                	}
                	if(pontos[i][j].pegarTipo() == Astro.categorias.GLITCH) {
                		System.out.print(Vermelho);
                	}
                	if(pontos[i][j].pegarTipo() == Astro.categorias.ESTRELA) {
                		System.out.print(Azul);
                	}
                	if(pontos[i][j].pegarTipo() == Astro.categorias.PLANETA) {
                		System.out.print(Amarelo);
                	}
                	
                    System.out.print(pontos[i][j].pegarIcone() + " " + Resetar);
                } 
                else System.out.print("- ");
            }      
            System.out.println(); 
        }	
	}
	
	public int PegarTamanho() {
		return tamanho;
	}
}
