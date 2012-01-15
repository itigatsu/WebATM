package com.tc.webatm.model.user;

import com.tc.webatm.model.BaseModel;
import com.tc.webatm.util.DbService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class SQLIteUserDAO implements UserDAO {
    @Override
    public User getById(int id) throws ClassNotFoundException, SQLException {
        List users = DbService.SELF.select("select * from user where id = " + id + ";");
        User user = new User();
        user.hydrateFromMap((Map)users.get(0));
        return user;
    }

    @Override
    public void add(BaseModel model) throws ClassNotFoundException, SQLException {
        if (!(model instanceof User)) {
            throw new IllegalArgumentException("User instance must be passed");
        }
        User user = (User)model;
        DbService.SELF.execQuery("insert into user(email, password) values (?, ?);", user.getEmail(), user.getPassword());
    }

    @Override
    public void update(BaseModel model) throws ClassNotFoundException, SQLException {
        if (!(model instanceof User)) {
            throw new IllegalArgumentException("User instance must be passed");
        }
        User user = (User)model;
        if (user.getId() < 1) {
            throw new IllegalArgumentException("User id must represent positive int");
        }
        DbService.SELF.execQuery("update user set email = ?, password = ? where id = ?;",
                user.getEmail(), user.getPassword(), user.getId());
    }

    @Override
    public List<User> getAll() throws ClassNotFoundException, SQLException {
        List users = DbService.SELF.select("select * from user;");
        List<User> ret = new ArrayList<User>();
        for (Object obj : users) {
            User u = new User();
            u.hydrateFromMap((Map)obj);
            ret.add(u);
        }
        return ret;
    }

    @Override
    public void delete(BaseModel model) throws ClassNotFoundException, SQLException {
        if (!(model instanceof User)) {
            throw new IllegalArgumentException("User instance must be passed");
        }

        User user = (User)model;
        if (user.getId() < 1) {
            throw new IllegalArgumentException("User id must represent positive int");
        }

        DbService.SELF.execQuery("delete from user where id = ?;", user.getId());
    }

    @Override
    public void deleteAll() throws ClassNotFoundException, SQLException {
        DbService.SELF.execQuery("delete from user;", null);
    }
}
