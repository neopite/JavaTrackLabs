package lab3.com.company.neophite.model.service;

import lab3.com.company.neophite.model.dao.UserDAO;
import lab3.com.company.neophite.model.dao.connection.BasicConnectionPool;
import lab3.com.company.neophite.model.dao.impl.UserDAOImpl;
import lab3.com.company.neophite.model.entity.User;

public class UserService {
    private UserDAO userDAO;

    public UserService() {
        BasicConnectionPool basicConnectionPool = BasicConnectionPool.getInstance(
                "jdbc:postgresql://127.0.0.1:5432/railwaydb",
                "postgres",
                "4427"
        );
        this.userDAO = new UserDAOImpl(basicConnectionPool.getConnection(),"users");
    }

    public User findUserByUsername(String username) {
        User user = userDAO.findUserByUsername(username);
        if (user == null) {
                return user;
        } else return user;
    }
}
