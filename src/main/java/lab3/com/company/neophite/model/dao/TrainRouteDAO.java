package lab3.com.company.neophite.model.dao;

import lab3.com.company.neophite.model.entity.TrainRoute;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public abstract class TrainRouteDAO extends AbstractDAO<TrainRoute , Long> {
    public TrainRouteDAO(Connection connection) {
        super(connection);
    }
    public abstract List<TrainRoute> getTrainRoutesByFirstStation(long name);
    public abstract List<TrainRoute> getTrainRoutesBySecondStation(long name);
    public abstract List<TrainRoute> getTrainRoutesBetweenTwoStations(long first, long second,Timestamp from, Timestamp to);
    public abstract boolean deleteAllRoutesWithStationId(long stationId);
    public abstract List<TrainRoute> getAllRoutesByStation(long stationId);
    public abstract boolean deleteByKey(Long key);
}
