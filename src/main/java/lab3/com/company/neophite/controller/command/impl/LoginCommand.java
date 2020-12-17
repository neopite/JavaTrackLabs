package lab3.com.company.neophite.controller.command.impl;

import lab3.com.company.neophite.controller.command.Command;
import lab3.com.company.neophite.model.entity.User;
import lab3.com.company.neophite.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginCommand implements Command {
    private UserService userService;

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        final HttpSession httpSession = request.getSession();
        User user = userService.findUserByUsername(request.getParameter("username"));
        if(user!=null){
            if(user.getPasswd().equalsIgnoreCase(request.getParameter("password"))){
                httpSession.setAttribute("user",user);
                return "/index.jsp";
            }
        }else{
            request.setAttribute("error","Bad credentials");
            return "/login.jsp";
        }
        return "/index.jsp";
    }
}
