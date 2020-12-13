package lab3;

import lab3.com.company.neophite.model.dao.TrainRouteDAO;
import lab3.com.company.neophite.model.dao.impl.DAOFactoryImpl;
import lab3.com.company.neophite.model.entity.TrainRoute;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        TrainRouteDAO trainRouteDAO = DAOFactoryImpl.getDaoFactory().createTrainRouteDAO();
        List<TrainRoute> trainTrips = trainRouteDAO.getTrainRoutesByFirstStation(1);
        for (int itter = 0; itter < trainTrips.size(); itter++) {
            System.out.println(trainTrips.get(itter));
        }
    }
}