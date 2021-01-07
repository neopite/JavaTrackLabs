package lab2_5.com.company.neophite.controller.command.impl;

import lab2_5.com.company.neophite.controller.command.Command;
import lab2_5.com.company.neophite.model.entity.Station;
import lab2_5.com.company.neophite.model.entity.TrainRoute;
import lab2_5.com.company.neophite.model.service.StationService;
import lab2_5.com.company.neophite.model.service.TrainRouteService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class GetAllRoutesCommand implements Command {
    private TrainRouteService trainRouteService;
    private StationService stationService;

    public GetAllRoutesCommand(TrainRouteService trainRouteService,StationService stationService) {
        this.trainRouteService = trainRouteService;
        this.stationService = stationService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        List<TrainRoute> trainRouteList = trainRouteService.getAllRoutes();
        List<Station> listOfStation = stationService.getAllStation();
        request.setAttribute("stations",listOfStation);
        request.setAttribute("routes",trainRouteList);
        return "/jsp/admin/trainRoutesManaging.jsp";
    }
}
