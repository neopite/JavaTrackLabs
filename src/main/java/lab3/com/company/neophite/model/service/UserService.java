package lab3.com.company.neophite.model.service;

import lab3.com.company.neophite.model.dao.DAOFactory;
import lab3.com.company.neophite.model.dao.UserDAO;
import lab3.com.company.neophite.model.dao.connection.BasicConnectionPool;
import lab3.com.company.neophite.model.entity.User;

public class UserService {
    private DAOFactory daoFactory;
    private BasicConnectionPool basicConnectionPool;

    public UserService(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
        this.basicConnectionPool = BasicConnectionPool.getInstance();
    }

    public User findUserByUsername(String username) {
        try (UserDAO userDAO = daoFactory.createUserDAO(basicConnectionPool.getConnection())) {
            User user = userDAO.findUserByUsername(username);
            return user;
        }
    }

    public boolean decreaseMoney(long userId,float money){
        boolean executed = false;
        try(UserDAO userDAO = daoFactory.createUserDAO(basicConnectionPool.getConnection())){
            executed = userDAO.updateUsersMoney(userId,money);
        }
        return executed;
    }

    public User createUser(User user){
        System.out.println(daoFactory.toString());
        try(UserDAO userDAO = daoFactory.createUserDAO(basicConnectionPool.getConnection())){
            User newUser = userDAO.findUserByUsername(user.getUsername());
            if(newUser==null){
                userDAO.create(user);
                return newUser;
            }else {
            //    throw new Exception()
            }
        }
        return null;
    }
}
