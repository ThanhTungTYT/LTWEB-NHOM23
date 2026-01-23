package com.example.ltwebnhom23.services;

import com.example.ltwebnhom23.cart.Cart;
import com.example.ltwebnhom23.dao.AccountDao;
import com.example.ltwebnhom23.dao.OrderDao;
import com.example.ltwebnhom23.model.Order;
import com.example.ltwebnhom23.model.OrderItem;
import com.example.ltwebnhom23.model.User;

import java.sql.Timestamp;
import java.util.List;

public class OrderService {
    private final OrderDao dao = new OrderDao();
    public boolean create(Order order, Cart cart) {
        return dao.createOrder(order, cart);
    }

    public List<Order> getAllOrders() {
        return dao.getAllOrders();
    }
    public List<OrderItem> getItemsByOrderId(int orderId) {
        return dao.getItemsByOrderId(orderId);
    }
    public boolean updateOrder(Order order) {
        return dao.updateOrderStatus(order);
    }
}
