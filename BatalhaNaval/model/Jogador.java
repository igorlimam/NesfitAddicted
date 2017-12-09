package model;
import java.util.ArrayList;

public class Jogador {

	private MarDeGuerra tabuleiro = new MarDeGuerra();
	private String nome;
	private int embarcacoesDestruidas = 0;
	private ArrayList<Observer> observers = new ArrayList<>();
	
	public Jogador() {}
	
	public Jogador(String nome) {
		
		setNome(nome);
		
	}
	
	public void addObserver(Observer o) {
		observers.add(o);
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void incrementaPonto() {
		embarcacoesDestruidas++;
	}
	
	public int getPontuacao() {
		return embarcacoesDestruidas;
	}
	
	public MarDeGuerra getMarDeGuerra() {
		return tabuleiro;
	}
	
	public Embarcacao atirar(Coordenada c, MarDeGuerra tabuleiro) throws EspacoJaJogadoException{
		//Agua
		if(tabuleiro.getTabuleiro()[c.getLinha()][c.getColuna()] == null) {
			//Acao para agua
			tabuleiro.getTabuleiro()[c.getLinha()][c.getColuna()] = new ArrayList<>();
			tabuleiro.getTabuleiro()[c.getLinha()][c.getColuna()].add(true);
			return null;
		}else if(tabuleiro.getTabuleiro()[c.getLinha()][c.getColuna()].get(0) instanceof Boolean){
			//Or instanceof mina
			//Caso ja tenha jogado neste espaco
			throw new EspacoJaJogadoException("Voce ja jogou neste espaco",c);
		}else {
			if(tabuleiro.getTabuleiro()[c.getLinha()][c.getColuna()].get(0) instanceof Embarcacao) {
				
				//Acertou alguma coisa
				Embarcacao e = (Embarcacao)tabuleiro.getTabuleiro()[c.getLinha()][c.getColuna()].get(0);
				
				if(tabuleiro.getTabuleiro()[c.getLinha()][c.getColuna()].size() > 1) {
					//Aqui explodiria uma mina
				}
				
				tabuleiro.getTabuleiro()[c.getLinha()][c.getColuna()] = new ArrayList<>();
				tabuleiro.getTabuleiro()[c.getLinha()][c.getColuna()].add(true);
				
				
				//Retorna a embarcacao pra saber se destruiu completamente
				return e;
			}else {
				//explodiu apenas mina
				return null;
			}
			
			
		}
		
	}
	
	public void verificaCoordenada(int linha, int coluna) throws CoordenadaImpossivelException{
		if((linha > 20 || linha < 0)) {
			throw new CoordenadaImpossivelException(true,linha);
		}else if((coluna > 20 || coluna < 0)) {
			throw new CoordenadaImpossivelException(false,coluna);
		}
	}
	
	public void atualizarObservers(Embarcacao e) {
		
		for(Observer o:observers) {
			
			o.update(e);
			
		}
		
	}
	
}
