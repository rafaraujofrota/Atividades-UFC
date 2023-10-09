package classes;

public class Aluno extends Cliente {
	private int matricula;
	
	public Aluno(String nome, int matricula) {
		super(nome);
		this.matricula = matricula;
		this.indentificador = "Matrícula";
	}
	
	@Override
	public int PegarIndentificador() {
		return matricula;
	}
}
