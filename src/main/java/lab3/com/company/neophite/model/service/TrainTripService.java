package lab3.com.company.neophite.model.service;

import lab3.com.company.neophite.model.dao.DAOFactory;
import lab3.com.company.neophite.model.dao.StationDAO;
import lab3.com.company.neophite.model.dao.TrainRouteDAO;
import lab3.com.company.neophite.model.dao.TrainTripDAO;
import lab3.com.company.neophite.model.dao.connection.BasicConnectionPool;
import lab3.com.company.neophite.model.entity.TrainRoute;
import lab3.com.company.neophite.model.entity.TrainTrip;

import java.util.ArrayList;
import java.util.List;

public class TrainTripService {
    private DAOFactory daoFactory;
    private BasicConnectionPool basicConnectionPool;

    public TrainTripService(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
        this.basicConnectionPool = BasicConnectionPool.getInstance();
    }

    public List<TrainTrip> getTrainTripsBetweenTwoStations(String first, String second) {
        try (StationDAO stationDAO = daoFactory.createStationDAO(basicConnectionPool.getConnection());
             TrainTripDAO trainTripDAO = daoFactory.createTrainTripDAO(basicConnectionPool.getConnection());
             TrainRouteDAO trainRouteDAO = daoFactory.createTrainRouteDAO(basicConnectionPool.getConnection())
        ) {
            long firstStationId = stationDAO.findStationByName(first).getId();
            long secondStationId = stationDAO.findStationByName(second).getId();
            List<TrainRoute> listOFTrainRoutesBetweenTwoStations =
                    trainRouteDAO.getTrainRoutesBetweenTwoStations(firstStationId, secondStationId);
            List<TrainTrip> listOfTrainsTrip = new ArrayList<>();
            for (int itter = 0; itter < listOFTrainRoutesBetweenTwoStations.size(); itter++) {
                listOfTrainsTrip.addAll(trainTripDAO.findTrainTripsByRoute(
                        listOFTrainRoutesBetweenTwoStations.get(itter).getId()
                ));
            }
            return listOfTrainsTrip;
        }
    }

    public List<TrainTrip> getAllTripsFromTheStation(String startStation) {
        try (StationDAO stationDAO = daoFactory.createStationDAO(basicConnectionPool.getConnection());
             TrainRouteDAO trainRouteDAO = daoFactory.createTrainRouteDAO(basicConnectionPool.getConnection());
             TrainTripDAO trainTripDAO = daoFactory.createTrainTripDAO(basicConnectionPool.getConnection())
        ) {
            long startStationId = stationDAO.findStationByName(startStation).getId();
            List<TrainRoute> allRoutesFromTheCurrentStation = trainRouteDAO.getTrainRoutesByFirstStation(startStationId);
            List<TrainTrip> allTrips = new ArrayList<>();
            for (int itter = 0; itter < allRoutesFromTheCurrentStation.size(); itter++) {
                allTrips.addAll(trainTripDAO.findTrainTripsByRoute(allRoutesFromTheCurrentStation.get(itter).getId()));
            }
            return allTrips;
        }
    }

    public List<TrainTrip> getAllTripsToTheStation(String endStation) {
        try (StationDAO stationDAO = daoFactory.createStationDAO(basicConnectionPool.getConnection());
             TrainRouteDAO trainRouteDAO = daoFactory.createTrainRouteDAO(basicConnectionPool.getConnection());
             TrainTripDAO trainTripDAO = daoFactory.createTrainTripDAO(basicConnectionPool.getConnection())
        ) {
            long endStationId = stationDAO.findStationByName(endStation).getId();
            List<TrainRoute> allRoutesToTheStation = trainRouteDAO.getTrainRoutesBySecondStation(endStationId);
            List<TrainTrip> allTrips = new ArrayList<>();
            for (int itter = 0; itter < allRoutesToTheStation.size(); itter++) {
                allTrips.addAll(trainTripDAO.findTrainTripsByRoute(allRoutesToTheStation.get(itter).getId()));
            }
            return allTrips;
        }
    }

    public TrainTrip findTrainTripById(long id) {
        TrainTrip trainTrip;
        try (TrainTripDAO trainTripDAO = DAOFactory.getDaoFactory().createTrainTripDAO(basicConnectionPool.getConnection())) {
            trainTrip = trainTripDAO.findByKey(id);
        }
        return trainTrip;
    }
}
