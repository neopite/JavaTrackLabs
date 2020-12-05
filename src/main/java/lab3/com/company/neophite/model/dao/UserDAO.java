package lab3.com.company.neophite.model.dao;

import lab3.com.company.neophite.model.dao.connection.ConnectionPool;
import lab3.com.company.neophite.model.entity.User;

import java.sql.Connection;

public abstract class UserDAO extends AbstractDAO<User,Long> {
    public UserDAO(Connection connection, String table) {
        super(connection, table);
    }
    public abstract User findUserByUsername(String username);
}
