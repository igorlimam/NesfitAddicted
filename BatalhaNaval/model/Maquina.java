package model;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Stack;
public class Maquina extends Jogador{

	private SecureRandom rand = new SecureRandom();
	private ArrayList<Coordenada> possiveisJogadas = new ArrayList<>();
	private Stack<Coordenada> areasRedor = new Stack<>();
	private Stack<Coordenada> modificadorCoordenada = new Stack<>();
	
	public Maquina() {
		
		for(int i = 0;i<20;++i) {
			for(int j = 0;j<20;++j) {
				possiveisJogadas.add(new Coordenada(i,j));
			}
		}
		adicionarEmbarcacao();
	}
	
	private void adicionarEmbarcacao() {
		
		Embarcacao[] e = new Embarcacao[4];
		e[0] = new Embarcacao("Navio",3);
		e[1] = new Embarcacao("Submarino",4);
		e[2] = new Embarcacao("Porta avioes",6);
		e[3] = new Embarcacao("Cruzador",5);
		
		for(int i = 0;i<4;++i) {
			int n = rand.nextInt(2);
			int cordL = rand.nextInt(20);
			int cordC = rand.nextInt(20);
			if(n == 0) {
				try {
					if(!this.getMarDeGuerra().adicionar(e[i], true, new Coordenada(cordL,cordC))) {
						i--;
						continue;
					}
				}catch(SobreposicaoEmbarcacaoException ex) {
					--i;
					continue;
				}
			}else {
				try {
					if(!this.getMarDeGuerra().adicionar(e[i], false, new Coordenada(cordL,cordC))) {
						i--;
						continue;
					}
				}catch(SobreposicaoEmbarcacaoException ex) {
					--i;
					continue;
				}
			}
		}
		
	}
	
	public Embarcacao atirar(MarDeGuerra tabuleiro) {
		//Esta implementacao do tiro falha no caso da seguinte configuracao:
		/*
		 *
		 * N S S S S S
		 * N
		 * N
		 * 
		 * Ao atirar em S depois do N, ele atirara no primeiro N mas nao nos N subsequentes
		 * 
		 */
		if(!areasRedor.empty()) {
			
			Coordenada c = areasRedor.pop();
			Coordenada mod = modificadorCoordenada.pop();
			for(int i = 0;i<possiveisJogadas.size();++i) {
				if(possiveisJogadas.get(i).getLinha() == c.getLinha() &&
						possiveisJogadas.get(i).getColuna() == c.getColuna()) {
					possiveisJogadas.remove(i);
					break;
				}
			}
			
			Embarcacao e = null;
			if(tabuleiro.getTabuleiro()[c.getLinha()][c.getColuna()] != null && 
					tabuleiro.getTabuleiro()[c.getLinha()][c.getColuna()].get(0) instanceof Embarcacao) {
				
				e = (Embarcacao)tabuleiro.getTabuleiro()[c.getLinha()][c.getColuna()].get(0);
				
				tabuleiro.getTabuleiro()[c.getLinha()][c.getColuna()] = new ArrayList<>();
				tabuleiro.getTabuleiro()[c.getLinha()][c.getColuna()].add(true);
				
				if((c.getLinha()+mod.getLinha() < 20 && c.getLinha()+mod.getLinha() >= 0) && 
						(c.getColuna()+mod.getColuna() < 20 && c.getColuna()+mod.getColuna() >= 0)) {
					areasRedor.push(new Coordenada(c.getLinha()+mod.getLinha(),c.getColuna()+mod.getColuna()));
					modificadorCoordenada.push(mod);
				}
				
			}
			return e;
		}else {
		
			int n = rand.nextInt(possiveisJogadas.size());
			Coordenada c = possiveisJogadas.get(n);
			possiveisJogadas.remove(n);
			
			if(tabuleiro.getTabuleiro()[c.getLinha()][c.getColuna()] == null) {
				//Acao para agua
				tabuleiro.getTabuleiro()[c.getLinha()][c.getColuna()] = new ArrayList<>();
				tabuleiro.getTabuleiro()[c.getLinha()][c.getColuna()].add(true);
				return null;
			}else {
				if(tabuleiro.getTabuleiro()[c.getLinha()][c.getColuna()].get(0) instanceof Embarcacao) {
					//acertou alguma coisa
					Embarcacao e = (Embarcacao)tabuleiro.getTabuleiro()[c.getLinha()][c.getColuna()].get(0);
					if(tabuleiro.getTabuleiro()[c.getLinha()][c.getColuna()].size() > 1) {
						//Explodiu uma mina
					}
					tabuleiro.getTabuleiro()[c.getLinha()][c.getColuna()] = new ArrayList<>();
					tabuleiro.getTabuleiro()[c.getLinha()][c.getColuna()].add(true);
					
					if(c.getLinha()+1 < 20) {
						areasRedor.push(new Coordenada(c.getLinha()+1,c.getColuna()));
						modificadorCoordenada.push(new Coordenada(1,0));
					}
					if(c.getLinha()-1 >= 0) {
						areasRedor.push(new Coordenada(c.getLinha()-1,c.getColuna()));
						modificadorCoordenada.push(new Coordenada(-1,0));
					}
					if(c.getColuna()+1 < 20) {
						areasRedor.push(new Coordenada(c.getLinha(),c.getColuna()+1));
						modificadorCoordenada.push(new Coordenada(0,1));
					}
					if(c.getColuna()-1 >= 0) {
						areasRedor.push(new Coordenada(c.getLinha(),c.getColuna()-1));
						modificadorCoordenada.push(new Coordenada(0,-1));
					}
					
					//Retorna a embarcacao pra saber se ja terminou de explodi-la
					return e;
				}else {
					//explodiu apenas mina
					return null;
				}
				
				
			}
		}
	}
	
}
