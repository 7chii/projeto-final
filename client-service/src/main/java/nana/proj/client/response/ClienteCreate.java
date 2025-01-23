package nana.proj.client.response;

public class ClienteCreate {
	private String nome;
	private String telefone;
	private Boolean correntista;
	private float saldoCc;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public Boolean getCorrentista() {
		return correntista;
	}
	public void setCorrentista(Boolean correntista) {
		this.correntista = correntista;
	}
	public float getSaldoCc() {
		return saldoCc;
	}
	public void setSaldoCc(float saldoCc) {
		this.saldoCc = saldoCc;
	}
	public ClienteCreate(String nome, String telefone, Boolean correntista, float saldoCc) {
		this.nome = nome;
		this.telefone = telefone;
		this.correntista = correntista;
		this.saldoCc = saldoCc;
	}
	public ClienteCreate() {
	}
	
}
