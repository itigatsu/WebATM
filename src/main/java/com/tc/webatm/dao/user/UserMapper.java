package com.tc.webatm.dao.user;

import com.tc.webatm.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class UserMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new User().setId(Integer.parseInt(rs.getString("id")))
                .setEmail(rs.getString("email"))
                .setPassword(rs.getString("password"));
    }
}