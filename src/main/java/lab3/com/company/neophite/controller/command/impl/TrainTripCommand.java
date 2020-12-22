package lab3.com.company.neophite.controller.command.impl;

import lab3.com.company.neophite.controller.command.Command;
import lab3.com.company.neophite.controller.util.CustomException;
import lab3.com.company.neophite.controller.util.Pagination;
import lab3.com.company.neophite.controller.util.Validator;
import lab3.com.company.neophite.model.entity.TrainTrip;
import lab3.com.company.neophite.model.service.TrainTripService;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.ArrayList;
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
            return "/jsp/home.jsp";
        }
        String firstStation=request.getParameter("fromStation");
        String secondStation=request.getParameter("toStation");
        Date dateFrom ;
        Date dateTo;
        try{
            dateFrom = Date.valueOf(request.getParameter("dateFrom"));
            dateTo = Date.valueOf(request.getParameter("dateTo"));
            Validator.checkDateSeq(dateFrom,dateTo);
        }catch (CustomException | IllegalArgumentException customException){
            request.setAttribute("dateError","Bad Date Input");
            return "/jsp/home.jsp";
        }
        List<TrainTrip> trainTrips = trainTripService.getTrainTripsBetweenTwoStations(firstStation, secondStation,dateFrom,dateTo);
        //request.setAttribute("trips",trainTrips);
        Pagination.pagination(request, (ArrayList) trainTrips,"trips",1);
        return "/jsp/home.jsp";
    }
}
