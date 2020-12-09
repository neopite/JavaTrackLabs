package lab3.com.company.neophite.model.dao.impl;

import lab3.com.company.neophite.model.dao.TicketDAO;
import lab3.com.company.neophite.model.entity.Ticket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TicketDAOImpl extends TicketDAO {

    private final String table = "ticket";
    private final String CREATE = "insert into " +table +
            " (train_trip,id_user,place) values(?,?,?)";
    private final String FIND_TICKET_BY_ID = "select * from " + table + " where id_ticket=?";
    private final String DELETE_TICKET_BY_ID = "delete from " + table + " where id_ticket=?";



    public TicketDAOImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Ticket create(Ticket ticket) {
        try(PreparedStatement preparedStatement = getStatement(CREATE)){
            preparedStatement.setLong(1,ticket.getTrainTripId());
            preparedStatement.setLong(2,ticket.getUserId());
            preparedStatement.setLong(3,ticket.getPlace());
            preparedStatement.execute();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return ticket;
    }

    @Override
    public Ticket findByKey(Long key) {
        Ticket ticket = null;
        try(PreparedStatement preparedStatement = getStatement(FIND_TICKET_BY_ID)) {
            preparedStatement.setLong(1,key);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                ticket = new Ticket(resultSet.getLong("id_ticket"),
                                    resultSet.getLong("train_trip"),
                                    resultSet.getLong("id_user"),
                                    resultSet.getInt("place")
                        );
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return ticket;
    }

    @Override
    public boolean deleteByKey(Long key) {
        boolean isExecuted = false;
        try(PreparedStatement preparedStatement = getStatement(DELETE_TICKET_BY_ID)){
            preparedStatement.setLong(1,key);
            isExecuted = preparedStatement.execute();
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return isExecuted;
    }

    @Override
    public List<Ticket> getAll() {
        return null;
    }
}
