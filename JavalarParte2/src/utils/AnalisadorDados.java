package utils;

import java.util.ArrayList;

import classes.Relatorio;

public class AnalisadorDados {
	private ArrayList<Relatorio> lista;
	
	public AnalisadorDados(ArrayList<Relatorio> dados) throws Exception {
		if(dados == null) throw new Exception("Nenhum Dado Buscado");
		
		lista = dados;
	}
	
	public int[] AnalisarBugs() {
		// Total Bugs e Devs = Colisoes de Todos os Planetas + qnt em Todos os Quadrantes
		int[] somaQuadrantesBug = new int[4];
		int[] somaQuadrantesDev = new int[4];
		int[] qntModificadores = new int[2];
		
		for(Relatorio dado: lista) {
			for(int i = 0; i < 4; i++) {
				somaQuadrantesBug[i] += dado.bugsPorQuadrante[i];
				somaQuadrantesDev[i] += dado.devsPorQuadrante[i];
			}
			for(PlanetaDadosRelatorio p : dado.dadosPlaneta) {
				qntModificadores[0] += p.colisaoBugs;
				qntModificadores[1] += p.colisaoDevs;
			}
		}
		
		int maisBug = 0;
		int maisDev = 0;
 		
		for(int i = 1; i <= 3; i++ ) {
			if(somaQuadrantesBug[i] > somaQuadrantesBug[maisBug]) maisBug = i;
			if(somaQuadrantesDev[i] > somaQuadrantesDev[maisDev]) maisDev = i;
		}
		
		int somaBug = qntModificadores[0];
		int somaDev = qntModificadores[1];
		
		for(int i = 0; i < 4; i++) {
			somaBug += somaQuadrantesBug[i];
			somaDev += somaQuadrantesDev[i];
		}
		
		int[] dadosModificadores = {maisBug + 1, maisDev + 1, somaBug, somaDev,};
		
		return dadosModificadores;
	}
	
	public String[] AnalisarPlanetas() {
		String[] planetas = PlanetaDadosRelatorio.nomesPlanetas;
		int[] mortes = new int[planetas.length];
		int[] vida = new int[planetas.length];
		
		for(Relatorio dado: lista) {
			PlanetaDadosRelatorio[] p = dado.dadosPlaneta;
			
			for(int i = 0; i < mortes.length; i++) {
				if(p[i].velocidade == 0) mortes[i]++;
				vida[i] += p[i].velocidade;
			}
		}
		
		int maisMorte = 0;
		int maisVida = 0;
 		
		for(int i = 1; i < planetas.length; i++ ) {
			if(mortes[i] > mortes[maisMorte]) maisMorte = i;
			if(vida[i] > vida[maisVida]) maisVida = i;
		}
		
		String[] dadosPlanetas = {planetas[maisMorte], planetas[maisVida]};
		return dadosPlanetas;
		
	}
	
	public int[] AnalisarTempo() {
		int[] dadosTempo = new int[2];
		
		for(Relatorio dado: lista) {
			for(PlanetaDadosRelatorio p: dado.dadosPlaneta) {
				dadosTempo[0] += p.horasTotais;
				dadosTempo[1] += p.anosTotais;
			}	
		}
		
		return dadosTempo;
	}
	
	public double[] AnalisarVelocidade() {
		int[] somaVelocidades = new int[PlanetaDadosRelatorio.planetasDisponiveis.length];
		
		for(Relatorio dado: lista) {
			PlanetaDadosRelatorio[] p = dado.dadosPlaneta;
			
			for(int i = 0; i < p.length; i++) {
				somaVelocidades[i] += p[i].velocidade;
			}	
		}
		
		double[] mediaVelocidades = new double[somaVelocidades.length];
		
		for(int i = 0; i < somaVelocidades.length; i++) {
			mediaVelocidades[i] = (double) somaVelocidades[i] / lista.size();
			mediaVelocidades[i] = Math.round(mediaVelocidades[i] * 1000)/1000.0;
		}
		
		return mediaVelocidades;
	}
	
	public String[] AnalisarAluno() {
		ArrayList<Integer> quantidadeMatricula = new ArrayList<Integer>();
		ArrayList<String> matriculasChecar = new ArrayList<String>();
		
		for(Relatorio dado: lista) {
			if(!matriculasChecar.contains(dado.matricula)) {
				matriculasChecar.add(dado.matricula);
			}
		}
		
		for(String matricula: matriculasChecar) {
			int quantidade = 0;
			for(Relatorio dado: lista) {
				if(matricula.equals(dado.matricula)) quantidade++;
			}
			quantidadeMatricula.add(quantidade);
		}
		
		int maior = 0;
		
		for(int i = 1; i < quantidadeMatricula.size(); i++ ) {
			if(quantidadeMatricula.get(i) > quantidadeMatricula.get(maior)) maior = i;
		}
		
		String matriculaMaisComum = matriculasChecar.get(maior);
		
		String nomeMaisComum = "";
		
		for(Relatorio dado: lista) {
			if(dado.matricula.equals(matriculaMaisComum)) {
				nomeMaisComum = dado.nomeAluno;
				break;
			}
		}
		
		String[] dadosAluno = { matriculaMaisComum, nomeMaisComum };
		return dadosAluno;
		
	}
	
	public String PegarResumo() {
		String[] nomePlanetas = PlanetaDadosRelatorio.nomesPlanetas;
		int[] dadosBugs = AnalisarBugs();
		double[] dadosVelocidade = AnalisarVelocidade();
		int[] dadosTempo = AnalisarTempo();
		
		String Resumo = String.join(" - ", AnalisarAluno()) +  ", " + String.join(", ", AnalisarPlanetas());
		Resumo += ", " + dadosBugs[0] + ", " + dadosBugs[1] + ", " + lista.size() + ", ";
		
		for(int i = 0; i < dadosVelocidade.length; i++) {
			Resumo += nomePlanetas[i] + ": " + dadosVelocidade[i];
			if(i != dadosVelocidade.length - 1) Resumo += " - ";
		}
		
		Resumo += ", " + dadosBugs[2] + ", " + dadosBugs[3];
		Resumo += ", " + dadosTempo[0] + ", " + dadosTempo[1];
		
		return Resumo;
	}
	
}
