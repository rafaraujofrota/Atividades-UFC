
public class Aluno {
	private static int totalCadastrado = 0;
	private static Aluno[] AlunosCadastrados = new Aluno[10000]; 
	
	private String nome;
	private int matricula;
	
	public Aluno(String nome, int matricula) {
		this.nome = nome;
		this.matricula = matricula;
		AlunosCadastrados[totalCadastrado] = this;
		totalCadastrado++;
	}
	
	public static int PegarTotalAlunos() {
		return totalCadastrado;
	}
	
	public String PegarNome() {
		return nome;
	}
	
	public int PegarMatricula() {
		return matricula;
	}
	
	public static Aluno PegarAlunoPorMatricula(int matricula) {
				
		for(Aluno a: AlunosCadastrados) {
			if(a == null) return null;
			if(matricula == a.PegarMatricula()) return a;
		}
		
		return null;
	}
}
