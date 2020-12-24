package lab3.com.company.neophite.controller.command.impl;

import lab3.com.company.neophite.controller.command.Command;
import lab3.com.company.neophite.controller.util.CustomException;
import lab3.com.company.neophite.controller.util.Pagination;
import lab3.com.company.neophite.controller.util.Validator;
import lab3.com.company.neophite.model.entity.TrainTrip;
import lab3.com.company.neophite.model.service.TrainTripService;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TrainTripCommand implements Command {
    private TrainTripService trainTripService;

    public TrainTripCommand(TrainTripService trainTripService) {
        this.trainTripService = trainTripService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        if(request.getParameter("page")!=null){
            Pagination.pagination(request, (ArrayList)request.getSession(false).getAttribute("trips"),"trips",3);
            return "/jsp/home.jsp";
        }
        try {
            Validator.checkStationName( request.getParameter("fromStation"));
            Validator.checkStationName( request.getParameter("toStation"));
        } catch (CustomException customException) {
            Pagination.pagination(request, (ArrayList)request.getSession(false).getAttribute("trips"),"trips",3);
            request.setAttribute("error",customException.getMessage());
            return "/jsp/home.jsp";
        }
        String firstStation=request.getParameter("fromStation");
        String secondStation=request.getParameter("toStation");
        SimpleDateFormat utilDate = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
        Date dateFrom ;
        Date dateTo;
        try{
            dateFrom = utilDate.parse(request.getParameter("dateFrom"));
            dateTo =  utilDate.parse(request.getParameter("dateTo"));
            Validator.checkDateSeq(dateFrom,dateTo);
        }catch (CustomException | ParseException |IllegalArgumentException customException){
            Pagination.pagination(request, (ArrayList)request.getSession(false).getAttribute("trips"),"trips",3);
            request.setAttribute("dateError","Bad Date Input");
            return "/jsp/home.jsp";
        }
        Timestamp sqlDateFrom= new Timestamp(dateFrom.getTime());
        Timestamp sqlDateTo = new Timestamp(dateTo.getTime());
        List<TrainTrip> trainTrips = trainTripService.getTrainTripsBetweenTwoStations(firstStation, secondStation,sqlDateFrom,sqlDateTo);
        Pagination.pagination(request, (ArrayList) trainTrips,"trips",3);
        request.getSession(false).setAttribute("trips",trainTrips);
        return "/jsp/home.jsp";
    }
}
