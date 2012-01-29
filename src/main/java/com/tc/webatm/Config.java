package com.tc.webatm;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;

abstract public class Config {
    public static final int CONNECT_JDBC = 1;
    public static final int CONNECT_HYNERNATE = 1;

    private static String appPath = "";
    private static ClassPathXmlApplicationContext mainContext;

    public static int getConnectType() {
        return CONNECT_JDBC;
    }
    
    public static String getAppPath() {
        return appPath;
    }
    
    public static void setAppPath(String path) {
        if (path == null || path.isEmpty()) {
            return;
        }

        appPath = path.replaceAll("[/\\\\]+$", "");
        if (!appPath.isEmpty()) {
            appPath += File.separator;
        }
    }

    public static ClassPathXmlApplicationContext getMainContext() {
        if (mainContext == null) {
            mainContext = new ClassPathXmlApplicationContext("context.xml");
        }
        return mainContext;
    }
}
