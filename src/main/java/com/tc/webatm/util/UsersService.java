package com.tc.webatm.util;

import com.tc.webatm.model.user.User;

abstract public class UsersService {
    private static User loggedUser;
    
    public static final String ADMIN_EMAIL = "admin@webatm.com";
    public static final String ADMIN_PASSWORD = "admin";
    
    public static void setLoggedUser(User u) {
        loggedUser = u;
    }

    public static User getLoggedUser() {
        return loggedUser;
    }

    public static boolean isUserLogged() {
        return (loggedUser != null);
    }
}
