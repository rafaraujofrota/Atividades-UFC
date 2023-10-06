import java.util.Scanner;

import classes.Planeta;
import classes.Plano;
import controllers.GerenciadorSimulacao;
import utils.Observador;

public class Javalar {
	public static Scanner Entrada = new Scanner(System.in);
	
	public static void criarPlanetas(GerenciadorSimulacao controle) throws Exception {
		Planeta[] lista = new Planeta[7];
		
		// Definindo os dados básicos dos planetas de acordo com a ordem
		// Se alguma posição do Array for nula aquela posição no plano ficará vazia
		lista[0] = new Planeta("Python", 'P', 4, 24, "Descricao do Python");
		lista[1] = new Planeta("Javascript", 'J', 3, 10, "Descricao do Javascript");
		lista[2] = new Planeta("Ruby", 'R', 2, 48, "Descricao do Ruby");
		lista[3] = new Planeta("PHP", 'H', 2, 60, "Descricao do PHP");
		lista[4] = new Planeta("C#", '#', 1, 4, "Descricao do C#");
		lista[5] = new Planeta("C++", '+', 2, 0.5, "Descricao do C++");
		lista[6] = new Planeta("C", 'C', 10, 1, "Descricao do C");
		
		controle.DefinirPlanetas(lista);
	}

	public static void main(String[] args) throws Exception {	
		Plano Simulacao = new Plano(7);
		GerenciadorSimulacao Gerenciador = new GerenciadorSimulacao(Simulacao);
		criarPlanetas(Gerenciador);
		
		Observador Dados = new Observador(Gerenciador.PegarPlanetasAtivos());
		
		boolean acabou = false;
		
		System.out.println("************************");
		System.out.println("* BEM-VINDO AO JAVALAR *");
		System.out.println("************************");
		
		while(!acabou) {
			System.out.println("Digite oque deseja fazer");
			System.out.println("1 = Passar Tempo");
			System.out.println("2 = Adicionar Glitchs ou Devs");
			System.out.println("3 = Ver dados e estatisticas");
			System.out.println("Outro = Sair");
			
			int opcao = Entrada.nextInt();
			
			if(opcao == 1) {
				
				System.out.println("Diga quanto tempo quer passar: ");
				int tempo = Entrada.nextInt();
				
				Dados.imprimirHoras(tempo, true);
				Gerenciador.PassaTempo(tempo);
				
			} else if(opcao == 2) {
				
				System.out.println("Diga quantos Glitchs: ");
				int nGlitchs = Entrada.nextInt();
				System.out.println("Diga quantos Devs: ");
				int nDevs = Entrada.nextInt();
				
				Gerenciador.AdicionarAstro(nGlitchs, nDevs);
				
			} else if(opcao == 3) {
				
				boolean estaAlinhado = Dados.estaAlinhado(Simulacao.PegarCentro());
				Dados.imprimirVelocidade(false, true);
				Dados.imprimirDistancias();
				Dados.imprimirHemisferio(Simulacao.PegarCentro());
				System.out.println("\nEstao Alinhados? " + (estaAlinhado ? "Sim" : "Nao"));
				
			} else acabou = true;
			
			System.out.println(acabou ? "\nDados Finais" : "\nImagem Atual");
			Simulacao.Renderizar();
			System.out.println();
		}
		
		System.out.println("Unidades Totais: " + Gerenciador.PegarUnidadesTotais());
		System.out.println("Instantes Solicitados: " + Gerenciador.PegarNumeroExecucoes());
		Dados.imprimirRelatorioFinal();
	}

}
