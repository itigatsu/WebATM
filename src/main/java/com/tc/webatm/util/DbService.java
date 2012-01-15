package com.tc.webatm.util;

import com.tc.webatm.model.DAOFactory;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.sql.*;
import java.util.List;

public enum DbService {
    SELF;

    public Connection getConnection() throws ClassNotFoundException, SQLException {//throws DatabaseNotAccessibleException {
        return DAOFactory.getDAOFactory().getConnection();
    }

    public void bulkUpdate(List queries) throws SQLException, ClassNotFoundException {
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

    public void execQuery(String sql, Object... params) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        try {
            conn = getConnection();
            QueryRunner run = new QueryRunner();
            if (params != null) {
                //run.update(sql, params);
            } else {
                //run.update(sql);
            }
            conn.close();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception ignored) {}
            }
        }
    }

    public List select(String query) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        List result = null;
        try {
            conn = getConnection();
            QueryRunner run = new QueryRunner();
            result = (List)run.query(conn, query, new MapListHandler());
            conn.close();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception ignored) {}
            }
        }
        return result;
    }
}
