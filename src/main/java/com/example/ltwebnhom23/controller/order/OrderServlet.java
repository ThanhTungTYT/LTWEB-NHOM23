package com.example.ltwebnhom23.controller.order;

import com.example.ltwebnhom23.cart.Cart;
import com.example.ltwebnhom23.model.Order;
import com.example.ltwebnhom23.model.Promotion;
import com.example.ltwebnhom23.model.User;
import com.example.ltwebnhom23.services.OrderService;
import com.example.ltwebnhom23.services.PromotionService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {

    private final OrderService orderService = new OrderService();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession s = req.getSession();
        User user = (User) s.getAttribute("user");
        Cart cart = (Cart) s.getAttribute("cart");

        if (user == null) {
            resp.sendRedirect("login");
            return;
        }

        Order order = new Order();
        order.setUserId(user.getId());
        order.setTotal(cart.getTotal());

        double discount = 0;

        order.setDiscount(discount);
        order.setFinalAmount(cart.getTotal() - discount);

        orderService.create(order, cart);
        s.removeAttribute("cart");
        resp.sendRedirect("success.jsp");
    }
}