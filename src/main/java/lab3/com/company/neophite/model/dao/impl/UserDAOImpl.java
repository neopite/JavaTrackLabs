package lab3.com.company.neophite.model.dao.impl;

import lab3.com.company.neophite.ConnectionPoll;
import lab3.com.company.neophite.model.dao.AbstractDAO;
import lab3.com.company.neophite.model.dao.UserDAO;
import lab3.com.company.neophite.model.entity.User;

import java.io.OptionalDataException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl extends UserDAO {

    private final String CREATE_QUERY = "insert into " + this.getTable() +
            " (username,password,name,age,email) values(?,?,?,?,?)";
    private final String FIND_USER_BY_USERNAME = "select * from " + this.getTable() + " where username='?'";
    private final String FIND_USER_BY_ID = "select * from " + this.getTable() + " where id=?";
    private final String DELETE_USER_BY_ID = "delete from " + this.getTable() + " where id=?";
    private final String GET_ALL_USERS = "select* from " + this.getTable();


    public UserDAOImpl(ConnectionPoll pool, String table) {
        super(pool, table);
    }

    public User findUserByUsername(String username) {
        PreparedStatement preparedStatement = getStatement(FIND_USER_BY_USERNAME);
        User findedUser = null;
        try {
            preparedStatement.setString(1, username);
            ResultSet res = preparedStatement.executeQuery();
            while (res.next()) {
                findedUser = new User(
                        res.getLong("id"),
                        res.getString("username"),
                        res.getString("password"),
                        res.getString("name"),
                        res.getInt("age"),
                        res.getString("email")
                );
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return findedUser;

    }

    @Override
    public User create(User user) {
        PreparedStatement preparedStatement = getStatement(CREATE_QUERY);
        try {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPasswd());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setInt(4, user.getAge());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return user;
    }

    @Override
    public User findObjectByKeyValue(Long key) {
        PreparedStatement preparedStatement = getStatement(FIND_USER_BY_ID);
        User user = null;
        try {
            preparedStatement.setLong(1, key);
            ResultSet res = preparedStatement.executeQuery();
            user = new User(
                    res.getLong("id"),
                    res.getString("username"),
                    res.getString("password"),
                    res.getString("name"),
                    res.getInt("age"),
                    res.getString("email")
            );
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return user;
    }

    @Override
    public boolean deleteByKey(Long key) {
        PreparedStatement preparedStatement = getStatement(DELETE_USER_BY_ID);
        int deletedRows = 0;
        try {
            preparedStatement.setLong(1, key);
            deletedRows = preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return deletedRows != 0;
    }


    @Override
    public List<User> getAll() {
        PreparedStatement preparedStatement = getStatement(GET_ALL_USERS);
        List<User> listOfUsers = new ArrayList<User>();
        ResultSet res = null;
        try {
            res = preparedStatement.executeQuery();
            while (res.next()) {
                User user = new User(
                        res.getLong("id"),
                        res.getString("username"),
                        res.getString("password"),
                        res.getString("name"),
                        res.getInt("age"),
                        res.getString("email")
                );
                listOfUsers.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return listOfUsers;
    }
}
