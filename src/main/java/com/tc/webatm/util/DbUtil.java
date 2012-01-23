package com.tc.webatm.util;

import com.tc.webatm.Config;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public enum DbUtil {
    SELF;

    public Connection getConnection() throws ClassNotFoundException, SQLException, IOException {//throws DatabaseNotAccessibleException {
        Connection con = null;
        switch (Config.getConnectType()) {
            case Config.CONNECT_JDBC:
                Class.forName("org.sqlite.JDBC");
                //con = DriverManager.getConnection("jdbc:sqlite:" + Config.getAppPath() + "WebATM.db");
                String dbPath = (new ClassPathResource("WebATM.db")).getFile().getAbsolutePath();
                con = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
            break;
        }
        return con;
    }

    public void bulkUpdate(List queries) throws SQLException, ClassNotFoundException, IOException {
        for (Object query : queries) {
            update((String) query);
        }
    }

    public void update(String query, Object... params) throws SQLException, ClassNotFoundException, IOException {
        Connection conn = null;
        try {
            conn = getConnection();
            QueryRunner run = new QueryRunner();
            if (params != null) {
                run.update(conn, query, params);
            } else {
                run.update(conn, query);
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

    public List select(String query, Object... params) throws SQLException, ClassNotFoundException, IOException {
        Connection conn = null;
        List result = null;
        try {
            conn = getConnection();
            QueryRunner run = new QueryRunner();
            if (params != null) {
                result = (List)run.query(conn, query, new MapListHandler(), params);
            } else {
                result = (List)run.query(conn, query, new MapListHandler());
            }
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

    public void reloadDbSchema() throws ClassNotFoundException, SQLException, IOException {
        ArrayList<String> queries = new ArrayList<String>();

        queries.add("drop table if exists user;");
        queries.add("create table user (id integer not null unique primary key autoincrement, "
                + "email text not null, password text not null);");

        queries.add("drop table if exists currency;");
        queries.add("create table currency (id integer not null unique primary key autoincrement, "
                + "title text unique not null);");

        queries.add("drop table if exists account;");
        queries.add("create table account (id integer not null unique primary key autoincrement, "
                + "user_id int not null, currency_id int not null, state int default 1, "
                + "title text not null, balance int default 0, unique(user_id, title));");

        queries.add("drop table if exists `transaction`;");
        //queries.add("create table `transaction` (id integer not null primary key autoincrement, account int not null, title text not null);");

        bulkUpdate(queries);
    }

    public void initDbWithMockData() throws ClassNotFoundException, SQLException, IOException {
        ArrayList<String> queries = new ArrayList<String>();

        int userId = 1;
        int currencyId = 1;
        queries.add("insert into user (id, email, password) values(" + userId + ", 'user@test.com', 'test');");

        queries.add("insert into currency (id, title) values(" + currencyId + ", 'USD');");
        queries.add("insert into currency (id, title) values(" + (currencyId+1) + ", 'EUR');");

        queries.add("insert into account (user_id, currency_id, title) values(" + userId + ", " + currencyId + ", 'usd account');");

        bulkUpdate(queries);
    }
}
