package lab2_5.com.company.neophite.controller.command.impl;

import lab2_5.com.company.neophite.controller.command.Command;
import lab2_5.com.company.neophite.model.entity.Role;
import lab2_5.com.company.neophite.model.entity.User;
import lab2_5.com.company.neophite.model.service.UserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginCommand implements Command {
    private UserService userService;
    private static final Logger log = LogManager.getLogger(LogoutCommand.class);


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
                log.info("User " + user.getName() + "is logged in");
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
