package classes;

public class Funcionarios extends Cliente {
	private int siape;
	
	public Funcionarios(String nome, int siape) {
		super(nome);
		this.siape = siape;
		this.indentificador = "SIAPE";
	}
	
	@Override
	public int PegarIndentificador() {
		return siape;
	}
}