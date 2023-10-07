import java.util.Scanner;

import classes.Planeta;
import classes.Plano;
import controllers.GerenciadorSimulacao;
import utils.Observador;

public class Javalar {
	public static Scanner Entrada = new Scanner(System.in);
	
	public static void criarPlanetas(GerenciadorSimulacao controle) throws Exception {
		Planeta[] lista = new Planeta[7];
		
		// Definindo os dados b�sicos dos planetas de acordo com a ordem
		// Se alguma posi��o do Array for nula aquela posi��o no plano ficar� vazia
		lista[0] = new Planeta("Python", 'P', 4, 24, "Python: uma linguagem de alto n�vel simples, f�cil de ler e muito voltada para manipula��o de dados,\ntornando-a �tima para IA, mas fazendo-a mais lenta que as demais");
		lista[1] = new Planeta("Javascript", 'J', 3, 10, "Javascript: a linguagem padr�o dos navegadores e websites, � conhecida pelo formato JSON e sua tipagem fraca.\nPor ser extremamente famosa diversos frameworks s�o feitos regurlamente");
		lista[2] = new Planeta("Ruby", 'R', 2, 48, "Ruby: uma liguagem orientada a objetos com uma sintaxe extremamente �nica e que d� bastante liberdade.\nSua popularidade aumentou gra�as ao Rails, um framework para constru��o de aplicativos web");
		lista[3] = new Planeta("PHP", 'H', 2, 60, "PHP: a linguagem que revolucionou a web, tornando a cria��o de websites muito mais acess�vel.\nEla � utilizada nos servidores e foi muito �til por ser integrada junto a HTML.\nApesar de n�o ser t�o popular ela ainda � uma das mais utilizadas, principalmente junto ao Wordpress");
		lista[4] = new Planeta("C#", '#', 1, 4, "C#: a linguagem usada no framework .NET do windows, al�m de ser popular na cria��o de jogos, principalmente por conta da Engine Unity.\nFoi criada pela Microsoft inspirada no C++ e tem uma m�quina virtual semelhante a do Java chamada Common Language Runtime.");
		lista[5] = new Planeta("C++", '+', 2, 0.5, "C++: uma linguagem geral OOP, com recursos de alto e baixo n�vel, sendo uma das mais populares.\nMuitas vezes � considerado um Super conjunto de C, ou seja c�digos do C normalmente podem ser compilados em C++");
		lista[6] = new Planeta("C", 'C', 10, 1, "C: uma linguagem antiga mas ainda muito importante, respons�vel por grandes ferramentas atuais, como os Kernels do Linux, Windows e MacOS.\nTamb�m Banco de dados como MySQL e interpretadores de outras linguagem como Python.\nSua sintaxe e recursos se tornaram um padr�o para todas as outras linguagens");
		
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
			System.out.println("Digite o que deseja fazer");
			System.out.println("1 = Passar Tempo");
			System.out.println("2 = Adicionar Glitchs ou Devs");
			System.out.println("3 = Ver dados e estat�sticas");
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
				System.out.println("\nEst�o Alinhados? " + (estaAlinhado ? "Sim" : "N�o"));
				
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
