package lab3.com.company.neophite.model.service;

import lab3.com.company.neophite.model.dao.StationDAO;
import lab3.com.company.neophite.model.dao.connection.BasicConnectionPool;
import lab3.com.company.neophite.model.dao.TrainRouteDAO;
import lab3.com.company.neophite.model.dao.TrainTripDAO;
import lab3.com.company.neophite.model.dao.impl.StationDAOImpl;
import lab3.com.company.neophite.model.dao.impl.TrainRouteDAOImpl;
import lab3.com.company.neophite.model.dao.impl.TrainTripDAOImpl;
import lab3.com.company.neophite.model.entity.Station;
import lab3.com.company.neophite.model.entity.TrainRoute;
import lab3.com.company.neophite.model.exception.StationNotFoundException;
import lab3.com.company.neophite.model.exception.TrainRouteNotFoundException;
import lab3.com.company.neophite.model.exception.TrainTripNotFoundException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class StationService {

    private StationDAO stationDAO;
    private TrainRouteDAO trainRouteDAO;
    private TrainTripDAO trainTripDAO;
    private Connection transactionConnection;

    public StationService() {
        BasicConnectionPool basicConnectionPool = BasicConnectionPool.getInstance(
                "jdbc:postgresql://127.0.0.1:5432/railwaydb",
                "postgres",
                "4427"
        );
        this.transactionConnection = basicConnectionPool.getConnection();
        stationDAO = new StationDAOImpl(transactionConnection, "stations");
        trainRouteDAO = new TrainRouteDAOImpl(transactionConnection, "trains_route");
        trainTripDAO = new TrainTripDAOImpl(transactionConnection, "train_trip");
    }


    public void deleteStation(long stationId) {
        try {
            transactionConnection.setAutoCommit(false);
            boolean station = stationDAO.deleteByKey(stationId);
            if(!station){
                throw new StationNotFoundException("Station with id : " +stationId + " not found");
            }
            List<TrainRoute> listOfRoutesByStation = trainRouteDAO.getAllRoutesByStation(stationId);
            boolean trainRouteIstrue = trainRouteDAO.deleteAllRoutesWithStationId(stationId);
            if(!trainRouteIstrue){
                throw new TrainRouteNotFoundException("Train Routes with station : " + stationId +"  not found");
            }
            for (TrainRoute trainRoute : listOfRoutesByStation) {
                boolean isDeleted = trainTripDAO.deleteAllTrainTripsByRouteId(trainRoute.getId());
                if (!isDeleted) {
                    throw new TrainTripNotFoundException("Train trip with id of route : " + trainRoute.getId() + " not found");
                }
            }
            transactionConnection.commit();
            transactionConnection.setAutoCommit(true);
        } catch (SQLException | StationNotFoundException | TrainRouteNotFoundException | TrainTripNotFoundException exception ) {
            try {
                transactionConnection.rollback();
            } catch (SQLException throwables) {
                exception.printStackTrace();
            }
        }
    }

    public Station addStation(Station station) {
        return stationDAO.create(station);
    }

    public Station updateStation(long id, String newStationName) {
        return stationDAO.updateStation(id, newStationName);
    }
}
