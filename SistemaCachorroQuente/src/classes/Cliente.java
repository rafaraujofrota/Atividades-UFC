package classes;

public abstract class Cliente {
	
	protected String nome;
	protected String indentificador;
	protected String tipo;
	
	public Cliente(String nome) {
		this.nome = nome;
		this.tipo = this.getClass().getSimpleName();
	}
	
	public String PegarNome() {
		return nome;
	}
	
	public String PegarTipo() {
		return tipo;
	}
	
	public String PegarNomeID() {
		return indentificador;
	}
	
	public abstract int PegarIndentificador();
	
}

