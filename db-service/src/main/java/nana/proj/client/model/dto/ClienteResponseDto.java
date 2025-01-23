package nana.proj.client.model.dto;

public class ClienteResponseDto {
	
	private Long id;
	private String nome;
	private String telefone;
	private Boolean correntista;
	private float saldoCc;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public ClienteResponseDto(Long id, String nome, String telefone, Boolean correntista, float saldoCc) {
		this.id = id;
		this.nome = nome;
		this.telefone = telefone;
		this.correntista = correntista;
		this.saldoCc = saldoCc;
	}
	public ClienteResponseDto() {
	}
	
}
