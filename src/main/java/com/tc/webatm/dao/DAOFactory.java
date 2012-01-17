package com.tc.webatm.dao;

import com.tc.webatm.Config;
import com.tc.webatm.dao.jdbc.JDBCUserDAO;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class DAOFactory {
    public static final int SQLITE = 1;

    public abstract Connection getConnection() throws ClassNotFoundException, SQLException;
    public abstract void reloadDbSchema() throws ClassNotFoundException, SQLException;
    public abstract void initDbWithMockData() throws ClassNotFoundException, SQLException;

    public static UserDAO getUserDAO() {
        switch (Config.getConnectType()) {
            case Config.CONNECT_JDBC:
                return new JDBCUserDAO();
            //break;
        }
        return null;
    }
}
