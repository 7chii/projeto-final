package nana.proj.client.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import jakarta.validation.Valid;
import nana.proj.client.exception.EntityNotFoundException;
import nana.proj.client.exception.handler.ErrorMessage;
import nana.proj.client.model.ClienteModel;
import nana.proj.client.model.dto.ClienteCreateDto;
import nana.proj.client.model.dto.ClienteResponseDto;
import nana.proj.client.model.dto.mapper.ClienteMapper;
import nana.proj.client.service.ClientService;

@CrossOrigin(origins = "http://localhost:8765")
@Tag(name = "db endpoint")
@RestController
@RequestMapping("/db-service")
public class ClientController {
	@Autowired
	private ClientService service;
	
	@Operation(summary = "Localizar um cliente por ID"
			, description = "Recurso para localizar um cliente por ID",
			responses = {
					@ApiResponse(responseCode = "200"
							, description = "Recurso localizado com sucesso",
							content = @Content(mediaType = " application/json;charset=UTF-8"
							, schema = @Schema(implementation= ClienteModel.class))),
					@ApiResponse(responseCode = "404"
							, description = "Recurso nao encontrado",
							content = @Content(mediaType = " application/json;charset=UTF-8"
							, schema = @Schema(implementation= ErrorMessage.class)))
			})
	@GetMapping("/{id}")
	public ResponseEntity<ClienteModel> getCliente(@PathVariable long id) {
		ClienteModel cliente = service.buscarPorId(id);
		return ResponseEntity.ok(cliente);
	}
	@Operation(summary = "Localizar um cliente por Nome"
			, description = "Recurso para localizar um cliente por nome",
			responses = {
					@ApiResponse(responseCode = "200"
							, description = "Recurso localizado com sucesso",
							content = @Content(mediaType = " application/json;charset=UTF-8"
							, schema = @Schema(implementation= ClienteModel.class))),
					@ApiResponse(responseCode = "404"
							, description = "Recurso nao encontrado",
							content = @Content(mediaType = " application/json;charset=UTF-8"
							, schema = @Schema(implementation= ErrorMessage.class)))
			})
	@GetMapping("/getByNome/{nome}")
	public ResponseEntity<ClienteResponseDto> getClienteByNome(@PathVariable String nome){
		try {
			ClienteModel cliente = service.buscarPorNome(nome);
			return ResponseEntity.ok(ClienteMapper.toDto(cliente));
		} catch (java.lang.IllegalArgumentException ex) {
			throw new EntityNotFoundException("entrada nao encontrada");
		}
		
	}
	@Operation(summary = "Cadastrar um novo cliente"
			, description = "Recurso para cadastrar um cliente",
			responses = {
					@ApiResponse(responseCode = "201"
							, description = "Recurso criado com sucesso",
							content = @Content(mediaType = " application/json;charset=UTF-8"
							, schema = @Schema(implementation= ClienteModel.class))),
					@ApiResponse(responseCode = "422"
							, description = "Dados invalidos",
							content = @Content(mediaType = " application/json;charset=UTF-8"
							, schema = @Schema(implementation= ErrorMessage.class)))
			})
	@PostMapping
	public ResponseEntity<ClienteResponseDto> create(@Valid @RequestBody ClienteCreateDto cliente) throws org.springframework.web.bind.MethodArgumentNotValidException{
		ClienteModel client = service.salvar(ClienteMapper.toCliente(cliente));
		return ResponseEntity.status(HttpStatus.CREATED).body(ClienteMapper.toDto(client));
	}
	@Operation(summary = "Atualizar um cliente por ID"
			, description = "Recurso para atualizar um cliente por ID",
			responses = {
					@ApiResponse(responseCode = "200"
							, description = "Recurso alterado com sucesso",
							content = @Content(mediaType = " application/json;charset=UTF-8"
							, schema = @Schema(implementation= ClienteModel.class))),
					@ApiResponse(responseCode = "404"
							, description = "Recurso nao encontrado",
							content = @Content(mediaType = " application/json;charset=UTF-8"
							, schema = @Schema(implementation= ErrorMessage.class))),
					@ApiResponse(responseCode = "422"
					, description = "Dados invalidos",
					content = @Content(mediaType = " application/json;charset=UTF-8"
					, schema = @Schema(implementation= ErrorMessage.class)))
			})
	@PatchMapping("/{id}")
	public ResponseEntity<Void> updateData(@PathVariable Long id, @Valid @RequestBody ClienteCreateDto cliente) throws org.springframework.web.bind.MethodArgumentNotValidException{
		service.updateDados(id ,ClienteMapper.toCliente(cliente));
		return ResponseEntity.noContent().build();
	}
	@Operation(summary = "Deletar um cliente por ID"
			, description = "Recurso para deletar um cliente por ID",
			responses = {
					@ApiResponse(responseCode = "200"
							, description = "Recurso deletado com sucesso",
							content = @Content(mediaType = " application/json;charset=UTF-8"
							, schema = @Schema(implementation= ClienteModel.class))),
					@ApiResponse(responseCode = "404"
							, description = "Recurso nao encontrado",
							content = @Content(mediaType = " application/json;charset=UTF-8"
							, schema = @Schema(implementation= ErrorMessage.class)))
			})
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteEntry(@PathVariable Long id){
			service.deleteDados(id);
			return ResponseEntity.ok("entrada deletada.");
	}
	
}
