import lab3.com.company.neophite.model.dao.connection.BasicConnectionPool;
import lab3.com.company.neophite.model.dao.impl.DAOFactoryImpl;
import lab3.com.company.neophite.model.dao.impl.StationDAOImpl;
import lab3.com.company.neophite.model.entity.Station;
import lab3.com.company.neophite.model.entity.User;
import lab3.com.company.neophite.model.service.StationService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class StationServiceTest {
    private StationService stationService;
    private DAOFactoryImpl mockDaoFactory;
    private StationDAOImpl mockStationDao;
    private BasicConnectionPool mockBasicConnectionPool;

    @Before
    public void setUp() throws Exception {
      mockDaoFactory = mock(DAOFactoryImpl.class);
      mockStationDao = mock(StationDAOImpl.class);
      mockBasicConnectionPool = mock(BasicConnectionPool.class);
      stationService = new StationService();

      Field daoFactoryField = stationService.getClass().getDeclaredField("daoFactory");
      daoFactoryField.setAccessible(true);
      daoFactoryField.set(stationService,mockDaoFactory);

      Field basicConnectionPool = stationService.getClass().getDeclaredField("basicConnectionPool");
      basicConnectionPool.setAccessible(true);
      basicConnectionPool.set(stationService,mockBasicConnectionPool);

    }

    @Test
    public void createStation_Should_return_Ok() {
        Station station = new Station(1,"Konotop");
        when(mockDaoFactory.createStationDAO(mockBasicConnectionPool.getConnection())).thenReturn(mockStationDao);
        when(mockStationDao.create(station)).thenReturn(station);
        stationService.addStation(station);
        verify(mockStationDao).create(station);
    }

    @Test
    public void findStationById_Should_return_ok() {
        int id = 1;
        Station station = new Station(id,"Konotop",true);
        when(mockDaoFactory.createStationDAO(mockBasicConnectionPool.getConnection())).thenReturn(mockStationDao);
        when(mockStationDao.findByKey((long) id)).thenReturn(station);
        Station station1 = stationService.findStationById(id);
        Assert.assertEquals(station,station1);
    }

    @Test
    public void getAllStations_Should_return_ok() {
        Station station = new Station(1,"Konotop",true);
        Station station1 = new Station(2,"Konotop",true);
        Station station2 = new Station(3,"Konotop",true);
        List<Station> listOfStations = new ArrayList<>();
        listOfStations.add(station);
        listOfStations.add(station1);
        listOfStations.add(station2);
        when(mockDaoFactory.createStationDAO(mockBasicConnectionPool.getConnection())).thenReturn(mockStationDao);
        when(mockStationDao.getAll()).thenReturn(listOfStations);
        Assert.assertEquals(listOfStations,stationService.getAllStation());
    }

    @Test
    public void updateStationById() {
        long id = 1 ;
        Station station = new Station(1,"Konotop",true);
        Station updatedStation = new Station(1,"Lol",true);
        when(mockDaoFactory.createStationDAO(mockBasicConnectionPool.getConnection())).thenReturn(mockStationDao);
        when(mockStationDao.updateStation(id,"Lol")).thenReturn(updatedStation);
        Assert.assertEquals(updatedStation,stationService.updateStation(1,"Lol"));
    }

}
