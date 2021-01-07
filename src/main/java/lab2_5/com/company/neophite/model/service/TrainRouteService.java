package lab2_5.com.company.neophite.model.service;

import lab2_5.com.company.neophite.model.dao.DAOFactory;
import lab2_5.com.company.neophite.model.dao.TrainRouteDAO;
import lab2_5.com.company.neophite.model.dao.TrainTripDAO;
import lab2_5.com.company.neophite.model.dao.connection.BasicConnectionPool;
import lab2_5.com.company.neophite.model.entity.TrainRoute;
import lab2_5.com.company.neophite.model.entity.TrainTrip;
import lab2_5.com.company.neophite.model.exception.StationNotFoundException;
import lab2_5.com.company.neophite.model.exception.TrainRouteNotFoundException;
import lab2_5.com.company.neophite.model.exception.TrainTripNotFoundException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TrainRouteService {
    private  Connection transactionConnection;
    private DAOFactory daoFactory ;
    private BasicConnectionPool basicConnectionPool;

    public TrainRouteService(DAOFactory daoFactory) {
        this.transactionConnection = BasicConnectionPool.getInstance().getConnection();
        this.daoFactory = daoFactory;
        this.basicConnectionPool = BasicConnectionPool.getInstance();
    }

    public TrainRouteService() {
        this.daoFactory = DAOFactory.getDaoFactory();
        this.basicConnectionPool = BasicConnectionPool.getInstance();
    }

    public TrainRoute addTrainRoute(TrainRoute trainRoute) {
        try(TrainRouteDAO trainRouteDAO = daoFactory.createTrainRouteDAO(basicConnectionPool.getConnection())) {
            return trainRouteDAO.create(trainRoute);
        }
    }

    public List<TrainRoute> getAllRoutes(){
        try (TrainRouteDAO trainTripDAO = daoFactory.createTrainRouteDAO(basicConnectionPool.getConnection())){
                return trainTripDAO.getAll();
            }
    }

    public void deleteTrainRoute(long trainRoute) {
        try(TrainRouteDAO trainRouteDAO = daoFactory.createTrainRouteDAO(transactionConnection);
            TrainTripDAO trainTripDAO = daoFactory.createTrainTripDAO(transactionConnection)
        ) {
            transactionConnection.setAutoCommit(false);

            List<TrainTrip> trainTrips = trainTripDAO.findTrainTripsByRoute(trainRoute);
            if(trainTrips.isEmpty()){
                trainRouteDAO.deleteByKey(trainRoute);
                transactionConnection.commit();
                transactionConnection.setAutoCommit(true);
                return;
            }
            boolean deleted = trainRouteDAO.deleteByKey(trainRoute);
            if(!deleted){
                throw new TrainRouteNotFoundException("No found this trainRoute");
            }
            boolean isDeleted = trainTripDAO.deleteAllTrainTripsByRouteId(trainRoute);
            if (!isDeleted) {
                throw new TrainTripNotFoundException("Train trips with id of route : " + trainRoute + " not found");
            }
            transactionConnection.commit();
            transactionConnection.setAutoCommit(true);
        } catch (SQLException | StationNotFoundException | TrainRouteNotFoundException | TrainTripNotFoundException exception) {
            try {
                transactionConnection.rollback();
                throw exception;
            } catch (SQLException throwables) {
                exception.printStackTrace();
            }
        }
    }

}
