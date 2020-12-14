package lab3.com.company.neophite.model.exception;

public class NotMuchMoneyException extends RuntimeException {
    public NotMuchMoneyException(String errorMessage) {
        super(errorMessage);
    }
}
