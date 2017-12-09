package view;

import java.util.Scanner;

import controller.CadastroController;
import model.Jogador;


public class Cadastro{
	
	private CadastroController cadastroController;
	
	public Cadastro(Jogador modelJogador) {
		cadastroController = new CadastroController(modelJogador,this);
		cadastroController.cadastrarNome();
		cadastroController.cadastrarEmbarcacoes();
	}
	
	public String exibeEscolhaNome() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Digite seu nome: ");
		return sc.nextLine();
	} 
	
	public String exibirEscolhaPosicao(String linhaOuColuna,String nomeEmbarcacao) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print(linhaOuColuna+" do indice inicial do "+nomeEmbarcacao+": ");
		String linhaColuna = sc.nextLine();
		return linhaColuna;
	}
	
	public char exibirEscolhaSentido() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Sentido da embarcacao V - vertical, H - Horizontal: ");
		return sc.nextLine().charAt(0);
	}
	
	public void mensagemImpossivel() {
		System.out.println("Impossivel");
	}
	
	public void exibeErro(String message) {System.out.println(message);}
		
	public void exibeSucesso(String message) {System.out.println(message);}
	
}
