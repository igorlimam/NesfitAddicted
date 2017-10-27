package pessoal_transportadora;

import estrutura_processos.DocumentoProcesso;
import estrutura_processos.HeapMax;
import estrutura_processos.ListaDuplamenteEncadeada;

public class Secretario extends Credenciais{

	private ListaDuplamenteEncadeada<DocumentoProcesso> lista;
	
	public Secretario(String nome,int cpf,int senha,
			ListaDuplamenteEncadeada<DocumentoProcesso> lista){
		
		super.setNome(nome);
		super.setCpf(cpf);
		super.setSenha(senha);
		super.setIsSecretario(true);
		setLista(lista);
		
	}

	public ListaDuplamenteEncadeada<DocumentoProcesso> getLista() {
		return lista;
	}

	public void setLista(ListaDuplamenteEncadeada<DocumentoProcesso> lista) {
		this.lista = lista;
	}
	
	public void removerDocumentoProcesso(HeapMax heap,int tokenPrioridade){
		
		DocumentoProcesso doc = lista.remover();
		if(doc!=null){
			doc.setEncarregado(super.getNome());
			doc.setPrioridade(tokenPrioridade);
			heap.inserir(doc);
		}
		
	}
	
}
