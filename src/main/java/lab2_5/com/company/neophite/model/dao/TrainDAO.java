package lab2_5.com.company.neophite.model.dao;

import lab2_5.com.company.neophite.model.entity.Train;

import java.sql.Connection;

public abstract class TrainDAO extends AbstractDAO<Train,Long> {
    public TrainDAO(Connection connection) {
        super(connection);
    }
}
