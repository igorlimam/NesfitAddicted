package model;

public class CoordenadaImpossivelException extends Exception{

	public CoordenadaImpossivelException() {
		super("Uma ou todas as coordenadas sao invalidas");
	}
	
	public CoordenadaImpossivelException(boolean linhaColuna,int o) {
		//true - linha, false = coluna
		super("A "+ (linhaColuna?"linha":"coluna") +" digitada esta incorreta. "+o);
	}

}
