package lab3.com.company.neophite.model.dao;

import lab3.com.company.neophite.ConnectionPoll;

public abstract class TrainTripDAO extends AbstractDAO<TrainTripDAO,Long> {
    public TrainTripDAO(ConnectionPoll pool, String table) {
        super(pool, table);
    }
}
