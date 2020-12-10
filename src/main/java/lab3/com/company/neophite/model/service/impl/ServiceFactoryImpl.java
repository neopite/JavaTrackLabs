package lab3.com.company.neophite.model.service.impl;

import lab3.com.company.neophite.model.dao.DAOFactory;
import lab3.com.company.neophite.model.service.*;

public class ServiceFactoryImpl extends ServiceFactory {

    @Override
    public StationService createStationService() {
        return new StationService(DAOFactory.getDaoFactory());
    }

    @Override
    public UserService createUserService() {
        return new UserService(DAOFactory.getDaoFactory());
    }

    @Override
    public TrainRouteService createTrainRouteService() {
        return new TrainRouteService(DAOFactory.getDaoFactory());
    }

    @Override
    public TrainTripService createTrainTripService() {
        return new TrainTripService(DAOFactory.getDaoFactory());
    }
}
