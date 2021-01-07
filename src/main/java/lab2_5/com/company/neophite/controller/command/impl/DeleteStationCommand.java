package lab2_5.com.company.neophite.controller.command.impl;

import lab2_5.com.company.neophite.controller.command.Command;
import lab2_5.com.company.neophite.model.service.StationService;

import javax.servlet.http.HttpServletRequest;

public class DeleteStationCommand implements Command {
    private StationService stationService ;

    public DeleteStationCommand(StationService stationService) {
        this.stationService = stationService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        stationService.deleteStation(Long.parseLong(request.getParameter("stationId")));
        return "/admin/stationManaging";
    }

}
