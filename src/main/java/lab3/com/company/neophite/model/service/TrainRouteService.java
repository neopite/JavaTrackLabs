package lab3.com.company.neophite.model.service;

import lab3.com.company.neophite.model.dao.DAOFactory;
import lab3.com.company.neophite.model.dao.TrainRouteDAO;
import lab3.com.company.neophite.model.dao.TrainTripDAO;
import lab3.com.company.neophite.model.dao.connection.BasicConnectionPool;
import lab3.com.company.neophite.model.entity.TrainRoute;
import lab3.com.company.neophite.model.exception.StationNotFoundException;
import lab3.com.company.neophite.model.exception.TrainRouteNotFoundException;
import lab3.com.company.neophite.model.exception.TrainTripNotFoundException;

import java.sql.Connection;
import java.sql.SQLException;

public class TrainRouteService {
    private final Connection transactionConnection;
    private DAOFactory daoFactory ;

    public TrainRouteService(DAOFactory daoFactory) {
        this.transactionConnection = BasicConnectionPool.getInstance().getConnection();
        this.daoFactory = daoFactory;
    }

    public TrainRoute addTrainRoute(TrainRoute trainRoute) {
        try(TrainRouteDAO trainRouteDAO = daoFactory.createTrainRouteDAO()) {
            return trainRouteDAO.create(trainRoute);
        }
    }

    public void deleteTrainRoute(long trainRoute) {
        try(TrainRouteDAO trainRouteDAO = daoFactory.createTrainRouteDAO() ;
            TrainTripDAO trainTripDAO = daoFactory.createTrainTripDAO()
        ) {
            transactionConnection.setAutoCommit(false);

            boolean trainRouteIstrue = trainRouteDAO.deleteAllRoutesWithStationId(trainRoute);
            if (!trainRouteIstrue) {
                throw new TrainRouteNotFoundException("Train Routes with id : " + trainRoute + "  not found");
            }
            boolean isDeleted = trainTripDAO.deleteAllTrainTripsByRouteId(trainRoute);
            if (!isDeleted) {
                throw new TrainTripNotFoundException("Train trip with id of route : " + trainRoute + " not found");
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

}
