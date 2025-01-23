package nana.proj.client.exceptions;

@SuppressWarnings("serial")
public class UnprocessableDataException extends RuntimeException {
	public UnprocessableDataException(String message) {
        super(message);
    }
}
