import lab3.com.company.neophite.model.dao.DAOFactory;
import lab3.com.company.neophite.model.dao.TrainTripDAO;
import lab3.com.company.neophite.model.dao.connection.BasicConnectionPool;
import lab3.com.company.neophite.model.dao.impl.DAOFactoryImpl;
import lab3.com.company.neophite.model.dao.impl.StationDAOImpl;
import lab3.com.company.neophite.model.dao.impl.TrainRouteDAOImpl;
import lab3.com.company.neophite.model.dao.impl.TrainTripDAOImpl;
import lab3.com.company.neophite.model.entity.*;
import lab3.com.company.neophite.model.exception.TrainRouteNotFoundException;
import lab3.com.company.neophite.model.service.StationService;
import lab3.com.company.neophite.model.service.TrainRouteService;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class TrainRouteServiceTest {
    private TrainRouteService trainRouteService;

    private DAOFactoryImpl mockDaoFactory;

    private TrainRouteDAOImpl mockTrainRouteDAO;
    private TrainTripDAOImpl mockTrainTripDAO;

    private BasicConnectionPool mockBasicConnectionPool;
    private Connection mockTransactionalConnection;

    @Before
    public void setUp() throws Exception {
        mockDaoFactory = mock(DAOFactoryImpl.class);

        mockTrainRouteDAO = mock(TrainRouteDAOImpl.class);
        mockTrainTripDAO = mock(TrainTripDAOImpl.class);

        mockBasicConnectionPool = mock(BasicConnectionPool.class);
        mockTransactionalConnection = mock(Connection.class);

        trainRouteService = new TrainRouteService();

        Field daoFactoryField = trainRouteService.getClass().getDeclaredField("daoFactory");
        daoFactoryField.setAccessible(true);
        daoFactoryField.set(trainRouteService,mockDaoFactory);

        Field basicConnectionPool = trainRouteService.getClass().getDeclaredField("basicConnectionPool");
        basicConnectionPool.setAccessible(true);
        basicConnectionPool.set(trainRouteService,mockBasicConnectionPool);

        Field transactionField = trainRouteService.getClass().getDeclaredField("transactionConnection");
        transactionField.setAccessible(true);
        transactionField.set(trainRouteService,mockTransactionalConnection);
    }

    @Test
    public void getAllRoutes(){
        Station station = new Station(1, "Konotop");
        Station station1 = new Station(2, "Kiev");
        Timestamp timestamp = new Timestamp(new Date().getTime());
        Timestamp timestamp2 = new Timestamp(new Date().getTime());
        TrainRoute trainRoute = new TrainRoute(station, station1, timestamp, timestamp2);
        List<TrainRoute> trainRouteList = new ArrayList<>();
        trainRouteList.add(trainRoute);
        trainRouteList.add(trainRoute);
        trainRouteList.add(trainRoute);
        when(mockDaoFactory.createTrainRouteDAO(mockBasicConnectionPool.getConnection())).thenReturn(mockTrainRouteDAO);
        when(mockTrainRouteDAO.getAll()).thenReturn(trainRouteList);
        assertEquals(trainRouteList,trainRouteService.getAllRoutes());
        verify(mockTrainRouteDAO).getAll();
    }

    @Test
    public void deleteTrainRouteById() throws SQLException {
        long idRoute = 1 ;
        User user = new User(1, "test", "test123");

        Station station = new Station(1, "Konotop");
        Station station1 = new Station(2, "Kiev");
        Timestamp timestamp = new Timestamp(new Date().getTime());
        Timestamp timestamp2 = new Timestamp(new Date().getTime());
        TrainRoute trainRoute = new TrainRoute(1,station, station1, timestamp, timestamp2);

        Train train = new Train(1, "Sappsan", 213);

        TrainTrip trainTrip = new TrainTrip(1, trainRoute, train, 25, 10);

        List<TrainTrip> trainTripsByRouteId = new ArrayList<>();
        trainTripsByRouteId.add(trainTrip);
        trainTripsByRouteId.add(trainTrip);
        trainTripsByRouteId.add(trainTrip);
        trainTripsByRouteId.add(trainTrip);

        when(mockDaoFactory.createTrainRouteDAO(mockTransactionalConnection)).thenReturn(mockTrainRouteDAO);
        when(mockDaoFactory.createTrainTripDAO(mockTransactionalConnection)).thenReturn(mockTrainTripDAO);
        when(mockTrainTripDAO.findTrainTripsByRoute(idRoute)).thenReturn(trainTripsByRouteId);
        when(mockTrainRouteDAO.deleteByKey(idRoute)).thenReturn(true);
        when(mockTrainTripDAO.deleteAllTrainTripsByRouteId(idRoute)).thenReturn(true);
        trainRouteService.deleteTrainRoute(idRoute);
        verify(mockTransactionalConnection).commit();
    }

    @Test
    public void addRoute(){
        Station station = new Station(1, "Konotop");
        Station station1 = new Station(2, "Kiev");
        Timestamp timestamp = new Timestamp(new Date().getTime());
        Timestamp timestamp2 = new Timestamp(new Date().getTime());
        TrainRoute trainRoute = new TrainRoute(1,station, station1, timestamp, timestamp2);
        when(mockDaoFactory.createTrainRouteDAO(mockBasicConnectionPool.getConnection())).thenReturn(mockTrainRouteDAO);
        when(mockTrainRouteDAO.create(trainRoute)).thenReturn(trainRoute);
        assertEquals(trainRoute,trainRouteService.addTrainRoute(trainRoute));
        verify(mockTrainRouteDAO).create(trainRoute);
    }

    @Test(expected = TrainRouteNotFoundException.class)
    public void deleteTrainRouteById_Should_throw_TrainRouteNotFoundException(){
        long idRoute = 1 ;

        Station station = new Station(1, "Konotop");
        Station station1 = new Station(2, "Kiev");
        Timestamp timestamp = new Timestamp(new Date().getTime());
        Timestamp timestamp2 = new Timestamp(new Date().getTime());
        TrainRoute trainRoute = new TrainRoute(1,station, station1, timestamp, timestamp2);

        Train train = new Train(1, "Sappsan", 213);

        TrainTrip trainTrip = new TrainTrip(1, trainRoute, train, 25, 10);

        List<TrainTrip> trainTripsByRouteId = new ArrayList<>();
        trainTripsByRouteId.add(trainTrip);
        trainTripsByRouteId.add(trainTrip);
        trainTripsByRouteId.add(trainTrip);
        trainTripsByRouteId.add(trainTrip);

        when(mockDaoFactory.createTrainRouteDAO(mockTransactionalConnection)).thenReturn(mockTrainRouteDAO);
        when(mockDaoFactory.createTrainTripDAO(mockTransactionalConnection)).thenReturn(mockTrainTripDAO);
        when(mockTrainTripDAO.findTrainTripsByRoute(idRoute)).thenReturn(trainTripsByRouteId);
        when(mockTrainRouteDAO.deleteByKey(idRoute)).thenReturn(false);
        trainRouteService.deleteTrainRoute(idRoute);
    }

    @Test
    public void deleteTrainRouteById_without_relation_with_train_trips() throws SQLException {
        long idRoute = 1 ;
        List<TrainTrip> trainTripsByRouteId = new ArrayList<>();
        when(mockDaoFactory.createTrainRouteDAO(mockTransactionalConnection)).thenReturn(mockTrainRouteDAO);
        when(mockDaoFactory.createTrainTripDAO(mockTransactionalConnection)).thenReturn(mockTrainTripDAO);
        when(mockTrainTripDAO.findTrainTripsByRoute(idRoute)).thenReturn(trainTripsByRouteId);
        trainRouteService.deleteTrainRoute(idRoute);
        verify(mockTrainRouteDAO).deleteByKey(idRoute);
        verify(mockTransactionalConnection).commit();
    }

}
