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

    public TrainTripService(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public List<TrainTrip> getTrainTripsBetweenTwoStations(String first, String second) {
        try (StationDAO stationDAO = daoFactory.createStationDAO();
             TrainTripDAO trainTripDAO = daoFactory.createTrainTripDAO();
             TrainRouteDAO trainRouteDAO = daoFactory.createTrainRouteDAO()
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
        try(StationDAO stationDAO = daoFactory.createStationDAO();
            TrainRouteDAO trainRouteDAO = daoFactory.createTrainRouteDAO();
            TrainTripDAO trainTripDAO = daoFactory.createTrainTripDAO()
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
        try (StationDAO stationDAO = daoFactory.createStationDAO();
             TrainRouteDAO trainRouteDAO = daoFactory.createTrainRouteDAO();
             TrainTripDAO trainTripDAO = daoFactory.createTrainTripDAO()
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
}
