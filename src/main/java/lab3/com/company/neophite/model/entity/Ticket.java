package lab3.com.company.neophite.model.entity;

public class Ticket {
    private long id;
    private User userId;
    private TrainTrip trainTripId;
    private int place;

    public Ticket() {
    }

    public Ticket(long id, User userId, TrainTrip trainTripId, int place) {
        this.id = id;
        this.userId = userId;
        this.trainTripId = trainTripId;
        this.place = place;
    }

    public Ticket(User userId, TrainTrip trainTripId, int place) {
        this.userId = userId;
        this.trainTripId = trainTripId;
        this.place = place;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public TrainTrip getTrainTripId() {
        return trainTripId;
    }

    public void setTrainTripId(TrainTrip trainTripId) {
        this.trainTripId = trainTripId;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }
}
