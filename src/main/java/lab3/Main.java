package lab3;

import lab3.com.company.neophite.model.dao.impl.DAOFactoryImpl;
import lab3.com.company.neophite.model.entity.TrainTrip;
import lab3.com.company.neophite.model.service.ServiceFactory;
import lab3.com.company.neophite.model.service.TrainTripService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        TrainTripService trainTripService = ServiceFactory.getInstance().createTrainTripService();
        List<TrainTrip> trainTrips = trainTripService.getAllTripsFromTheStation("Kiev");
        for (int itter = 0; itter < trainTrips.size(); itter++) {
            System.out.println(trainTrips.get(itter));
        }
    }
}