import java.util.Scanner;

import classes.Planeta;
import classes.Plano;
import controllers.GerenciadorSimulacao;

public class Javalar {
	public static Scanner Entrada = new Scanner(System.in);
	
	public static void criarPlanetas(GerenciadorSimulacao controle) {
		Planeta[] lista = new Planeta[7];
		// Definindo os dados básicos dos planetas de acordo com a ordem
		lista[0] = new Planeta("Python", 'P', 4, 24, "Descricao do Python");
		lista[1] = new Planeta("Javascript", 'J', 3, 10, "Descricao do Javascript");
		lista[2] = new Planeta("Ruby", 'R', 2, 48, "Descricao do Ruby");
		lista[3] = new Planeta("PHP", 'H', 2, 60, "Descricao do PHP");
		lista[4] = new Planeta("C#", '#', 1, 4, "Descricao do C#");
		lista[5] = new Planeta("C++", '+', 2, 0.5, "Descricao do C++");
		lista[6] = new Planeta("C", 'C', 10, 1, "Descricao do C");
		
		controle.DefinirPlanetas(lista);
	}

	public static void main(String[] args) {
		Plano Simulacao = new Plano(7);
		GerenciadorSimulacao Gerenciador = new GerenciadorSimulacao(Simulacao);
		criarPlanetas(Gerenciador);
		
		System.out.println("Diga quanto tempo quer passar: ");
		int tempo = Entrada.nextInt();
		Gerenciador.AdicionarAstro(100, 100);
		Gerenciador.PassaTempo(tempo);
		
		
		Simulacao.Renderizar();
	}

}
