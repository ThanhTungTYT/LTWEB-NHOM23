package com.example.ltwebnhom23.services;

import com.example.ltwebnhom23.dao.AuthDao;
import com.example.ltwebnhom23.model.User;

public class AuthService {
    private AuthDao a = new AuthDao();

    public User login(String email, String password){
        User u = a.login(email, password);

        if(u == null) return null;
        return u;
    }
}
