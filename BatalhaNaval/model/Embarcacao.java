package model;

public class Embarcacao {

	private String nome;
	private int tamanho;
	private Coordenada[] coordenada;
	public Embarcacao() {}
	
	public Embarcacao(String nome,int tamanho) {
		
		setNome(nome);
		setTamanho(tamanho);
		
	}

	public String getNome() {
		return nome;
	}
	
	public int getTamanho() {
		return tamanho;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setTamanho(int tamanho) {
		if(tamanho>0) {
			this.tamanho = tamanho;
			coordenada = new Coordenada[tamanho];
		}
	}
	
	public void setCoordenada(int index,Coordenada coordenada) {
		this.coordenada[index] = coordenada;
	}
	
	public Coordenada getCoordenada(int index) {
		return coordenada[index];
	}
	
}
