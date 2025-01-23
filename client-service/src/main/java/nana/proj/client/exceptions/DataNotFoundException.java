package nana.proj.client.exceptions;

@SuppressWarnings("serial")
public class DataNotFoundException extends RuntimeException {
	public DataNotFoundException(String message) {
        super(message);
    }
}
