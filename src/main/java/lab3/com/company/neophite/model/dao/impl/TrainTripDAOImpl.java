package lab3.com.company.neophite.model.dao.impl;

import lab3.com.company.neophite.model.dao.TrainTripDAO;
import lab3.com.company.neophite.model.entity.TrainTrip;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrainTripDAOImpl extends TrainTripDAO {

    private final String table = "train_trip";
    private final String CREATE = "insert into " + table +
            " (train_route,price,train,available_seats) values(?,?)";
    private final String FIND_TRAIN_TRIP_BY_ID = "select * from " + table + " where id_train_trip=? and isActive=true";
    private final String DELETE_TRAIN_TRIPS_BY_ID = "update " + table + " set isActive=false where id_train=?";
    private final String DELETE_TRIPS_BY_ROUTE_ID = "update " + table + " set isActive=false where train_route=?";
    private final String GET_ALL_TRIPS = "select * from " +table+ " where isActive=true";
    private final String FIND_TRAIN_TRIPS_BY_ROUTE = "select * from " + table+ " where train_route=? and isActive=true";


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
                TrainTrip trainTrip = new TrainTrip(
                        resultSet.getLong("id_train_trip"),
                        resultSet.getLong("train"),
                        resultSet.getLong("train_route"),
                        resultSet.getFloat("price"),
                        resultSet.getInt("available_seats")
                );
                trainTrips.add(trainTrip);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return trainTrips;
    }

    @Override
    public boolean deleteAllTrainTripsByRouteId(long routeId) {
        try (PreparedStatement preparedStatement = getStatement(DELETE_TRIPS_BY_ROUTE_ID)) {
            preparedStatement.setLong(1, routeId);
            return preparedStatement.execute();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return false;
    }

    @Override
    public TrainTrip create(TrainTrip trainTrip) {
        try (PreparedStatement preparedStatement = getStatement(CREATE)) {
            preparedStatement.setLong(1, trainTrip.getTrainRouteId());
            preparedStatement.setDouble(2, trainTrip.getPrice());
            preparedStatement.setLong(3, trainTrip.getTrainId());
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
                trainTrip = new TrainTrip(
                        resultSet.getLong("id_train,trip"),
                        resultSet.getLong("train"),
                        resultSet.getLong("train_route"),
                        resultSet.getFloat("price"),
                        resultSet.getInt("available_seats")
                );
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
                TrainTrip trainTrip = new TrainTrip(
                        resultSet.getLong("id_train,trip"),
                        resultSet.getLong("train"),
                        resultSet.getLong("train_route"),
                        resultSet.getFloat("price"),
                        resultSet.getInt("available_seats")
                );
                listOfTrainTrips.add(trainTrip);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return listOfTrainTrips;
    }
}
