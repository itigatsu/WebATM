package com.tc.webatm.controller;

import com.tc.webatm.util.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

abstract public class BaseController extends HttpServlet {
    protected Map<String,Command> commands = new HashMap<String,Command>();

    abstract public void init();
    abstract protected String getDefaultCommand();

    protected interface Command {
        public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
    }

    protected void processCommand(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if (action == null){
            action = getDefaultCommand();
        }

        Command command = commands.get(action);

        if (command == null){
            throw new IllegalArgumentException( "No command for form action: " + action);
        }

        //Principal p = req.getUserPrincipal();
        //user.setLogin(req.getRemoteUser());

        req.setAttribute("user", UserService.getLoggedUser());

        command.execute(req, resp);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processCommand(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processCommand(req, resp);
    }

    public void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processCommand(req, resp);
    }

    public void doHead(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processCommand(req, resp);
    }

    public void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processCommand(req, resp);
    }

    //with no exception! =)
}
