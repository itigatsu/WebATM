package com.tc.webatm.model;

import java.sql.SQLException;
import java.util.Collection;

public interface BaseDAO {
    public void add(BaseModel model) throws ClassNotFoundException, SQLException;
    public void update(BaseModel model) throws ClassNotFoundException, SQLException;
    public Collection getAll() throws ClassNotFoundException, SQLException;
    public void delete(BaseModel model) throws ClassNotFoundException, SQLException;
    public void deleteAll() throws ClassNotFoundException, SQLException;
}
