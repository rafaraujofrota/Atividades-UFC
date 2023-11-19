import java.util.Scanner;

import classes.CachorroQuente;
import classes.Cliente;
import classes.Igredientes;
import controller.Cadastrador;
import controller.Conexao;
import controller.Venda;

public class Main {
	
	private static Scanner Entrada = new Scanner(System.in);
	private static Cadastrador Registro = new Cadastrador();

	public static void MostrarOpcoes(String texto, Enum<?>[] itens) {	
		System.out.println(texto);
		for(int i = 0; i < itens.length; i++) {
			System.out.println("-" + i + " " + itens[i].name());
		}
	}
	
	public static int ValidarOpcao(int opcao, int max) {
		if(opcao < 0 || opcao >= max) return 0;
		else return opcao;
	}
	
	public static void Comprar() {
		System.out.println("Digite seu Indentificador");
		int matricula = Entrada.nextInt();
		Cliente cliente = Registro.PegarCliente(matricula);
		
		if(cliente == null) {
			System.out.println("Indentificador inválido ou não cadastrado");
			return;
		}
		
		System.out.println("Quantos Cachorros Quentes você vai querer?");
		int quantidaDeCachorros = ValidarOpcao(Entrada.nextInt(), 100);
		if(quantidaDeCachorros == 0) return;
		
		Igredientes.Proteina[] proteinas = Igredientes.Proteina.values();
		Igredientes.Queijos[] queijos = Igredientes.Queijos.values();
		Igredientes.Adicionais[] adicionais = Igredientes.Adicionais.values();
		Igredientes.Bebidas[] bebidas = Igredientes.Bebidas.values();
		
		int[] op = {0, 0, 0};
		CachorroQuente[] Produtos = new CachorroQuente[quantidaDeCachorros];
		
		for(int i = 0; i < quantidaDeCachorros; i++) {
			MostrarOpcoes("Escolha a Proteína", proteinas);
			op[0] = ValidarOpcao(Entrada.nextInt(), proteinas.length);
			
			MostrarOpcoes("Escolha o Queijo", queijos);
			op[1] = ValidarOpcao(Entrada.nextInt(), queijos.length);

			MostrarOpcoes("Escolha os adicionais", adicionais);
			System.out.println("Digite a quantidade de Adicionais, Max: " + adicionais.length);
			int quantidadeAdicionais = ValidarOpcao(Entrada.nextInt(), adicionais.length + 1);
			
			Igredientes.Adicionais[] adicionaisEscolhidos = new Igredientes.Adicionais[quantidadeAdicionais];
			
			for(int j = 0; j < quantidadeAdicionais; j++) {
				System.out.println("Escolha a opção N" + (j + 1));
				int opcao = ValidarOpcao(Entrada.nextInt(), adicionais.length);
				adicionaisEscolhidos[j] = adicionais[opcao];
			}
			
			Produtos[i] = new CachorroQuente(proteinas[op[0]], queijos[op[1]], adicionaisEscolhidos);
		}
		
		MostrarOpcoes("Escolha as Bebidas", bebidas);
		op[2] = ValidarOpcao(Entrada.nextInt(), bebidas.length);
		
		Venda NovaVenda = new Venda(cliente, Produtos, bebidas[op[2]]);
		System.out.println("Nova Venda Efetuada, Imprimindo Recibo");
		NovaVenda.ImprimirRecibo();
	}
	
	public static void main(String[] args) {
		boolean fechar = false;
		
		Conexao teste = new Conexao();
		
		while(!fechar) {
			int numClientes = Registro.PegarTotal();
			System.out.println("Clientes cadastrados: " + numClientes);
			System.out.println("0-Fechar");
			System.out.println("1-Cadastrar");
			if(numClientes > 0) System.out.println("2-Comprar");
			
			int opcao = Entrada.nextInt();
			Entrada.nextLine();
			
			if(opcao == 0) fechar = true;
			else if(opcao == 1) Registro.Cadastrar(Entrada);
			else if(opcao == 2 && numClientes > 0) Comprar();
			else System.out.println("Opção Inválida, tente novamente");
			
		}
		
		System.out.println("Cachorros Quentes vendidos: " + Venda.cachorrosVendidos);
		System.out.println("Valor arrecadado: " + Venda.totalArrecadado());
		System.out.println("Valor descontado: " + Venda.totalDesconto());
		Venda.cachorrosPorCliente();
		Venda.bebidaMaisVendida();
		Venda.tipoMaisVendido();
	}

}
