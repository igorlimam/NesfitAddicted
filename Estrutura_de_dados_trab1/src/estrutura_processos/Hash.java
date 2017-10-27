package estrutura_processos;

import pessoal_transportadora.*;

public class Hash {

	@SuppressWarnings("unchecked")
	private ListaEncadeada<Credenciais>[] hash = (ListaEncadeada<Credenciais>[]) new ListaEncadeada[11];
	
	public Hash(){
		for(int i = 0;i<11;++i){
			hash[i] = new ListaEncadeada<>();
		}
	}
	
	private int h(long chave){
		
		chave = ((chave/((chave%10)+1))+(long)((chave*((double)39/(double)64))))%11;
		
		return (int)chave;
	}
	
	public void inserir(Credenciais usuario){
		hash[h(usuario.getCpf())].adicionar(usuario);
	}
	
	public Credenciais remover(int chave){
		int k = h(chave);
		if(hash[k].length() == 0){
			return null;
		}else{
			int pos = this.getPosicaoCpf(chave, hash[k].getInicio()); 
			if(pos > -1){
				return hash[k].remover(pos);
			}else{
				return null;
			}
		}
		
	}
	
	public Credenciais autenticarUsuario(int cpf,int senha){
		
		int k = h(cpf);
		if(hash[k].length() == 0){
			return null;
		}else{
			int pos = getPosicaoCpf(cpf, hash[k].getInicio());
			if(pos > -1){
				No<Credenciais> no = hash[k].buscarElementoHash(pos);
				if(no == null){
					return null;
				}else{ 
					Credenciais ref = no.getElement();
					if(ref.getSenha() == senha){
						return ref;
					}else{
						return null;
					}
				}
			}else{
				return null;
			}
		}
		
	}
	
	private int getPosicaoCpf(int cpf,No<Credenciais> first){
		
		for(int i = 0;first!=null;++i){
			
			if(cpf == first.getElement().getCpf()){
				return i;
			}
			first = first.getProx();
			
		}
		return -1;
		
	}
	
}
