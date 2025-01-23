package nana.proj.client.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import nana.proj.client.response.ClienteCreate;
import nana.proj.client.response.ClienteResponse;
@Configuration
@FeignClient(name="db-service")
public interface DbProxy {

	@GetMapping("/db-service/{id}")
	public ClienteResponse getCliente(@PathVariable long id);
	
	@GetMapping("/db-service/getByNome/{nome}")
	public ClienteResponse getClienteByNome(@PathVariable String nome);
	
	@PostMapping("/db-service")
	public ClienteResponse create(@RequestBody ClienteCreate cliente);
	
	@PatchMapping("/db-service/{id}")
	public ResponseEntity<Void> updateData(@PathVariable Long id, @RequestBody ClienteCreate cliente);
	
	@DeleteMapping("/db-service/{id}")
	public ResponseEntity<String> deleteEntry(@PathVariable Long id);
	
}
