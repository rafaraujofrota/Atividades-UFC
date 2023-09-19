
public class Venda {
	private Aluno cliente;
	private CachorroQuente[] produtos;
	private Extras extra;
	
	public Venda(Aluno c, CachorroQuente[] p, Extras e) {
		this.cliente = c;
		this.produtos = p;
		if(extra != null) this.extra = e;
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
		System.out.println("Aluno " + cliente.PegarNome() + " Matricula: " + cliente.PegarMatricula());
		for(int i = 0; i < produtos.length; i++) {
			System.out.println("Cachorro Quente N" + ( i + 1));
			System.out.println("Sabor " + produtos[i].PegarProteina());
			System.out.println("Queijo " + produtos[i].PegarQueijo());
			System.out.println("Adicionais: ");
			
			String[] adicionais = produtos[i].PegarAdicionais();
			for(int j = 0; j < adicionais.length; j++) {
				System.out.println("-" + adicionais[j]);
			}
		}
		System.out.println("*Extras");
		System.out.println("Bebidas: ");
		System.out.println("-" + extra.PegarBebida());
	}
}
