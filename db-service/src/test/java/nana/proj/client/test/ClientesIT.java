package nana.proj.client.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

import nana.proj.client.exception.handler.ErrorMessage;
import nana.proj.client.model.dto.ClienteCreateDto;
import nana.proj.client.model.dto.ClienteResponseDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = "../resources/sql/clientes-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "../resources/sql/clientes-delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class ClientesIT {
	@Autowired
	WebTestClient testClient;
	@Test
	public void createCliente_ComDadosValidos_RetornarClienteCriadoComStatus201(){
		ClienteResponseDto responseBody = testClient
				.post()
				.uri("/db-service")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.bodyValue(new ClienteCreateDto("mariana pacheco cordeiro", "62983231822", true, 5000))
				.exchange()
				.expectStatus().isCreated()
				.expectBody(ClienteResponseDto.class)
				.returnResult().getResponseBody();
		org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
		org.assertj.core.api.Assertions.assertThat(responseBody.getId()).isNotNull();
		org.assertj.core.api.Assertions.assertThat(responseBody.getNome()).isEqualTo("mariana pacheco cordeiro");
		org.assertj.core.api.Assertions.assertThat(responseBody.getTelefone()).isEqualTo("62983231822");
		
	}
	@Test
	public void createCliente_dadosInvalidos_retornarErrorMessageStatus422() {
		ErrorMessage responseBody = testClient
				.post()
				.uri("/db-service")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.bodyValue(new ClienteCreateDto("", "", null, 5000))
				.exchange()
				.expectStatus().isEqualTo(422)
				.expectBody(ErrorMessage.class)
				.returnResult().getResponseBody();
		org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);

        responseBody = testClient
				.post()
				.uri("/db-service")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.bodyValue(new ClienteCreateDto("mariana pacheco", "6298323", true, 5000))
				.exchange()
				.expectStatus().isEqualTo(422)
				.expectBody(ErrorMessage.class)
				.returnResult().getResponseBody();
		org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);

        responseBody = testClient
				.post()
				.uri("/db-service")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.bodyValue(new ClienteCreateDto("mari", "23232322323", true, 0))
				.exchange()
				.expectStatus().isEqualTo(422)
				.expectBody(ErrorMessage.class)
				.returnResult().getResponseBody();
		org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);

	}
	@Test
	public void buscarClientePorId_retornarClienteEStatus200() {
		ClienteResponseDto responseBody = testClient
				.get()
				.uri("/db-service/101")
				.exchange()
				.expectStatus().isOk()
				.expectBody(ClienteResponseDto.class)
				.returnResult().getResponseBody();
		org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
		org.assertj.core.api.Assertions.assertThat(responseBody.getId()).isNotNull();
		org.assertj.core.api.Assertions.assertThat(responseBody.getNome()).isEqualTo("maria julia da silva");
		org.assertj.core.api.Assertions.assertThat(responseBody.getTelefone()).isEqualTo("21991840656");
	}
	@Test
	public void buscarClientePorNome_retornarClienteEStatus200() {
		ClienteResponseDto responseBody = testClient
				.get()
				.uri("/db-service/getByNome/maria julia da silva")
				.exchange()
				.expectStatus().isOk()
				.expectBody(ClienteResponseDto.class)
				.returnResult().getResponseBody();
		org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
		org.assertj.core.api.Assertions.assertThat(responseBody.getId()).isNotNull();
		org.assertj.core.api.Assertions.assertThat(responseBody.getNome()).isEqualTo("maria julia da silva");
		org.assertj.core.api.Assertions.assertThat(responseBody.getTelefone()).isEqualTo("21991840656");
	}
	@Test
	public void buscarClientePorId_idInexistente_retornarErrorMessageEStatus404() {
		ErrorMessage responseBody = testClient
				.get()
				.uri("/db-service/0")
				.exchange()
				.expectStatus().isEqualTo(404)
				.expectBody(ErrorMessage.class)
				.returnResult().getResponseBody();
		org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
		org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(404);
	}
	@Test
	public void buscarClientePorNome_nomeInexistente_retornarErrorMessageEStatus404() {
		ErrorMessage responseBody = testClient
				.get()
				.uri("/db-service/getByNome/maripaper1111")
				.exchange()
				.expectStatus().isEqualTo(404)
				.expectBody(ErrorMessage.class)
				.returnResult().getResponseBody();
		org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
		org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(404);
	}
	@Test
	public void atualizarClientePorIdEBody_dadosValidos_retornarStatus204() {
		testClient
		.patch()
		.uri("/db-service/101")
		.contentType(MediaType.APPLICATION_JSON)
		.bodyValue(new ClienteCreateDto("julia maria da silva", "21991840653", true, 7000))
		.exchange()
		.expectStatus().isNoContent();
	}
	@Test
	public void atualizarClientePorIdEBody_dadosInvalidos_retornarErrorMessageEStatus422() {
		ErrorMessage responseBody = testClient
				.patch()
				.uri("/db-service/101")
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(new ClienteCreateDto("", "", true, 0))
				.exchange()
				.expectStatus().isEqualTo(422)
				.expectBody(ErrorMessage.class)
				.returnResult().getResponseBody();
		org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);
        
        responseBody = testClient
				.patch()
				.uri("/db-service/101")
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(new ClienteCreateDto("maria", "232323", null, 0))
				.exchange()
				.expectStatus().isEqualTo(422)
				.expectBody(ErrorMessage.class)
				.returnResult().getResponseBody();
		org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);
	}
	@Test
	public void atualizarClientePorIdEBody_dadosValidosEIdInvalido_retornarErrorMessageEStatus404(){
		ErrorMessage responseBody = testClient
				.patch()
				.uri("/db-service/0")
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(new ClienteCreateDto("mariana pacheco cordeiro", "92929292929", true, 2333))
				.exchange()
				.expectStatus().isEqualTo(404)
				.expectBody(ErrorMessage.class)
				.returnResult().getResponseBody();
		org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(404);
        
	}
	@Test
	public void deletarClientePorId_retornarStatus200() {
		String responseBody = testClient
				.delete()
				.uri("/db-service/101")
				.exchange()
				.expectStatus().isOk()
				.expectBody(String.class)
				.returnResult().getResponseBody();
		org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
		org.assertj.core.api.Assertions.assertThat(responseBody).isEqualTo("entrada deletada.");
	}
	@Test
	public void deletarClientePorId_invalido_retornarErrorMessageEStatus404() {
		ErrorMessage responseBody = testClient
				.delete()
				.uri("/db-service/0")
				.exchange()
				.expectStatus().isNotFound()
				.expectBody(ErrorMessage.class)
				.returnResult().getResponseBody();
		org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
		org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(404);
	}
}
