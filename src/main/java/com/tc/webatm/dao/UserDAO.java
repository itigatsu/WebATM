package com.tc.webatm.dao;

import com.tc.webatm.model.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

public interface UserDAO {
    public void add(User user) throws ClassNotFoundException, SQLException;
    public void update(User user) throws ClassNotFoundException, SQLException;
    public Collection getAll() throws ClassNotFoundException, SQLException;
    public void delete(User user) throws ClassNotFoundException, SQLException;
    public void delete(int id) throws ClassNotFoundException, SQLException;
    public void deleteAll() throws ClassNotFoundException, SQLException;
    public User getById(int id) throws ClassNotFoundException, SQLException, IOException;
}
