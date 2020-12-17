package lab3.com.company.neophite.controller;

import lab3.com.company.neophite.controller.command.Command;
import lab3.com.company.neophite.controller.command.CommandList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

public class Servlet extends HttpServlet {
    private HashMap<String, CommandList> contoller = new HashMap<>();

    @Override
    public void init() throws ServletException {
        contoller.put("/registration", CommandList.REGISTER);
        contoller.put("/trips", CommandList.GET_TRIPS);
        contoller.put("/logout", CommandList.LOGOUT);
        contoller.put("/login", CommandList.LOGIN);
        contoller.put("/addStation",CommandList.ADD_STATION);
        contoller.put("/getAdminPage",CommandList.GET_ALL_STATIONS);
        contoller.put("/deleteStation",CommandList.DELETE_STATION);
        contoller.put("/editStation",CommandList.EDIT_STATION);
        contoller.put("/buyTicket",CommandList.BUY_TICKET);
        contoller.put("/getTickets",CommandList.GET_ALL_USERS_TICKETS);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String URI = req.getRequestURI();
        Command command = contoller.get(URI).getCommand();
        String result = command.execute(req);
        if (result.contains("redirect:")) {
            resp.sendRedirect(result.replace("redirect:", ""));
        } else {
            req.getRequestDispatcher(result).forward(req, resp);
        }
    }

}