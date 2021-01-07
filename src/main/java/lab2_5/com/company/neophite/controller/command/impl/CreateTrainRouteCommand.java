package lab2_5.com.company.neophite.controller.command.impl;

import lab2_5.com.company.neophite.controller.command.Command;
import lab2_5.com.company.neophite.model.entity.Station;
import lab2_5.com.company.neophite.model.entity.TrainRoute;
import lab2_5.com.company.neophite.model.service.StationService;
import lab2_5.com.company.neophite.model.service.TrainRouteService;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
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
        Station station1 = stationService.findStationById(Long.parseLong(request.getParameter("startStation")));
        Station station2 = stationService.findStationById(Long.parseLong(request.getParameter("endStation")));
        Date from = null;
        Date to = null;
        SimpleDateFormat utilDate = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
        try {
            from = utilDate.parse(request.getParameter("startDate"));
            to =  utilDate.parse(request.getParameter("endDate"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Timestamp sqlDateFrom= new Timestamp(from.getTime());
        Timestamp sqlDateTo = new Timestamp(to.getTime());
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
