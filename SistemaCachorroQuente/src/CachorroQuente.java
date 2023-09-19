
public class CachorroQuente {
    public static enum proteinas {
        SALSICHA, LINGUICA, FRANGO, BACON
    }

    public static enum queijos {
        MUSSARELA, PRATO, PARMESAO, COALHO
    }

    public static enum adicionais {
        MAIONESE, KETCHUP, OVO, BATATA
    }
	
	private Enum<proteinas> proteina;
	private Enum<queijos> queijo;
	private Enum<adicionais>[] adicionais;
	
	public CachorroQuente(Enum<proteinas> p, Enum<queijos> q, Enum<adicionais>[] a) {
		this.proteina = p;
		this.queijo = q;
		
		if(a.length > 0) this.adicionais = a;
	}
	
	public String PegarProteina() {
		return this.proteina.name();
	}
	
	public String PegarQueijo() {
		return this.queijo.name();
	}
	
	public String[] PegarAdicionais() {
		if(this.adicionais.length <= 0) return null;
		
		String[] adicionaisTexto = new String[this.adicionais.length];
		for(int i = 0; i < this.adicionais.length; i++) {
			adicionaisTexto[i] = this.adicionais[i].name();
		}
		return adicionaisTexto;
	}
}
