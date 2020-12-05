package lab3.com.company.neophite.model.dao.impl;

import lab3.com.company.neophite.model.dao.connection.ConnectionPool;
import lab3.com.company.neophite.model.dao.StationDAO;
import lab3.com.company.neophite.model.entity.Station;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StationDAOImpl extends StationDAO {

    private final String CREATE = "insert into " + this.getTable() +
            " (name) values(?)";
    private final String FIND_STATION_BY_NAME = "select * from " + this.getTable() + " where name=?";
    private final String FIND_STATION_BY_ID = "select * from " + this.getTable() + " where id=?";
    private final String DELETE_STATION_BY_ID = "delete from " + this.getTable() + " where id=?";
    private final String UPDATE_STATION_BY_ID = "update " + this.getTable() + " set name=? where id=?";
    private final String GET_ALL_STATION = "select * from " + this.getTable();

    public StationDAOImpl(Connection connection, String table) {
        super(connection, table);
    }

    public Station findStationByName(String name) {
        Station station = null;
        try (PreparedStatement preparedStatement = getStatement(FIND_STATION_BY_NAME)) {
            preparedStatement.setString(1, name);
            ResultSet res = preparedStatement.executeQuery();
            while (res.next()) {
                station = new Station(res.getLong("id_station"), res.getString("name"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return station;
    }

    @Override
    public Station updateStation(Long id, String newValue) {
        try(PreparedStatement preparedStatement = getStatement(UPDATE_STATION_BY_ID)){
            preparedStatement.setString(1,newValue);
            preparedStatement.setLong(2,id);
            preparedStatement.executeUpdate();
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return new Station(id,newValue);
    }

    public Station create(Station station) {
        boolean executed = false;
        try (PreparedStatement preparedStatement = getStatement(CREATE)) {
            preparedStatement.setString(1, station.getName());
            executed = preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return executed ? station : null;
    }

    public Station findByKey(Long key) {
        Station station = null;
        try (PreparedStatement preparedStatement = getStatement(FIND_STATION_BY_ID)) {
            preparedStatement.setLong(1, key);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                station = new Station(
                        resultSet.getLong("id_station"),
                        resultSet.getString("name"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return station;
    }

    public boolean deleteByKey(Long key) {
        ResultSet resultSet = null;
        try (PreparedStatement preparedStatement = getStatement(DELETE_STATION_BY_ID)) {
            preparedStatement.setLong(1, key);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    public List<Station> getAll() {
        List<Station> listOfStation = new ArrayList<Station>();
        try (PreparedStatement preparedStatement = getStatement(GET_ALL_STATION)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Station station = new Station(
                        resultSet.getLong("id_station"),
                        resultSet.getString("name")
                );
                listOfStation.add(station);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return listOfStation;
    }
}
