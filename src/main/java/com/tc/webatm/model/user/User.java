package com.tc.webatm.model.user;

import com.tc.webatm.model.BaseModel;
import com.tc.webatm.util.UsersService;

import java.util.Collection;

public class User extends BaseModel {
    private String email;
    private String password;

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public boolean getIsAdmin() {
        return email.equals(UsersService.ADMIN_EMAIL);
    }

    public boolean isAdmin() {
        return getIsAdmin();
    }

    public String toString(){
        return "Email: " + getEmail();
    }
}
