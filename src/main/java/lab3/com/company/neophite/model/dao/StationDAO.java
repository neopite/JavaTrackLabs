package lab3.com.company.neophite.model.dao;

import lab3.com.company.neophite.model.dao.connection.ConnectionPool;
import lab3.com.company.neophite.model.entity.Station;

import java.sql.Connection;

public abstract class StationDAO extends AbstractDAO<Station,Long> {
    public StationDAO(Connection connection, String table) {
        super(connection, table);
    }
    public abstract Station findStationByName(String name);
    public abstract Station updateStation(Long id , String newValue);
}
