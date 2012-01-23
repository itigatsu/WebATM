package com.tc.webatm.dao;

import com.tc.webatm.Config;

public abstract class DAOFactory {
    public static UserDAO getUserDAO() {
        UserDAO userDAO = (UserDAO)Config.getMainContext().getBean("userDAO");
        return userDAO;
/*
       switch (Config.getConnectType()) {
            case Config.CONNECT_JDBC:
                return new JDBCUserDAO();
            //break;
        }
        return null;
*/
    }
}
