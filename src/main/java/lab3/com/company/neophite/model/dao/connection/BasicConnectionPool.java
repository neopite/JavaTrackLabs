package lab3.com.company.neophite.model.dao.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BasicConnectionPool implements ConnectionPool {

    private String url;
    private String user;
    private String password;
    private static BasicConnectionPool instance = null;  // lazy loading
    private static List<Connection> connectionPool;
    private static List<Connection> usedConnections = new ArrayList();
    private static int INITIAL_POOL_SIZE = 10;


    private BasicConnectionPool(String url,String user,String password,List<Connection> newPool){
        this.url = url;
        this.user = user;
        this.password = password;
        this.connectionPool = newPool;
    }

    public static BasicConnectionPool getInstance(String url, String user, String password) {
        if (instance == null) {
            List<Connection> pool = new ArrayList<Connection>(INITIAL_POOL_SIZE);
            for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
                try {
                    pool.add(createConnection(url, user, password));
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            instance =new BasicConnectionPool(url, user, password, pool);
            return instance;
        }
        return instance;
    }

    public Connection getConnection() {
        Connection connection = connectionPool
                .remove(connectionPool.size() - 1);
        usedConnections.add(connection);
        return connection;
    }

    public boolean shutdownConnection(Connection connection) {
        connectionPool.add(connection);
        return usedConnections.remove(connection);
    }

    public String getUrl() {
        return this.url;
    }

    public String getUser() {
        return this.user;
    }

    private static Connection createConnection(
            String url, String user, String password)
            throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public String getPassword() {
        return this.password;
    }

    public List<Connection> getConnectionPool() {
        return connectionPool;
    }
}
