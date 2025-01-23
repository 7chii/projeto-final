package nana.proj.client.response;

import java.io.Console;
import java.io.IOException;
import java.io.InputStream;



import com.fasterxml.jackson.databind.ObjectMapper;

import feign.Response;
import feign.codec.ErrorDecoder;
import nana.proj.client.exceptions.DataNotFoundException;
import nana.proj.client.exceptions.UnprocessableDataException;

public class RetreiveMessageErrorDecoder implements ErrorDecoder{

	private ErrorDecoder errorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        ErrorMessage message = null;
        try (InputStream bodyIs = response.body()
            .asInputStream()) {
            ObjectMapper mapper = new ObjectMapper();
            message = mapper.readValue(bodyIs, ErrorMessage.class);
        } catch (IOException e) {
            return new Exception(e.getMessage());
        }
        switch (response.status()) {
        case 404:
            return new DataNotFoundException(message.getMessage() != null ? message.getMessage() : "Nao encontrado");
        case 422:
        	return new UnprocessableDataException(message.getMessage() != null? message.getErrors().toString() : "Dados invalidos");
        default:
            return errorDecoder.decode(methodKey, response);
        }
    }

}
