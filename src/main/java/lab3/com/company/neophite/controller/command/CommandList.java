package lab3.com.company.neophite.controller.command;

import lab3.com.company.neophite.controller.command.impl.*;
import lab3.com.company.neophite.model.service.ServiceFactory;

public enum CommandList {
    REGISTER(new RegistrationCommand(ServiceFactory.getInstance().createUserService())) , //TODO : change Service Factory init
    GET_TRIPS(new TrainTripCommand(ServiceFactory.getInstance().createTrainTripService())),
    LOGIN(new LoginCommand(ServiceFactory.getInstance().createUserService())),
    LOGOUT(new LogoutCommand()),
    ADD_STATION(new AddStationCommand(ServiceFactory.getInstance().createStationService())),
    GET_ALL_STATIONS(new GetManipulationPageCommand(ServiceFactory.getInstance().createStationService())),
    DELETE_STATION(new DeleteStationCommand(ServiceFactory.getInstance().createStationService())),
    EDIT_STATION(new EditStationCommand(ServiceFactory.getInstance().createStationService())),
    DELETE_ROUTE(new DeleteTrainRouteCommand(ServiceFactory.getInstance().createTrainRouteService())),
    GET_ROUTES_PAGE(new GetAllRoutesCommand(ServiceFactory.getInstance().createTrainRouteService())),
    BUY_TICKET(new BuyTicketCommand(ServiceFactory.getInstance().createTicketService(),ServiceFactory.getInstance().createTrainTripService())),
    GET_ALL_USERS_TICKETS(new GetUsersTicketsCommand(ServiceFactory.getInstance().createTicketService()));

    private Command command;
    private CommandList(Command command){
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }

}
