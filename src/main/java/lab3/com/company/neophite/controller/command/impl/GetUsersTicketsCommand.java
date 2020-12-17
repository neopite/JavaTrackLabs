package lab3.com.company.neophite.controller.command.impl;

import lab3.com.company.neophite.controller.command.Command;
import lab3.com.company.neophite.model.entity.Ticket;
import lab3.com.company.neophite.model.entity.User;
import lab3.com.company.neophite.model.service.TicketService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class GetUsersTicketsCommand implements Command {
    private TicketService ticketService;

    public GetUsersTicketsCommand(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        List<Ticket> listOfUsersTickets = ticketService.getAllTicketsByUserId(((User)(request.getSession().getAttribute("user"))).getId());
        request.setAttribute("usrTickets",listOfUsersTickets);
        return "boughtTickets.jsp";
    }
}
