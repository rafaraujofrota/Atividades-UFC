import java.util.Scanner;

public class Lista {
	public static void questao(int num) {
		System.out.println("\nQuestão " + num);
	}
	
	public static void main(String[] args) {
		Scanner Entrada = new Scanner(System.in);
		
		System.out.println("Digite o número A: ");
		int A = Entrada.nextInt();
		System.out.println("Digite o número B: ");
		int B = Entrada.nextInt();
		Entrada.close();
		
		questao(1);
		if(A > 10) System.out.println("A > 10");
		
		if(A + B == 20) System.out.println("A + B == 20");
		else System.out.println("Número Inválido");
		
		questao(2);
		if(A < 10) System.out.println("A < 10");
		else if(A + B == 20) System.out.println("A + B == 20");
		else System.out.println("Número Inválido");
		
		questao(3);
		if(A == 10) System.out.println("A == 10");
		if(A + B == 20) System.out.println("A + B == 20");
		if(A == 10) System.out.println("B == 10");
		
		questao(4);
		if(A > 10 || A + B == 20) System.out.println("Número Válido");
		else if(A == B) System.out.println("A é igual B; A e B são diferentes de 10; A é menor que 10"); 
		else System.out.println("Número Inválido");
		
		questao(5);
		if(A > 10 || A + B == 20) System.out.println("Número Válido");
		else System.out.println("Número Inválido");
		
		questao(6);
		if(A > 10) System.out.println("A > 10");
		else System.out.println("A <= 10");
		
		if(A + B == 20) System.out.println("A + B == 20");
		else System.out.println("A + B != 20");
		
		questao(7);
		if(A > 10 && A+B == 20) System.out.println("Números Válidos");
		else System.out.println("Número não válido");
		
		questao(8);
		if(A > 10) System.out.println("A > 10");
		
		if(A + B == 20) System.out.println("A + B == 20");
		else System.out.println("Número Inválido");
		
		questao(9);
		if(A > 10 && A + B == 20) System.out.println("A + B == 20");
		else System.out.println("Número inválido");
		
		questao(10);
		if(!( A > 10)) System.out.println("Número Menor que 10");
		if(!(A + B == 20)) System.out.println("Número diferente de 20");
		
		
		
		questao(11);
		if(!(A > 10) && A + B == 20) System.out.println("A + B == 20");
		else System.out.println("Número Inválido");
		
		questao(12);
		if(A > 10) System.out.println("A > 10");
		else if(A + B == 20) System.out.println("A + B == 20");
		else System.out.println("Números inválidos");
		
		System.out.println("Sejam Bem-vindos à disciplina de Técnicas de Programação");
		
		questao(13);
		if(A > 10) {
			System.out.println("A > 10");
			if(A + B == 20) System.out.println("A + B == 20");
			else System.out.println("Número Inválido");
		} else System.out.println("Número Inválido");
		
		questao(14);
		if(A > 10) {
			System.out.println("A > 10");
			if(A + B == 20) System.out.println("A + B == 20");
			else System.out.println("Número Inválido");
		}
		
		questao(15);
		if(A < 10) System.out.println("A > 10");
		if(A + B == 20) System.out.println("A + B == 20");
		if(A >= 10 && A + B != 20) System.out.println("Número inválido");
		
		questao(16);
		if(A == 10) System.out.println("A == 10");
		if(A + B == 10) System.out.println("A + B == 20");
		if(B == 10) System.out.println("B == 10");
		
		questao(17);
		if(A > 10 || A + B == 20) System.out.println("Número Válido");
		else if(A == B) System.out.println("A é igual B");
		else if(A < 10 && B != 10) System.out.println("A é menor que 10");
		else System.out.println("Número Inválido");
		
		questao(18);
		if(A > 10 || A + B == 20) System.out.println("Número Inválido");
		else System.out.println("Número Inválido");
		
		questao(19);
		if(A > 10) System.out.println("A > 10");
		else System.out.println("A <= 10");
		
		if(A + B == 20) System.out.println("A + B == 20");
		else System.out.println("A + B != 20");
		
		questao(20);
		if( A > 10 || A + B == 20) System.out.println("Números Válidos");
		else System.out.println("Números não válidos");
		
		System.out.println("Sejam Bem-vindos à disciplina de técnicas de Programação");
	}
}
