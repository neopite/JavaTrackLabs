package lab2_5.com.company.neophite.model.dao.impl;

import lab2_5.com.company.neophite.model.dao.*;

import java.sql.Connection;

public class DAOFactoryImpl extends DAOFactory {


    @Override
    public StationDAO createStationDAO(Connection connection) {
        return new StationDAOImpl(connection);
    }

    @Override
    public TicketDAO createTicketDAO(Connection connection) {
        return new TicketDAOImpl(connection);
    }

    @Override
    public TrainDAO createTrainDAO(Connection connection) {
        return new TrainDAOImpl(connection);
    }

    @Override
    public TrainRouteDAO createTrainRouteDAO(Connection connection) {
        return new TrainRouteDAOImpl(connection);
    }

    @Override
    public TrainTripDAO createTrainTripDAO(Connection connection) {
        return new TrainTripDAOImpl(connection);
    }

    @Override
    public UserDAO createUserDAO(Connection connection) {
        return new UserDAOImpl(connection);
    }

    @Override
    public RoleDAOImpl createRoleDAO(Connection connection) {
        return new RoleDAOImpl(connection);
    }
}
