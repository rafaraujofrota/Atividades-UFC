package controller;

import java.util.ArrayList;

import classes.CachorroQuente;
import classes.Cliente;
import classes.Igredientes;

public class Venda {
	private Cliente cliente;
	private CachorroQuente[] produtos;
	private Igredientes.Bebidas bebida;
	
	public static double totalArrecadado;
	public static double totalDesconto;
	public static int cachorrosVendidos;
	public static ArrayList<Venda> historico = new ArrayList<>();
	
	public double subtotal;
	public double total;
	
	public Venda(Cliente c, CachorroQuente[] p, Igredientes.Bebidas e) {
		this.cliente = c;
		this.produtos = p;
		this.bebida = e;
		
		cachorrosVendidos += p.length;
		
		Igredientes.Proteina[] proteinas = Igredientes.Proteina.values();
		int[] quantidade = new int[proteinas.length];
		
		for(CachorroQuente q: p) {
			quantidade[q.PegarProteina().ordinal()] += 1;
		}
		
		for(int i = 0; i < quantidade.length; i++) {
			double valor = quantidade[i] * proteinas[i].pegarValor();
			subtotal += valor;
			
			if(quantidade[i] > proteinas[i].quantidadeDesconto()) {
				double desconto = valor * (proteinas[i].pegarDesconto() / 100);
				valor -= desconto;
			}
			total += valor;
		}
		
		historico.add(this);
	}
	
	public Cliente PegarCliente() {
		return this.cliente;
	}
	
	public CachorroQuente[] PegarProduto() {
		return this.produtos;
	}
	
	public Igredientes.Bebidas PegarBebida() {
		return this.bebida;
	}
	
	public void ImprimirRecibo() {
		System.out.print("\n" + cliente.PegarTipo() + ": " + cliente.PegarNome() + " -  ");
		System.out.println(cliente.PegarNomeID() + ": " + cliente.PegarIndentificador());
		
		for(int i = 0; i < produtos.length; i++) {
			System.out.println("\nCachorro Quente N" + ( i + 1));
			System.out.println("Sabor " + produtos[i].PegarProteina());
			System.out.println("Queijo " + produtos[i].PegarQueijo());
			
			if(produtos[i].PegarAdicionais() != null) {
				System.out.println("\nAdicionais: ");
				Igredientes.Adicionais[] adicionais = produtos[i].PegarAdicionais();
				for(int j = 0; j < adicionais.length; j++) {
					System.out.println(" -" + adicionais[j]);
				}
			}
		}
		
		if(bebida != null) {
			System.out.println("\n*Extras");
			System.out.println("  Bebidas: ");
			System.out.println("   -" + bebida.name());
		}
		
		System.out.println("\nSubtotal: " + subtotal);
		System.out.println("Total: " + total);
		System.out.println();
	}
	
	public static double totalArrecadado() {
		double total = 0;
		for(Venda v: historico) {
			total += v.total;
		}
		return total;
	}
	
	public static double totalDesconto() {
		double subtotal = 0;
		for(Venda v: historico) {
			subtotal += v.subtotal;
		}
		return subtotal - totalArrecadado();
	}
	
	// Talvez mudar essas funções ai em baixo para outra classe depois;
	public static void cachorrosPorCliente() {
		int vendasAluno = 0;
		int vendasFuncionarios = 0;
		
		for(Venda v: historico) {
			int qnt = v.produtos.length;
			if(v.cliente.PegarTipo().equals("Aluno")) vendasAluno += qnt;
			else vendasFuncionarios += qnt;
		}
		
		System.out.println("Vendas para Alunos: " + vendasAluno);
		System.out.println("Vendas para Funcionarios: " + vendasFuncionarios);
	}
	
	public static void tipoMaisVendido() {
		Igredientes.Proteina[] proteinas = Igredientes.Proteina.values();
		int[] quantidade = new int[proteinas.length];
		
		for(Venda v: historico) {
			for(CachorroQuente q: v.produtos) {
				quantidade[q.PegarProteina().ordinal()] += 1;
			}
		}
		
		int maior = quantidade[0];
		int posicaoMaior = 0;
		for(int i = 1; i < quantidade.length; i++) {
			if(quantidade[i] > maior) {
				maior = quantidade[i];
				posicaoMaior = i;
			}
		}
		
		System.out.println("A Proteína mais vendida é " + proteinas[posicaoMaior] + " Com " + maior + " Vendas");
	}
	
	public static void bebidaMaisVendida() {
		Igredientes.Bebidas[] bebidas = Igredientes.Bebidas.values();
		int[] quantidade = new int[bebidas.length];
		
		for(Venda v: historico) {
			quantidade[v.bebida.ordinal()] += 1;
		}
		
		int maior = quantidade[0];
		int posicaoMaior = 0;
		
		for(int i = 1; i < quantidade.length; i++) {
			if(quantidade[i] > maior) {
				maior = quantidade[i];
				posicaoMaior = i;
			}
		}
		
		System.out.println("A Bebida mais vendida é " + bebidas[posicaoMaior] + " Com " + maior + " Vendas");
	}
}
