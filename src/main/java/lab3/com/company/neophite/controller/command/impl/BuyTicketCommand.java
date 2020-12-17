package lab3.com.company.neophite.controller.command.impl;

import lab3.com.company.neophite.controller.command.Command;
import lab3.com.company.neophite.model.entity.Ticket;
import lab3.com.company.neophite.model.entity.TrainTrip;
import lab3.com.company.neophite.model.entity.User;
import lab3.com.company.neophite.model.service.TicketService;
import lab3.com.company.neophite.model.service.TrainTripService;

import javax.servlet.http.HttpServletRequest;

public class BuyTicketCommand implements Command {
    private TicketService ticketService;
    private TrainTripService trainTripService;

    public BuyTicketCommand(TicketService ticketService,TrainTripService trainTrip) {
        this.ticketService = ticketService;
        this.trainTripService = trainTrip;
    }

    @Override
    public String execute(HttpServletRequest request) {
        User currentUser = (User)request.getSession().getAttribute("user");
        long trainTripId = Long.parseLong(request.getParameter("trainTrip"));
        TrainTrip trainTrip = trainTripService.findTrainTripById(trainTripId);
        Ticket newTicket = new Ticket(currentUser,trainTrip);
        ticketService.createTicket(newTicket);
        return "/index.jsp";
    }
}
