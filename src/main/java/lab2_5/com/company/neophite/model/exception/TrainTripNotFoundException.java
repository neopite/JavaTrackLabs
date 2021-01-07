package lab2_5.com.company.neophite.model.exception;

public class TrainTripNotFoundException extends RuntimeException {
    public TrainTripNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
