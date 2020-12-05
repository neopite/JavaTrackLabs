package lab3.com.company.neophite.model.dao;

import lab3.com.company.neophite.model.dao.connection.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class AbstractDAO<T, PK> {

    private Connection connection;
    private String table;

    public AbstractDAO(Connection connection, String table) {
        this.connection = connection;
        this.table = table;
    }


    public abstract T create(T t);

    public abstract T findByKey(PK key);

    public abstract boolean deleteByKey(PK key);

    public abstract List<T> getAll();

    public void close() {
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void closeStatement(Statement statement) {
        try {
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public PreparedStatement getStatement(String query) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = this.connection.prepareStatement(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return preparedStatement;
    }

    public Connection getConnection() {
        return connection;
    }


    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }
}
