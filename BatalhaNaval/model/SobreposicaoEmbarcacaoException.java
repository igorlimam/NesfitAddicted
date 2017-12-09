package model;

public class SobreposicaoEmbarcacaoException extends Exception{

	public SobreposicaoEmbarcacaoException() {
		super("Impossivel posicionar em cima de outra embarcacao");
	}
	
	public SobreposicaoEmbarcacaoException(Coordenada c) {
		super("Ja existe uma embarcacao nas coordenadas ("+c.getLinha()+","+c.getColuna()+").");
	}
	
}
