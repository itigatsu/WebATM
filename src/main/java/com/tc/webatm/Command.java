package com.tc.webatm;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Command {
    public String getViewPath();
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
}
