package com.example.ltwebnhom23.services;

import com.example.ltwebnhom23.dao.AccountDao;
import com.example.ltwebnhom23.model.User;
import com.example.ltwebnhom23.model.Address;

public class AccountService {
    private AccountDao accountDao = new AccountDao();

    public User getAccountInfo(int userId) {
        return accountDao.getUserById(userId);
    }

    public Address getUserAddress(int userId) {
        return accountDao.getAddressByUserId(userId);
    }

    public boolean updateUserInfo(int userId, String fullName, String phone, int addressId, String city, String district, String streetAddress) {
        return accountDao.updateUser(userId, fullName, phone, addressId, city, district, streetAddress);
    }
}