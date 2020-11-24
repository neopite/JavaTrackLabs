package lab3.com.company.neophite.model.entity;

public class TrainTrip {
    private long id;
    private long trainRouteId;
    private long trainId;
    private float price;
    private int availableSeats;

    public TrainTrip() {
    }

    public TrainTrip(long id, long trainRouteId, long trainId, float price, int availableSeats) {
        this.id = id;
        this.trainRouteId = trainRouteId;
        this.trainId = trainId;
        this.price = price;
        this.availableSeats = availableSeats;
    }

    public TrainTrip(long trainRouteId, long trainId, float price, int availableSeats) {
        this.trainRouteId = trainRouteId;
        this.trainId = trainId;
        this.price = price;
        this.availableSeats = availableSeats;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTrainRouteId() {
        return trainRouteId;
    }

    public void setTrainRouteId(long trainRouteId) {
        this.trainRouteId = trainRouteId;
    }

    public long getTrainId() {
        return trainId;
    }

    public void setTrainId(long trainId) {
        this.trainId = trainId;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }
}
