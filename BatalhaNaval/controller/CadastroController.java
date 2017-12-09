package controller;

import model.*;
import view.Cadastro;

public class CadastroController {

	private Jogador modelJogador;
	private Cadastro viewCadastro;
	
	public CadastroController(Jogador modelJogador,Cadastro viewCadastro) {
		this.modelJogador = modelJogador;
		this.viewCadastro = viewCadastro;
	}
	
	public void cadastrarNome() {
		modelJogador.setNome(viewCadastro.exibeEscolhaNome());
	}
	
	public void cadastrarEmbarcacoes(){
		
		Embarcacao[] e = new Embarcacao[4];
		
		e[0] = new Embarcacao("Navio",3);
		e[1] = new Embarcacao("Submarino",4);
		e[2] = new Embarcacao("Porta avioes",6);
		e[3] = new Embarcacao("Cruzador",5);
		
		
		for(int i = 0;i<4;++i) {
			int linha = 0, coluna = 0;
			char sentido = 'A';
			try {
				do {
					linha = Integer.parseInt(viewCadastro.exibirEscolhaPosicao("Linha", e[i].getNome()));
					if(linha < 0 || linha >= 20) {
						viewCadastro.exibeErro("Linha invalida");
					}
				}while(linha<0 || linha >= 20);
				
				do {
					coluna = Integer.parseInt(viewCadastro.exibirEscolhaPosicao("Coluna", e[i].getNome()));
					if(coluna < 0 || coluna >= 20) {
						viewCadastro.exibeErro("Coluna invalida");
					}
				}while(coluna<0 || coluna >= 20);
				
				do {
					sentido = viewCadastro.exibirEscolhaSentido();
					if(sentido!= 'H' && sentido!='V') {
						viewCadastro.exibeErro("Os sentidos sao apenas V para vertical e H para horizontal");
					}
				}while(sentido!= 'H' && sentido!='V');
				
			}catch(Exception ex) {
				
				viewCadastro.exibeErro("Preencha a linha e a coluna com valores inteiros");
				--i;
				continue;
			}
			//true = horizontal
			//false = vertical
			if(sentido == 'H') {
				try {
					if(!modelJogador.getMarDeGuerra().adicionar(e[i], true, new Coordenada(linha,coluna))) {
						--i;
						viewCadastro.exibeErro("Ha um choque de coordenadas");
						continue;
					}else {
						viewCadastro.exibeSucesso(e[i].getNome()+" adicionado com sucesso");
					}
				}catch(SobreposicaoEmbarcacaoException ex) {
					viewCadastro.exibeErro(ex.getMessage());
					--i;
					continue;
				}
			}else {
				try {
					if(!modelJogador.getMarDeGuerra().adicionar(e[i], false, new Coordenada(linha,coluna))) {
						viewCadastro.exibeErro("Ha um choque de coordenadas");
						--i;
						continue;
					}else {
						viewCadastro.exibeSucesso(e[i].getNome()+" adicionado com sucesso");
					}
				}catch(SobreposicaoEmbarcacaoException ex) {
					viewCadastro.exibeErro(ex.getMessage());
					--i;
					continue;
				}
			}
			
		}
		
	}
	
	
	
}
