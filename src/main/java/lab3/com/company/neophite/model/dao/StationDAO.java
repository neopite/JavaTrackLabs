package lab3.com.company.neophite.model.dao;

import lab3.com.company.neophite.model.entity.Station;

import java.sql.Connection;

public abstract class StationDAO extends AbstractDAO<Station,Long> {
    public StationDAO(Connection connection) {
        super(connection);
    }
    public abstract Station findStationByName(String name);
    public abstract Station updateStation(Long id , String newValue);
}
