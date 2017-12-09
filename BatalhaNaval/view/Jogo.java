package view;

import java.util.Scanner;

import controller.JogoController;
import model.Coordenada;
import model.Embarcacao;
import model.Jogador;
import model.Maquina;
import model.Observer;

public class Jogo implements Observer{
	
	private Jogador modelJogador;
	private Maquina modelMaquina;
	private JogoController jogoController;
	private int vez = 0;
	
	public Jogo(Jogador modelJogador, Maquina modelMaquina) {
		
		this.modelJogador = modelJogador;
		this.modelMaquina = modelMaquina;
		jogoController = new JogoController(modelJogador, modelMaquina, this);
		modelJogador.addObserver(this);
		modelMaquina.addObserver(this);
		exibirMenu();
	}
	
	public void exibeMensagem(String mensagem) {
		System.out.println(mensagem);
	}

	
	public void exibeMarJogadorRevelado() {
		System.out.print("-  ");
		for(int i = 0;i<20;++i)
			if(i<10)
				System.out.print(" "+i+" ");
			else
				System.out.print(i+" ");
		System.out.println();
		
		for(int i = 0;i<20;++i) {
			if(i<10)
				System.out.print(i+"  ");
			else
				System.out.print(i+" ");
			for(int j = 0;j<20;++j) {
				jogoController.decidirOQueImprimirJogador(new Coordenada(i,j));
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public void printAgua() {System.out.print(" ~ ");}
	public void printEmbarcacao(Embarcacao e) {System.out.print(" "+e.getNome().charAt(0)+" ");}
	public void printJaJogado() {System.out.print(" x ");}
	
	public void maquinaJogando() {
		
		exibeMarJogadorRevelado();
		System.out.println("Agora eh a vez da maquina");
		(new Scanner(System.in)).nextLine();
		
	}
	
	public int exibirEscolhaPosicao(String linhaOuColuna) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Indice da "+linhaOuColuna+" do tiro: ");
		int linhaColuna = sc.nextInt();
		sc.nextLine();
		return linhaColuna;
	}
	
	public String rejogar() {
		System.out.println("Jogar novamente? SIM ou NAO ?");
		Scanner sc = new Scanner(System.in);
		return sc.nextLine();
	}
	
	public void exibeErro(String message) {System.out.println(message);}
	
	public void exibeAtingiuAgua(String quem) {System.out.println(quem+" atingiu a agua");}
	
	public void exibeMarMaquina() {
		System.out.print("-  ");
		for(int i = 0;i<20;++i)
			if(i<10)
				System.out.print(" "+i+" ");
			else
				System.out.print(i+" ");
		System.out.println();
		
		for(int i = 0;i<20;++i) {
			if(i<10)
				System.out.print(i+"  ");
			else
				System.out.print(i+" ");
			for(int j = 0;j<20;++j) {
				jogoController.decidirOQueImprimirMaquina(new Coordenada(i,j));
			}
			System.out.println("");
		}
		System.out.println();
	}
	
	public void exibirMenu() {
		
		exibeMarMaquina();
		
		System.out.println("Jogador, escolha uma das opcoes");
		System.out.println("1 - atirar");
		System.out.println("0 - Sair");
		Scanner sc = new Scanner(System.in);
		String opcao = sc.nextLine();
		jogoController.selecionarAcaoJogavel(opcao);
	}
	
	public void mensagemDestruiuEmbarcacao(String nomeEmbarcacao) {
		System.out.println("Voce destruiu o "+nomeEmbarcacao+" do inimigo");
	}
	
	public void exibeAtingiu(String quem) {
		System.out.println(quem+" atingiu uma embarcacao");
	}
	
	public void exibeOpcaoInvalida() {System.out.println("Opcao invalida");}
	
	@Override
	public void update(Object o) {
		
		if(o == null) {
			vez = 1-vez;
		}
		if(modelJogador.getPontuacao() == 4) {
			System.out.println(modelJogador.getNome()+" Venceu a partida");
			jogoController.novoJogo(this);
		}else if(modelMaquina.getPontuacao() == 4) {
			System.out.println("A maquina venceu a partida");
			jogoController.novoJogo(this);
		}else {
			jogoController.vezJogada(vez);
		}
		
	}

	
}
