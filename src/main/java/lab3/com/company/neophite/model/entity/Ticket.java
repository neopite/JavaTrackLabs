package lab3.com.company.neophite.model.entity;

import java.util.Objects;

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

    public Ticket(User userId, TrainTrip trainTripId) {
        this.userId = userId;
        this.trainTripId = trainTripId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return id == ticket.id &&
                place == ticket.place &&
                Objects.equals(userId, ticket.userId) &&
                Objects.equals(trainTripId, ticket.trainTripId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, trainTripId, place);
    }
}
