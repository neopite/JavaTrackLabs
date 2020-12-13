package lab3.com.company.neophite.model.entity;

public class TrainTrip {
    private long id;
    private TrainRoute traintRoute;
    private Train trainId;
    private float price;
    private int availableSeats;
    private boolean isActive;

    public TrainTrip() {
    }

    public TrainTrip(long id, TrainRoute traintRoute, Train trainId, float price, int availableSeats) {
        this.id = id;
        this.traintRoute = traintRoute;
        this.trainId = trainId;
        this.price = price;
        this.availableSeats = availableSeats;
    }

    public TrainTrip(TrainRoute traintRoute, Train trainId, float price, int availableSeats) {
        this.traintRoute = traintRoute;
        this.trainId = trainId;
        this.price = price;
        this.availableSeats = availableSeats;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public TrainRoute getTraintRoute() {
        return traintRoute;
    }

    public void setTraintRoute(TrainRoute traintRoute) {
        this.traintRoute = traintRoute;
    }

    public Train getTrainId() {
        return trainId;
    }

    public void setTrainId(Train trainId) {
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

    @Override
    public String toString() {
        return "TrainTrip{" +
                "id=" + id +
                ", trainRouteId=" + traintRoute +
                ", trainId=" + trainId +
                ", price=" + price +
                ", availableSeats=" + availableSeats +
                '}';
    }
}
