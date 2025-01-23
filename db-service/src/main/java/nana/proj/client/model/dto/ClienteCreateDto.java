package nana.proj.client.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ClienteCreateDto {
	
	
	@NotBlank(message = "nome nao pode ser vazio")
	@Size(min = 10, max = 50, message = "nome deve ter entre 10 e 50 caracteres")
	private String nome;
	
	@NotBlank(message = "telefone nao pode ser vazio.")
	@Size(min=11, max=11, message = "telefone deve ter 11 caracteres")
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
	public ClienteCreateDto(String nome, String telefone, Boolean correntista, float saldoCc) {
		this.nome = nome;
		this.telefone = telefone;
		this.correntista = correntista;
		this.saldoCc = saldoCc;
	}
	public ClienteCreateDto() {
	}
	
	

}
