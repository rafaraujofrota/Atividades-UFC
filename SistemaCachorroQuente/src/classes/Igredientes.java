package classes;

public class Igredientes {
	public enum Proteina {
		SALSICHA(2, 2, 10),
		LINGUICA(3, 2, 12),
		FRANGO(2.5, 3, 13),
		BACON(3.5, 4, 14),;

		private double valor;
		private int quantidadeDesconto;
		private double desconto;
		
		private Proteina(double valor, int quantD, double desconto) {
			this.valor = valor;
			quantidadeDesconto = quantD;
			this.desconto = desconto;
		}

		public double pegarValor() {
			return valor;
		}

		public int quantidadeDesconto() {
			return quantidadeDesconto;
		}

		public double pegarDesconto() {
			return desconto;
		}

	}
	
	public static enum Queijos {
        MUSSARELA, PRATO, PARMESAO, COALHO
    }

    public static enum Adicionais {
        MAIONESE, KETCHUP, OVO, BATATA
    }
    
    public static enum Bebidas {
        COCA, DELRIO, SUCO
    }
}
