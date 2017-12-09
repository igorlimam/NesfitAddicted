package view;
import model.Jogador;
import model.Maquina;

public class Principal {

	public static void main(String[] args) {
		Jogador jogador = new Jogador();
		Cadastro cadastro = new Cadastro(jogador);
		Jogo jogo = new Jogo(jogador,new Maquina());
	}
	
}
