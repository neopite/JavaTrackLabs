package lab3.com.company.neophite.controller.command.impl;

import lab3.com.company.neophite.controller.command.Command;
import lab3.com.company.neophite.model.entity.User;
import lab3.com.company.neophite.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class RegistrationCommand implements Command {
    private UserService userService;

    public RegistrationCommand(UserService userService) {
        this.userService = userService;
    }


    @Override
    public String execute(HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        String password = (String) request.getAttribute("password");
        if(username.length()==0){
            return "reg.jsp";
        }else{
            User newUser = new User(
                    (String)request.getAttribute("username"),
                    (String)request.getAttribute("password"),
                    (String)request.getAttribute("name"),
                    (int)request.getAttribute("age"),
                    (String)request.getAttribute("email")
            );
            userService.createUser(newUser);
            return "redirect:/login";
        }
    }
}
