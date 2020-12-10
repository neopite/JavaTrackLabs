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
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (username.length() == 0) {
            return "/reg.jsp";
        } else {
            User newUser = new User(
                    request.getParameter("username"),
                    request.getParameter("password"),
                    request.getParameter("name"),
                    Integer.parseInt(request.getParameter("age")),
                    request.getParameter("email")
            );
            userService.createUser(newUser);
            return "redirect:/login.jsp";
        }
    }
}
