package estrutura_processos;

public class HeapMax {

	private DocumentoProcesso[] heap = new DocumentoProcesso[20];
	private int tam = 0;
	
	private void subir(int i){
		
		int j = ((i-1)/2);
		
		if(j>=0){
			if(heap[i].getPrioridade() > heap[j].getPrioridade()){
				DocumentoProcesso aux = heap[i];
				heap[i] = heap[j];
				heap[j] = aux;
				subir(j);
				
			}
		}
		
	}
	
	private void descer(int i){
		
		int j = (2*i)+1;
		if(j<tam){
			
			if(j<tam-1 && (heap[j+1].getPrioridade() > heap[j].getPrioridade())){
				j++;
			}
			
			if(heap[i].getPrioridade() < heap[j].getPrioridade()){
				DocumentoProcesso aux = heap[i];
				heap[i] = heap[j];
				heap[j] = aux;
				descer(j);
			}
			
		}
		
	}
	
	public void inserir(DocumentoProcesso doc){
		
		if(tam<heap.length){
			heap[tam] = doc;
			subir(tam);
			++tam;
		}
		
	}
	
	public DocumentoProcesso remover(){
		DocumentoProcesso rem = null;
		if(tam>0){
			rem = heap[0];
			--tam;
			heap[0] = heap[tam];
			descer(0);
		}
		return rem;
	}
	
	public boolean empty(){
		if(tam == 0)
			return true;
		return false;
	}
	
}
