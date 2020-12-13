package lab3.com.company.neophite.model.mapper.impl;

import lab3.com.company.neophite.model.entity.Station;
import lab3.com.company.neophite.model.mapper.ObjectMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StationMapper implements ObjectMapper<Station> {
    @Override
    public Station extractEntityFromTheRS(ResultSet resultSet) throws SQLException {
        return new Station(resultSet.getLong("id_station"),
                resultSet.getString("name")
                );
    }
}
