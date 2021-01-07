package lab2_5.com.company.neophite.model.exception;

public class NoFreeSeatException extends RuntimeException {
    public NoFreeSeatException(String errorMessage) {
        super(errorMessage);
    }
}
