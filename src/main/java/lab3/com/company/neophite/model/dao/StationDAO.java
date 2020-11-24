package lab3.com.company.neophite.model.dao;

import lab3.com.company.neophite.ConnectionPoll;
import lab3.com.company.neophite.model.entity.Station;

public abstract class StationDAO extends AbstractDAO<Station,Long> {
    public StationDAO(ConnectionPoll pool, String table) {
        super(pool, table);
    }
    public abstract Station findStationByName(String name);
}
