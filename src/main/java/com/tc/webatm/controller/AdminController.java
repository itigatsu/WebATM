package com.tc.webatm.controller;

import com.tc.webatm.Command;
import com.tc.webatm.dao.user.UserDAO;
import com.tc.webatm.util.DbUtil;

import java.io.*;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.http.*;

public class AdminController extends BaseController {
    //todo: clarify this with Igor why non-static beans not loaded
    private static UserDAO userDAO;

    public void setUserDAO(UserDAO uDAO) {
        if (userDAO == null) { // =\
            userDAO = uDAO;
        }
        userDAO = uDAO;
    }

    public UserDAO getUserDAO() {
        //userDAO = (UserDAO)getBeanFromWebAppContext("userDAOStandalone");
        return userDAO;
    }

    public void init() {
        commands.put("initDb",  new InitDbCommand());
        commands.put("dashboard", new DashboardCommand());
        commands.put("users", new UsersCommand());
    }
    
    protected String getDefaultCommand() {
        return "dashboard";
    }

    private class InitDbCommand implements Command {
        public String getViewPath() {
            return "/WEB-INF/view/admin/reloadDb.jsp";
        }

        public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            ArrayList<String> msgs = new ArrayList<String>();

            try {
                msgs.add("Reloading database schema...");
                DbUtil.SELF.reloadDbSchema();
                msgs.add("OK");

                msgs.add("Initialising database with mock data...");
                DbUtil.SELF.initDbWithMockData();
                msgs.add("OK");
            } catch (Exception e) {
                msgs.add("FAILED: " + e.getMessage());
            }

            req.setAttribute("msgs", msgs);
            render("/WEB-INF/view/admin/reloadDb.jsp", req, resp);
        }
    }

    private class DashboardCommand implements Command {
        public String getViewPath() {
            return "/WEB-INF/view/admin/dashboard.jsp";
        }

        public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            //some logic goes here
        }
    }

    private class UsersCommand implements Command {
        public String getViewPath() {
            return "/WEB-INF/view/admin/users.jsp";
        }

        public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            try {
                req.setAttribute("users", getUserDAO().getAll());
            } catch (Exception e) {
                errors.add("Failed to load users: " + e.getMessage());
            }
        }
    }
}
