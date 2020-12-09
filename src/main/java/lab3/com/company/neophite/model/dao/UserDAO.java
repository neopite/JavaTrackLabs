package lab3.com.company.neophite.model.dao;

import lab3.com.company.neophite.model.entity.User;

import java.sql.Connection;

public abstract class UserDAO extends AbstractDAO<User,Long> {
    public UserDAO(Connection connection) {
        super(connection);
    }
    public abstract User findUserByUsername(String username);
}
