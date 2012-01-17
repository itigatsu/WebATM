package com.tc.webatm.controller;

import com.tc.webatm.dao.DAOFactory;
import com.tc.webatm.util.DbService;

import java.io.*;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.http.*;

public class AdminController extends BaseController {
    public void init() {
        commands.put("initDb",  new InitDbCommand());
        commands.put("dashboard", new DashboardCommand());
    }
    
    protected String getDefaultCommand() {
        return "dashboard";
    }

    private class InitDbCommand implements Command {
        public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            ArrayList<String> msgs = new ArrayList<String>();

            try {
                msgs.add("Reloading database schema...");
                DbService.SELF.reloadDbSchema();
                msgs.add("OK");

                msgs.add("Initialising database with mock data...");
                DbService.SELF.initDbWithMockData();
                msgs.add("OK");
            } catch (Exception e) {
                msgs.add("FAILED: " + e.getMessage());
            }

            req.setAttribute("msgs", msgs);
            req.getRequestDispatcher("/WEB-INF/view/admin/reloadDb.jsp").forward(req, resp);
        }
    }

    private class DashboardCommand implements Command {
        public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            //ServletContext context = req.getSession().getServletContext();
            //context.getRequestDispatcher("/WEB-INF/view/admin/dashboard.jsp").forward(req, resp);
            req.getRequestDispatcher("/WEB-INF/view/admin/dashboard.jsp").forward(req, resp);
        }
    }
}
