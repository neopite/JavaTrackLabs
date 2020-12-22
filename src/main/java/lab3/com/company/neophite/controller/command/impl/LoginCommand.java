package lab3.com.company.neophite.controller.command.impl;

import lab3.com.company.neophite.controller.command.Command;
import lab3.com.company.neophite.model.entity.Role;
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
        final HttpSession httpSession = request.getSession(true);
        User user = userService.findUserByUsername(request.getParameter("username"));
        Role role ;
        if(user!=null){
            role = user.getRoles().get(0);
            if(user.getPasswd().equalsIgnoreCase(request.getParameter("password"))){
                httpSession.setAttribute("user",user);
                return role.getName().equalsIgnoreCase("admin")?"/jsp/admin/home.jsp" : "/jsp/home.jsp";
            }
        }else{
            request.setAttribute("error","Bad credentials");
            return "/jsp/login.jsp";
        }
        return "/jsp/login.jsp";
    }
}
