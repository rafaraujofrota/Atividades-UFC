import java.util.Scanner;

public class Main {
	
	private static Scanner Entrada = new Scanner(System.in);

	public static void MostrarOpcoes(String texto, Enum<?>[] itens) {	
		System.out.println(texto);
		for(int i = 0; i < itens.length; i++) {
			System.out.println("-" + i + " " + itens[i].name());
		}
	}
	
	// Função para corrigir problema do NextInt Pular o NextLine
	public static int ScanearInt() {
		int num = Entrada.nextInt();
		Entrada.nextLine();
		return num;
	}
	
	public static void Cadastrar() {
		System.out.println("Insira seu nome: ");
		String nome = Entrada.nextLine();
		System.out.println("Insira sua matricula: ");
		int matricula = ScanearInt();
		
		if(Aluno.PegarTotalAlunos() == 0 || Aluno.PegarAlunoPorMatricula(matricula) != null) {
			new Aluno(nome, matricula);
			System.out.println("Cadastrado com Sucesso");
		} else {
			System.out.println("Aluno já existe, tente novamente");
		}
		
	}
	
	public static int ValidarOpcao(int opcao, int max) {
		if(opcao < 0 || opcao >= max) return 0;
		else return opcao;
	}
	
	public static void Comprar() {
		// tentei usar esse metodo com Enums pois seria possível aumentar a quantidade de itens 
		// sem mudar nada no código
		
		System.out.println("Digite sua Matricula");
		int matricula = ScanearInt();
		Aluno Cliente = Aluno.PegarAlunoPorMatricula(matricula);
		
		if(Cliente == null) {
			System.out.println("Matricula inválida ou não cadastrada");
			return;
		}
		
		System.out.println("Quantos Cachorros Quentes você vai querer?");
		int quantidaDeCachorros = ValidarOpcao(ScanearInt(), 100);
		if(quantidaDeCachorros == 0) return;
		
		Enum<CachorroQuente.proteinas>[] proteinas = CachorroQuente.proteinas.values();
		Enum<CachorroQuente.queijos>[] queijos = CachorroQuente.queijos.values();
		Enum<CachorroQuente.adicionais>[] adicionais = CachorroQuente.adicionais.values();
		Enum<Extras.bebidas>[] bebidas = Extras.bebidas.values();
		
		int[] op = {0, 0, 0};
		CachorroQuente[] Produtos = new CachorroQuente[quantidaDeCachorros];
		
		for(int i = 0; i < quantidaDeCachorros; i++) {
			MostrarOpcoes("Escolha a Proteina", proteinas);
			op[0] = ValidarOpcao(ScanearInt(), proteinas.length);
			
			MostrarOpcoes("Escolha o Queijo", queijos);
			op[1] = ValidarOpcao(ScanearInt(), queijos.length);

			
			MostrarOpcoes("Escolha os adicionais", adicionais);
			System.out.println("Digite a quantidade de Adicionais, Max: " + adicionais.length);
			int quantidadeAdicionais = ValidarOpcao(ScanearInt(), adicionais.length + 1);
			
			Enum<CachorroQuente.adicionais>[] adicionaisEscolhidos = new Enum[quantidadeAdicionais];
			
			for(int j = 0; j < quantidadeAdicionais; j++) {
				System.out.println("Escolha a opção N" + (j + 1));
				int opcao = ValidarOpcao(ScanearInt(), adicionais.length);
				adicionaisEscolhidos[j] = adicionais[opcao];
			}
			
			Produtos[i] = new CachorroQuente(proteinas[op[0]], queijos[op[1]], adicionaisEscolhidos);
		}
		
		MostrarOpcoes("Escolha as Bebidas", bebidas);
		op[2] = ValidarOpcao(ScanearInt(), bebidas.length);
		Extras Bebida = new Extras(bebidas[op[2]]);
		
		Venda NovaVenda = new Venda(Cliente, Produtos, Bebida);
		System.out.println("Nova Venda Efetuada, Imprimindo Recibo");
		NovaVenda.ImprimirRecibo();
	}
	
	public static void main(String[] args) {
		boolean fechar = false;
		
		while(!fechar) {
			int numAlunos = Aluno.PegarTotalAlunos();
			System.out.println("Alunos Cadastrados: " + numAlunos);
			System.out.println("0-Fechar");
			System.out.println("1-Cadastrar Alunos");
			if(numAlunos > 0) System.out.println("2-Comprar");
			
			int opcao = ScanearInt();
			
			if(opcao == 0) fechar = true;
			else if(opcao == 1) Cadastrar();
			else if(opcao == 2 && numAlunos > 0) Comprar();
			else System.out.println("Opção Inválida, tente novamente");
			
		}
	}

}
