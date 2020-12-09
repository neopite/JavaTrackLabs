package lab3.com.company.neophite.model.service;

import lab3.com.company.neophite.model.dao.connection.BasicConnectionPool;
import lab3.com.company.neophite.model.dao.StationDAO;
import lab3.com.company.neophite.model.dao.TrainRouteDAO;
import lab3.com.company.neophite.model.dao.TrainTripDAO;
import lab3.com.company.neophite.model.dao.impl.StationDAOImpl;
import lab3.com.company.neophite.model.dao.impl.TrainRouteDAOImpl;
import lab3.com.company.neophite.model.dao.impl.TrainTripDAOImpl;
import lab3.com.company.neophite.model.entity.TrainRoute;
import lab3.com.company.neophite.model.entity.TrainTrip;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class TrainTripService {
    private TrainTripDAO trainTripDAO;
   private TrainRouteDAO trainRouteDAO;
   private StationDAO stationDAO;

    public TrainTripService() {
        BasicConnectionPool basicConnectionPool = BasicConnectionPool.getInstance();
        trainTripDAO = new TrainTripDAOImpl(basicConnectionPool.getConnection(),"train_trip");
        trainRouteDAO = new TrainRouteDAOImpl(basicConnectionPool.getConnection(),"trains_route");
        stationDAO = new StationDAOImpl(basicConnectionPool.getConnection(),"stations");
    }

    public List<TrainTrip> getTrainTripsBetweenTwoStations(String first, String second){
        long firstStationId = stationDAO.findStationByName(first).getId();
        long secondStationId = stationDAO.findStationByName(second).getId();
        List<TrainRoute> listOFTrainRoutesBetweenTwoStations =
                trainRouteDAO.getTrainRoutesBetweenTwoStations(firstStationId,secondStationId);
        List<TrainTrip> listOfTrainsTrip = new ArrayList<>();
        for (int itter = 0; itter < listOFTrainRoutesBetweenTwoStations.size(); itter++) {
            listOfTrainsTrip.addAll(trainTripDAO.findTrainTripsByRoute(
                    listOFTrainRoutesBetweenTwoStations.get(itter).getId()
            ));
        }
        return listOfTrainsTrip;
    }

    public List<TrainTrip> getAllTripsFromTheStation(String startStation){
        long startStationId = stationDAO.findStationByName(startStation).getId();
        List<TrainRoute> allRoutesFromTheCurrentStation = trainRouteDAO.getTrainRoutesByFirstStation(startStationId);
        List<TrainTrip> allTrips = new ArrayList<>();
        for (int itter = 0; itter < allRoutesFromTheCurrentStation.size(); itter++) {
            allTrips.addAll(trainTripDAO.findTrainTripsByRoute(allRoutesFromTheCurrentStation.get(itter).getId()));
        }
        return allTrips;
    }

    public List<TrainTrip> getAllTripsToTheStation(String endStation){
        long endStationId = stationDAO.findStationByName(endStation).getId();
        List<TrainRoute> allRoutesToTheStation = trainRouteDAO.getTrainRoutesBySecondStation(endStationId);
        List<TrainTrip> allTrips = new ArrayList<>();
        for (int itter = 0; itter < allRoutesToTheStation.size(); itter++) {
            allTrips.addAll(trainTripDAO.findTrainTripsByRoute(allRoutesToTheStation.get(itter).getId()));
        }
        return allTrips;
    }
}
