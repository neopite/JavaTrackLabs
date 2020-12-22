package lab3.com.company.neophite.model.dao.impl;

import lab3.com.company.neophite.model.dao.TicketDAO;
import lab3.com.company.neophite.model.entity.Ticket;
import lab3.com.company.neophite.model.mapper.impl.TicketMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketDAOImpl extends TicketDAO {
    private TicketMapper ticketMapper = new TicketMapper();

    private final String table = "ticket";
    private final String FIND_ALL = "select * ,s.name as start_station,s2.name as end_station , s.id_station as id_start , s2.id_station as id_end from ticket \n" +
            "        left join train_trip tt on ticket.train_trip = tt.id_train_trip\n" +
            "        left join trains_route tr on tt.train_route = tr.id_train_route\n" +
            "       left join stations s on tr.station_start = s.id_station left join stations s2 on tr.station_end = s2.id_station " +
            "        left join trains t on tt.train = t.id_train" +
            " left join users u on ticket.id_user = u.id";

    private final String CREATE = "insert into " + table +
            " (train_trip,id_user,place) values(?,?,?)";
    private final String FIND_TICKET_BY_ID = FIND_ALL + " where id_ticket=?";
    private final String DELETE_TICKET_BY_ID = "delete on " + "ticket" + " where id_ticket=?";
    private final String FIND_TICKETS_BY_USER_ID = FIND_ALL + " where u.id=?";


    public TicketDAOImpl(Connection connection) {
        super(connection);
    }

    @Override
    public List<Ticket> findTicketsByUsersId(long userId) {
        List<Ticket> listOfTickets = new ArrayList<>();
        try(PreparedStatement preparedStatement = getStatement(FIND_TICKETS_BY_USER_ID)) {
            preparedStatement.setLong(1,userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Ticket ticket = ticketMapper.extractEntityFromTheRS(resultSet);
                listOfTickets.add(ticket);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return listOfTickets;
    }

    @Override
    public Ticket create(Ticket ticket) {
        try (PreparedStatement preparedStatement = getStatement(CREATE)) {
            preparedStatement.setLong(1, ticket.getTrainTripId().getTrainId().getId());
            preparedStatement.setLong(2, ticket.getUserId().getId());
            preparedStatement.setLong(3, ticket.getPlace());
            preparedStatement.execute();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return ticket;
    }

    @Override
    public Ticket findByKey(Long key) {
        Ticket ticket = null;
        try (PreparedStatement preparedStatement = getStatement(FIND_TICKET_BY_ID)) {
            preparedStatement.setLong(1, key);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ticket = ticketMapper.extractEntityFromTheRS(resultSet);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return ticket;
    }

    @Override
    public boolean deleteByKey(Long key) {
        boolean isExecuted = false;
        try (PreparedStatement preparedStatement = getStatement(DELETE_TICKET_BY_ID)) {
            preparedStatement.setLong(1, key);
            isExecuted = preparedStatement.execute();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return isExecuted;
    }

    @Override
    public List<Ticket> getAll() {
        return null;
    }
}
