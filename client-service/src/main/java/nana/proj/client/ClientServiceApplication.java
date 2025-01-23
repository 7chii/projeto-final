package nana.proj.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import nana.proj.client.config.FeignConfig;

@SpringBootApplication
@EnableFeignClients(basePackages = "nana.proj.client", defaultConfiguration = FeignConfig.class)
public class ClientServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientServiceApplication.class, args);
	}

}
