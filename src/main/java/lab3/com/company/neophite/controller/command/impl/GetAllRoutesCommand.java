package lab3.com.company.neophite.controller.command.impl;

import lab3.com.company.neophite.controller.command.Command;
import lab3.com.company.neophite.model.entity.TrainRoute;
import lab3.com.company.neophite.model.service.TrainRouteService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class GetAllRoutesCommand implements Command {
    private TrainRouteService trainRouteService;

    public GetAllRoutesCommand(TrainRouteService trainRouteService) {
        this.trainRouteService = trainRouteService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        List<TrainRoute> trainRouteList = trainRouteService.getAllRoutes();
        request.setAttribute("routes",trainRouteList);
        return "/jsp/admin/trainRouteManaging.jsp";
    }
}
