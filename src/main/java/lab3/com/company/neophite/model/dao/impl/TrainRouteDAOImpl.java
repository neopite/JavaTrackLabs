package lab3.com.company.neophite.model.dao.impl;

import lab3.com.company.neophite.ConnectionPoll;
import lab3.com.company.neophite.model.dao.TrainRouteDAO;
import lab3.com.company.neophite.model.dao.TrainTripDAO;
import lab3.com.company.neophite.model.entity.TrainRoute;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrainRouteDAOImpl extends TrainRouteDAO {

    private final String CREATE = "insert into " + this.getTable() +
            " (station_start,start_date,station_end,end_date) values(?,?,?,?)";
    private final String FIND_BY_TRAIN_ROUTE_ID = "select * from " + this.getTable() + " where id_train_route='?'";
    private final String FIND_BY_FIRST_STATION = "select * from " + this.getTable() + " where station_start='?'";
    private final String FIND_BY_END_STATION= "select * from " + this.getTable() + " where station_end=?";
    private final String DELETE_ROUTE_BY_ID = "delete from " + this.getTable() + " where id_train_route=?";
    private final String GET_ALL_TRAIN_ROUTES = "select * from " + this.getTable();

    public TrainRouteDAOImpl(ConnectionPoll pool, String table) {
        super(pool, table);
    }

    public List<TrainRoute> getTrainRoutesByFirstStation(long name) {
        List<TrainRoute> listOfTraintRoutes = new ArrayList<TrainRoute>();
        PreparedStatement preparedStatement = getStatement(FIND_BY_FIRST_STATION);
        try {
            preparedStatement.setLong(1,name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                TrainRoute trainRoute = new TrainRoute(
                        resultSet.getLong("station_start"),
                        resultSet.getLong("station_end"),
                        resultSet.getDate("start_date"),
                        resultSet.getDate("end_date")
                );
                listOfTraintRoutes.add(trainRoute);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return listOfTraintRoutes;
    }

    public List<TrainRoute> getTrainRoutesBySecondStation(long name) {
        List<TrainRoute> listOfTraintRoutes = new ArrayList<TrainRoute>();
        PreparedStatement preparedStatement = getStatement(FIND_BY_FIRST_STATION);
        try {
            preparedStatement.setLong(1,name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                TrainRoute trainRoute = new TrainRoute(
                        resultSet.getLong("station_start"),
                        resultSet.getLong("station_end"),
                        resultSet.getDate("start_date"),
                        resultSet.getDate("end_date")
                );
                listOfTraintRoutes.add(trainRoute);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return listOfTraintRoutes;
    }


    public TrainRoute create(TrainRoute trainRoute) {
        PreparedStatement preparedStatement = getStatement(CREATE);
        try {
            preparedStatement.setLong(1,trainRoute.getStartStation());
            preparedStatement.setDate(2, (Date) trainRoute.getStartDate());
            preparedStatement.setLong(3,trainRoute.getFinishStation());
            preparedStatement.setDate(4,(Date) trainRoute.getFinishDate());
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return trainRoute;
    }

    public TrainRoute findObjectByKeyValue(Long key) {
        PreparedStatement preparedStatement = getStatement(FIND_BY_TRAIN_ROUTE_ID);
        TrainRoute trainRoute = null;
        try {
            preparedStatement.setLong(1,key);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                trainRoute = new TrainRoute(
                        resultSet.getLong("station_start"),
                        resultSet.getLong("station_end"),
                        resultSet.getDate("start_date"),
                        resultSet.getDate("end_date")
                );
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return trainRoute;
    }

    public boolean deleteByKey(Long key) {
        PreparedStatement preparedStatement = getStatement(DELETE_ROUTE_BY_ID);
        boolean isExecuted = false;
        try {
            preparedStatement.setLong(1,key);
            isExecuted = preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return isExecuted;
    }

    public List<TrainRoute> getAll() {
        List<TrainRoute> listOTraintRoutes = new ArrayList<TrainRoute>();
        PreparedStatement preparedStatement = getStatement(GET_ALL_TRAIN_ROUTES);
        try {
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
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
        }finally {
            try {
                preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return listOTraintRoutes;
    }
}
