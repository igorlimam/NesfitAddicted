package model;
import java.util.ArrayList;

public class MarDeGuerra {

	private ArrayList[][] tabuleiro = new ArrayList[20][20];
	
	public void moverEmbarcacao(Embarcacao embarcacao) {}
	
	public boolean embarcacaoDestruida(Embarcacao embarcacao) {
		if(embarcacao == null)
			return false;
		
		for(int i = 0;i<embarcacao.getTamanho();++i) {
			Coordenada c = embarcacao.getCoordenada(i);
			if(tabuleiro[c.getLinha()][c.getColuna()]!= null &&
					tabuleiro[c.getLinha()][c.getColuna()].get(0) instanceof Embarcacao) {
				return false;
			}
		
		}
		return true;
		
		
	}
	
	public ArrayList[][] getTabuleiro(){
		return tabuleiro;
	}
	
	public boolean adicionar(Embarcacao embarcacao, boolean sentido,Coordenada coordenadaInicial) throws SobreposicaoEmbarcacaoException{
		//true - horizontal
		//false - vertical
		
		if(sentido) {
			if(embarcacao.getTamanho()+coordenadaInicial.getColuna() > 20) {
				return false;
			}
			for(int i = 0;i<embarcacao.getTamanho();++i) {
				if(tabuleiro[coordenadaInicial.getLinha()][coordenadaInicial.getColuna()+i] == null) {
					embarcacao.setCoordenada(i, new Coordenada(coordenadaInicial.getLinha(),
							coordenadaInicial.getColuna()+i));
				}else {
					for(int j = 0;j<embarcacao.getTamanho();++j) {
						embarcacao.setCoordenada(j, null);
					}
					throw new SobreposicaoEmbarcacaoException(
							new Coordenada(coordenadaInicial.getLinha(),coordenadaInicial.getColuna()+i));
					
				}
			}
		}else {
			if(embarcacao.getTamanho()+coordenadaInicial.getLinha() > 20) {
				return false;
			}
			for(int i = 0;i<embarcacao.getTamanho();++i) {
				if(tabuleiro[coordenadaInicial.getLinha()+i][coordenadaInicial.getColuna()] == null) {
					
					embarcacao.setCoordenada(i, new Coordenada(coordenadaInicial.getLinha()+i,
							coordenadaInicial.getColuna()));
					
				}else {
					for(int j = 0;j<embarcacao.getTamanho();++j) {
						embarcacao.setCoordenada(j, null);
					}
					throw new SobreposicaoEmbarcacaoException(
							new Coordenada(coordenadaInicial.getLinha()+i,coordenadaInicial.getColuna()));
				}
			}
		}
		
		for(int i = 0;i<embarcacao.getTamanho();++i) {
			if(tabuleiro[embarcacao.getCoordenada(i).getLinha()][embarcacao.getCoordenada(i).getColuna()] == null) {
				tabuleiro[embarcacao.getCoordenada(i).getLinha()][embarcacao.getCoordenada(i).getColuna()] = 
						new ArrayList<>();
			}
			tabuleiro[embarcacao.getCoordenada(i).getLinha()][embarcacao.getCoordenada(i).getColuna()].
			add(0,embarcacao);
		}
		return true;
	}
	
	private boolean isAgua(Coordenada c) {
		
		if(tabuleiro[c.getLinha()][c.getColuna()]!= null) {
			return false;
		}
		return true;
	}
	
	public boolean isExploded(Coordenada c) {
		if(isAgua(c)) {
			return false;
		}else if(tabuleiro[c.getLinha()][c.getColuna()].get(0) instanceof Boolean) {
			return true;
		}
		return false;
		
	}
	public boolean isEmbarcacao(Coordenada c) {
		
		if(tabuleiro[c.getLinha()][c.getColuna()]!= null && 
				tabuleiro[c.getLinha()][c.getColuna()].get(0) instanceof Embarcacao) {
			return true;
		}
		return false;
		
	}
	
	public Embarcacao getEmbarcacao(Coordenada c) {
		if(tabuleiro[c.getLinha()][c.getColuna()]==null) {
			return null;
		}
		return (Embarcacao)tabuleiro[c.getLinha()][c.getColuna()].get(0);
	}
	
}
