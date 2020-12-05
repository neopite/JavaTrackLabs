package lab3.com.company.neophite.model.dao.impl;

import lab3.com.company.neophite.model.dao.connection.ConnectionPool;
import lab3.com.company.neophite.model.dao.TrainDAO;
import lab3.com.company.neophite.model.entity.Train;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrainDAOImpl extends TrainDAO {

    private final String CREATE = "insert into " + this.getTable() +
            " (model,count_of_places) values(?,?)";
    private final String FIND_TRAIN_BY_TRAIN_ID = "select * from " + this.getTable() + " where id_train=?";
    private final String DELETE_TRAIN_BY_ID = "delete from " + this.getTable() + " where id_train=?";
    private final String GET_ALL_TRAINS = "select * from " + this.getTable();


    public TrainDAOImpl(Connection connection, String table) {
        super(connection, table);
    }

    public Train create(Train train) {
        try (PreparedStatement preparedStatement = getStatement(CREATE)) {
            preparedStatement.setString(1, train.getModel());
            preparedStatement.setInt(2, train.getPlaces());
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return train;
    }

    public Train findByKey(Long key) {
        Train train = null;
        try (PreparedStatement preparedStatement = getStatement(FIND_TRAIN_BY_TRAIN_ID)) {
            preparedStatement.setLong(1, key);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                train = new Train(
                        resultSet.getString("model"),
                        resultSet.getInt("count_of_places")
                );
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return train;
    }

    public boolean deleteByKey(Long key) {
        boolean isExecuted = false;
        try(PreparedStatement preparedStatement = getStatement(DELETE_TRAIN_BY_ID)){
            preparedStatement.setLong(1,key);
            isExecuted = preparedStatement.execute();
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return isExecuted;
    }

    public List<Train> getAll() {
        List<Train> listOfTrains = new ArrayList<>();
        try(PreparedStatement preparedStatement = getStatement(GET_ALL_TRAINS)){
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Train train = new Train(
                  resultSet.getString("model"),
                  resultSet.getInt("count_of_places")
                );
                listOfTrains.add(train);
            }
        }catch (SQLException exception){
            exception.printStackTrace();
        }
        return listOfTrains;
    }
}
