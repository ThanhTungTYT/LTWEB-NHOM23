package com.example.ltwebnhom23.dao;

import com.example.ltwebnhom23.model.User;
import com.example.ltwebnhom23.model.Address;

import org.jdbi.v3.core.Jdbi;

public class AccountDao extends BaseDao {

    public User getUserById(int userId) {
        return getJdbi().withHandle(handle ->
                handle.createQuery("SELECT * FROM users WHERE user_id = :id")
                        .bind("id", userId)
                        .mapToBean(User.class)
                        .findFirst()
                        .orElse(null)
        );
    }
    public Address getAddressByUserId(int userId) {
        return getJdbi().withHandle(handle ->
                handle.createQuery("SELECT * FROM user_addresses WHERE user_id = :uid")
                        .bind("uid", userId)
                        .mapToBean(Address.class) // Mapping tự động vào class Address bạn gửi
                        .findFirst()
                        .orElse(null)
        );
    }
}