package execucao;

import java.util.Scanner;

import estrutura_processos.DocumentoProcesso;
import estrutura_processos.ListaDuplamenteEncadeada;

public class Formularios {
	
	private static Scanner sc = new Scanner(System.in);
	
	public static int menu(){
		
		System.out.println("Transportadora");
		System.out.println("1 - Cadastrar documento");
		System.out.println("2 - Remover processo");
		System.out.println("3 - Recolher documento para transportar");
		System.out.println("0 - sair");
		int opcao = sc.nextInt();
		sc.nextLine();
		return opcao;
		
	}
	
	public static String preencheVendedor(){
		
		System.out.print("Digite o nome do vendedor: ");
		return sc.nextLine();
		
	}
	
	public static String preencheComprador(){
		System.out.print("Digite o nome do comprador: ");
		return sc.nextLine();
	}
	
	public static double preenchePeso(){
		while(true){
			try{
				System.out.print("Digite o peso da encomenda: ");
				double peso =  sc.nextDouble();
				sc.nextLine();
				return peso;
			}catch(Exception e){
				System.out.println("Dado invalido");
				sc.nextLine();
			}
			
		}
		
	}
	
	public static boolean preencheTipoEncomenda(){
		char choice;
		while(true){
			System.out.print("Entrega rapida? S para SIM e N para N: ");
			choice = sc.nextLine().charAt(0);
			if(choice == 'S'){
				return true;
			}else if(choice == 'N'){
				return false;
			}else{
				System.out.println("Entrada invalida");
			}
			
		}
	}
	
	public static String preencheDetalhes(){
		System.out.print("Detalhes da encomenda: ");
		return sc.nextLine();
	}
	
	public static String preencherNome(){
		
		System.out.print("Digite seu nome cadastrado: ");
		return sc.nextLine();
		
	}
	
	public static int preencherCpf(){
		
		while(true){
			System.out.print("Digite seu cpf: ");
			int cpf = sc.nextInt();
			sc.nextLine();
			if(cpf>0)
				return cpf;
			else
				System.out.println("Entrada invalida");
		}
		
	}
	
	public static int preencherSenha(){
		
		System.out.print("Digite sua senha: ");
		int senha = sc.nextInt();
		sc.nextLine();
		return senha;
		
	}
	
	public static int preencherPrioridade(ListaDuplamenteEncadeada<DocumentoProcesso> listaProcessos,
			String nomeSecretario){
		
		System.out.println("--Dados do documento--");
		System.out.println("Id: "+listaProcessos.getFim().getElement().getIdCode());
		System.out.println("Encarregado: "+nomeSecretario);
		System.out.println("Comprador: "+listaProcessos.getFim().getElement().getComprador());
		System.out.println("Vendedor: "+listaProcessos.getFim().getElement().getVendedor());
		System.out.println("Peso: "+listaProcessos.getFim().getElement().getPeso());
		System.out.println("Detalhes:\n"+listaProcessos.getFim().getElement().getDetalhes());
		
		if(listaProcessos.getFim().getElement().isEnvioRapido()){
			System.out.println("Encomenda para envio Rapido");
		}else{
			System.out.println("Encomenda para envio comum");
		}
		
		int prioridade = -1;
		while(prioridade<0 || prioridade > 100){
			System.out.print("Defina a prioridade do documento: ");
			prioridade = sc.nextInt();
			sc.nextLine();
			if(prioridade<0 || prioridade > 100){
				System.out.println("Prioridade invalida");
			}
		}
		return prioridade;
		
	}
	
}
