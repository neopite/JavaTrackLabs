package lab3.com.company.neophite.model.entity;


import java.sql.Date;

public class TrainRoute {
    private long id;
    private Station startStation;
    private Station finishStation;
    private Date startDate;
    private Date finishDate;
    private boolean isActive;

    public TrainRoute() {
    }

    public TrainRoute(Station startStation, Station finishStation, Date startDate, Date finishDate) {
        this.startStation = startStation;
        this.finishStation = finishStation;
        this.startDate = startDate;
        this.finishDate = finishDate;
    }

    public TrainRoute(long id, Station startStation, Station finishStation, Date startDate, Date finishDate) {
        this.id = id;
        this.startStation = startStation;
        this.finishStation = finishStation;
        this.startDate = startDate;
        this.finishDate = finishDate;
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

    public Station getStartStation() {
        return startStation;
    }

    public void setStartStation(Station startStation) {
        this.startStation = startStation;
    }

    public Station getFinishStation() {
        return finishStation;
    }

    public void setFinishStation(Station finishStation) {
        this.finishStation = finishStation;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    @Override
    public String toString() {
        return "TrainRoute{" +
                "id=" + id +
                ", startStation=" + startStation +
                ", finishStation=" + finishStation +
                ", startDate=" + startDate +
                ", finishDate=" + finishDate +
                '}';
    }
}
