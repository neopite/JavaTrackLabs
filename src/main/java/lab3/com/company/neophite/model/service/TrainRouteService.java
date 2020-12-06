package lab3.com.company.neophite.model.service;

import lab3.com.company.neophite.model.dao.TrainRouteDAO;
import lab3.com.company.neophite.model.dao.TrainTripDAO;
import lab3.com.company.neophite.model.dao.connection.BasicConnectionPool;
import lab3.com.company.neophite.model.dao.impl.TrainRouteDAOImpl;
import lab3.com.company.neophite.model.dao.impl.TrainTripDAOImpl;
import lab3.com.company.neophite.model.entity.TrainRoute;
import lab3.com.company.neophite.model.exception.StationNotFoundException;
import lab3.com.company.neophite.model.exception.TrainRouteNotFoundException;
import lab3.com.company.neophite.model.exception.TrainTripNotFoundException;

import java.sql.Connection;
import java.sql.SQLException;

public class TrainRouteService {
    private TrainRouteDAO trainRouteDAO;
    private TrainTripDAO trainTripDAO;
    private final Connection transactionConnection;

    public TrainRouteService() {
        BasicConnectionPool basicConnectionPool = BasicConnectionPool.getInstance(
                "jdbc:postgresql://127.0.0.1:5432/railwaydb",
                "postgres",
                "4427"
        );
        this.transactionConnection = basicConnectionPool.getConnection();
        trainRouteDAO = new TrainRouteDAOImpl(transactionConnection, "trains_route");
        trainTripDAO = new TrainTripDAOImpl(transactionConnection, "train_trip");
    }

    public TrainRoute addTrainRoute(TrainRoute trainRoute) {
        return trainRouteDAO.create(trainRoute);
    }

    public void deleteTrainRoute(long trainRoute) {
        try {
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
