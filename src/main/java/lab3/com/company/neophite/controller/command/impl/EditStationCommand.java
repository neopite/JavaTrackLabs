package lab3.com.company.neophite.controller.command.impl;

import lab3.com.company.neophite.controller.command.Command;
import lab3.com.company.neophite.model.service.StationService;

import javax.servlet.http.HttpServletRequest;

public class EditStationCommand implements Command {
    private StationService stationService;

    public EditStationCommand(StationService stationService) {
        this.stationService = stationService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        if (request.getMethod().equalsIgnoreCase("GET")) {
            request.setAttribute("stationId",request.getParameter("stationId"));
            return "/stationEdit.jsp";
        } else {
            stationService.updateStation(Long.parseLong(request.getParameter("stationId")), request.getParameter("stationName"));
            return "/getAdminPage";
        }
    }
}
