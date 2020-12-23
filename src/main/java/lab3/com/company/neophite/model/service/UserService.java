package lab3.com.company.neophite.model.service;

import lab3.com.company.neophite.model.dao.DAOFactory;
import lab3.com.company.neophite.model.dao.UserDAO;
import lab3.com.company.neophite.model.dao.connection.BasicConnectionPool;
import lab3.com.company.neophite.model.dao.impl.RoleDAOImpl;
import lab3.com.company.neophite.model.dao.impl.UserDAOImpl;
import lab3.com.company.neophite.model.entity.Role;
import lab3.com.company.neophite.model.entity.User;
import lab3.com.company.neophite.model.exception.UserAlreadyCreatedException;

import java.util.List;

public class UserService {
    private DAOFactory daoFactory;
    private BasicConnectionPool basicConnectionPool;

    public UserService(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
        this.basicConnectionPool = BasicConnectionPool.getInstance();
    }

    public UserService() {
        this.daoFactory = DAOFactory.getDaoFactory();
        this.basicConnectionPool = BasicConnectionPool.getInstance();
    }


    public User findUserByUsername(String username) {
        try (UserDAO userDAO = daoFactory.createUserDAO(basicConnectionPool.getConnection());
             RoleDAOImpl roleDAO = daoFactory.createRoleDAO(basicConnectionPool.getConnection())
        ) {
            User user = userDAO.findUserByUsername(username);
            if (user == null) {
                return null;
            }
            List<Role> usersRoles = roleDAO.findUsersRole(user.getId());
            user.setRoles(usersRoles);
            return user;
        }
    }


    public User createUser(User user) throws UserAlreadyCreatedException {
        try (UserDAO userDAO = daoFactory.createUserDAO(basicConnectionPool.getConnection())) {
            User newUser = userDAO.findUserByUsername(user.getUsername());
            if (newUser == null) {
                userDAO.create(user);
                return user;
            } else {
                throw new UserAlreadyCreatedException("User with :"+user.getName()+ " us already created");
            }
        }
    }
}
