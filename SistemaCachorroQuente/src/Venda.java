
public class Venda {
	private Aluno cliente;
	private CachorroQuente[] produtos;
	private Extras extra;
	
	public Venda(Aluno c, CachorroQuente[] p, Extras e) {
		this.cliente = c;
		this.produtos = p;
		if(e != null) this.extra = e;
	}
	
	public Aluno PegarCliente() {
		return this.cliente;
	}
	
	public CachorroQuente[] PegarProduto() {
		return this.produtos;
	}
	
	public Extras PegarExtras() {
		return this.extra;
	}
	
	public void ImprimirRecibo() {
		System.out.println("\nAluno: " + cliente.PegarNome() + " - " + " Matricula: " + cliente.PegarMatricula());
		for(int i = 0; i < produtos.length; i++) {
			System.out.println("\nCachorro Quente N" + ( i + 1));
			System.out.println("Sabor " + produtos[i].PegarProteina());
			System.out.println("Queijo " + produtos[i].PegarQueijo());
			
			if(produtos[i].PegarAdicionais() != null) {
				System.out.println("\nAdicionais: ");
				String[] adicionais = produtos[i].PegarAdicionais();
				for(int j = 0; j < adicionais.length; j++) {
					System.out.println(" -" + adicionais[j]);
				}
			}
		}
		
		if(extra != null) {
			System.out.println("\n*Extras");
			System.out.println("  Bebidas: ");
			System.out.println("   -" + extra.PegarBebida());
		}
		System.out.println("");
	}
}
