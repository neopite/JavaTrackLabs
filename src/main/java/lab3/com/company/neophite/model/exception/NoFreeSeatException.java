package lab3.com.company.neophite.model.exception;

public class NoFreeSeatException extends RuntimeException {
    public NoFreeSeatException(String errorMessage) {
        super(errorMessage);
    }
}
