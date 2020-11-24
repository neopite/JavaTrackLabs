package lab3.com.company.neophite.model.entity;

import java.util.Date;

public class TrainRoute {
    private long id;
    private long startStation;
    private long finishStation;
    private Date startDate;
    private Date finishDate;

    public TrainRoute() {
    }

    public TrainRoute(long startStation, long finishStation, Date startDate, Date finishDate) {
        this.startStation = startStation;
        this.finishStation = finishStation;
        this.startDate = startDate;
        this.finishDate = finishDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getStartStation() {
        return startStation;
    }

    public void setStartStation(long startStation) {
        this.startStation = startStation;
    }

    public long getFinishStation() {
        return finishStation;
    }

    public void setFinishStation(long finishStation) {
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
}
