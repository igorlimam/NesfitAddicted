package pessoal_transportadora;

public abstract class Credenciais {

	private String nome;
	private int cpf;
	private int senha;
	private boolean isSecretario = false;
	
	public String getNome() {
		return nome;
	}
	
	public int getCpf() {
		return cpf;
	}
	
	public boolean getIsSecretario(){
		return isSecretario;
	}
	
	public void setIsSecretario(boolean secretario){
		this.isSecretario = secretario;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setCpf(int cpf) {
		if(cpf >= 0)
			this.cpf = cpf;
	}
	
	public int getSenha() {
		return senha;
	}
	
	public void setSenha(int senha) {
		this.senha = senha;
	}
	
}
