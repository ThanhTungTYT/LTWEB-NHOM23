package com.example.ltwebnhom23.services;

import com.example.ltwebnhom23.cart.Cart;
import com.example.ltwebnhom23.dao.OrderDao;
import com.example.ltwebnhom23.model.Order;
import java.sql.Timestamp;

public class OrderService {
    private final OrderDao dao = new OrderDao();

    public boolean create(Order order, Cart cart) {
        order.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        return dao.createOrder(order, cart);
    }
}
