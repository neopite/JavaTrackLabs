package lab3.com.company.neophite.model.mapper.impl;

import lab3.com.company.neophite.model.entity.User;
import lab3.com.company.neophite.model.mapper.ObjectMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements ObjectMapper<User> {
    @Override
    public User extractEntityFromTheRS(ResultSet resultSet) throws SQLException {
        return new User(
                resultSet.getLong("id"),
                resultSet.getString("username"),
                resultSet.getString("password"),
                resultSet.getString("name"),
                resultSet.getInt("age"),
                resultSet.getString("email"),
                resultSet.getFloat("money")
        );
    }
}
