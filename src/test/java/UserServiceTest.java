import lab3.com.company.neophite.model.dao.connection.BasicConnectionPool;
import lab3.com.company.neophite.model.dao.impl.DAOFactoryImpl;
import lab3.com.company.neophite.model.dao.impl.RoleDAOImpl;
import lab3.com.company.neophite.model.dao.impl.UserDAOImpl;
import lab3.com.company.neophite.model.entity.Role;
import lab3.com.company.neophite.model.entity.User;
import lab3.com.company.neophite.model.service.UserService;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;


public class UserServiceTest {
    private UserService userService;

    private UserDAOImpl mockUserDAO;
    private RoleDAOImpl mockRokeDao;

    private DAOFactoryImpl mockDaoFactory;
    private BasicConnectionPool mockConnection;


    @Before
    public void before() throws NoSuchFieldException, IllegalAccessException {
        mockDaoFactory = mock(DAOFactoryImpl.class);
        mockUserDAO = mock(UserDAOImpl.class);
        mockRokeDao = mock(RoleDAOImpl.class);
        mockConnection = mock(BasicConnectionPool.class);
        userService = new UserService();

        Field daoFactoryField = userService.getClass().getDeclaredField("daoFactory");
        daoFactoryField.setAccessible(true);
        daoFactoryField.set(userService,mockDaoFactory);

        Field basicConnection = userService.getClass().getDeclaredField("basicConnectionPool");
        basicConnection.setAccessible(true);
        basicConnection.set(userService,mockConnection);

    }

    @Test
    public void findUserByUsername() {
        long id = 1 ;
        String username = "test123";
        String passwd = "test";
        User user = new User((int) id,username,passwd);
        Role role = new Role("Admin");
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        user.setRoles(roles);
        when(mockDaoFactory.createUserDAO(mockConnection.getConnection())).thenReturn(mockUserDAO);
        when(mockDaoFactory.createRoleDAO(mockConnection.getConnection())).thenReturn(mockRokeDao);
        when(mockUserDAO.findUserByUsername(username)).thenReturn(user);
        when(mockRokeDao.findUsersRole(id)).thenReturn(roles);
        assertEquals(user,userService.findUserByUsername(username));
        verify(mockRokeDao).findUsersRole((long) 1);
    }

    @Test
    public void createUser_Should_throw_UserAlreadyCreatedException() {
        long id = 1 ;
        String username = "test123";
        String passwd = "test";
        User user = new User((int) id,username,passwd);
        Role role = new Role("Admin");
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        user.setRoles(roles);
        when(mockDaoFactory.createUserDAO(mockConnection.getConnection())).thenReturn(mockUserDAO);
        when(mockDaoFactory.createRoleDAO(mockConnection.getConnection())).thenReturn(mockRokeDao);
        when(mockUserDAO.findUserByUsername(username)).thenReturn(user);
        when(mockRokeDao.findUsersRole(id)).thenReturn(roles);
        assertEquals(user,userService.findUserByUsername(username));
        verify(mockRokeDao).findUsersRole((long) 1);
    }
    
    @Test
    public void createUser_Should_Create_New_User() {
        long id = 1 ;
        String username = "test123";
        String passwd = "test";
        User user = new User((int) id,username,passwd);
        Role role = new Role("Admin");
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        user.setRoles(roles);
        when(mockDaoFactory.createUserDAO(mockConnection.getConnection())).thenReturn(mockUserDAO);
        when(mockDaoFactory.createRoleDAO(mockConnection.getConnection())).thenReturn(mockRokeDao);
        when(mockUserDAO.findUserByUsername(username)).thenReturn(user);
        when(mockRokeDao.findUsersRole(id)).thenReturn(roles);
        assertEquals(user,userService.findUserByUsername(username));
        verify(mockRokeDao).findUsersRole((long) 1);
    }
}
