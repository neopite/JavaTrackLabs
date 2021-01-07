package lab2_5.com.company.neophite.model.dao;

import lab2_5.com.company.neophite.model.dao.impl.DAOFactoryImpl;
import lab2_5.com.company.neophite.model.dao.impl.RoleDAOImpl;

import java.sql.Connection;

public abstract class DAOFactory {
    private static DAOFactory daoFactory;

    public abstract StationDAO createStationDAO(Connection connection);

    public abstract TicketDAO createTicketDAO(Connection connection);

    public abstract TrainDAO createTrainDAO(Connection connection);

    public abstract TrainRouteDAO createTrainRouteDAO(Connection connection);

    public abstract TrainTripDAO createTrainTripDAO(Connection connection);

    public abstract UserDAO createUserDAO(Connection connection);

    public abstract RoleDAOImpl createRoleDAO(Connection connection);

    public static DAOFactory getDaoFactory() {
        if (daoFactory == null) {
            daoFactory = new DAOFactoryImpl();
        }
        return daoFactory;
    }

}
