package lab3.com.company.neophite.controller.command.impl;

import lab3.com.company.neophite.controller.command.Command;
import lab3.com.company.neophite.controller.util.CustomException;
import lab3.com.company.neophite.controller.util.Validator;
import lab3.com.company.neophite.model.entity.TrainTrip;
import lab3.com.company.neophite.model.service.TrainTripService;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.List;

public class TrainTripCommand implements Command {
    private TrainTripService trainTripService;

    public TrainTripCommand(TrainTripService trainTripService) {
        this.trainTripService = trainTripService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        try {
            Validator.checkStationName( request.getParameter("fromStation"));
            Validator.checkStationName( request.getParameter("toStation"));
        } catch (CustomException customException) {
            request.setAttribute("error",customException.getMessage());
            return "/index.jsp";
        }
        String firstStation=request.getParameter("fromStation");
        String secondStation=request.getParameter("toStation");
        Date dateFrom = Date.valueOf(request.getParameter("dateFrom"));
        Date dateTo = Date.valueOf(request.getParameter("dateTo"));
        try{
            Validator.checkDateSeq(dateFrom,dateTo);
        }catch (CustomException customException){
            request.setAttribute("dateError",customException.getMessage());
            return "/index.jsp";
        }
        List<TrainTrip> trainTrips = trainTripService.getTrainTripsBetweenTwoStations(firstStation, secondStation,dateFrom,dateTo);
        request.setAttribute("trips", trainTrips);
        return "/index.jsp";
    }
}
