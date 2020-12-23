package lab3.com.company.neophite.controller.command.impl;

import lab3.com.company.neophite.controller.command.Command;
import lab3.com.company.neophite.model.dao.TrainRouteDAO;
import lab3.com.company.neophite.model.service.TrainRouteService;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;

public class DeleteTrainRouteCommand implements Command {
    private TrainRouteService trainRouteService;

    public DeleteTrainRouteCommand(TrainRouteService trainRouteService) {
        this.trainRouteService = trainRouteService;
    }

    @Override
    public String execute(HttpServletRequest request) {
            trainRouteService.deleteTrainRoute(
                    Long.parseLong(request.getParameter("routeId")),
                    Date.valueOf(request.getParameter("startDate")),
                    Date.valueOf(request.getParameter("endDate"))

            );
            return "/admin/routesManaging";
    }
}
