package lab3.com.company.neophite.model.dao;

import lab3.com.company.neophite.model.entity.TrainTrip;

import java.sql.Connection;
import java.util.List;

public abstract class TrainTripDAO extends AbstractDAO<TrainTrip,Long> {
    public TrainTripDAO(Connection connection) {
        super(connection);
    }
    public abstract List<TrainTrip> findTrainTripsByRoute(long routeId);
    public abstract boolean deleteAllTrainTripsByRouteId(long routeId);
    public abstract boolean updateTrainTripAvailableSeats(long trainTripId,int seatsAvailable);
}
