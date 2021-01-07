package lab2_5.com.company.neophite.model.exception;

public class StationNotFoundException  extends RuntimeException{
    public StationNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
