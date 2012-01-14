package com.tc.webatm.model;

import com.tc.webatm.model.user.SQLIteUserDAO;
import com.tc.webatm.model.user.UserDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class SQLIteDAOFactory extends DAOFactory {
    @Override
    public UserDAO getUserDAO() {
        return new SQLIteUserDAO();
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        return DriverManager.getConnection("jdbc:sqlite:WebATM.db");
    }

    public void reloadDbSchema() throws ClassNotFoundException, SQLException {
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

        runUpdateQueries(queries);
    }

    public void initDbWithMockData() throws ClassNotFoundException, SQLException {
        ArrayList<String> queries = new ArrayList<String>();

        int userId = 1;
        int currencyId = 1;
        queries.add("insert into user (id, email, password) values(" + userId + ", 'user@test.com', 'test');");

        queries.add("insert into currency (id, title) values(" + currencyId + ", 'USD');");
        queries.add("insert into currency (id, title) values(" + (currencyId+1) + ", 'EUR');");

        queries.add("insert into account (user_id, currency_id, title) values(" + userId + ", " + currencyId + ", 'usd account');");

        runUpdateQueries(queries);
    }
}
