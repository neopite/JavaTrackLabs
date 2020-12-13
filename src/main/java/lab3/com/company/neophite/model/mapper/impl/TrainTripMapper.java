package lab3.com.company.neophite.model.mapper.impl;

import lab3.com.company.neophite.model.entity.TrainTrip;
import lab3.com.company.neophite.model.mapper.ObjectMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TrainTripMapper implements ObjectMapper<TrainTrip> {
    private TrainRouteMapper trainRouteMapper = new TrainRouteMapper();
    private TrainMapper trainMapper = new TrainMapper();
    @Override
    public TrainTrip extractEntityFromTheRS(ResultSet resultSet) throws SQLException {
        return new TrainTrip(resultSet.getLong("id_train_trip"),
                trainRouteMapper.extractEntityFromTheRS(resultSet),
                trainMapper.extractEntityFromTheRS(resultSet),
                resultSet.getFloat("price"),
                resultSet.getInt("available_seats")
        );
    }
}