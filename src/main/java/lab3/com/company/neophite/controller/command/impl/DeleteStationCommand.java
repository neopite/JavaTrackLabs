package lab3.com.company.neophite.controller.command.impl;

import lab3.com.company.neophite.controller.command.Command;
import lab3.com.company.neophite.model.service.StationService;

import javax.servlet.http.HttpServletRequest;

public class DeleteStationCommand implements Command {
    private StationService stationService ;

    public DeleteStationCommand(StationService stationService) {
        this.stationService = stationService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        stationService.deleteStation(Long.parseLong(request.getParameter("station_id")));
        return "/getAdminPage";
    }
}
