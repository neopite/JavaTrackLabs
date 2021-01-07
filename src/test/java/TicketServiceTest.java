import lab2_5.com.company.neophite.model.dao.connection.BasicConnectionPool;
import lab2_5.com.company.neophite.model.dao.impl.*;
import lab2_5.com.company.neophite.model.entity.*;
import lab2_5.com.company.neophite.model.exception.NoFreeSeatException;
import lab2_5.com.company.neophite.model.exception.NotMuchMoneyException;
import lab2_5.com.company.neophite.model.service.TicketService;
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

public class TicketServiceTest {
    private TicketService ticketService;

    private TicketDAOImpl mockTicketDao;
    private UserDAOImpl mockUserDao;
    private TrainTripDAOImpl mockTrainTripDao;
    private DAOFactoryImpl mockDaoFactory;

    private Ticket mockTicket;
    private TrainTrip mockTrainTrip;
    private User mockUser;

    private BasicConnectionPool mockBasicConnectionPool;
    private Connection mockTransactionalConnection;

    @Before
    public void before() throws NoSuchFieldException, IllegalAccessException {
        mockDaoFactory = mock(DAOFactoryImpl.class);

        mockTicketDao = mock(TicketDAOImpl.class);
        mockUserDao = mock(UserDAOImpl.class);
        mockTrainTripDao = mock(TrainTripDAOImpl.class);

        mockTrainTrip = mock(TrainTrip.class);
        mockUser  = mock(User.class);
        mockTicket = new Ticket(1,mockUser,mockTrainTrip,2);


        mockBasicConnectionPool = mock(BasicConnectionPool.class);
        mockTransactionalConnection = mock(Connection.class);
        ticketService = new TicketService();

        Field daoFactoryField = ticketService.getClass().getDeclaredField("daoFactory");
        daoFactoryField.setAccessible(true);
        daoFactoryField.set(ticketService, mockDaoFactory);

        Field basicConnection = ticketService.getClass().getDeclaredField("basicConnectionPool");
        basicConnection.setAccessible(true);
        basicConnection.set(ticketService, mockBasicConnectionPool);

        Field transactionField = ticketService.getClass().getDeclaredField("transactionConnection");
        transactionField.setAccessible(true);
        transactionField.set(ticketService, mockTransactionalConnection);
    }

    @Test
    public void getAllTicketsByUserId() {
        long userId = 1;

        User user = new User(1, "test", "test123");

        Station station = new Station(1, "Konotop");
        Station station1 = new Station(2, "Kiev");
        Timestamp timestamp = new Timestamp(new Date().getTime());
        Timestamp timestamp2 = new Timestamp(new Date().getTime());

        TrainRoute trainRoute = new TrainRoute(station, station1, timestamp, timestamp2);

        Train train = new Train(1, "Sappsan", 213);

        TrainTrip trainTrip = new TrainTrip(1, trainRoute, train, 25, 10);

        Ticket ticket = new Ticket(user, trainTrip, 5);

        List<Ticket> usersTickets = new ArrayList<>();
        usersTickets.add(ticket);
        usersTickets.add(ticket);
        usersTickets.add(ticket);
        usersTickets.add(ticket);
        when(mockDaoFactory.createTicketDAO(mockBasicConnectionPool.getConnection())).thenReturn(mockTicketDao);
        when(mockTicketDao.findTicketsByUsersId(user.getId())).thenReturn(usersTickets);
        List<Ticket> findedTickets = ticketService.getAllTicketsByUserId(userId);
        assertEquals(usersTickets, findedTickets);
        verify(mockTicketDao).findTicketsByUsersId(userId);
    }

    @Test(expected = NotMuchMoneyException.class)
    public void createTicket_Should_throw_NotMuchMoneyException() throws SQLException {
        float usersMoney = 60;
        float ticketPrice = 234;
        when(mockDaoFactory.createUserDAO(mockTransactionalConnection)).thenReturn(mockUserDao);
        when(mockDaoFactory.createTrainTripDAO(mockTransactionalConnection)).thenReturn(mockTrainTripDao);
        when(mockDaoFactory.createTicketDAO(mockTransactionalConnection)).thenReturn(mockTicketDao);
        when(mockTicket.getTrainTripId().getPrice()).thenReturn(ticketPrice);
        when(mockTicket.getUserId().getMoney()).thenReturn(usersMoney);
        ticketService.createTicket(mockTicket);
        verify(mockTransactionalConnection).rollback();
    }
    @Test(expected = NoFreeSeatException.class)
    public void createTicket_Should_throw_NoFreeSeatException() throws SQLException {
        float usersMoney = 32;
        float ticketPrice = 5;
        int availableSeats = 0;
        when(mockDaoFactory.createUserDAO(mockTransactionalConnection)).thenReturn(mockUserDao);
        when(mockDaoFactory.createTrainTripDAO(mockTransactionalConnection)).thenReturn(mockTrainTripDao);
        when(mockDaoFactory.createTicketDAO(mockTransactionalConnection)).thenReturn(mockTicketDao);
        when(mockTicket.getTrainTripId().getPrice()).thenReturn(ticketPrice);
        when(mockTicket.getUserId().getMoney()).thenReturn(usersMoney);
        when(mockTicket.getTrainTripId().getAvailableSeats()).thenReturn(availableSeats);
        ticketService.createTicket(mockTicket);
        verify(mockTransactionalConnection).rollback();
    }

    @Test
    public void createTicket_Should_Create_Ticket() throws SQLException {
        float usersMoney = 32;
        float ticketPrice = 5;
        int availableSeats = 10;
        when(mockDaoFactory.createUserDAO(mockTransactionalConnection)).thenReturn(mockUserDao);
        when(mockDaoFactory.createTrainTripDAO(mockTransactionalConnection)).thenReturn(mockTrainTripDao);
        when(mockDaoFactory.createTicketDAO(mockTransactionalConnection)).thenReturn(mockTicketDao);
        when(mockTicket.getTrainTripId().getPrice()).thenReturn(ticketPrice);
        when(mockTicket.getUserId().getMoney()).thenReturn(usersMoney);
        when(mockTicket.getTrainTripId().getAvailableSeats()).thenReturn(availableSeats);
        ticketService.createTicket(mockTicket);
        verify(mockTransactionalConnection).commit();
        verify(mockTicketDao).create(mockTicket);
    }

}
