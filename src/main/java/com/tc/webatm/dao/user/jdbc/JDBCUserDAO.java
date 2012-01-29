package com.tc.webatm.dao.user.jdbc;

import com.tc.webatm.dao.user.UserDAO;
import com.tc.webatm.dao.user.UserMapper;
import com.tc.webatm.model.User;
import com.tc.webatm.service.UserService;
import com.tc.webatm.util.DbUtil;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import javax.validation.constraints.NotNull;
import java.sql.SQLException;
import java.util.*;

public class JDBCUserDAO implements UserDAO {
    private JdbcTemplate jdbcTemplate;

    @Override
    public User getById(int id) throws ClassNotFoundException, SQLException {
        if (id < 1) {
            throw new IllegalArgumentException("User id must represent positive int");
        }

        User user = (User) this.jdbcTemplate.queryForObject("select * from user where id = ?",
                                                                new Object[] {id}, new UserMapper() );
        return user;
    }

    @Override
    public void add(@NotNull User user) throws ClassNotFoundException, SQLException {
        if (user.getId() > 0) {
            jdbcTemplate.update("insert into user(id, email, password) values (?, ?, ?);", new Object[] {user.getId(), user.getEmail(), user.getPassword()});
        } else {
            jdbcTemplate.update("insert into user(email, password) values (?, ?);", new Object[] {user.getEmail(), user.getPassword()});
        }
    }

    @Override
    public void update(@NotNull User user) throws ClassNotFoundException, SQLException {
        if (user.getId() < 1) {
            throw new IllegalArgumentException("User id must represent positive int");
        }
        jdbcTemplate.update("update user set email = ?, password = ? where id = ?;", new Object[] {user.getEmail(), user.getPassword(), user.getId()});
    }

    @Override
    public List<User> getAll() throws ClassNotFoundException, SQLException {
        List<User> users = (List<User>)this.jdbcTemplate.query("select * from user", new UserMapper() );
        return users;
    }

    @Override
    public void delete(@NotNull User user) throws ClassNotFoundException, SQLException {
        if (user.getId() < 1) {
            throw new IllegalArgumentException("User id must represent positive int");
        }
        jdbcTemplate.update("delete from user where id = ?;", new Object[] {user.getId()});
    }

    @Override
    public void delete(int id) throws ClassNotFoundException, SQLException {
        if (id < 1) {
            throw new IllegalArgumentException("User id must represent positive int");
        }

        jdbcTemplate.update("delete from user where id = ?;", new Object[] {id});
    }

    @Override
    public void deleteAll() throws ClassNotFoundException, SQLException {
        jdbcTemplate.update("delete from user;");
    }

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
}
