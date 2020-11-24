package lab3.com.company.neophite.model.entity;

public class Ticket {
    private long id;
    private long userId;
    private long trainTripId;
    private int place;
    private int vagon;

    public Ticket() {
    }

    public Ticket(long userId, long trainTripId, int place, int vagon) {
        this.userId = userId;
        this.trainTripId = trainTripId;
        this.place = place;
        this.vagon = vagon;
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
