package lab2_5.com.company.neophite.model.mapper.impl;

import lab2_5.com.company.neophite.model.entity.Train;
import lab2_5.com.company.neophite.model.mapper.ObjectMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TrainMapper implements ObjectMapper<Train> {
    @Override
    public Train extractEntityFromTheRS(ResultSet resultSet) throws SQLException {
        return new Train(resultSet.getLong("id_train"),
                resultSet.getString("model"),
                resultSet.getInt("count_of_places")
                );
    }
}
