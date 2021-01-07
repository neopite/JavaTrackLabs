package lab2_5.com.company.neophite.model.exception;

public class TrainRouteNotFoundException extends RuntimeException {
    public TrainRouteNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
