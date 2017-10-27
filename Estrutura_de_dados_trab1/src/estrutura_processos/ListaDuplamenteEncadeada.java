package estrutura_processos;

public class ListaDuplamenteEncadeada<E> {

	private No<E> INICIO,FIM;
	private int tam = 0;
	
	private void addIni(E element){
		
		if(tam == 0){
			instancia(element);
		}else{
			
			No<E> ref = new No<>(element);
			INICIO.setAnt(ref);
			ref.setProx(INICIO);
			INICIO = ref;
			
		}
		
		++tam;
		
	}
	
	public void adicionar(E element){
		if(tam == 0){
			instancia(element);
		}else{
			
			No<E> ref = new No<>(element);
			ref.setAnt(FIM);
			FIM.setProx(ref);
			FIM = ref;
			
		}
		++tam;
	}
	
	public void adicionar(E element,int posicao){
		
		if(posicao>=0 && posicao<=tam){
			
			if(posicao == 0){
				this.addIni(element);
			}else if(posicao == tam){
				this.adicionar(element);
			}else{
				
				No<E> ref = this.buscarPos(posicao);
				ref = ref.getAnt();
				No<E> aux = new No<>(element);
				
				aux.setAnt(ref);
				aux.setProx(ref.getProx());
				ref.getProx().setAnt(aux);
				ref.setProx(aux);
				++tam;
				
			}
			
		}else{
			System.out.println("Posicao invalida");
		}
		
	}
	
	private E removeIni(){
		if(tam > 0){
			E aux = INICIO.getElement();
			if(tam == 1){
				INICIO = FIM = null;
			}else{
				INICIO.getProx().setAnt(null);
				INICIO = INICIO.getProx();
			}
			--tam;
			return aux;
			
		}else{
			System.out.println("Nao ha elementos");
			return null;
		}
	}
	
	public E remover(){
		
		if(tam > 0){
			E aux = FIM.getElement();
			if(tam == 1){
				FIM = INICIO = null;
			}else{
				FIM.getAnt().setProx(null);
				FIM = FIM.getAnt();
			}
			--tam;
			return aux;
			
		}else{
			System.out.println("Nao ha elementos");
			return null;
		}
		
	}
	
	public No<E> getFim(){
		return FIM;
	}
	
	public E remover(int posicao){
		
		if(tam > 0){
			
			if(posicao>=0 && posicao<tam){
				
				if(posicao == 0){
					return this.removeIni();
				}else if(posicao == tam-1){
					return this.remover();
				}else{
					
					No<E> ref = buscarPos(posicao);
					E aux = ref.getElement();
					ref = ref.getAnt();
					ref.setProx(ref.getProx().getProx());
					
					if(tam>2){
						ref.getProx().setAnt(ref);
					}
					--tam;
					return aux;
				}
				
			}else{
				System.out.println("Out of range");
				return null;
			}
			
		}else{
			System.out.println("Nao ha elementos");
			return null;
		}
		
	}
	
	private void instancia(E element){
		INICIO = FIM = new No<>(element);
	}
	
	private No<E> buscarPos(int posicao){
		No<E> ref;
		if(posicao>(tam)/2){
			ref = FIM;
			for(int i = tam-1;i>posicao;--i){
				ref = ref.getProx();
			}
		}else{
			ref = INICIO;
			for(int i = 0;i<posicao;++i){
				ref = ref.getProx();
			}
		}
		
		return ref;
		
	}
	
	
	@Override
	public String toString(){
		
		StringBuilder str = new StringBuilder();
		
		str.append("[");
		if(tam > 0){
			No<E> ref = INICIO;
			while(ref.getProx()!=null){
				str.append(ref.getElement());
				str.append(", ");
				ref = ref.getProx();
			}
			str.append(ref.getElement());
		}
		str.append("]");
		
		return str.toString();
		
	}
	
	public int length(){
		return this.tam;
	}
	
}
