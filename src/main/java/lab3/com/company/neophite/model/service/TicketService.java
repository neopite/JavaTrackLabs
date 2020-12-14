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

public class TicketService {

    private DAOFactory daoFactory;
    private Connection transactionConnection;
    private BasicConnectionPool basicConnectionPool;

    public TicketService(DAOFactory daoFactory) {
        this.transactionConnection = BasicConnectionPool.getInstance().getConnection();
        this.daoFactory = daoFactory;
        this.basicConnectionPool = BasicConnectionPool.getInstance();
    }


    public void createTicket(Ticket ticket) {
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
            }else trainTripDAO.updateTrainTripAvailableSeats(ticket.getTrainTripId().getId(),)
            ticketDAO.create(ticket);
            transactionConnection.commit();
            transactionConnection.setAutoCommit(true);

        } catch (SQLException | NotMuchMoneyException | NoFreeSeatException exception) {
            try {
                transactionConnection.rollback();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
    }
}
