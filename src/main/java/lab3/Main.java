package lab3;

import lab3.com.company.neophite.model.entity.TrainTrip;
import lab3.com.company.neophite.model.service.TrainTripService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        TrainTripService trainTripService = new TrainTripService();
        List<TrainTrip> trainTrips = trainTripService.getAllTripsToTheStation("Kiev");
        for (int itter = 0; itter < trainTrips.size(); itter++) {
            System.out.println(trainTrips.get(itter));
        }
    }
}