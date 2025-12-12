package com.example.ltwebnhom23.dao;

import com.example.ltwebnhom23.model.User;

import java.sql.Connection;

public class AuthDao extends BaseDao {

    public User login(String email, String password){
        return getJdbi().withHandle(handle ->
                handle.createQuery("SELECT * FROM users WHERE email = :e AND password_hash = :p")
                        .bind("e", email)
                        .bind("p", password)
                        .mapToBean(User.class)
                        .findOne()
                        .orElse(null)
        );
    }
}
