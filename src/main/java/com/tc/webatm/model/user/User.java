package com.tc.webatm.model.user;

import com.tc.webatm.model.BaseModel;
import com.tc.webatm.util.UsersService;

import java.util.Map;

public class User extends BaseModel {
    private int id;
    private String email;
    private String password;

    public String getEmail()
    {
        return email;
    }

    public User setEmail(String email)
    {
        this.email = email;
        return this;
    }

    public int getId()
    {
        return id;
    }

    public User setId(int id)
    {
        this.id = id;
        return this;
    }

    public String getPassword()
    {
        return password;
    }

    public User setPassword(String password)
    {
        this.password = password;
        return this;
    }

    public boolean getIsAdmin() {
        return email.equals(UsersService.ADMIN_EMAIL);
    }

    public boolean isAdmin() {
        return getIsAdmin();
    }

    public String toString() {
        return "Id: " + getId() +  "; Email: " + getEmail();
    }
    
    public void hydrateFromMap(Map map) {
        //some kind of builder but without UserBuilder class. js (jquery) way. will it fit here?...
        this.setId((Integer) map.get("id"))
            .setEmail((String) map.get("email"))
            .setPassword((String) map.get("password"));
    }
}
