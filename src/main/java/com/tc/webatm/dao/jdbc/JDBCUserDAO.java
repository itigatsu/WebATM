package com.tc.webatm.dao.jdbc;

import com.tc.webatm.dao.UserDAO;
import com.tc.webatm.model.User;
import com.tc.webatm.util.DbService;
import com.tc.webatm.util.UserService;

import javax.validation.constraints.NotNull;
import java.sql.SQLException;
import java.util.*;

public class JDBCUserDAO implements UserDAO {
    @Override
    public User getById(int id) throws ClassNotFoundException, SQLException {
        if (id < 1) {
            throw new IllegalArgumentException("User id must represent positive int");
        }

        List users = DbService.SELF.select("select * from user where id = " + id + ";");
        return UserService.getHydratedFromMap((Map)users.get(0));
    }

    @Override
    public void add(@NotNull User user) throws ClassNotFoundException, SQLException {
        DbService.SELF.update("insert into user(email, password) values (?, ?);", user.getEmail(), user.getPassword());
    }

    @Override
    public void update(@NotNull User user) throws ClassNotFoundException, SQLException {
        if (user.getId() < 1) {
            throw new IllegalArgumentException("User id must represent positive int");
        }
        DbService.SELF.update("update user set email = ?, password = ? where id = ?;",
                user.getEmail(), user.getPassword(), user.getId());
    }

    @Override
    public List<User> getAll() throws ClassNotFoundException, SQLException {
        List users = DbService.SELF.select("select * from user;");
        List<User> ret = new ArrayList<User>();
        for (Object obj : users) {
            ret.add(UserService.getHydratedFromMap((Map)obj));
        }
        return ret;
    }

    @Override
    public void delete(@NotNull User user) throws ClassNotFoundException, SQLException {
        if (user.getId() < 1) {
            throw new IllegalArgumentException("User id must represent positive int");
        }

        DbService.SELF.update("delete from user where id = ?;", user.getId());
    }

    @Override
    public void delete(int id) throws ClassNotFoundException, SQLException {
        if (id < 1) {
            throw new IllegalArgumentException("User id must represent positive int");
        }

        User user = new User().setId(id);
        DbService.SELF.update("delete from user where id = ?;", user.getId());
    }

    @Override
    public void deleteAll() throws ClassNotFoundException, SQLException {
        DbService.SELF.update("delete from user;");
    }
}
