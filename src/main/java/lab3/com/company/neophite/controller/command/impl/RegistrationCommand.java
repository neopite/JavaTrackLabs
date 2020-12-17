package lab3.com.company.neophite.controller.command.impl;

import lab3.com.company.neophite.controller.command.Command;
import lab3.com.company.neophite.controller.util.CustomException;
import lab3.com.company.neophite.controller.util.Validator;
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
        if (username == null) {
            return "/reg.jsp";
        }
        User newUser = new User(
                request.getParameter("username"),
                request.getParameter("password"),
                request.getParameter("name"),
                Integer.parseInt(request.getParameter("age")),
                request.getParameter("email"));

        try {
            Validator.checkRegistrationCredentials(newUser);
        }catch (CustomException customException) {
            request.setAttribute("error",customException.getMessage());
            return "/reg.jsp";
        }
        userService.createUser(newUser);
        return "redirect:/login.jsp";
    }
}

