package model;

public class Coordenada {

	private int linha,coluna;
	
	public Coordenada() {}
	
	public Coordenada(int linha, int coluna) {
		setColuna(coluna);
		setLinha(linha);
	}

	public int getLinha() {
		return linha;
	}

	public void setLinha(int linha) {
		if(coluna >=0 && coluna<20)
			this.linha = linha;
	}

	public int getColuna() {
		return coluna;
	}

	public void setColuna(int coluna) {
		if(coluna>=0 && coluna<20)
			this.coluna = coluna;
	}
	
	
}
