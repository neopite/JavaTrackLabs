package lab2_5.com.company.neophite.model.mapper.impl;

import lab2_5.com.company.neophite.model.entity.Role;
import lab2_5.com.company.neophite.model.mapper.ObjectMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleMapper implements ObjectMapper<Role> {

    @Override
    public Role extractEntityFromTheRS(ResultSet resultSet) throws SQLException {
        return new Role(
                resultSet.getLong("id_role") ,
                resultSet.getString("name_role")
        );
    }
}
