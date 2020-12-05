package lab3.com.company.neophite.model.dao;

import lab3.com.company.neophite.model.dao.connection.ConnectionPool;
import lab3.com.company.neophite.model.entity.TrainRoute;

import java.sql.Connection;
import java.util.List;

public abstract class TrainRouteDAO extends AbstractDAO<TrainRoute , Long> {
    public TrainRouteDAO(Connection connection, String table) {
        super(connection, table);
    }
    public abstract List<TrainRoute> getTrainRoutesByFirstStation(long name);
    public abstract List<TrainRoute> getTrainRoutesBySecondStation(long name);
    public abstract List<TrainRoute> getTrainRoutesBetweenTwoStations(long first,long second);
}
