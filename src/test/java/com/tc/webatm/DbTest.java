package com.tc.webatm;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.tc.webatm.model.User;
import com.tc.webatm.util.DbService;
import junit.framework.TestCase;

public class DbTest extends TestCase {
    public void testInit() throws ClassNotFoundException, SQLException {
        //DbService.SELF.reloadDbSchema();
        //DbService.SELF.initDbWithMockData();

        List<User> users = DbService.SELF.getAllUsers();
        assertEquals(users.get(0).getEmail(), "user@test.com");

        System.out.println("Users:");
        for (User user : users) {
            System.out.println(user.getEmail());
        }
    }
}
