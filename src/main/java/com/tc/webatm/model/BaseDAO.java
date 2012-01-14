package com.tc.webatm.model;

import java.sql.SQLException;
import java.util.Collection;

public interface BaseDAO {
    public void add(BaseModel model);
    public void update(BaseModel newUser);
    public Collection getAll() throws ClassNotFoundException, SQLException;
    public void delete(BaseModel user);
    public void deleteAll();
}
