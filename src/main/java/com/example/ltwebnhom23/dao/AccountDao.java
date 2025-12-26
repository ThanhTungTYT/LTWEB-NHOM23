package com.example.ltwebnhom23.dao;

import com.example.ltwebnhom23.model.User;
import com.example.ltwebnhom23.model.Address;

public class AccountDao extends BaseDao {

    public User getUserById(int userId) {
        return getJdbi().withHandle(handle ->
                handle.createQuery("SELECT id , full_name, email, phone, password_hash, role FROM users WHERE id = :id")
                        .bind("id", userId)
                        .mapToBean(User.class)
                        .findFirst()
                        .orElse(null)
        );
    }
    public Address getAddressByUserId(int userId) {
        return getJdbi().withHandle(handle ->
                handle.createQuery("SELECT id , user_id, country, province, ward, address FROM user_addresses WHERE user_id = :uid")
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
}