package com.example.ltwebnhom23.services;

import com.example.ltwebnhom23.dao.AuthDao;
import com.example.ltwebnhom23.model.User;
import org.mindrot.jbcrypt.BCrypt;

public class AuthService {
    private AuthDao a = new AuthDao();

    public User login(String email, String password){
        User u = a.findByEmail(email);
        if(u == null) return null;
        boolean match = BCrypt.checkpw(password, u.getPassword_hash());
        return match ? u : null;
    }

    public boolean existsByEmail(String email){
        boolean exists = a.exists(email);
        return exists;
    }

    public boolean register(User user){
        String password_hash = BCrypt.hashpw(user.getPassword_hash(), BCrypt.gensalt());
        user.setPassword_hash(password_hash);
        return a.register(user);
    }
}
