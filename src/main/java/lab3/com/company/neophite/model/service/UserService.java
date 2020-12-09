package lab3.com.company.neophite.model.service;

import lab3.com.company.neophite.model.dao.DAOFactory;
import lab3.com.company.neophite.model.dao.UserDAO;
import lab3.com.company.neophite.model.entity.User;

public class UserService {
    private DAOFactory daoFactory;

    public UserService(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public User findUserByUsername(String username) {
        try (UserDAO userDAO = daoFactory.createUserDAO()) {
            User user = userDAO.findUserByUsername(username);
            if (user == null) {
                return user;
            } else return user;
        }
    }
}
