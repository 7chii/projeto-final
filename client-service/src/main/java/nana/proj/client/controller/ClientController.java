package nana.proj.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import nana.proj.client.proxy.DbProxy;
import nana.proj.client.response.ClienteCreate;
import nana.proj.client.response.ClienteResponse;

@CrossOrigin(origins = "http://localhost:8765")
@Tag(name= "cliente endpoint")
@RestController
@RequestMapping("cliente-service")
public class ClientController {
	
	@Autowired
	private DbProxy proxy;
	
	@Operation(summary = "Localizar um cliente por ID"
			, description = "Recurso para localizar um cliente por ID",
			responses = {
					@ApiResponse(responseCode = "200"
							, description = "Recurso localizado com sucesso",
							content = @Content(mediaType = " application/json;charset=UTF-8"
							, schema = @Schema(implementation= ClienteResponse.class))),
					@ApiResponse(responseCode = "404"
							, description = "Recurso nao encontrado",
							content = @Content(mediaType = " application/json;charset=UTF-8"
							, schema = @Schema(implementation= nana.proj.client.response.ErrorMessage.class)))
			})
	@GetMapping("/{id}")
	public ResponseEntity<ClienteResponse> encontrarClientePorId(@PathVariable Long id) {
		ClienteResponse cliente = proxy.getCliente(id);
		cliente.setScoreCredito((float) (cliente.getSaldoCc() * 0.1));
		return ResponseEntity.ok(cliente);
	}
	
	@Operation(summary = "Localizar um cliente por Nome"
			, description = "Recurso para localizar um cliente por nome",
			responses = {
					@ApiResponse(responseCode = "200"
							, description = "Recurso localizado com sucesso",
							content = @Content(mediaType = " application/json;charset=UTF-8"
							, schema = @Schema(implementation= ClienteResponse.class))),
					@ApiResponse(responseCode = "404"
							, description = "Recurso nao encontrado",
							content = @Content(mediaType = " application/json;charset=UTF-8"
							, schema = @Schema(implementation= nana.proj.client.response.ErrorMessage.class)))
			})
	@GetMapping("/getByNome/{nome}")
	public ResponseEntity<ClienteResponse> encontrarClientePorNome(@PathVariable String nome){
		ClienteResponse cliente = proxy.getClienteByNome(nome);
		cliente.setScoreCredito((float) (cliente.getSaldoCc() * 0.1));
		return ResponseEntity.ok(cliente);
	}
	@Operation(summary = "Cadastrar um novo cliente"
			, description = "Recurso para cadastrar um cliente",
			responses = {
					@ApiResponse(responseCode = "201"
							, description = "Recurso criado com sucesso",
							content = @Content(mediaType = " application/json;charset=UTF-8"
							, schema = @Schema(implementation= ClienteResponse.class))),
					@ApiResponse(responseCode = "422"
							, description = "Dados invalidos",
							content = @Content(mediaType = " application/json;charset=UTF-8"
							, schema = @Schema(implementation= nana.proj.client.response.ErrorMessage.class)))
			})
	@PostMapping
	public ResponseEntity<ClienteResponse> criarCliente(@RequestBody ClienteCreate cliente){
		proxy.create(cliente);
		ClienteResponse client = proxy.getClienteByNome(cliente.getNome());
		return ResponseEntity.status(201).body(client);
	}
	@Operation(summary = "Deletar um cliente por ID"
			, description = "Recurso para deletar um cliente por ID",
			responses = {
					@ApiResponse(responseCode = "200"
							, description = "Recurso deletado com sucesso",
							content = @Content(mediaType = " application/json;charset=UTF-8"
							, schema = @Schema(implementation= ClienteResponse.class))),
					@ApiResponse(responseCode = "404"
							, description = "Recurso nao encontrado",
							content = @Content(mediaType = " application/json;charset=UTF-8"
							, schema = @Schema(implementation= nana.proj.client.response.ErrorMessage.class)))
			})
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletarCliente(@PathVariable Long id){
		return proxy.deleteEntry(id);
	}
	@Operation(summary = "Atualizar um cliente por ID"
			, description = "Recurso para atualizar um cliente por ID",
			responses = {
					@ApiResponse(responseCode = "200"
							, description = "Recurso alterado com sucesso",
							content = @Content(mediaType = " application/json;charset=UTF-8"
							, schema = @Schema(implementation= ClienteResponse.class))),
					@ApiResponse(responseCode = "404"
							, description = "Recurso nao encontrado",
							content = @Content(mediaType = " application/json;charset=UTF-8"
							, schema = @Schema(implementation= nana.proj.client.response.ErrorMessage.class))),
					@ApiResponse(responseCode = "422"
					, description = "Dados invalidos",
					content = @Content(mediaType = " application/json;charset=UTF-8"
					, schema = @Schema(implementation= nana.proj.client.response.ErrorMessage.class)))
			})
	@PatchMapping("/{id}")
	public ResponseEntity<Void> atualizarCliente(@PathVariable Long id, @RequestBody ClienteCreate cliente){
		return proxy.updateData(id, cliente);
	}
	
}
