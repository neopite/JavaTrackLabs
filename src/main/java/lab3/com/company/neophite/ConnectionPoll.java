package lab3.com.company.neophite;

import java.sql.Connection;

public interface ConnectionPoll {
    Connection getConnection();
    boolean shutdownConnection(Connection connection);
    String getUrl();
    String getUser();
    String getPassword();
}
