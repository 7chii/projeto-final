package nana.proj.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.codec.ErrorDecoder;
import nana.proj.client.response.RetreiveMessageErrorDecoder;

@Configuration
public class FeignConfig {
	@Bean
	ErrorDecoder errorDecoder() {
	    return new RetreiveMessageErrorDecoder();
	}
}
