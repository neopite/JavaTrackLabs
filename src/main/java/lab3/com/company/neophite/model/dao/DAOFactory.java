package lab3.com.company.neophite.model.dao;

import lab3.com.company.neophite.model.dao.impl.DAOFactoryImpl;

public abstract class DAOFactory {
    private static DAOFactory daoFactory;
    public abstract StationDAO createStationDAO();
    public abstract TicketDAO createTicketDAO();
    public abstract TrainDAO createTrainDAO();
    public abstract TrainRouteDAO createTrainRouteDAO();
    public abstract TrainTripDAO createTrainTripDAO();
    public abstract UserDAO createUserDAO();
    public DAOFactory getDaoFactory(){
        if(daoFactory == null){
            daoFactory = new DAOFactoryImpl();

        }
        return daoFactory;
    }
}
