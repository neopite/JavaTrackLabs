package lab3.com.company.neophite.model.dao;

import lab3.com.company.neophite.ConnectionPoll;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.List;

public class RouteDAO extends AbstractDAO<RouteDAO, Long> {

    public RouteDAO(Connection connection, ConnectionPoll pool, String table) {
        super(connection, pool, table);
    }

    public RouteDAO create(RouteDAO routeDAO) {
        return null;
    }

    public RouteDAO findEntityByKey(Long key) {
        return null;
    }

    public boolean deleteByKey(Long key) {
        return false;
    }

    public RouteDAO update(Long key, RouteDAO entity) {
        return null;
    }

    public List<RouteDAO> getAll() {
        return null;
    }
}
