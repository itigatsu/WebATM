package com.tc.webatm.model.user;

import com.tc.webatm.model.BaseModel;
import com.tc.webatm.util.DbService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

public class SQLIteUserDAO implements UserDAO {
    @Override
    public User getById(int id) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void add(BaseModel model) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void update(BaseModel newUser) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Collection getAll() throws ClassNotFoundException, SQLException {
        Connection conn = null;
        ArrayList<User> ret = null;

        try {
            conn = DbService.SELF.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from user;");

            ret = new ArrayList<User>();
            while (rs.next()) {
                User u = new User();
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                ret.add(u);
            }
            rs.close();
            conn.close();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception ignored) {}
            }
        }
        return ret;
    }

    @Override
    public void delete(BaseModel user) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void deleteAll() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
