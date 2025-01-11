package exception;

public class NotAvailableInStorageException extends RuntimeException{
    public NotAvailableInStorageException(String message) {
        super(message);
    }
}
