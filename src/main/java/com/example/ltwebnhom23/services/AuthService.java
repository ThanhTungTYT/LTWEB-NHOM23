package com.example.ltwebnhom23.services;

import com.example.ltwebnhom23.dao.AuthDao;
import com.example.ltwebnhom23.model.User;

public class AuthService {
    private AuthDao authDao = new AuthDao();

    public User login(String email, String password){
        User u = authDao.findByEmail(email);
        if(u == null) return null;


        boolean match = (MD5Util.md5(password).equals(u.getPassword_hash()));
        return match ? u : null;
    }

    public boolean existsByEmail(String email){
        return authDao.exists(email);
    }

    public boolean register(User user){

        String hashedPassword = MD5Util.md5(user.getPassword_hash());
        user.setPassword_hash(hashedPassword);

        return authDao.register(user);
    }
}