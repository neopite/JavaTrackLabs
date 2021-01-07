package lab2_5.com.company.neophite.controller;

import lab2_5.com.company.neophite.controller.command.Command;
import lab2_5.com.company.neophite.controller.command.CommandList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class Servlet extends HttpServlet {
    private HashMap<String, CommandList> contoller = new HashMap<>();

    @Override
    public void init() throws ServletException {
        contoller.put("/registration", CommandList.REGISTER);
        contoller.put("/logout", CommandList.LOGOUT);
        contoller.put("/login", CommandList.LOGIN);
        contoller.put("/trips", CommandList.GET_TRIPS);
        contoller.put("/admin/addStation",CommandList.ADD_STATION);
        contoller.put("/admin/stationManaging",CommandList.GET_ALL_STATIONS);
        contoller.put("/admin/deleteStation",CommandList.DELETE_STATION);
        contoller.put("/admin/editStation",CommandList.EDIT_STATION);
        contoller.put("/user/buyTicket",CommandList.BUY_TICKET);
        contoller.put("/user/getTickets",CommandList.GET_ALL_USERS_TICKETS);
        contoller.put("/admin/deleteRoute",CommandList.DELETE_ROUTE);
        contoller.put("/admin/routesManaging",CommandList.GET_ROUTES_PAGE);
        contoller.put("/admin/routeCreationPage",CommandList.GET_ROUTE_CREATION_PAGE);
        contoller.put("/admin/createRoute",CommandList.CREATE_TRAIN_ROUTE);
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