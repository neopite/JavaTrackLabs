package lab3.com.company.neophite.model.dao.impl;

import lab3.com.company.neophite.model.dao.*;
import lab3.com.company.neophite.model.dao.connection.BasicConnectionPool;

public class DAOFactoryImpl extends DAOFactory {
    private static  BasicConnectionPool basicConnectionPool = BasicConnectionPool.getInstance();

    @Override
    public StationDAO createStationDAO() {
        return new StationDAOImpl(basicConnectionPool.getConnection());
    }

    @Override
    public TicketDAO createTicketDAO() {
        return new TicketDAOImpl(basicConnectionPool.getConnection());
    }

    @Override
    public TrainDAO createTrainDAO() {
        return new TrainDAOImpl(basicConnectionPool.getConnection());
    }

    @Override
    public TrainRouteDAO createTrainRouteDAO() {
        return new TrainRouteDAOImpl(basicConnectionPool.getConnection());
    }

    @Override
    public TrainTripDAO createTrainTripDAO() {
        return new TrainTripDAOImpl(basicConnectionPool.getConnection());
    }

    @Override
    public UserDAO createUserDAO() {
        return new UserDAOImpl(basicConnectionPool.getConnection());
    }
}
