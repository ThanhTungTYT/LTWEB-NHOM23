package com.example.ltwebnhom23.services;

import com.example.ltwebnhom23.dao.AccountDao;
import com.example.ltwebnhom23.dao.OrderDao;
import com.example.ltwebnhom23.model.Order;
import com.example.ltwebnhom23.model.User;
import com.example.ltwebnhom23.model.Address;

import java.util.List;

public class AccountService {
    private AccountDao accountDao = new AccountDao();
    private final OrderDao orderDao = new OrderDao();

    public User getAccountInfo(int userId) {
        return accountDao.getUserById(userId);
    }

    public Address getUserAddress(int userId) {
        return accountDao.getAddressByUserId(userId);
    }

    public boolean updateUserInfo(int userId, String fullName, String phone, int addressId, String city, String district, String streetAddress) {
        return accountDao.updateUser(userId, fullName, phone, addressId, city, district, streetAddress);
    }

    public List<Order> getOrdersByUserId(int userId) {
        List<Order> orders = orderDao.getOrdersByUserId(userId);

        for (Order o : orders) {
            o.setItems(orderDao.getItemsByOrderId(o.getId()));
        }
        return orders;
    }
    public List<User> getAllUser(){
        return accountDao.getAllUser();
    }
    public List<User> getNewUser(){
        return accountDao.getNewUser();
    }

    public boolean cancelOrder(int orderId) {
        Order order = new Order();
        order.setId(orderId);
        order.setStatus("Đã hủy");
        return orderDao.cancelOrder(order);
    }

    public boolean deleteUser(int uid){
        return accountDao.deleteUser(uid);
    }
    public boolean addUser(User user){
        String hashedPassword = MD5Util.md5(user.getPassword_hash());
        user.setPassword_hash(hashedPassword);

        return accountDao.addUser(user);
    }
}