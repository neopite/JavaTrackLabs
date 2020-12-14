package lab3.com.company.neophite.model.service;

import lab3.com.company.neophite.model.dao.DAOFactory;
import lab3.com.company.neophite.model.dao.StationDAO;
import lab3.com.company.neophite.model.dao.TrainRouteDAO;
import lab3.com.company.neophite.model.dao.TrainTripDAO;
import lab3.com.company.neophite.model.dao.connection.BasicConnectionPool;
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
import java.util.ArrayList;
import java.util.List;

public class StationService {

    private DAOFactory daoFactory;
    private Connection transactionConnection;
    private BasicConnectionPool basicConnectionPool;

    public StationService(DAOFactory daoFactory) {
        this.transactionConnection = BasicConnectionPool.getInstance().getConnection();
        this.daoFactory = daoFactory;
        this.basicConnectionPool = BasicConnectionPool.getInstance();
    }


    public void deleteStation(long stationId) {
        try (StationDAO stationDAO = DAOFactory.getDaoFactory().createStationDAO(transactionConnection);
             TrainRouteDAO trainRouteDAO = DAOFactory.getDaoFactory().createTrainRouteDAO(transactionConnection);
             TrainTripDAO trainTripDAO = DAOFactory.getDaoFactory().createTrainTripDAO(transactionConnection)) {

            transactionConnection.setAutoCommit(false);
            boolean station = stationDAO.deleteByKey(stationId);
            if (!station) {
                throw new StationNotFoundException("Station with id : " + stationId + " not found");
            }
            boolean trainRouteIstrue = trainRouteDAO.deleteAllRoutesWithStationId(stationId);
            if (!trainRouteIstrue) {
                throw new TrainRouteNotFoundException("Train Routes with station : " + stationId + "  not found");
            }

            List<TrainRoute> listOfRoutesByStation = trainRouteDAO.getAllRoutesByStation(stationId);

            for (TrainRoute trainRoute : listOfRoutesByStation) {
                boolean isDeleted = trainTripDAO.deleteAllTrainTripsByRouteId(trainRoute.getId());
                if (!isDeleted) {
                    throw new TrainTripNotFoundException("Train trip with id of route : " + trainRoute.getId() + " not found");
                }
            }
            transactionConnection.commit();
            transactionConnection.setAutoCommit(true);
        } catch (SQLException | StationNotFoundException | TrainRouteNotFoundException | TrainTripNotFoundException exception) {
            try {
                transactionConnection.rollback();
            } catch (SQLException throwables) {
                exception.printStackTrace();
            }
        }
    }

    public Station addStation(Station station) {
        Station station1;
        try (StationDAO stationDAO = daoFactory.createStationDAO(basicConnectionPool.getConnection())) {
            station1 = stationDAO.create(station);
        }
        return station1;
    }

    public Station updateStation(long id, String newStationName) {
        Station station;
        try (StationDAO stationDAO = daoFactory.createStationDAO(basicConnectionPool.getConnection())) {
            station = stationDAO.updateStation(id, newStationName);
        }
        return station;
    }

    public List<Station> getAllStation() {
        List<Station> listOfStation;
        try (StationDAO stationDAO = daoFactory.createStationDAO(basicConnectionPool.getConnection())) {
            listOfStation = stationDAO.getAll();
        }
        return listOfStation;
    }
}
