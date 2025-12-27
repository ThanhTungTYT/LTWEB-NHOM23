package com.example.ltwebnhom23.dao;

import com.example.ltwebnhom23.model.User;

public class AuthDao extends BaseDao {

    public User findByEmail(String email){
        return getJdbi().withHandle(handle ->
                handle.createQuery("SELECT * FROM users WHERE email = :e")
                        .bind("e", email)
                        .mapToBean(User.class)
                        .findOne()
                        .orElse(null)
        );
    }

    public boolean exists(String email){
        Integer count = getJdbi().withHandle(handle ->
                handle.createQuery("SELECT COUNT(*) FROM users WHERE email = :e")
                        .bind("e", email)
                        .mapTo(Integer.class)
                        .one()
        );
        return count != null && count > 0;
    }

    public boolean register(User user){
        return getJdbi().withHandle(handle ->
                handle.createUpdate(
                                "INSERT INTO users(full_name, email, phone, password_hash, role, created_at) " +
                                        "VALUES (:fullname, :email, :phone, :pass, :role, NOW())"
                        )
                        .bind("fullname", user.getFull_name())
                        .bind("email", user.getEmail())
                        .bind("phone", user.getPhone())
                        .bind("pass", user.getPassword_hash())
                        .bind("role", "Customer")
                        .execute() > 0
        );
    }
}