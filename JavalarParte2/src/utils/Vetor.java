package utils;

public class Vetor {
	public int x;
	public int y;
	
	public Vetor(int x, int y) {
		this.x = Math.abs(x);
		this.y = Math.abs(y);
	}
	
	public String PegarTextoVetor() {
		return "(" + x + ", " + y + ")";
	}
	
	public static boolean EhIgual(Vetor a, Vetor b) {
		return (a.x == b.x) && (a.y == b.y);
	}
	
	public static int AreaQuadrado(Vetor a, Vetor b) {
		return Math.abs((b.x - a.x) * (b.y - a.y));
	}
	
	public static double Distancia(Vetor a, Vetor b) {
		double lado1 = Math.pow((b.x - a.x), 2);
		double lado2 = Math.pow((b.y - a.y), 2);
		
		return Math.sqrt(lado1 + lado2);
	}
	
	public static double AreaPoligonoGauss(Vetor[] vertices) {
		// Função usando a Fórmula da Área de Gauss / Fórmula Shoelace
		
        double somaDeterminantes = 0.0;
        int n = vertices.length;

        for (int i = 0; i < n - 1; i++) {
			Vetor atual = vertices[i];
			Vetor prox = vertices[i + 1];
			
            somaDeterminantes += (atual.x * prox.y) - (atual.y * prox.x);
        }

        // Ligar o ultimo vertice de volta ao primeiro;
        somaDeterminantes += (vertices[n - 1].x * vertices[0].y) - (vertices[n - 1].y * vertices[0].x);

        return Math.abs(somaDeterminantes) / 2;
	}
}
