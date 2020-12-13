package lab3.com.company.neophite.controller.command;

import lab3.com.company.neophite.controller.command.impl.RegistrationCommand;
import lab3.com.company.neophite.controller.command.impl.TrainTripCommand;
import lab3.com.company.neophite.model.service.ServiceFactory;

public enum CommandList {
    REGISTER(new RegistrationCommand(ServiceFactory.getInstance().createUserService())) , //TODO : change Service Factory init
    GET_TRIPS(new TrainTripCommand(ServiceFactory.getInstance().createTrainTripService()));

    private Command command;
    private CommandList(Command command){
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }

}
