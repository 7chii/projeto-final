package nana.proj.client.response.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;
import nana.proj.client.exceptions.DataNotFoundException;
import nana.proj.client.exceptions.UnprocessableDataException;

@RestControllerAdvice
public class ErrorHandler {
	
	@ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<nana.proj.client.response.ErrorMessage> entityNotFoundException(RuntimeException ex, HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new nana.proj.client.response.ErrorMessage(request, HttpStatus.NOT_FOUND, ex.getMessage()));
    }
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<nana.proj.client.response.ErrorMessage> methodArgumentNotValidException(MethodArgumentNotValidException ex,
                                                                        HttpServletRequest request,
                                                                        BindingResult result) {
        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new nana.proj.client.response.ErrorMessage(request, HttpStatus.UNPROCESSABLE_ENTITY, "Campo(s) invalido(s)", result));
    }
	
	@ExceptionHandler(UnprocessableDataException.class)
    public ResponseEntity<nana.proj.client.response.ErrorMessage> unprocessableDataException(RuntimeException ex, HttpServletRequest request) {
		return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new nana.proj.client.response.ErrorMessage(request, HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage()));
    }
}
