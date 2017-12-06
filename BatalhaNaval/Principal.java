import java.util.Scanner;

public class Principal {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Jogador jogador = new Jogador("Joao");
		
		int opcao = 0;
		//Embarcacoes
		for(int i = 0;i < 4; ++i) {
			
			System.out.println("Cadastre a "+(i+1)+" embarcacao");
			String nome;
			System.out.print("Nome da embarcacao: ");
			nome = sc.nextLine();
			int tamanho = 1;
			while(true) {
				try {
					System.out.print("Tamanho do "+nome+": ");
					tamanho = sc.nextInt();
					if(tamanho <= 0) {
						throw new Exception();
					}
					sc.nextLine();
					break;
				}catch(Exception e) {
					sc.nextLine();
					continue;
				}
			}
			int linha = -1,coluna = -1;
			char sentido;
			while(true) {
				try {
					while(linha >= 20 || linha < 0) {
						System.out.print("linha da Posicao inicial "+nome+": ");
						linha = sc.nextInt();
						sc.nextLine();
					}
					while(coluna >= 20 || coluna < 0) {
						System.out.print("Coluna da Posicao inicial "+nome+": ");
						coluna = sc.nextInt();
						sc.nextLine();
					}
					while(true) {
						System.out.print("Sentido da embarcacao V - vertical, H - Horizontal: ");
						sentido = sc.nextLine().charAt(0);
						if(sentido == 'V') {
							
							if(!jogador.getMarDeGuerra().adicionar(new Embarcacao(nome,tamanho), false, 
									new Coordenada(linha,coluna))) {
								--i;
								System.out.println("Impossivel");
								break;
							}
							System.out.println(nome+" adcicionado com sucesso");
							break;
						}else if(sentido == 'H') {
							if(!jogador.getMarDeGuerra().adicionar(new Embarcacao(nome,tamanho), true, 
									new Coordenada(linha,coluna))) {
								--i;
								System.out.println("Impossivel");
								break;
							}
							System.out.println(nome+" adcicionado com sucesso");
							break;
						}
					}
					break;
				}catch(Exception e) {
					sc.nextLine();
					continue;
				}
			}
			
			
		}
		
		//Maquina
		Maquina maquina = new Maquina();
	}
	
}
