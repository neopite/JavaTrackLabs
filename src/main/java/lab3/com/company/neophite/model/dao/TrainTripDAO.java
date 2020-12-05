package lab3.com.company.neophite.model.dao;

import lab3.com.company.neophite.model.dao.connection.ConnectionPool;
import lab3.com.company.neophite.model.entity.TrainTrip;

import java.sql.Connection;
import java.util.List;

public abstract class TrainTripDAO extends AbstractDAO<TrainTrip,Long> {
    public TrainTripDAO(Connection connection, String table) {
        super(connection, table);
    }
    public abstract List<TrainTrip> findTrainTripsByRoute(long routeId);
}
