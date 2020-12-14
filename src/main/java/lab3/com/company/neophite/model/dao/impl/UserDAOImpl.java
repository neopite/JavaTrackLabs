package lab3.com.company.neophite.model.dao.impl;

import lab3.com.company.neophite.model.dao.UserDAO;
import lab3.com.company.neophite.model.entity.User;
import lab3.com.company.neophite.model.mapper.impl.UserMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl extends UserDAO {

    private UserMapper userMapper = new UserMapper();

    private final String table = "users";
    private final String CREATE_QUERY = "insert into " + table +
            " (username,password,name,age,email) values(?,?,?,?,?)";
    private final String FIND_USER_BY_USERNAME = "select * from " + table + " where username='?'";
    private final String FIND_USER_BY_ID = "select * from " +table+ " where id=?";
    private final String DELETE_USER_BY_ID = "delete from " + table + " where id=?";
    private final String GET_ALL_USERS = "select* from " + table;
    private final String UPDATE_USERS_MONEY = "update "+table+ " set money=? where id=?";


    public UserDAOImpl(Connection connection) {
        super(connection);
    }

    public User findUserByUsername(String username) {
        User findedUser = null;
        try(PreparedStatement preparedStatement = getStatement(FIND_USER_BY_USERNAME)) {
            preparedStatement.setString(1, username);
            ResultSet res = preparedStatement.executeQuery();
            while (res.next()) {
                findedUser = userMapper.extractEntityFromTheRS(res);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return findedUser;

    }

    public boolean updateUsersMoney(long userId , float currentMoney){
        boolean executed = false;
        try(PreparedStatement preparedStatement = getStatement(UPDATE_USERS_MONEY)) {
            preparedStatement.setFloat(1,currentMoney);
            preparedStatement.setLong(2,userId);
           return executed = preparedStatement.execute();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return executed;
    }

    @Override
    public User create(User user) {
        try(PreparedStatement preparedStatement = getStatement(CREATE_QUERY)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPasswd());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setInt(4, user.getAge());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    @Override
    public User findByKey(Long key) {
        User user = null;
        try(PreparedStatement preparedStatement = getStatement(FIND_USER_BY_ID)) {
            preparedStatement.setLong(1, key);
            ResultSet res = preparedStatement.executeQuery();
            user = userMapper.extractEntityFromTheRS(res);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean deleteByKey(Long key) {
        int deletedRows = 0;
        try(PreparedStatement preparedStatement = getStatement(DELETE_USER_BY_ID)) {
            preparedStatement.setLong(1, key);
            deletedRows = preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return deletedRows != 0;
    }


    @Override
    public List<User> getAll() {
        List<User> listOfUsers = new ArrayList<User>();
        ResultSet res = null;
        try(PreparedStatement preparedStatement = getStatement(GET_ALL_USERS)){
            res = preparedStatement.executeQuery();
            while (res.next()) {
                User user = userMapper.extractEntityFromTheRS(res);
                listOfUsers.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return listOfUsers;
    }
}
