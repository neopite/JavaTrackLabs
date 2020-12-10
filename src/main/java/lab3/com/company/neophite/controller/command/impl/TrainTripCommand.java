package lab3.com.company.neophite.controller.command.impl;

import lab3.com.company.neophite.controller.command.Command;
import lab3.com.company.neophite.model.entity.TrainTrip;
import lab3.com.company.neophite.model.service.TrainTripService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class TrainTripCommand implements Command {
private TrainTripService trainTripService;

    public TrainTripCommand(TrainTripService trainTripService) {
        this.trainTripService = trainTripService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String firstStation = request.getParameter("fromStation");
        String secondStation = request.getParameter("toStation");
        List<TrainTrip> trainTrips = trainTripService.getTrainTripsBetweenTwoStations(firstStation,secondStation);
        request.setAttribute("trips",trainTrips);
        return "/index.jsp";
    }
}
