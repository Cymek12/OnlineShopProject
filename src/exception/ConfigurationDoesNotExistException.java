package exception;

public class ConfigurationDoesNotExistException extends Exception {
    public ConfigurationDoesNotExistException(String message) {
        super(message);
    }
}
