package lab3.com.company.neophite.model.dao;

import lab3.com.company.neophite.model.entity.Ticket;

import java.sql.Connection;

public abstract class TicketDAO extends AbstractDAO<Ticket, Long> {

    public TicketDAO(Connection connection) {
        super(connection);
    }
}
