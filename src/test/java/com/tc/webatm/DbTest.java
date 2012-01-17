package com.tc.webatm;

import java.sql.*;
import java.util.Collection;

import com.tc.webatm.dao.DAOFactory;
import com.tc.webatm.dao.UserDAO;
import com.tc.webatm.model.User;
import com.tc.webatm.util.DbService;
import junit.framework.TestCase;

public class DbTest extends TestCase {
    //@Depends("#testInit")
    public void testUserDAO() throws ClassNotFoundException, SQLException {
		UserDAO userDAO = DAOFactory.getUserDAO();

        String initialEmail = "user@test.com";
        String changedMail = "user2@test.com";

        Collection users = userDAO.getAll();
        User targetUser = ((User)users.toArray()[0]);
        assertEquals(users.size(), 1);
        assertEquals(targetUser.getEmail(), initialEmail);

        userDAO.add(new User().setId(2).setEmail("aa@bb.cc").setPassword("123"));
        users = userDAO.getAll();
        assertEquals(users.size(), 2);

        System.out.println("Users:");
        for (Object user : users) {
            System.out.println(user);
        }

        userDAO.delete(2);
        users = userDAO.getAll();
        assertEquals(users.size(), 1);


        targetUser.setEmail(changedMail);
        userDAO.update(targetUser);

        users = userDAO.getAll();
        targetUser = ((User)users.toArray()[0]);
        assertEquals(targetUser.getEmail(), changedMail);

        targetUser.setEmail(initialEmail);
        userDAO.update(targetUser);
    }

    public void testInit() throws ClassNotFoundException, SQLException {
        /*
		DbService.SELF.reloadDbSchema();
        DbService.SELF.initDbWithMockData();
		*/
    }
}
