package com.tc.webatm.service;

import com.tc.webatm.model.User;
import java.util.Map;

abstract public class UserService {
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

    public static User getHydratedFromMap(Map map) {
        //some kind of builder but without UserBuilder class. js (jquery) way. will it fit here?...
        return new User().setId((Integer) map.get("id"))
                        .setEmail((String) map.get("email"))
                        .setPassword((String) map.get("password"));
    }
}
