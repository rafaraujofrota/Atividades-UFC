
public class Extras {
	public static enum bebidas {
        COCA, DELRIO, SUCO
    }
	
	private Enum<bebidas> bebida;
	
	public Extras(Enum<bebidas> b) {
		if(b != null) this.bebida = b;
	}
	
	public String PegarBebida() {
		return this.bebida.name();
	}
}
