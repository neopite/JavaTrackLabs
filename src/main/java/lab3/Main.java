package lab3;

import lab3.com.company.neophite.BasicConnectionPool;
import lab3.com.company.neophite.model.dao.impl.StationDAOImpl;
import lab3.com.company.neophite.model.dao.impl.TrainRouteDAOImpl;
import lab3.com.company.neophite.model.entity.Station;
import lab3.com.company.neophite.model.entity.TrainRoute;

import java.sql.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        BasicConnectionPool basicConnectionPool = BasicConnectionPool.create(
                "jdbc:postgresql://127.0.0.1:5432/railwaydb",
                "postgres",
                "4427"
        );

        StationDAOImpl stationDAO = new StationDAOImpl(basicConnectionPool,"stations");

        TrainRouteDAOImpl trainRouteDAO = new TrainRouteDAOImpl(basicConnectionPool,"trains_route");
        List<Station> listOfRoutes = stationDAO.getAll();
        TrainRoute trainRoute = new TrainRoute(
                listOfRoutes.get(0).getId(),
                listOfRoutes.get(1).getId(),
                new Date(41234123),
                new Date(432534254)
        );
       // trainRouteDAO.create(trainRoute);
        List<TrainRoute> t = trainRouteDAO.getTrainRoutesByFirstStation(1);
        for(TrainRoute g : t){
            System.out.println(g);
        }



    }
}
