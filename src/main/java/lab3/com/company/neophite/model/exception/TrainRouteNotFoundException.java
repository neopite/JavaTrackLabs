package lab3.com.company.neophite.model.exception;

public class TrainRouteNotFoundException extends RuntimeException {
    public TrainRouteNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
