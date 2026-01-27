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
    public List<Order> getOrdersByUserId(int userId) {
        List<Order> orders = dao.getOrdersByUserId(userId);

        for (Order o : orders) {
            o.setItems(dao.getItemsByOrderId(o.getId()));
        }
        return orders;
    }
    public boolean cancelOrder(int orderId) {
        Order order = new Order();
        order.setId(orderId);
        order.setStatus("Đã hủy");
        return dao.cancelOrder(order);
    }

    public int countOrders(String start, String end) {
        return dao.countOrdersWithFilter(start, end);
    }

    public List<Order> getOrdersPagination(String start, String end, int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        return dao.getOrdersWithFilter(start, end, pageSize, offset);
    }
}
