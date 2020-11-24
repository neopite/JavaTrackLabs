package lab3.com.company.neophite.model.entity;

public class Ticket {
    private long id;
    private Train train;
    private TrainRoute trainRoute;
    private int place;
    private int vagon;

    public Ticket() {
    }

    public Ticket(Train train, TrainRoute trainRoute, int place, int vagon) {
        this.train = train;
        this.trainRoute = trainRoute;
        this.place = place;
        this.vagon = vagon;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public TrainRoute getTrainRoute() {
        return trainRoute;
    }

    public void setTrainRoute(TrainRoute trainRoute) {
        this.trainRoute = trainRoute;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public int getVagon() {
        return vagon;
    }

    public void setVagon(int vagon) {
        this.vagon = vagon;
    }
}
