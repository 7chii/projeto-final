# Projeto Final

Aplicacao cliente e gerenciadora do banco de dados de contas bancarias

## Índice

- [Sobre](#sobre)
- [Tecnologias](#tecnologias)
- [Uso](#uso)
- [Testes](#testes)

## Sobre

Este é um projeto desenvolvido com Spring Boot para o gerenciamento simples de contas bancarias. A aplicacao cliente comunica com a aplicacao do banco de dados atraves do Feign, reconhece
a aplicacao do banco pelo Eureka(capaz de LoadBalancing) e possui documentacao completa de ambos os servicos pelo Swagger

## Tecnologias

O projeto utiliza as seguintes tecnologias:

- [Spring Boot](https://spring.io/projects/spring-boot)
- [Java Graal 17.0.7](https://www.oracle.com/java/technologies/javase/graalvm-jdk17-archive-downloads.html)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [Maven]
- [Banco de dados H2]
- [Feign]
- [Eureka Server/CLient]
- [Api Gateway]
  
## Uso
Acesse o Swagger-ui.html do api gateway -> localhost:8765/swagger.html

## Testes
Cobertura de 100% dos endpoints.
- Cliente-service:
  -createCliente_ComDadosValidos_RetornarClienteCriadoComStatus201() ->passou;
  
  -createCliente_dadosInvalidos_retornarErrorMessageStatus422() ->passou;
  
  -buscarClientePorId_retornarClienteEStatus200() ->passou;
  
  -buscarClientePorNome_retornarClienteEStatus200() ->passou;
  
  -buscarClientePorId_idInexistente_retornarErrorMessageEStatus404() ->passou;
  
  -buscarClientePorId_idInexistente_retornarErrorMessageEStatus404() ->passou;
  
  -atualizarClientePorIdEBody_dadosValidos_retornarStatus204() ->passou;
  
  -atualizarClientePorIdEBody_dadosInvalidos_retornarErrorMessageEStatus422() ->passou;
  
  -atualizarClientePorIdEBody_dadosValidosEIdInvalido_retornarErrorMessageEStatus404() ->passou;
  
  -deletarClientePorId_retornarStatus200() ->passou;
  
  -deletarClientePorId_invalido_retornarErrorMessageEStatus404() ->passou;
  
- Db-service:
  -createCliente_ComDadosValidos_RetornarClienteCriadoComStatus201() ->passou;
  
  -createCliente_dadosInvalidos_retornarErrorMessageStatus422() ->passou;
  
  -buscarClientePorId_retornarClienteEStatus200() ->passou;
  
  -buscarClientePorNome_retornarClienteEStatus200() ->passou;
  
  -buscarClientePorId_idInexistente_retornarErrorMessageEStatus404() ->passou;
  
  -buscarClientePorId_idInexistente_retornarErrorMessageEStatus404() ->passou;
  
  -atualizarClientePorIdEBody_dadosValidos_retornarStatus204() ->passou;
  
  -atualizarClientePorIdEBody_dadosInvalidos_retornarErrorMessageEStatus422() ->passou;
  
  -atualizarClientePorIdEBody_dadosValidosEIdInvalido_retornarErrorMessageEStatus404() ->passou;
  
  -deletarClientePorId_retornarStatus200() ->passou;
  
  -deletarClientePorId_invalido_retornarErrorMessageEStatus404() ->passou;
  
