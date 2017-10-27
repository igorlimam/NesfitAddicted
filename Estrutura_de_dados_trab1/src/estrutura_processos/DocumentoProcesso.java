package estrutura_processos;

public class DocumentoProcesso {
	
	private static int generator = 1;
	private int idCode;
	private int prioridade;
	private String vendedor,comprador,encarregado;
	private double peso;
	private boolean envioRapido;
	private String detalhes;
	
	
	public DocumentoProcesso(){
		idCode = generator++;
	}
	
	public int getIdCode() {
		return idCode;
	}
	public int getPrioridade() {
		return prioridade;
	}
	public String getVendedor() {
		return vendedor;
	}
	public String getComprador() {
		return comprador;
	}
	public String getEncarregado() {
		return encarregado;
	}
	public double getPeso() {
		return peso;
	}
	public boolean isEnvioRapido() {
		return envioRapido;
	}
	public String getDetalhes() {
		return detalhes;
	}
	
	public void setPrioridade(int prioridade) {
		if(prioridade>=0){
			
			this.prioridade = prioridade;
			//Aqui eh onde a heap maxima entra
		
		}
	}
	public void setVendedor(String vendedor) {
		this.vendedor = vendedor;
	}
	public void setComprador(String comprador) {
		this.comprador = comprador;
	}
	public void setEncarregado(String encarregado) {
		this.encarregado = encarregado;
	}
	public void setPeso(double peso) {
		if(peso>0.0)
			this.peso = peso;
	}
	public void setEnvioRapido(boolean envioRapido) {
		this.envioRapido = envioRapido;
	}
	public void setDetalhes(String detalhes) {
		this.detalhes = detalhes;
	}
	
	
	
}
