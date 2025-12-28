package com.example.ltwebnhom23.services;

import com.example.ltwebnhom23.dao.AuthDao;
import com.example.ltwebnhom23.model.User;
import org.mindrot.jbcrypt.BCrypt;

public class AuthService {
    private AuthDao authDao = new AuthDao();

    public User login(String email, String password){
        User u = authDao.findByEmail(email);
        if(u == null) return null;


        boolean match = BCrypt.checkpw(password, u.getPassword_hash());
        return match ? u : null;
    }

    public boolean existsByEmail(String email){
        return authDao.exists(email);
    }

    public boolean register(User user){

        String hashedPassword = BCrypt.hashpw(user.getPassword_hash(), BCrypt.gensalt());
        user.setPassword_hash(hashedPassword);

        return authDao.register(user);
    }
}