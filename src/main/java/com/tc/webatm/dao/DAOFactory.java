package com.tc.webatm.dao;

import com.tc.webatm.Config;
import com.tc.webatm.dao.jdbc.JDBCUserDAO;

public abstract class DAOFactory {
    public static UserDAO getUserDAO() {
        switch (Config.getConnectType()) {
            case Config.CONNECT_JDBC:
                return new JDBCUserDAO();
            //break;
        }
        return null;
    }
}
