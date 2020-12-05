package lab3.com.company.neophite.model.dao;

import lab3.com.company.neophite.model.dao.connection.ConnectionPool;
import lab3.com.company.neophite.model.entity.Train;

import java.sql.Connection;

public abstract class TrainDAO extends AbstractDAO<Train,Long> {
    public TrainDAO(Connection connection, String table) {
        super(connection, table);
    }
}
