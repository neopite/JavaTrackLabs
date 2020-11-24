package lab3.com.company.neophite.model.dao;

import lab3.com.company.neophite.ConnectionPoll;
import lab3.com.company.neophite.model.entity.User;

import java.sql.Connection;
import java.util.List;

public class UserDAO extends AbstractDAO<User,Long> {

    public UserDAO(Connection connection, ConnectionPoll pool, String table) {
        super(connection, pool, table);
    }

    @Override
    public User create(User user) {
        return null;
    }

    @Override
    public User findEntityByKey(Long key) {
        return null;
    }

    @Override
    public boolean deleteByKey(Long key) {
        return false;
    }

    @Override
    public User update(Long key, User entity) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }
}
