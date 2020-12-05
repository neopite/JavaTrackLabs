package lab3.com.company.neophite.model.exception;

import lab3.com.company.neophite.model.entity.User;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String errorMessage, Throwable err) {
        super(errorMessage, err);
}
}
