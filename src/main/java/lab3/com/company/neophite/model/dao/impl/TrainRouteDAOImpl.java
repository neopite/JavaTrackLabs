package lab3.com.company.neophite.model.dao.impl;

import lab3.com.company.neophite.model.dao.connection.ConnectionPool;
import lab3.com.company.neophite.model.dao.TrainRouteDAO;
import lab3.com.company.neophite.model.entity.TrainRoute;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrainRouteDAOImpl extends TrainRouteDAO {

    private final String CREATE = "insert into " + this.getTable() +
            " (station_start,start_date,station_end,end_date) values(?,?,?,?)";
    private final String FIND_BY_TRAIN_ROUTE_ID = "select * from " + this.getTable() + " where id_train_route=?";
    private final String FIND_BY_FIRST_STATION = "select * from " + this.getTable() + " where station_start=?";
    private final String FIND_BY_END_STATION = "select * from " + this.getTable() + " where station_end=?";
    private final String FIND_ROUTES_BETWEEN_TWO_STATION = "select * from " + this.getTable() + " where station_start=? and station_end=?";
    private final String DELETE_ROUTE_BY_ID = "delete from " + this.getTable() + " where id_train_route=?";
    private final String GET_ALL_TRAIN_ROUTES = "select * from " + this.getTable();

    public TrainRouteDAOImpl(Connection connection, String table) {
        super(connection, table);
    }

    public List<TrainRoute> getTrainRoutesByFirstStation(long name) {
        List<TrainRoute> listOfTraintRoutes = new ArrayList<TrainRoute>();
        try (PreparedStatement preparedStatement = getStatement(FIND_BY_FIRST_STATION)) {
            preparedStatement.setLong(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                TrainRoute trainRoute = new TrainRoute(
                        resultSet.getLong("id_train_route"),
                        resultSet.getLong("station_start"),
                        resultSet.getLong("station_end"),
                        resultSet.getDate("start_date"),
                        resultSet.getDate("end_date")
                );
                listOfTraintRoutes.add(trainRoute);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return listOfTraintRoutes;
    }

    public List<TrainRoute> getTrainRoutesBySecondStation(long name) {
        List<TrainRoute> listOfTraintRoutes = new ArrayList<TrainRoute>();
        try (PreparedStatement preparedStatement = getStatement(FIND_BY_END_STATION)) {
            preparedStatement.setLong(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                TrainRoute trainRoute = new TrainRoute(
                        resultSet.getLong("id_train_route"),
                        resultSet.getLong("station_start"),
                        resultSet.getLong("station_end"),
                        resultSet.getDate("start_date"),
                        resultSet.getDate("end_date")
                );
                listOfTraintRoutes.add(trainRoute);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return listOfTraintRoutes;
    }

    @Override
    public List<TrainRoute> getTrainRoutesBetweenTwoStations(long first, long second) {
        List<TrainRoute> listOfRoutes = new ArrayList<>();
        try(PreparedStatement preparedStatement = getStatement(FIND_ROUTES_BETWEEN_TWO_STATION)){
            preparedStatement.setLong(1,first);
            preparedStatement.setLong(2,second);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                TrainRoute trainRoute = new TrainRoute(
                        resultSet.getLong("id_train_route"),
                        resultSet.getLong("station_start"),
                        resultSet.getLong("station_end"),
                        resultSet.getDate("start_date"),
                        resultSet.getDate("end_date")
                );
                listOfRoutes.add(trainRoute);
            }
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return listOfRoutes;
    }


    public TrainRoute create(TrainRoute trainRoute) {
        try (PreparedStatement preparedStatement = getStatement(CREATE)) {
            preparedStatement.setLong(1, trainRoute.getStartStation());
            preparedStatement.setDate(2, (Date) trainRoute.getStartDate());
            preparedStatement.setLong(3, trainRoute.getFinishStation());
            preparedStatement.setDate(4, (Date) trainRoute.getFinishDate());
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return trainRoute;
    }

    public TrainRoute findByKey(Long key) {
        TrainRoute trainRoute = null;
        try (PreparedStatement preparedStatement = getStatement(FIND_BY_TRAIN_ROUTE_ID)) {
            preparedStatement.setLong(1, key);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                trainRoute = new TrainRoute(
                        resultSet.getLong("station_start"),
                        resultSet.getLong("station_end"),
                        resultSet.getDate("start_date"),
                        resultSet.getDate("end_date")
                );
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return trainRoute;
    }

    public boolean deleteByKey(Long key) {
        boolean isExecuted = false;
        try(        PreparedStatement preparedStatement = getStatement(DELETE_ROUTE_BY_ID)) {
            preparedStatement.setLong(1, key);
            isExecuted = preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return isExecuted;
    }

    public List<TrainRoute> getAll() {
        List<TrainRoute> listOTraintRoutes = new ArrayList<TrainRoute>();
        try(        PreparedStatement preparedStatement = getStatement(GET_ALL_TRAIN_ROUTES)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                TrainRoute trainRoute = new TrainRoute(
                        resultSet.getLong("station_start"),
                        resultSet.getLong("station_end"),
                        resultSet.getDate("start_date"),
                        resultSet.getDate("end_date")
                );
                listOTraintRoutes.add(trainRoute);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return listOTraintRoutes;
    }
}
