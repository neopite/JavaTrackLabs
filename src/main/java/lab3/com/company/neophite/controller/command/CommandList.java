package lab3.com.company.neophite.controller.command;

import lab3.com.company.neophite.controller.command.impl.RegistrationCommand;
import lab3.com.company.neophite.model.service.UserService;

public enum CommandList {
    REGISTRATE(new RegistrationCommand(new UserService()));


    private Command command;
    private CommandList(Command command){
        this.command = command;
    }
}
