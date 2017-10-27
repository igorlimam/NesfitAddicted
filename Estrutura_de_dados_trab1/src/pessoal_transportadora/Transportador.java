package pessoal_transportadora;

import estrutura_processos.DocumentoProcesso;
import estrutura_processos.HeapMax;

public class Transportador extends Credenciais{

	HeapMax heapPrioridade;
	
	public Transportador(String nome,int cpf,int senha,HeapMax heap){
		super.setCpf(cpf);
		super.setNome(nome);
		super.setSenha(senha);
		super.setIsSecretario(false);
		setHeapPrioridade(heap);
	}
	
	public String removerDocumentoProcesso(){
		
		StringBuilder str = new StringBuilder();
		
		str.append("Dados do documento recebido\n");
		DocumentoProcesso ref = heapPrioridade.remover();
		str.append("Transportador: "+super.getNome()+"\n");
		str.append("Id: "+ref.getIdCode()+"\n");
		str.append("Encarregado: "+ref.getEncarregado()+"\n");
		str.append("Comprador: "+ref.getComprador()+"\n");
		str.append("Vendedor: "+ref.getVendedor()+"\n");
		str.append("Peso: "+ref.getPeso()+"\n");
		str.append("Detalhes:\n "+ref.getDetalhes()+"\n");
		
		if(ref.isEnvioRapido()){
			str.append("Encomenda para envio Rapido\n");
		}else{
			str.append("Encomenda para envio comum\n");
		}
		return str.toString();
		
	}

	public HeapMax getHeapPrioridade() {
		return heapPrioridade;
	}

	public void setHeapPrioridade(HeapMax heapPrioridade) {
		this.heapPrioridade = heapPrioridade;
	}
	
	
	
}
