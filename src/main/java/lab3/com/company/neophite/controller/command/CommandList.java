package lab3.com.company.neophite.controller.command;

import lab3.com.company.neophite.controller.command.impl.RegistrationCommand;
import lab3.com.company.neophite.controller.command.impl.TrainTripCommand;
import lab3.com.company.neophite.model.service.ServiceFactory;
import lab3.com.company.neophite.model.service.UserService;

public enum CommandList {
    REGISTER(new RegistrationCommand(ServiceFactory.getInstance().createUserService())) ,
    GET_TRIPS(new TrainTripCommand(ServiceFactory.getInstance().createTrainTripService()));

    private Command command;
    private CommandList(Command command){
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }

}
