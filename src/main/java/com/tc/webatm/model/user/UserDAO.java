package com.tc.webatm.model.user;

import com.tc.webatm.model.BaseDAO;

import java.util.Collection;

public interface UserDAO extends BaseDAO {
    public User getById(int id);
}
