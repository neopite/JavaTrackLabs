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
        try (UserDAO userDAO = daoFactory.createUserDAO()) { //TODO : Connection
            User user = userDAO.findUserByUsername(username);
            return user;
        }
    }

    public User createUser(User user){
        System.out.println(daoFactory.toString());
        try(UserDAO userDAO = daoFactory.createUserDAO()){
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
