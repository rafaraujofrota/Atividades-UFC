import java.util.Scanner;

import classes.Plano;
import controllers.GerenciadorSimulacao;
import utils.Observador;
import view.TelaPrincipal;

public class Javalar {
	public static Scanner Entrada = new Scanner(System.in);

	public static void main(String[] args) throws Exception {	
		Plano Simulacao = new Plano(7);
		GerenciadorSimulacao Gerenciador = new GerenciadorSimulacao(Simulacao);
		Gerenciador.povoarComPlanetasBasicos();
		
		Observador Dados = new Observador(Gerenciador.PegarPlanetasAtivos());
		
		boolean acabou = false;
		
		// Teste Janela
			new TelaPrincipal(Gerenciador);
		//
		
		System.out.println("************************");
		System.out.println("* BEM-VINDO AO JAVALAR *");
		System.out.println("************************");
		
		while(!acabou) {
			System.out.println("Digite o que deseja fazer");
			System.out.println("1 = Passar Tempo");
			System.out.println("2 = Adicionar Glitchs ou Devs");
			System.out.println("3 = Ver dados e estatísticas");
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
				System.out.println("\nEstão Alinhados? " + (estaAlinhado ? "Sim" : "Não"));
				
			} else acabou = true;
			
			System.out.println(acabou ? "\nDados Finais" : "\nImagem Atual");
			Simulacao.RenderizarDebug();
			System.out.println();
		}
		
		System.out.println("Unidades Totais: " + Gerenciador.PegarUnidadesTotais());
		System.out.println("Instantes Solicitados: " + Gerenciador.PegarNumeroExecucoes());
		Dados.imprimirRelatorioFinal();
	}

}
