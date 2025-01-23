package nana.proj.client.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity(name= "clientes")
public class ClienteModel implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@jakarta.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(unique=true,nullable = false, length = 50)
	private String nome;
	@Column(nullable = false, length = 11)
	private String telefone;
	@Column(nullable = false)
	private Boolean correntista;
	@Column(name="saldo_cc", nullable = false)
	private float saldoCc;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		return Objects.hash(correntista, id, nome, saldoCc, telefone);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClienteModel other = (ClienteModel) obj;
		return Objects.equals(correntista, other.correntista) && id == other.id && Objects.equals(nome, other.nome)
				&& Float.floatToIntBits(saldoCc) == Float.floatToIntBits(other.saldoCc)
				&& Objects.equals(telefone, other.telefone);
	}
	public ClienteModel() {
	}
	public ClienteModel(long id, String nome, String telefone, Boolean correntista, float saldoCc) {
		this.id = id;
		this.nome = nome;
		this.telefone = telefone;
		this.correntista = correntista;
		this.saldoCc = saldoCc;
	}
	
	
	
}
