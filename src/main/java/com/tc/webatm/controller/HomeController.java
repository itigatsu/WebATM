package com.tc.webatm.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class HomeController extends BaseController {
    public void init() {
        commands.put("index", new IndexCommand());
    }

    protected String getDefaultCommand() {
        return "index";
    }

    private class IndexCommand implements Command {
        public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            req.getRequestDispatcher("/WEB-INF/view/home/index.jsp").forward(req, resp);
        }
    }
}
