package lab2_5;

import lab2_5.com.company.neophite.model.service.ServiceFactory;
import lab2_5.com.company.neophite.model.service.StationService;

public class Main {
    public static void main(String[] args) {
        StationService stationService = ServiceFactory.getInstance().createStationService();
        stationService.deleteStation(1);
    }
}