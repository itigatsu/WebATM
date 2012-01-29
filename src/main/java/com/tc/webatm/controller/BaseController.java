package com.tc.webatm.controller;

import com.tc.webatm.Command;
import com.tc.webatm.service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

abstract public class BaseController extends HttpServlet {
    protected Map<String,Command> commands;
    protected ArrayList<String> errors;

    abstract public void init();
    abstract protected String getDefaultCommand();

    public BaseController() {
        commands = new HashMap<String,Command>();
        errors = new ArrayList<String>();
    }

    protected Object getBeanFromWebAppContext(String beanName) {
        WebApplicationContext springContext = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        return springContext.getBean(beanName);
    }
    
    @Deprecated
    private void invokeContext() {
        //invoking main context
        new ClassPathXmlApplicationContext("webContext.xml");

        //context = new ClassPathXmlApplicationContext("config.xml")
        //ConfigurableApplicationContext ctx = (ConfigurableApplicationContext) context;
        //ctx.registerShutdownHook();
    }

    protected void processCommand(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //clear errors on every request. reason: servlet is loading once into container
        errors.clear();

        //context is invoking using web.xml context param
        //invokeContext();

        //no need in this once resources were put to the standard dirs
        //Config.setAppPath(req.getPathTranslated());
        //Config.setAppPath("");

        String action = req.getParameter("action");

        if (action == null) {
            action = getDefaultCommand();
        }

        Command command = commands.get(action);

        if (command == null) {
            throw new IllegalArgumentException( "No command found for action: " + action);
        }

        //Principal p = req.getUserPrincipal();
        //user.setLogin(req.getRemoteUser());

        command.execute(req, resp);

        req.setAttribute("user", UserService.getLoggedUser());
        req.setAttribute("errors", errors);

        req.getRequestDispatcher(command.getViewPath()).forward(req, resp);
    }

    protected void render(String view, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //ServletContext context = req.getSession().getServletContext();
        //context.getRequestDispatcher("/WEB-INF/view/admin/dashboard.jsp").forward(req, resp);
        req.getRequestDispatcher(view).forward(req, resp);
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
}