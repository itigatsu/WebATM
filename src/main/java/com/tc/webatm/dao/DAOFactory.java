package com.tc.webatm.dao;

import com.tc.webatm.Config;
import com.tc.webatm.dao.user.jdbc.JDBCUserDAO;
import com.tc.webatm.dao.user.UserDAO;

@Deprecated
public abstract class DAOFactory {
    public static UserDAO getUserDAO() {
        UserDAO userDAO = (UserDAO)Config.getMainContext().getBean("userDAO");
        return userDAO;
    }

    public static UserDAO getByConfig() {
       switch (Config.getConnectType()) {
            case Config.CONNECT_JDBC:
                return new JDBCUserDAO();
            //break;
        }
        return null;
    }
}
