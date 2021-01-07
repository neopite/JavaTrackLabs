package lab2_5.com.company.neophite.model.dao.connection;

import java.sql.Connection;

public interface ConnectionPool {
    Connection getConnection();
    boolean shutdownConnection(Connection connection);

}
