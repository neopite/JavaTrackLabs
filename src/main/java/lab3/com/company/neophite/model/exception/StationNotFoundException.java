package lab3.com.company.neophite.model.exception;

public class StationNotFoundException  extends RuntimeException{
    public StationNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
