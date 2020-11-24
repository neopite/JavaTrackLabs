package lab3.com.company.neophite.model.entity;

public class TrainTrip {
    private long id;
    private TrainRoute route;
    private float price;
    private Train train;
    private int availableSeats;

    public TrainTrip() {
    }

    public TrainTrip(TrainRoute route, float price, Train train, int availableSeats) {
        this.route = route;
        this.price = price;
        this.train = train;
        this.availableSeats = availableSeats;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public TrainRoute getRoute() {
        return route;
    }

    public void setRoute(TrainRoute route) {
        this.route = route;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }
}
