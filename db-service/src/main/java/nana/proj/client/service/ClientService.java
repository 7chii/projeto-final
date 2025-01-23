package nana.proj.client.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nana.proj.client.exception.EntityNotFoundException;
import nana.proj.client.model.ClienteModel;
import nana.proj.client.repository.ClientRepository;

@Service
public class ClientService {
	@Autowired
	private ClientRepository repository;
	
	@Transactional(readOnly = true)
	public ClienteModel buscarPorId(Long id) {
		return repository.findById(id).orElseThrow(
				() -> new EntityNotFoundException(String.format("Usuario id=%s nao encontrado", id)));
	}
	public ClienteModel salvar(ClienteModel cliente) {
		return repository.save(cliente);
	}
	public ClienteModel updateDados(Long id,ClienteModel cliente) {
		ClienteModel client = repository.findById(id).orElseThrow(
				() -> new EntityNotFoundException(String.format("Usuario id=%s nao encontrado", id)));
		client.setNome(cliente.getNome());
		client.setSaldoCc(cliente.getSaldoCc());
		client.setTelefone(cliente.getTelefone());
		return repository.save(client);
	}
	public void deleteDados(Long id) {
		ClienteModel client = repository.findById(id).orElseThrow(
				() -> new EntityNotFoundException(String.format("Usuario id=%s nao encontrado", id)));
		repository.delete(client);
	}
	public ClienteModel buscarPorNome(String nome) {
	try {
		return repository.findByNome(nome);
	} catch (EntityNotFoundException e) {
		throw new EntityNotFoundException(String.format("Usuario nome=%s nao encontrado", nome));
	}
	}
}
