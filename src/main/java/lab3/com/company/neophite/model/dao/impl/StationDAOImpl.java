package lab3.com.company.neophite.model.dao.impl;

import lab3.com.company.neophite.ConnectionPoll;
import lab3.com.company.neophite.model.dao.StationDAO;
import lab3.com.company.neophite.model.entity.Station;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StationDAOImpl extends StationDAO {

    private final String CREATE = "insert into " + this.getTable() +
            " (name) values(?)";
    private final String FIND_STATION_BY_NAME = "select * from " + this.getTable() + " where name='?'";
    private final String FIND_STATION_BY_ID = "select * from " + this.getTable() + " where id=?";
    private final String DELETE_STATION_BY_ID = "delete from " + this.getTable() + " where id=?";
    private final String GET_ALL_STATION = "select * from " + this.getTable();

    public StationDAOImpl(ConnectionPoll pool, String table) {
        super(pool, table);
    }

    public Station findStationByName(String name) {
        PreparedStatement preparedStatement = getStatement(FIND_STATION_BY_NAME);
        Station station = null;
        try {
            preparedStatement.setString(1, name);
            ResultSet res = preparedStatement.executeQuery();
            while (res.next()) {
                station = new Station(res.getLong("id_station"), res.getString("name"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return station;
    }

    public Station create(Station station) {
        PreparedStatement preparedStatement = getStatement(CREATE);
        boolean executed = false;
        try {
            preparedStatement.setString(1, station.getName());
            executed = preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return executed ? station : null;
    }

    public Station findObjectByKeyValue(Long key) {
        PreparedStatement preparedStatement = getStatement(FIND_STATION_BY_ID);
        Station station = null;
        try {
            preparedStatement.setLong(1,key);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                station = new Station(
                        resultSet.getLong("id_station"),
                        resultSet.getString("name"));
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
        return station;
    }

    public boolean deleteByKey(Long key) {
        PreparedStatement preparedStatement = getStatement(DELETE_STATION_BY_ID);
        ResultSet resultSet = null;
        try {
            preparedStatement.setLong(1,key);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    public List<Station> getAll() {
        PreparedStatement preparedStatement = getStatement(GET_ALL_STATION);
        List<Station> listOfStation = new ArrayList<Station>();
        try {
           ResultSet resultSet =  preparedStatement.executeQuery();
           while (resultSet.next()){
               Station station = new Station(
                       resultSet.getLong("id_station"),
                       resultSet.getString("name")
               );
               listOfStation.add(station);
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
        return listOfStation;
    }
}
