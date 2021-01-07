package lab2_5.com.company.neophite.model.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String errorMessage) {
        super(errorMessage);
}
}
