import java.util.ArrayList;

public class Jogador {

	private MarDeGuerra tabuleiro = new MarDeGuerra();
	private String nome;
	private int dinheiro = 0;
	
	public Jogador() {}
	
	public Jogador(String nome) {
		
		setNome(nome);
		
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getDinheiro() {
		return dinheiro;
	}
	public void incrementaDinheiro(int valor) {
		this.dinheiro += valor;
	}
	public void comprar(int valor) {
		this.dinheiro -= valor;
	}
	public void zerarDinheiro() {
		dinheiro = 0;
	}
	public MarDeGuerra getMarDeGuerra() {
		return tabuleiro;
	}
	
	public Embarcacao atirar(Coordenada c) {
		//Agua
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
				return e;
			}else {
				//explodiu apenas mina
				return null;
			}
			
			
		}
		
	}
	
}
