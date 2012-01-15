package com.tc.webatm.model;

import com.tc.webatm.model.user.User;
import com.tc.webatm.model.user.UserDAO;
import com.tc.webatm.util.DbService;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class DAOFactory {
    public static final int SQLITE = 1;

    public abstract Connection getConnection() throws ClassNotFoundException, SQLException;
    public abstract void reloadDbSchema() throws ClassNotFoundException, SQLException;
    public abstract void initDbWithMockData() throws ClassNotFoundException, SQLException;

    public abstract UserDAO getUserDAO();

    public static DAOFactory getDAOFactory() {
        int whichOne = SQLITE;//suppose it was taken from the config
        switch (whichOne) {
            case SQLITE:
                return new SQLIteDAOFactory();
        }
        return null;
    }
}
