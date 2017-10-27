package execucao;

import pessoal_transportadora.Credenciais;
import pessoal_transportadora.Estagiario;
import pessoal_transportadora.Secretario;
import pessoal_transportadora.Transportador;
import estrutura_processos.DocumentoProcesso;
import estrutura_processos.Hash;
import estrutura_processos.HeapMax;
import estrutura_processos.ListaDuplamenteEncadeada;

public class Principal {

	public static void main(String[] caverna_do_pytao) {
		
		ListaDuplamenteEncadeada<DocumentoProcesso> listaProcessos;
		listaProcessos= new ListaDuplamenteEncadeada<>();
		HeapMax heapPrioridades = new HeapMax();
		Hash hash = new Hash();
		
		//Demora demais
		/*
		System.out.println("-Secretarios-");
		for(int i = 0;i<3;++i){
			System.out.println("Secretario(a) "+(i+1));
			Secretario sec = new Secretario(Formularios.preencherNome(),
					Formularios.preencherCpf(),Formularios.preencherSenha(),listaProcessos);
			hash.inserir(sec);
		}
		
		System.out.println("\n\n-Transportadores(as)-");
		for(int i = 0;i<5;++i){
			System.out.println("Transportador(a) "+(i+1));
			Transportador transportador = new Transportador(
					Formularios.preencherNome(),Formularios.preencherCpf(),
					Formularios.preencherSenha(),heapPrioridades);
			hash.inserir(transportador);
		}
		*/
		
		Secretario sec = new Secretario("Jerry Smith",123456,1,listaProcessos);
		hash.inserir(sec);
		sec = new Secretario("Ilusive Man",654321,1,listaProcessos);
		hash.inserir(sec);
		sec = new Secretario("esqueciOCpf",147258,1,listaProcessos);
		hash.inserir(sec);
		
		Transportador trans = new Transportador("Hermes",400289,22,heapPrioridades);
		hash.inserir(trans);
		trans = new Transportador("Papaleguas",444444,1,heapPrioridades);
		hash.inserir(trans);
		trans = new Transportador("XLR8",789456,1,heapPrioridades);
		hash.inserir(trans);
		trans = new Transportador("Barry Allen",999999,123,heapPrioridades);
		hash.inserir(trans);
		trans = new Transportador("Jason atras de voce",667766,123,heapPrioridades);
		hash.inserir(trans);
		
		int opcao = 0;
		do{
			
			opcao = Formularios.menu();
			
			switch(opcao){
				case 1:
					
					Estagiario estagiaro = new Estagiario();
					
					estagiaro.preencherDocumento(Formularios.preencheVendedor(),
							Formularios.preencheComprador(),
							Formularios.preenchePeso(),
							Formularios.preencheTipoEncomenda(),
							Formularios.preencheDetalhes());
					
					estagiaro.inserirDocumentoLista(listaProcessos);
					
					System.out.println("Adicionado com sucesso!");
					
					break;
				case 2:
					
					if(listaProcessos.length() == 0){
						System.out.println("Não ha documentos para serem retirados");
						break;
					}
					
					System.out.println("-Autenticacao-");
					Credenciais credenciaisSecretario = hash.autenticarUsuario(Formularios.preencherCpf(),
							Formularios.preencherSenha());
					
					
					if(credenciaisSecretario == null){
						System.out.println("CPF e/ou senha invalidos");
						break;
					}else if(!credenciaisSecretario.getIsSecretario()){
						System.out.println("Nao eh secretario");
						break;
					}
					
					Secretario secretario = (Secretario)credenciaisSecretario;
					
					System.out.println(secretario.getIsSecretario());
					secretario.removerDocumentoProcesso(heapPrioridades, 
							Formularios.preencherPrioridade(listaProcessos,secretario.getNome()));
					
					System.out.println("Prioridade atribuida com sucesso!");
					
					break;
				case 3:
					
					if(heapPrioridades.empty()){
						System.out.println("Nao ha documentos a espera de transporte");
						break;
					}
					
					System.out.println("-Autenticacao-");
					Credenciais credenciaisTransportador = hash.autenticarUsuario(
							Formularios.preencherCpf(), Formularios.preencherSenha());
					
					if(credenciaisTransportador == null){
						System.out.println("CPF e/ou senha invalidos");
					}else if(credenciaisTransportador.getIsSecretario()){
						System.out.println("Nao eh transportador");
					}else{
						Transportador transportador = (Transportador)credenciaisTransportador;
						System.out.println(transportador.removerDocumentoProcesso());
						System.out.println("Encomenda enviada para transporte!");
					}
					
					break;
					
				case 0:
					System.out.println("Saindo");
					break;
				default:
					System.out.println("Opcao invalida >: (");
					
					
			}
			
		}while(opcao!=0);
		
		
		
		
	}
	
}
