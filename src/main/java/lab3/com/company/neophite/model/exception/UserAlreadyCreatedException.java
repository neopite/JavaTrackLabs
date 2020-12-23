package lab3.com.company.neophite.model.exception;

public class UserAlreadyCreatedException extends Exception {
    public UserAlreadyCreatedException(String errorMessage) {
        super(errorMessage);
    }
}
