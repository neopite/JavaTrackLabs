package lab2_5.com.company.neophite.controller.command.impl;

import lab2_5.com.company.neophite.controller.command.Command;
import lab2_5.com.company.neophite.model.entity.Ticket;
import lab2_5.com.company.neophite.model.entity.TrainTrip;
import lab2_5.com.company.neophite.model.entity.User;
import lab2_5.com.company.neophite.model.service.TicketService;
import lab2_5.com.company.neophite.model.service.TrainTripService;

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
        if(currentUser.getMoney()<trainTrip.getPrice()){
            request.setAttribute("errorMoney","Not enough money");
            return "/jsp/home.jsp";
        }
        Ticket newTicket = new Ticket(currentUser,trainTrip);
        ticketService.createTicket(newTicket);
        return "/jsp/home.jsp";
    }
}
