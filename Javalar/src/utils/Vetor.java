package utils;

public class Vetor {
	public int x;
	public int y;
	
	public Vetor(int x, int y) {
		this.x = Math.abs(x);
		this.y = Math.abs(y);
	}
	
	public static boolean EhIgual(Vetor a, Vetor b) {
		return (a.x == b.x) && (a.y == b.y);
	}
	
	public static int CalcularArea(Vetor a, Vetor b) {
		return Math.abs((b.x - a.x) * (b.y - a.y));
	}
	
	public String PegarTextoVetor() {
		return "(" + x + ", " + y + ")";
	}
	
	public static double Distancia(Vetor a, Vetor b) {
		double lado1 = Math.pow((b.x - a.x), 2);
		double lado2 = Math.pow((b.y - a.y), 2);
		
		return Math.sqrt(lado1 + lado2);
	}
}
