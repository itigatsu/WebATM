package com.tc.webatm;

import java.sql.*;
import java.util.Collection;

import com.tc.webatm.model.BaseDAO;
import com.tc.webatm.model.DAOFactory;
import com.tc.webatm.model.user.User;
import com.tc.webatm.model.user.UserDAO;
import junit.framework.TestCase;

public class DbTest extends TestCase {
    public void testInit() throws ClassNotFoundException, SQLException {
        //DbService.SELF.reloadDbSchema();
        //DbService.SELF.initDbWithMockData();

        UserDAO userDAO = DAOFactory.getDAOFactory().getUserDAO();

        Collection u = userDAO.getAll();
        assertEquals(((User)u.toArray()[0]).getEmail(), "user@test.com");

        System.out.println("Users:");
        for (Object user : u) {
            System.out.println(user);
        }
    }
}
