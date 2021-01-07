package lab2_5.com.company.neophite.model.exception;

public class UserAlreadyCreatedException extends Exception {
    public UserAlreadyCreatedException(String errorMessage) {
        super(errorMessage);
    }
}
