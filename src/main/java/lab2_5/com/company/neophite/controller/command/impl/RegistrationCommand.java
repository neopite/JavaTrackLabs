package lab2_5.com.company.neophite.controller.command.impl;

import lab2_5.com.company.neophite.controller.command.Command;
import lab2_5.com.company.neophite.controller.util.CustomException;
import lab2_5.com.company.neophite.controller.util.Validator;
import lab2_5.com.company.neophite.model.entity.User;
import lab2_5.com.company.neophite.model.exception.UserAlreadyCreatedException;
import lab2_5.com.company.neophite.model.service.UserService;

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
            return "/jsp/reg.jsp";
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
            return "/jsp/reg.jsp";
        }
        try {
            userService.createUser(newUser);
        } catch (UserAlreadyCreatedException e) {
            request.setAttribute("error","User already created , please provide another credentials");
        }
        return "redirect:/jsp/login.jsp";
    }
}

