package controller;

import java.util.InputMismatchException;

import javax.activity.InvalidActivityException;

import model.Coordenada;
import model.CoordenadaImpossivelException;
import model.Embarcacao;
import model.EspacoJaJogadoException;
import model.Jogador;
import model.Maquina;
import view.Cadastro;
import view.Jogo;

public class JogoController {

	private Jogador modelJogador;
	private Maquina modelMaquina;
	private Jogo viewJogo;
	
	public JogoController(Jogador modelJogador,Maquina modelMaquina,Jogo viewJogo) {
		
		this.viewJogo = viewJogo;
		this.modelJogador = modelJogador;
		this.modelMaquina = modelMaquina;
	}
	
	public void vezJogada(int vez) {
		
		if(vez == 1) {
			//maquina
			viewJogo.maquinaJogando();
			Embarcacao e = modelMaquina.atirar(modelJogador.getMarDeGuerra());
			if(e!=null)
				viewJogo.exibeAtingiu("Maquina");
			else
				viewJogo.exibeAtingiuAgua("Maquina");
			if(modelJogador.getMarDeGuerra().embarcacaoDestruida(e)) {
				modelMaquina.incrementaPonto();
				viewJogo.mensagemDestruiuEmbarcacao(e.getNome());
			}
			viewJogo.exibeMarJogadorRevelado();
			modelMaquina.atualizarObservers(e);
		}else {
			viewJogo.exibirMenu();
		}
		
	}
	
	public void opcoesDeJogo() {
		try {
			viewJogo.exibirMenu();
		}catch(Exception e) {
			viewJogo.exibeOpcaoInvalida();
			opcoesDeJogo();
		}
	}
	
	public void selecionarAcaoJogavel(String opcao){
		int op = -1;
		try {
			op = Integer.parseInt(opcao);
		}catch(Exception e) {
			viewJogo.exibeErro("Entrada invalida\n");
			viewJogo.exibirMenu();
		}
		switch(op) {
			case 1:
				int linha,coluna;
				try {
					do {
						linha = viewJogo.exibirEscolhaPosicao("Linha");
						if(linha<0 || linha>=20) {
							viewJogo.exibeErro("Linha invalida");
						}
					}while(linha<0 || linha>=20);
					
					do {
						coluna = viewJogo.exibirEscolhaPosicao("Coluna");
						if(coluna<0 || coluna>=20) {
							viewJogo.exibeErro("Coluna invalida");
						}
					}while(coluna<0 || coluna>=20);
					
					modelJogador.verificaCoordenada(linha, coluna);
					Embarcacao e = modelJogador.atirar(new Coordenada(linha,coluna),modelMaquina.getMarDeGuerra());
					
					if(e!=null) {
						viewJogo.exibeAtingiu(modelJogador.getNome());
					}else {
						viewJogo.exibeAtingiuAgua(modelJogador.getNome());
					}
					
					if(modelMaquina.getMarDeGuerra().
							embarcacaoDestruida(e)){
						modelJogador.incrementaPonto();
						viewJogo.mensagemDestruiuEmbarcacao(e.getNome());
					}
					viewJogo.exibeMarMaquina();
					modelJogador.atualizarObservers(e);
					
				}catch(EspacoJaJogadoException|CoordenadaImpossivelException ex) {
					viewJogo.exibeErro(ex.getMessage());
					viewJogo.exibirMenu();
				}catch(InputMismatchException ex) {
					viewJogo.exibeErro("Dica: Utilize valores inteiros para linha e coluna");
					viewJogo.exibirMenu();
				}
				break;
			case 0:
				novoJogo(viewJogo);
				break;
			default:
				viewJogo.exibeErro("Opcao invalida");
				viewJogo.exibirMenu();
				break;
		}
	}
	
	public void novoJogo(Jogo jogo) {
		String decisao = null;
		do {
			decisao = viewJogo.rejogar();
			if(decisao.equals("SIM")) {
				Jogador jogador = new Jogador();
				Cadastro cadastro = new Cadastro(jogador);
				jogo = new Jogo(jogador,new Maquina());
			}else if(decisao.equals("NAO")) {
				viewJogo.exibeMensagem("Ate mais ver!");
			}
		}while(!decisao.equals("SIM") && !decisao.equals("NAO"));
	}
	
	public void decidirOQueImprimirJogador(Coordenada c) {
		
		if(modelJogador.getMarDeGuerra().isExploded(c)) {
			viewJogo.printJaJogado();
		}else if(modelJogador.getMarDeGuerra().isEmbarcacao(c)){
			viewJogo.printEmbarcacao(modelJogador.getMarDeGuerra().getEmbarcacao(c));
		}else {
			viewJogo.printAgua();
		}
		
	}
	
	public void decidirOQueImprimirMaquina(Coordenada c) {
		if(modelMaquina.getMarDeGuerra().isExploded(c)) {
			System.out.print(" x ");
		}else {
			System.out.print(" ~ ");
			
		}
	}
	
}
