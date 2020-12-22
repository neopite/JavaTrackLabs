package lab3.com.company.neophite.model.mapper.impl;

import lab3.com.company.neophite.model.entity.Station;
import lab3.com.company.neophite.model.mapper.ObjectMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StationMapper implements ObjectMapper<Station> {
    public Station extractEntityFromTheRS(ResultSet resultSet,String str) throws SQLException {
        switch (str){
          case  "start_station" :
              return new Station(resultSet.getLong("id_start"),
                    resultSet.getString("start_station"));
            case "end_station" :
                return new Station(resultSet.getLong("id_end"),
                    resultSet.getString("end_station"));
        }
       return extractEntityFromTheRS(resultSet);
    }

    @Override
    public Station extractEntityFromTheRS(ResultSet resultSet) throws SQLException {
        return new Station(resultSet.getLong("id_station"),
                resultSet.getString("name")
        );
    }
}
