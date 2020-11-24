package lab3.com.company.neophite.model.dao;

import lab3.com.company.neophite.ConnectionPoll;
import lab3.com.company.neophite.model.entity.TrainRoute;

public abstract class RouteDAO extends AbstractDAO<TrainRoute, Long>{

    public RouteDAO(ConnectionPoll pool, String table) {
        super(pool, table);
    }
}
