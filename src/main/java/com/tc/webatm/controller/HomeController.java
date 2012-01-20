package com.tc.webatm.controller;

import com.tc.webatm.Command;

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
        public String getViewPath() {
            return "/WEB-INF/view/home/index.jsp";
        }

        public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            //some logic goes here
        }
    }
}
