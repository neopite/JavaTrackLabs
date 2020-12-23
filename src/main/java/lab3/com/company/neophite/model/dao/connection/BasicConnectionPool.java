package lab3.com.company.neophite.model.dao.connection;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class BasicConnectionPool implements ConnectionPool {

    private static final String DB_PROPERTIES =
            "C:\\Users\\Stami\\IdeaProjects\\Java-Lab01\\src\\main\\resources\\database.properties";
    private static DataSource dataSource;
    private static BasicConnectionPool instance = null;  // lazy loading
    private static List<Connection> connectionPool;
    private static List<Connection> usedConnections = new ArrayList<Connection>();
    private static final int INITIAL_POOL_SIZE = 20;


    private BasicConnectionPool(List<Connection> newPool) {
        connectionPool = newPool;
    }

    private BasicConnectionPool() {
        connectionPool = new ArrayList<>();
        getInstance();
    }

    public static BasicConnectionPool getInstance() {
        if (instance == null) {
            List<Connection> pool = new ArrayList<Connection>(INITIAL_POOL_SIZE);
            dataSource = getDataSource();
            for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
                try {
                    Connection connection = createConnection(dataSource.getUrl(), dataSource.getUsername(), dataSource.getPassword());
                    pool.add(connection);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            instance = new BasicConnectionPool(pool);
            return instance;
        }
        return instance;
    }

    private static DataSource getDataSource() {
        DataSource dbCredits = null;
        try (FileReader fileReader = new FileReader(DB_PROPERTIES)) {
            Properties properties = new Properties();
            properties.load(fileReader);
            dbCredits = new DataSource(
                    properties.getProperty("db_username"),
                    properties.getProperty("db_password"),
                    properties.getProperty("db_url")
            );
            dataSource = dbCredits;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataSource;
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

    private static Connection createConnection(
            String url, String user, String password)
            throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(url, user, password);
    }

    public List<Connection> getConnectionPool() {
        return connectionPool;
    }

    public static void setDataSource(DataSource dataSource) {
        BasicConnectionPool.dataSource = dataSource;
    }

    public static void setInstance(BasicConnectionPool instance) {
        BasicConnectionPool.instance = instance;
    }
}
