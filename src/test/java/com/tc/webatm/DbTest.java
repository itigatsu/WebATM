package com.tc.webatm;

import java.sql.*;
import java.util.Collection;
import java.util.Properties;

import com.tc.webatm.dao.UserDAO;
import com.tc.webatm.model.User;
import junit.framework.TestCase;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DbTest extends TestCase {

    //loads from bean in test-context.xml
    //member static bc of TestCase method specific calls
    private static UserDAO userDAO;

    //needed for spring to call this setter when injecting bean
    public void setUserDAO(UserDAO uDAO) {
        userDAO = uDAO;
    }

    @Override
    protected void setUp() throws Exception {
        //invoking needed context
        ApplicationContext context = new ClassPathXmlApplicationContext("testContext.xml");
        //ConfigurableApplicationContext ctx = (ConfigurableApplicationContext) context;
        //ctx.registerShutdownHook();
        super.setUp();
    }

    //@Depends("#testInit")
    public void testUserDAO() throws ClassNotFoundException, SQLException {
        //Config.setAppPath(System.getProperty("user.dir"));
        //Config.setAppPath("");

        //already loaded
        //userDAO = DAOFactory.getUserDAO();

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
            lastUId = ((User) user).getId();
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
