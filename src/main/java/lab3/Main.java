package lab3;

import lab3.com.company.neophite.model.dao.DAOFactory;
import lab3.com.company.neophite.model.dao.TrainRouteDAO;
import lab3.com.company.neophite.model.dao.connection.BasicConnectionPool;
import lab3.com.company.neophite.model.dao.impl.DAOFactoryImpl;
import lab3.com.company.neophite.model.entity.TrainRoute;
import lab3.com.company.neophite.model.service.ServiceFactory;
import lab3.com.company.neophite.model.service.StationService;

public class Main {
    public static void main(String[] args) {
        StationService stationService = ServiceFactory.getInstance().createStationService();
        stationService.deleteStation(1);
    }
}