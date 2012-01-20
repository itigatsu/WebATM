package com.tc.webatm;

import java.sql.*;
import java.util.Collection;

import com.tc.webatm.dao.DAOFactory;
import com.tc.webatm.dao.UserDAO;
import com.tc.webatm.model.User;
import com.tc.webatm.util.DbUtil;
import junit.framework.TestCase;

public class DbTest extends TestCase {
    //@Depends("#testInit")
    public void testUserDAO() throws ClassNotFoundException, SQLException {
		UserDAO userDAO = DAOFactory.getUserDAO();

        String initialEmail = "user@test.com";
        String changedMail = "user2@test.com";

        //get all
        Collection users = userDAO.getAll();
        User targetUser = ((User)users.toArray()[0]);
        assertEquals(targetUser.getEmail(), initialEmail);

        //add
        userDAO.add(new User().setId(2).setEmail("aa@bb.cc").setPassword("123"));
        users = userDAO.getAll();
        assertNotSame(users.size(), 1);

        int lastUId = 0;
        System.out.println("Users:");
        for (Object user : users) {
            System.out.println(user);
            lastUId = ((User)user).getId();
        }

        //delete
        userDAO.delete(lastUId);
        users = userDAO.getAll();
        assertEquals(users.size(), 1);


        //update
        targetUser.setEmail(changedMail);
        userDAO.update(targetUser);

        users = userDAO.getAll();
        targetUser = ((User)users.toArray()[0]);
        assertEquals(targetUser.getEmail(), changedMail);

        targetUser.setEmail(initialEmail);
        userDAO.update(targetUser);
    }

    public void testInit() throws ClassNotFoundException, SQLException {
		//DbUtil.SELF.reloadDbSchema();
        //DbUtil.SELF.initDbWithMockData();
    }
}
