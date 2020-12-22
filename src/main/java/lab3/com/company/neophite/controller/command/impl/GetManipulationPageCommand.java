package lab3.com.company.neophite.controller.command.impl;

import lab3.com.company.neophite.controller.command.Command;
import lab3.com.company.neophite.model.entity.Station;
import lab3.com.company.neophite.model.service.StationService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class GetManipulationPageCommand implements Command {
    private StationService stationService;

    public GetManipulationPageCommand(StationService stationService) {
        this.stationService = stationService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        List<Station> listOfStation = stationService.getAllStation();
        request.setAttribute("stations",listOfStation);
        return "/jsp/admin/managing.jsp";
    }
}
