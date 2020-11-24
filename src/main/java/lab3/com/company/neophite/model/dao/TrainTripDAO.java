package lab3.com.company.neophite.model.dao;

import lab3.com.company.neophite.ConnectionPoll;
import lab3.com.company.neophite.model.entity.TrainTrip;

import java.util.List;

public abstract class TrainTripDAO extends AbstractDAO<TrainTrip,Long> {
    public TrainTripDAO(ConnectionPoll pool, String table) {
        super(pool, table);
    }
    public abstract List<TrainTrip> findTrainTripsByRoute(long routeId);
}
