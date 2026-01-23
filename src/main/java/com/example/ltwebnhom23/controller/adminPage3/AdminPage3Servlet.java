package com.example.ltwebnhom23.controller.adminPage3;

import com.example.ltwebnhom23.model.Order;
import com.example.ltwebnhom23.model.OrderItem;
import com.example.ltwebnhom23.model.User;
import com.example.ltwebnhom23.services.OrderService;
import com.example.ltwebnhom23.services.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet(name = "AdminPage3Servlet", value = "/adminPage3")


public class AdminPage3Servlet extends HttpServlet {

    private final OrderService orderService = new OrderService();
    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        var orders = orderService.getAllOrders();
        List<User> users = userService.getAllUsers();

        Map<Integer, User> userMap = users.stream()
                .collect(Collectors.toMap(User::getId, u -> u));

        // map: orderId -> list item
        Map<Integer, List<OrderItem>> orderItemsMap = new HashMap<>();
        for (Order o : orders) {
            orderItemsMap.put(
                    o.getId(),
                    orderService.getItemsByOrderId(o.getId())
            );
        }

        req.setAttribute("orders", orders);
        req.setAttribute("userMap", userMap);
        req.setAttribute("orderItemsMap", orderItemsMap);

        req.getRequestDispatcher("/adminPage3.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        int orderId = Integer.parseInt(req.getParameter("orderId"));
        String status = "Đã giao";

        Order order = new Order();
        order.setId(orderId);
        order.setStatus(status);

        boolean updated = orderService.updateOrder(order);
        System.out.println("Update order " + orderId + ": " + updated);

        resp.sendRedirect(req.getContextPath() + "/adminPage3");
    }
}
