import java.util.ArrayList;
import java.util.Random;
public class Maquina {

	private Random rand = new Random();
	private ArrayList<Coordenada> possiveisJogadas = new ArrayList<>();
	private MarDeGuerra tabuleiro = new MarDeGuerra();
	private boolean lockedIn[] = new boolean[4];
	private ArrayList<Coordenada> areasRedor = new ArrayList<>();
	private int indexAreaRedor = 0;
	
	public Maquina() {
		
		for(int i = 0;i<20;++i) {
			for(int j = 0;j<20;++j) {
				possiveisJogadas.add(new Coordenada(i,j));
			}
		}
		adicionarEmbarcacao();
	}
	
	public void setTabuleiro(MarDeGuerra tabuleiro) {
		this.tabuleiro = tabuleiro;
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
				if(!tabuleiro.adicionar(e[i], true, new Coordenada(cordL,cordC))) {
					i--;
					continue;
				}
			}else {
				if(!tabuleiro.adicionar(e[i], false, new Coordenada(cordL,cordC))) {
					i--;
					continue;
				}
			}
		}
		
	}
	
	
	
	public Embarcacao atirar() {
		
		if(lockedIn) {
			
		}else if(areasRedor.size() > 0) {
			
			Coordenada c = areasRedor.get(indexAreaRedor);
			if(tabuleiro.getTabuleiro()[c.getLinha()][c.getColuna()] != null && 
					tabuleiro.getTabuleiro()[c.getLinha()][c.getColuna()].get(0) instanceof Embarcacao) {
				
				
				
			}
			
		}else {
		
			int n = rand.nextInt(possiveisJogadas.size());
			Coordenada c = possiveisJogadas.get(n);
			possiveisJogadas.remove(n);
			
			if(tabuleiro.getTabuleiro()[c.getLinha()][c.getColuna()] == null) {
				//Acao para agua
				tabuleiro.getTabuleiro()[c.getLinha()][c.getColuna()] = new ArrayList<>();
				tabuleiro.getTabuleiro()[c.getLinha()][c.getColuna()].add(true);
				System.out.println("Agua");
				return null;
			}else if(tabuleiro.getTabuleiro()[c.getLinha()][c.getColuna()].get(0) instanceof Boolean){
				//Or instanceof mina
				System.out.println("Voce ja jogou neste espaco");
				return null;
			}else {
				if(tabuleiro.getTabuleiro()[c.getLinha()][c.getColuna()].get(0) instanceof Embarcacao) {
					System.out.println("Acertou alguma coisa");
					Embarcacao e = (Embarcacao)tabuleiro.getTabuleiro()[c.getLinha()][c.getColuna()].get(0);
					if(tabuleiro.getTabuleiro()[c.getLinha()][c.getColuna()].size() > 1) {
						System.out.println("Explodiu sua mina");
					}
					tabuleiro.getTabuleiro()[c.getLinha()][c.getColuna()] = new ArrayList<>();
					tabuleiro.getTabuleiro()[c.getLinha()][c.getColuna()].add(true);
					
					if(c.getLinha()+1 < 20) {
						areasRedor.add(new Coordenada(c.getLinha()+1,c.getColuna()));
					}
					if(c.getLinha()-1 >= 0) {
						areasRedor.add(new Coordenada(c.getLinha()-1,c.getColuna()));
					}
					if(c.getColuna()+1 < 20) {
						areasRedor.add(new Coordenada(c.getLinha(),c.getColuna()+1));
					}
					if(c.getColuna()-1 >= 0) {
						areasRedor.add(new Coordenada(c.getLinha(),c.getColuna()-1));
					}
					
					
					return e;
				}else {
					//explodiu apenas mina
					return null;
				}
				
				
			}
		}
	}
	
}
