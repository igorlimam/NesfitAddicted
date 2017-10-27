package estrutura_processos;

public class No<E> {
	
	private E element;
	private No<E> prox;
	private No<E> ant;
	
	public No(E element){
		this.element = element;
	}

	public E getElement() {
		return element;
	}

	public void setElement(E element) {
		this.element = element;
	}

	public No<E> getProx() {
		return prox;
	}

	public void setProx(No<E> prox) {
		this.prox = prox;
	}

	public No<E> getAnt() {
		return ant;
	}

	public void setAnt(No<E> ant) {
		this.ant = ant;
	}
	
	
	
}
