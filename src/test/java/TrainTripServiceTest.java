import lab2_5.com.company.neophite.model.dao.connection.BasicConnectionPool;
import lab2_5.com.company.neophite.model.dao.impl.DAOFactoryImpl;
import lab2_5.com.company.neophite.model.dao.impl.StationDAOImpl;
import lab2_5.com.company.neophite.model.dao.impl.TrainRouteDAOImpl;
import lab2_5.com.company.neophite.model.dao.impl.TrainTripDAOImpl;
import lab2_5.com.company.neophite.model.entity.Station;
import lab2_5.com.company.neophite.model.entity.Train;
import lab2_5.com.company.neophite.model.entity.TrainRoute;
import lab2_5.com.company.neophite.model.entity.TrainTrip;
import lab2_5.com.company.neophite.model.service.TrainTripService;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TrainTripServiceTest {
    private TrainTripService trainTripService;

    private DAOFactoryImpl mockDaoFactory;

    private StationDAOImpl mockStationDao;
    private TrainRouteDAOImpl mockTrainRouteDAO;
    private TrainTripDAOImpl mockTrainTripDAO;

    private BasicConnectionPool mockBasicConnectionPool;

    @Before
    public void setUp() throws Exception {
        mockDaoFactory = mock(DAOFactoryImpl.class);

        mockStationDao = mock(StationDAOImpl.class);
        mockTrainRouteDAO = mock(TrainRouteDAOImpl.class);
        mockTrainTripDAO = mock(TrainTripDAOImpl.class);

        mockBasicConnectionPool = mock(BasicConnectionPool.class);

        trainTripService = new TrainTripService();

        Field daoFactoryField = trainTripService.getClass().getDeclaredField("daoFactory");
        daoFactoryField.setAccessible(true);
        daoFactoryField.set(trainTripService,mockDaoFactory);

        Field basicConnectionPool = trainTripService.getClass().getDeclaredField("basicConnectionPool");
        basicConnectionPool.setAccessible(true);
        basicConnectionPool.set(trainTripService,mockBasicConnectionPool);
    }

    @Test
    public void getTrainTripsBetweenStations_Should_Return_Train_Trips(){
        Train train = new Train(1,"samsa",23);
        Station station = new Station(1,"Konotop");
        Station station1 = new Station(2,"Kyiv");
        Timestamp timestamp = new Timestamp(new Date().getTime());
        Timestamp timestamp1 = new Timestamp(new Date().getTime());
        TrainRoute trainRoute = new TrainRoute(1,station, station1, timestamp, timestamp1);
        TrainTrip trainTrip = new TrainTrip(1,trainRoute,train,32,43);
        List<TrainTrip> trainTrips = new ArrayList<>();
        trainTrips.add(trainTrip);
        List<TrainRoute> trainRouteList = new ArrayList<>();
        trainRouteList.add(trainRoute);

        when(mockDaoFactory.createStationDAO(mockBasicConnectionPool.getConnection())).thenReturn(mockStationDao);
        when(mockDaoFactory.createTrainRouteDAO(mockBasicConnectionPool.getConnection())).thenReturn(mockTrainRouteDAO);
        when(mockDaoFactory.createTrainTripDAO(mockBasicConnectionPool.getConnection())).thenReturn(mockTrainTripDAO);
        when(mockStationDao.findStationByName(station.getName())).thenReturn(station);
        when(mockStationDao.findStationByName(station1.getName())).thenReturn(station1);
        when(mockTrainRouteDAO.getTrainRoutesBetweenTwoStations(1,2,timestamp,timestamp1)).thenReturn(trainRouteList);
        when(mockTrainTripDAO.findTrainTripsByRoute(trainRouteList.get(0).getId())).thenReturn(trainTrips);
        assertEquals(trainTrips,trainTripService.getTrainTripsBetweenTwoStations("Konotop","Kyiv",timestamp,timestamp1));
    }

    @Test
    public void getTrainTripsBetweenStations_Should_Return_Empty_List(){
        Station station = new Station(1,"Konotop");
        Station station1 = new Station(2,"Kyiv");
        Timestamp timestamp = new Timestamp(new Date().getTime());
        Timestamp timestamp1 = new Timestamp(new Date().getTime());
        List<TrainTrip> trainTrips = new ArrayList<>();

        when(mockDaoFactory.createStationDAO(mockBasicConnectionPool.getConnection())).thenReturn(mockStationDao);
        when(mockDaoFactory.createTrainRouteDAO(mockBasicConnectionPool.getConnection())).thenReturn(mockTrainRouteDAO);
        when(mockDaoFactory.createTrainTripDAO(mockBasicConnectionPool.getConnection())).thenReturn(mockTrainTripDAO);
        when(mockStationDao.findStationByName(station.getName())).thenReturn(null);
        when(mockStationDao.findStationByName(station1.getName())).thenReturn(station1);
        assertEquals(trainTrips,trainTripService.getTrainTripsBetweenTwoStations("Konotop","Kyiv",timestamp,timestamp1));

    }
}
