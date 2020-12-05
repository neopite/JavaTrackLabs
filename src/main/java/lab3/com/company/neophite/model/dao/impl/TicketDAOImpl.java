package lab3.com.company.neophite.model.dao.impl;

import lab3.com.company.neophite.model.dao.TicketDAO;
import lab3.com.company.neophite.model.entity.Ticket;

import java.sql.Connection;
import java.util.List;

public class TicketDAOImpl extends TicketDAO {

    private final String CREATE = "insert into " + this.getTable() +
            " (train_trip,id_user,place) values(?,?,?)";
    private final String FIND_STATION_BY_NAME = "select * from " + this.getTable() + " where name=?";
    private final String FIND_STATION_BY_ID = "select * from " + this.getTable() + " where id=?";
    private final String DELETE_STATION_BY_ID = "delete from " + this.getTable() + " where id=?";
    private final String UPDATE_STATION_BY_ID = "update " + this.getTable() + " set name=? where id=?";
    private final String GET_ALL_STATION = "select * from " + this.getTable();


    public TicketDAOImpl(Connection connection, String table) {
        super(connection, table);
    }

    @Override
    public Ticket create(Ticket ticket) {
        return null;
    }

    @Override
    public Ticket findByKey(Long key) {
        return null;
    }

    @Override
    public boolean deleteByKey(Long key) {
        return false;
    }

    @Override
    public List<Ticket> getAll() {
        return null;
    }
}
