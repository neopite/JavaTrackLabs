package lab3.com.company.neophite.model.dao;

import lab3.com.company.neophite.ConnectionPoll;
import lab3.com.company.neophite.model.entity.User;

public abstract class UserDAO extends AbstractDAO<User,Long> {
    public UserDAO(ConnectionPoll pool, String table) {
        super(pool, table);
    }
    public abstract User findUserByUsername(String username);
}
