package lab2_5.com.company.neophite.model.dao.impl;

import lab2_5.com.company.neophite.model.dao.AbstractDAO;
import lab2_5.com.company.neophite.model.entity.Role;
import lab2_5.com.company.neophite.model.mapper.impl.RoleMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleDAOImpl extends AbstractDAO<Role,Long> {
    private final RoleMapper roleMapper = new RoleMapper();
    private final String FIND_ROLE_BY_ID = "select * from roles where id=?";
    private final String FIND_ALL_ROLES = "select distinct * from roles";
    private final String GET_USERS_ROLES ="select id_role,name_role from users left join users_roles ur on ur.user_id = users.id left join roles r on r.id_role = ur.role_id where id=?";


    public RoleDAOImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Role create(Role role) {
        return null;
    }

    @Override
    public Role findByKey(Long key) {
        Role role = null;
        try(PreparedStatement preparedStatement = getStatement(FIND_ROLE_BY_ID)){
            preparedStatement.setLong(1,key);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                role = roleMapper.extractEntityFromTheRS(resultSet);
            }
            return role;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return role;
    }

    public List<Role> findUsersRole(long userId) {
        List<Role> usersRoles = new ArrayList<>();
        try(PreparedStatement preparedStatement = getStatement(GET_USERS_ROLES)) {
            preparedStatement.setLong(1,userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Role role = roleMapper.extractEntityFromTheRS(resultSet);
                usersRoles.add(role);
            }
            return usersRoles;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return usersRoles;
    }

    @Override
    public boolean deleteByKey(Long key) {
        return false;
    }

    @Override
    public List<Role> getAll() {
        List<Role> roles = new ArrayList<>();
      try(PreparedStatement preparedStatement = getStatement(FIND_ALL_ROLES)) {
          ResultSet resultSet = preparedStatement.executeQuery();
          while(resultSet.next()){
              Role role = roleMapper.extractEntityFromTheRS(resultSet);
              roles.add(role);
          }
          return roles;
      } catch (SQLException sqlException) {
          sqlException.printStackTrace();
      }
      return roles;
    }
}
