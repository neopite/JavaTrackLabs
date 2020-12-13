package lab3.com.company.neophite.model.mapper.impl;

import lab3.com.company.neophite.model.entity.Station;
import lab3.com.company.neophite.model.entity.TrainRoute;
import lab3.com.company.neophite.model.mapper.ObjectMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TrainRouteMapper implements ObjectMapper<TrainRoute> {
    private StationMapper stationMapper = new StationMapper();

    @Override
    public TrainRoute extractEntityFromTheRS(ResultSet resultSet) throws SQLException {
        Station stationStart = stationMapper.extractEntityFromTheRS(resultSet);
        resultSet.next();
        Station endStation = stationMapper.extractEntityFromTheRS(resultSet);
        return new TrainRoute(resultSet.getLong("id_train_route"),
                stationStart,
                endStation,
                resultSet.getDate("start_date"),
                resultSet.getDate("end_date")
                );
    }
}
