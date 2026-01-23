package com.example.ltwebnhom23.dao;

import com.example.ltwebnhom23.model.*;

import java.util.List;

public class AccountDao extends BaseDao {

    public User getUserById(int userId) {
        return getJdbi().withHandle(handle ->
                handle.createQuery("SELECT id, full_name, email, phone, password_hash, role, created_at FROM users WHERE id = :id")
                        .bind("id", userId)
                        .mapToBean(User.class)
                        .findFirst()
                        .orElse(null)
        );
    }

    public Address getAddressByUserId(int userId) {
        return getJdbi().withHandle(handle ->
                handle.createQuery("SELECT id, user_id, country, province, ward, address FROM user_addresses WHERE user_id = :uid")
                        .bind("uid", userId)
                        .mapToBean(Address.class)
                        .findFirst()
                        .orElse(null)
        );
    }

    public boolean updateUser(int userId, String fullName, String phone, int addressId, String city, String district, String streetAddress) {
        return getJdbi().withHandle(handle -> {
            int userRows = handle.createUpdate("UPDATE users SET full_name = :name, phone = :phone WHERE id = :id")
                    .bind("name", fullName)
                    .bind("phone", phone)
                    .bind("id", userId)
                    .execute();

            int addrRows = 0;
            if (addressId > 0) {
                addrRows = handle.createUpdate("UPDATE user_addresses SET province = :city, ward = :ward, address = :addr WHERE id = :aid")
                        .bind("city", city)
                        .bind("ward", district)
                        .bind("addr", streetAddress)
                        .bind("aid", addressId)
                        .execute();
            } else {
                addrRows = handle.createUpdate("INSERT INTO user_addresses (user_id, country, province, ward, address) VALUES (:uid, 'VN', :city, :ward, :addr)")
                        .bind("uid", userId)
                        .bind("city", city)
                        .bind("ward", district)
                        .bind("addr", streetAddress)
                        .execute();
            }

            return userRows > 0 || addrRows > 0;
        });
    }
    public List<User> getAllUser(){
        return getJdbi().withHandle(handle ->
                handle.createQuery("SELECT * FROM users")
                        .mapToBean(User.class)
                        .list()
        );
    }
    public List<User> getNewUser(){
        return getJdbi().withHandle(handle ->
                handle.createQuery("SELECT * FROM users WHERE DATE(created_at) = CURDATE()")
                        .mapToBean(User.class)
                        .list()
        );
    }
    public boolean deleteUser(int uid){
        return getJdbi().withHandle(handle ->
                handle.createUpdate("DELETE FROM users WHERE id = :uid")
                        .bind("uid", uid)
                        .execute() > 0
        );
    }
    public boolean addUser(User user){
        return getJdbi().withHandle(handle ->
                handle.createUpdate("INSERT INTO users(full_name, email, phone, password_hash, role, created_at) VALUES (:name, :email, :phone, :pass, :role, NOW())")
                        .bind("name", user.getFull_name())
                        .bind("email", user.getEmail())
                        .bind("phone", user.getPhone())
                        .bind("pass", user.getPassword_hash())
                        .bind("role", user.getRole())
                        .execute() > 0
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
    public List<User> getUserByKeyword(String key){
        return getJdbi().withHandle(handle ->
                handle.createQuery("SELECT * FROM users WHERE full_name LIKE :kw OR email LIKE :kw OR phone LIKE :kw")
                        .bind("kw", "%" + key + "%")
                        .mapToBean(User.class)
                        .list()
        );
    }

}