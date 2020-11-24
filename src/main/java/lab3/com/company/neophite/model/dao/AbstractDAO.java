package lab3.com.company.neophite.model.dao;

import lab3.com.company.neophite.ConnectionPoll;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class AbstractDAO<T, PK> {

    private Connection connection;
    private ConnectionPoll connectionPoll;
    private String table;

    public AbstractDAO(Connection connection, ConnectionPoll pool, String table) {
        this.connectionPoll = pool;
        this.connection = pool.getConnection();
        this.table  = table;
    }

    public abstract T create(T t);

    public abstract T findEntityByKey(PK key);

    public abstract boolean deleteByKey(PK key);

    public abstract T update(PK key, T entity);

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
