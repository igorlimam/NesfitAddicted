package pessoal_transportadora;

import estrutura_processos.DocumentoProcesso;
import estrutura_processos.ListaDuplamenteEncadeada;

public class Estagiario {

	private DocumentoProcesso documento = new DocumentoProcesso();
	
	public Estagiario(){}
	
	public void preencherDocumento(String vendedor,String comprador,double peso,
			boolean isRapida,String detalhes){
		
		documento.setVendedor(vendedor);
		documento.setComprador(comprador);
		documento.setPeso(peso);
		documento.setEnvioRapido(isRapida);
		documento.setDetalhes(detalhes);
		
	}
	
	public void inserirDocumentoLista(ListaDuplamenteEncadeada<DocumentoProcesso> lista){
		lista.adicionar(documento);
	}
	
}
