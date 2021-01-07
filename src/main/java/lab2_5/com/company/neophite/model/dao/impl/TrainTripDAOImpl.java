package lab2_5.com.company.neophite.model.dao.impl;

import lab2_5.com.company.neophite.model.dao.TrainTripDAO;
import lab2_5.com.company.neophite.model.entity.TrainTrip;
import lab2_5.com.company.neophite.model.mapper.impl.TrainTripMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrainTripDAOImpl extends TrainTripDAO {
private TrainTripMapper trainTripMapper = new TrainTripMapper();
    private final String table = "train_trip";
    private final String CREATE = "insert into " + table +
            " (train_route,price,train,available_seats) values(?,?)";
    private final String FIND_ALL = "select id_train_trip, id_train_route,s.id_station as id_start , s2.id_station as id_end , available_seats, price , start_date,end_date ,id_train, model ,count_of_places , s.name as start_station,s2.name as end_station \n" +
            "from train_trip\n" +
            "         left join trains_route tr on train_trip.train_route = tr.id_train_route\n" +
            "      left join stations s on  tr.station_start = s.id_station\n" +
            "               left join stations s2 on tr.station_end = s2.id_station" +
            "         left join trains t on train_trip.train = t.id_train";

    private final String FIND_TRAIN_TRIP_BY_ID = FIND_ALL + " where id_train_trip=? and train_trip.isActive=true";
    private final String DELETE_TRAIN_TRIPS_BY_ID = "update " + table + " set isActive=false where id_train_trip=?";
    private final String DELETE_TRIPS_BY_ROUTE_ID = "update " + table + " set isActive=false where train_route=?";
    private final String GET_ALL_TRIPS = FIND_ALL+ " where isActive=true";
    private final String FIND_TRAIN_TRIPS_BY_ROUTE = FIND_ALL + " where train_route=? and train_trip.isActive=true";
    private final String UPDATE_TRAIN_TRIP_SEATS = "update train_trip set available_seats=? where id_train_trip=?";


    public TrainTripDAOImpl(Connection connection) {
        super(connection);
    }

    @Override
    public List<TrainTrip> findTrainTripsByRoute(long routeId) {
        List<TrainTrip> trainTrips = new ArrayList<>();
        try (PreparedStatement preparedStatement = getStatement(FIND_TRAIN_TRIPS_BY_ROUTE)) {
            preparedStatement.setLong(1, routeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                TrainTrip trainTrip = trainTripMapper.extractEntityFromTheRS(resultSet);
                trainTrips.add(trainTrip);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return trainTrips;
    }

    @Override
    public boolean deleteAllTrainTripsByRouteId(long routeId) {
        int executed = 0;
        try (PreparedStatement preparedStatement = getStatement(DELETE_TRIPS_BY_ROUTE_ID)) {
            preparedStatement.setLong(1, routeId);
            executed = preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return executed!=0;
    }

    @Override
    public boolean updateTrainTripAvailableSeats(long trainTripId, int seatsAvailable) {
        boolean executed = false;
        try(PreparedStatement preparedStatement = getStatement(UPDATE_TRAIN_TRIP_SEATS)) {
            preparedStatement.setLong(1,seatsAvailable);
            preparedStatement.setLong(2,trainTripId);
            return executed = preparedStatement.execute();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return executed;
    }

    @Override
    public TrainTrip create(TrainTrip trainTrip) {
        try (PreparedStatement preparedStatement = getStatement(CREATE)) {
            preparedStatement.setLong(1, trainTrip.getTrainRoute().getId());
            preparedStatement.setDouble(2, trainTrip.getPrice());
            preparedStatement.setLong(3, trainTrip.getTrainId().getId());
            preparedStatement.setInt(4, trainTrip.getAvailableSeats());
            preparedStatement.execute();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return trainTrip;
    }

    @Override
    public TrainTrip findByKey(Long key) {
        TrainTrip trainTrip = null;
        try (PreparedStatement preparedStatement = getStatement(FIND_TRAIN_TRIP_BY_ID)) {
            preparedStatement.setLong(1, key);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                trainTrip = trainTripMapper.extractEntityFromTheRS(resultSet);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return trainTrip;
    }

    @Override
    public boolean deleteByKey(Long key) {
        try (PreparedStatement preparedStatement = getStatement(DELETE_TRAIN_TRIPS_BY_ID)) {
            preparedStatement.setLong(1, key);
            return preparedStatement.execute();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return false;
    }

    @Override
    public List<TrainTrip> getAll() {
        List<TrainTrip> listOfTrainTrips = new ArrayList<>();
        try (PreparedStatement preparedStatement = getStatement(GET_ALL_TRIPS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                TrainTrip trainTrip =trainTripMapper.extractEntityFromTheRS(resultSet);
                listOfTrainTrips.add(trainTrip);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return listOfTrainTrips;
    }
}
