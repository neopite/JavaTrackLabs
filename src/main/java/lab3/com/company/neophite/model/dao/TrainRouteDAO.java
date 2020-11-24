package lab3.com.company.neophite.model.dao;

import lab3.com.company.neophite.ConnectionPoll;
import lab3.com.company.neophite.model.entity.TrainRoute;

import java.util.List;

public abstract class TrainRouteDAO extends AbstractDAO<TrainRoute , Long> {
    public TrainRouteDAO(ConnectionPoll pool, String table) {
        super(pool, table);
    }
    public abstract List<TrainRoute> getTrainRoutesByFirstStation(long name);
    public abstract List<TrainRoute> getTrainRoutesBySecondStation(long name);
}
