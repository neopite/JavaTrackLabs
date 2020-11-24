package lab3.com.company.neophite.model.service;

import lab3.com.company.neophite.BasicConnectionPool;
import lab3.com.company.neophite.model.dao.TrainRouteDAO;
import lab3.com.company.neophite.model.dao.TrainTripDAO;
import lab3.com.company.neophite.model.dao.impl.StationDAOImpl;
import lab3.com.company.neophite.model.dao.impl.TrainRouteDAOImpl;
import lab3.com.company.neophite.model.dao.impl.TrainTripDAOImpl;
import lab3.com.company.neophite.model.entity.TrainRoute;

public class StationService {
    private StationDAOImpl stationDAO;
    private TrainRouteDAO trainRouteDAO;
    private TrainTripDAO trainTripDAO;
    public StationService(){
        BasicConnectionPool basicConnectionPool = BasicConnectionPool.create(
                "jdbc:postgresql://127.0.0.1:5432/railwaydb",
                "postgres",
                "4427"
        );
        stationDAO = new StationDAOImpl(basicConnectionPool,"stations");
        trainRouteDAO = new TrainRouteDAOImpl(basicConnectionPool,"trains_route");
        trainTripDAO = new TrainTripDAOImpl(basicConnectionPool,"train_trip");
    }

    public void deleteStation(long stationId){

    }
}
