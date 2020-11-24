package lab3;

import lab3.com.company.neophite.BasicConnectionPool;
import lab3.com.company.neophite.model.dao.impl.StationDAOImpl;
import lab3.com.company.neophite.model.entity.Station;

public class Main {
    public static void main(String[] args) {
        BasicConnectionPool basicConnectionPool = BasicConnectionPool.create(
                "jdbc:postgresql://127.0.0.1:5432/railwaydb",
                "postgres",
                "4427"
        );

        StationDAOImpl stationDAO = new StationDAOImpl(basicConnectionPool,"stations");
        stationDAO.create(new Station("Konotop"));
        stationDAO.create(new Station("Kiev"));
        stationDAO.create(new Station("Bahmach"));


    }
}
