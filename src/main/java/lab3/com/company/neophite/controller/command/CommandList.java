package lab3.com.company.neophite.controller.command;

import lab3.com.company.neophite.controller.command.impl.RegistrationCommand;
import lab3.com.company.neophite.model.service.ServiceFactory;
import lab3.com.company.neophite.model.service.UserService;

public enum CommandList {
    REGISTRATE(new RegistrationCommand(ServiceFactory.getInstance().createUserService()));

    private Command command;
    private CommandList(Command command){
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }

}
