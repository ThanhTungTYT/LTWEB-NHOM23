package com.example.ltwebnhom23.services;

import com.example.ltwebnhom23.dao.UserDao;
import com.example.ltwebnhom23.model.User;

import java.util.List;

public class UserService {
    private final UserDao userDao = new UserDao();


    public List<User> getAllUsers() {
        return userDao.getAll();
    }
}
