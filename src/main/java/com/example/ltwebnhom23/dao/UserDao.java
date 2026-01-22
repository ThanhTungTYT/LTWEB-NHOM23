package com.example.ltwebnhom23.dao;

import com.example.ltwebnhom23.model.User;

import java.util.List;

public class UserDao extends BaseDao{
    public List<User> getAll() {
        return getJdbi().withHandle(h ->
                h.createQuery(
                                "SELECT id, full_name, email, phone, role, created_at " +
                                        "FROM users"
                        )
                        .mapToBean(User.class)
                        .list()
        );
    }
    public String getFullNameById(int userId) {
        return getJdbi().withHandle(h ->
                h.createQuery("SELECT full_name FROM users WHERE id = :id")
                        .bind("id", userId)
                        .mapTo(String.class)
                        .findOne()
                        .orElse("Không xác định")
        );
    }
}
