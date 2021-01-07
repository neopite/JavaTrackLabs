package lab2_5.com.company.neophite.model.service;

import lab2_5.com.company.neophite.model.dao.DAOFactory;
import lab2_5.com.company.neophite.model.dao.StationDAO;
import lab2_5.com.company.neophite.model.dao.TrainRouteDAO;
import lab2_5.com.company.neophite.model.dao.TrainTripDAO;
import lab2_5.com.company.neophite.model.dao.connection.BasicConnectionPool;
import lab2_5.com.company.neophite.model.entity.Station;
import lab2_5.com.company.neophite.model.entity.TrainRoute;
import lab2_5.com.company.neophite.model.entity.TrainTrip;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class TrainTripService {
    private DAOFactory daoFactory;
    private BasicConnectionPool basicConnectionPool;

    public TrainTripService(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
        this.basicConnectionPool = BasicConnectionPool.getInstance();
    }

    public TrainTripService() {
        this.daoFactory = DAOFactory.getDaoFactory();
        this.basicConnectionPool = BasicConnectionPool.getInstance();
    }

    public List<TrainTrip> getTrainTripsBetweenTwoStations(String first, String second, Timestamp from, Timestamp to) {
        try (StationDAO stationDAO = daoFactory.createStationDAO(basicConnectionPool.getConnection());
             TrainTripDAO trainTripDAO = daoFactory.createTrainTripDAO(basicConnectionPool.getConnection());
             TrainRouteDAO trainRouteDAO = daoFactory.createTrainRouteDAO(basicConnectionPool.getConnection())
        ) {
            Station stationFrom = stationDAO.findStationByName(first);
            Station stationTo = stationDAO.findStationByName(second);
            if (stationTo ==null || stationFrom == null){
                return new ArrayList<>();
            }
            List<TrainRoute> listOFTrainRoutesBetweenTwoStations =
                    trainRouteDAO.getTrainRoutesBetweenTwoStations(stationFrom.getId(), stationTo.getId(),from,to);
            List<TrainTrip> listOfTrainsTrip = new ArrayList<>();
            for (int itter = 0; itter < listOFTrainRoutesBetweenTwoStations.size(); itter++) {
                listOfTrainsTrip.addAll(trainTripDAO.findTrainTripsByRoute(
                        listOFTrainRoutesBetweenTwoStations.get(itter).getId()
                ));
            }
            return listOfTrainsTrip;
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
