package lab3.com.company.neophite.model.service;

import lab3.com.company.neophite.model.service.impl.ServiceFactoryImpl;

public abstract class ServiceFactory {
    private static ServiceFactory serviceFactory;


    public static ServiceFactory getInstance() {
        if (serviceFactory == null) {
                    serviceFactory = new ServiceFactoryImpl();
        }
        return serviceFactory;
    }

    public abstract StationService createStationService();

    public abstract UserService createUserService();

    public abstract TrainRouteService createTrainRouteService();

    public abstract TrainTripService createTrainTripService();

}
