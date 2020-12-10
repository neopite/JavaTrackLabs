package lab3.com.company.neophite.controller;

import lab3.com.company.neophite.controller.command.Command;
import lab3.com.company.neophite.controller.command.CommandList;
import lab3.com.company.neophite.controller.command.impl.RegistrationCommand;
import lab3.com.company.neophite.model.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class Servlet extends HttpServlet {
    private HashMap<String, Command> contoller = new HashMap<>();

    @Override
    public void init() throws ServletException {
        contoller.put("/registration",new RegistrationCommand(ServiceFactory.getInstance().createUserService()));
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String URI = req.getRequestURI();
        Command command = contoller.get(URI);
        String result = command.execute(req);

    }


}
