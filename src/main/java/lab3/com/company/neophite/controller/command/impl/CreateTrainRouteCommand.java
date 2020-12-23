package lab3.com.company.neophite.controller.command.impl;

import lab3.com.company.neophite.controller.command.Command;
import lab3.com.company.neophite.model.entity.Station;
import lab3.com.company.neophite.model.entity.TrainRoute;
import lab3.com.company.neophite.model.service.StationService;
import lab3.com.company.neophite.model.service.TrainRouteService;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateTrainRouteCommand implements Command {
    private TrainRouteService trainRouteService;
    private StationService stationService;

    public CreateTrainRouteCommand(TrainRouteService trainRouteService, StationService stationService) {
        this.trainRouteService = trainRouteService;
        this.stationService = stationService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Station station1 = stationService.findStationById(Long.parseLong(request.getParameter("startStation")));
        Station station2 = stationService.findStationById(Long.parseLong(request.getParameter("endStation")));
        Date from = null;
        Date to = null;
        SimpleDateFormat utilDate = new SimpleDateFormat("yyyyMMddHHmm");
        try {
            from = utilDate.parse(request.getParameter("startDate"));
            to =  utilDate.parse(request.getParameter("endDate"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        java.sql.Date sqlDateFrom=  new java.sql.Date(from.getTime());
        java.sql.Date sqlDateTo =  new java.sql.Date(to.getTime());
        TrainRoute trainRoute = new TrainRoute(
                station1,
                station2,
                sqlDateFrom,
                sqlDateTo
        );
        trainRouteService.addTrainRoute(trainRoute);
        return "/admin/routesManaging";
    }
}
