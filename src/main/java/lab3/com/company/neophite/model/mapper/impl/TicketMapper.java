package lab3.com.company.neophite.model.mapper.impl;

import lab3.com.company.neophite.model.entity.Ticket;
import lab3.com.company.neophite.model.mapper.ObjectMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TicketMapper implements ObjectMapper<Ticket> {
    private TrainTripMapper trainTripMapper = new TrainTripMapper();
    private UserMapper userMapper = new UserMapper();

    @Override
    public Ticket extractEntityFromTheRS(ResultSet resultSet) throws SQLException {
        return new Ticket(
                resultSet.getLong("id_ticket"),
                userMapper.extractEntityFromTheRS(resultSet),
                trainTripMapper.extractEntityFromTheRS(resultSet),
                resultSet.getInt("place")
                );
    }
}
