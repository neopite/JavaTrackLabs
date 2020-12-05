package lab3.com.company.neophite.model.entity;

public class Ticket {
    private long id;
    private long userId;
    private long trainTripId;
    private float price;

    public Ticket() {
    }

    public Ticket(long id, long userId, long trainTripId, float price) {
        this.id = id;
        this.userId = userId;
        this.trainTripId = trainTripId;
        this.price = price;
    }

    public Ticket(long userId, long trainTripId, float price) {
        this.userId = userId;
        this.trainTripId = trainTripId;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getTrainTripId() {
        return trainTripId;
    }

    public void setTrainTripId(long trainTripId) {
        this.trainTripId = trainTripId;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
