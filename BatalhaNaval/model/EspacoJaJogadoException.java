package model;

public class EspacoJaJogadoException extends Exception{

	public EspacoJaJogadoException() {
		super("Voce ja jogou neste espaco, tente novamente");
	}
	
	public EspacoJaJogadoException(String message) {
		super(message);
	}
	
	public EspacoJaJogadoException(String message,Coordenada c) {
		this(message+". Nas coordenadas ("+c.getLinha()+","+c.getColuna()+").");
	}
	
}
