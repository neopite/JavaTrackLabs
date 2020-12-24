package lab3.com.company.neophite.model.service;

import lab3.com.company.neophite.model.dao.DAOFactory;
import lab3.com.company.neophite.model.dao.TicketDAO;
import lab3.com.company.neophite.model.dao.TrainTripDAO;
import lab3.com.company.neophite.model.dao.UserDAO;
import lab3.com.company.neophite.model.dao.connection.BasicConnectionPool;
import lab3.com.company.neophite.model.entity.Ticket;
import lab3.com.company.neophite.model.entity.TrainTrip;
import lab3.com.company.neophite.model.exception.NoFreeSeatException;
import lab3.com.company.neophite.model.exception.NotMuchMoneyException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TicketService {

    private DAOFactory daoFactory;
    private Connection transactionConnection;
    private BasicConnectionPool basicConnectionPool;

    public TicketService(DAOFactory daoFactory) {
        this.transactionConnection = BasicConnectionPool.getInstance().getConnection();
        this.daoFactory = DAOFactory.getDaoFactory();
        this.basicConnectionPool = BasicConnectionPool.getInstance();
    }

    public TicketService() {
        this.daoFactory = DAOFactory.getDaoFactory();
        this.basicConnectionPool = BasicConnectionPool.getInstance();
    }


    public Ticket createTicket(Ticket ticket) throws RuntimeException {
        try (UserDAO userDAO = daoFactory.createUserDAO(transactionConnection);
             TrainTripDAO trainTripDAO = daoFactory.createTrainTripDAO(transactionConnection);
             TicketDAO ticketDAO = daoFactory.createTicketDAO(transactionConnection)
        ) {

            transactionConnection.setAutoCommit(false);
            float trainTripPrice = ticket.getTrainTripId().getPrice();
            float usersMoney = ticket.getUserId().getMoney();
            if (usersMoney - trainTripPrice < 0) {
                throw new NotMuchMoneyException("Not much money for purchase");
            } else userDAO.updateUsersMoney(ticket.getUserId().getId(), usersMoney - trainTripPrice);
            boolean freeSeats = ticket.getTrainTripId().getAvailableSeats() != 0;
            if (!freeSeats) {
                throw new NoFreeSeatException("No free places");
            }else {
                trainTripDAO.updateTrainTripAvailableSeats( (int) ticket.getTrainTripId().getId(),ticket.getTrainTripId().getAvailableSeats()-1);
            }
            ticket.setPlace(ticket.getTrainTripId().getAvailableSeats());
            ticketDAO.create(ticket);
            transactionConnection.commit();
            transactionConnection.setAutoCommit(true);
            return ticket;

        } catch (SQLException | NotMuchMoneyException | NoFreeSeatException exception) {
            try {
                transactionConnection.rollback();
                throw exception;
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
        return ticket;
    }
    public List<Ticket> getAllTicketsByUserId(long userId){
        List<Ticket> listOfTickets ;
        try(TicketDAO ticketDAO = daoFactory.createTicketDAO(basicConnectionPool.getConnection())){
            listOfTickets = ticketDAO.findTicketsByUsersId(userId);
        }
        return listOfTickets;
    }
}
