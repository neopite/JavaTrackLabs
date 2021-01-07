package lab2_5.com.company.neophite.model.dao;

import lab2_5.com.company.neophite.model.entity.Ticket;

import java.sql.Connection;
import java.util.List;

public abstract class TicketDAO extends AbstractDAO<Ticket, Long> {

    public TicketDAO(Connection connection) {
        super(connection);
    }
    public abstract List<Ticket> findTicketsByUsersId(long userId);

}
