package lab3.com.company.neophite.model.dao;

import lab3.com.company.neophite.ConnectionPoll;
import lab3.com.company.neophite.model.entity.Train;

public abstract class TrainDAO extends AbstractDAO<Train,Long> {
    public TrainDAO(ConnectionPoll pool, String table) {
        super(pool, table);
    }
}
