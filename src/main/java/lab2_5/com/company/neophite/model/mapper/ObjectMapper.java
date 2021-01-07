package lab2_5.com.company.neophite.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ObjectMapper<T> {
    T extractEntityFromTheRS(ResultSet resultSet) throws SQLException;
}
