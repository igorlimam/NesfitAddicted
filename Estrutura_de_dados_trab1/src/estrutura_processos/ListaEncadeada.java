package estrutura_processos;

public class ListaEncadeada<E> {

	private No<E> INICIO;
	private No<E> FIM;
	private int tam = 0;
	
	//Item A
	private void addIni(E element){
		
		if(tam == 0){
			instanciaInicio(element);
		}else{
			
			No<E> ref = new No<>(element);
			ref.setElement(element);
			ref.setProx(INICIO);
			INICIO = ref;
			
		}
		++tam;
	}
	
	//Item B
	public void adicionar(E element){
		
		if(tam == 0){
			instanciaInicio(element);
		}else{
			No<E> ref = new No<E>(element);
			FIM.setProx(ref);
			FIM = ref;
			
		}
		++tam;
		
	}
	
	private void instanciaInicio(E element){
		this.INICIO = new No<>(element);
		FIM = INICIO;
	}
	
	private boolean isPos(Integer element){
		if(element >= 0){
			return true;
		}else{
			System.out.println("Nao permitido numero negativo");
			return false;
		}
	}
	
	//Item C
	public void adicionar(E element,int posicao){
		
		if(element instanceof Integer){
			if(!isPos((Integer)element)){
				return;
			}
		}
		
		if(posicao >= 0 && posicao<=tam){
			
			if(posicao == 0){
				addIni(element);
			}else if(posicao == tam){
				adicionar(element);
			}else{
				
				No<E> ref = buscarElement(posicao);
				No<E> aux = new No<>(element);
				
				aux.setProx(ref.getProx());
				ref.setProx(aux);
				++tam;
				
			}
			
		}else{
			System.out.println("Posicao invalida. 2FAB4U");
		}
		
	}
	
	public No<E> getInicio(){
		return INICIO;
	}
	
	private No<E> buscarElement(int posicao){
		
		No<E> ref = INICIO;
		
		for(int i = 1;i<posicao;++i){
			ref = ref.getProx();
		}
		
		return ref;
		
	}
	
	public No<E> buscarElementoHash(int posicao){
		
		if(posicao>=0 && posicao<tam){
			if(tam == 0){
				return null;
			}else{
				if(posicao == 0)
					return buscarElement(posicao);
				else
					return buscarElement(posicao).getProx();
			}
		}else{
			return null;
		}
		
	}
	
	//Item D
	private E removerIni(){
		
		if(tam>0){
			E aux = INICIO.getElement();
			
			INICIO = INICIO.getProx();
			--tam;
			
			return aux;
		}else{
			System.out.println("Nao ha elementos");
			return null;
		}
		
	}
	
	//Item E
	public E remover(){
		
		if(tam>1){
			No<E> ref = buscarElement(tam-1);
			E aux = ref.getProx().getElement();
			ref.setProx(null);
			FIM = ref;
			--tam;
			
			return aux;
			
		}else if(tam == 1){
			
			E aux = INICIO.getElement();
			FIM = INICIO = null;
			--tam;
			return aux;
			
		}else{
			System.out.println("Nao ha elementos");
			return null;
		}
		
	}
	
	//Item F
	public E remover(int posicao){
		
		if(tam>0){
			
			if(posicao>=0 && posicao<tam){
				
				if(posicao == 0){
					return this.removerIni();
				}else if(posicao == tam-1){
					return this.remover();
				}else{
					
					No<E> ref = buscarElement(posicao);
					E aux  = ref.getProx().getElement();
					ref.setProx(ref.getProx().getProx());
					--tam;
					
					return aux;
					
				}
				
			}else{
				System.out.println("Posicao invalida. 24HEAD4U");
				return null;
			}
			
		}else{
			System.out.println("Nao ha elementos");
			return null;
		}
		
	}
	
	//Item G
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
	
	//Item H
	public int length(){
		return tam;
	}
	
	//3.3
	public String imprime(){
		if(INICIO == null){
			return "[]";
		}else{
			return "["+imprimeR(INICIO.getProx())+INICIO.getElement()+"]";
		}
	}
	private String imprimeR(No<E> el){
		if(el == null){
			return "";
		}else if(el.getProx() == null){
			return el.getElement().toString()+", ";
		}else{
			return imprimeR(el.getProx())+el.getElement().toString()+", ";
		}
	}
	
}
