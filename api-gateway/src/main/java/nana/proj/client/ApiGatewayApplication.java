package nana.proj.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import org.springframework.cloud.gateway.route.RouteLocator;

import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import jakarta.ws.rs.HttpMethod;


@EnableDiscoveryClient
@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "API gateway", version = "1.0",description = "documentation of API gateway v1.0"))
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}
	
	@Bean
	 public RouteLocator routeLocator(RouteLocatorBuilder builder) {
	  return builder
	    .routes()
	    .route(r -> r.path("/db-api-docs").and().method(HttpMethod.GET).uri("lb://db-service"))
	    .route(r -> r.path("/cliente-api-docs").and().method(HttpMethod.GET).uri("lb://cliente-service"))
	    .build();
	 }

}
