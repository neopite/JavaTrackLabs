package lab3.com.company.neophite.model.entity;

public class TrainTrip {
    private long id;
    private long traintRoute;
    private long trainId;
    private float price;
    private int availableSeats;
    private boolean isActive;

    public TrainTrip() {
    }

    public TrainTrip(long id, long traintRoute, long trainId, float price, int availableSeats) {
        this.id = id;
        this.traintRoute = traintRoute;
        this.trainId = trainId;
        this.price = price;
        this.availableSeats = availableSeats;
    }

    public TrainTrip(long traintRoute, long trainId, float price, int availableSeats) {
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

    public long getTraintRoute() {
        return traintRoute;
    }

    public void setTraintRoute(long traintRoute) {
        this.traintRoute = traintRoute;
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
