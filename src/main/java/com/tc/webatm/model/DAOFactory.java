package com.tc.webatm.model;

import com.tc.webatm.model.user.UserDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
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

    protected void runUpdateQueries(List queries) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();

            for (Object query : queries) {
                String sql = (String) query;
                st.executeUpdate(sql);
            }
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception ignored) {}
            }
        }
    }
}
