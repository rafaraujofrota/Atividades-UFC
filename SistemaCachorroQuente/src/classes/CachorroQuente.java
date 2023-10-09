package classes;

public class CachorroQuente {	
	private Igredientes.Proteina proteina;
	private Igredientes.Queijos queijo;
	private Igredientes.Adicionais[] adicionais;
	private double preco;
	
	public CachorroQuente(Igredientes.Proteina p, Igredientes.Queijos q, Igredientes.Adicionais[] a) {
		this.proteina = p;
		this.queijo = q;
		this.adicionais = a;
		
		// Botei assim pois pode mudar no futuro
		preco = p.pegarValor();
	}
	
	public Igredientes.Proteina PegarProteina() {
		return this.proteina;
	}
	
	public Igredientes.Queijos PegarQueijo() {
		return this.queijo;
	}
	
	public double PegarPreco() {
		return this.preco;
	}
	
	public Igredientes.Adicionais[] PegarAdicionais() {
		return this.adicionais;
	}
}
