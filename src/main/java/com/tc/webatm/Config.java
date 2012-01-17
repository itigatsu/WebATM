package com.tc.webatm;

abstract public class Config {
    public static final int CONNECT_JDBC = 1;
    public static final int CONNECT_HYNERNATE = 1;

    public static int getConnectType() {
        return CONNECT_JDBC;
    }
}
