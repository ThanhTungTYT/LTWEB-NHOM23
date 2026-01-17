package com.example.ltwebnhom23.controller.order;

import com.example.ltwebnhom23.services.PromotionService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/payment")
public class PaymentServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession s = req.getSession(false);
        if (s == null || s.getAttribute("cart") == null) {
            resp.sendRedirect("cart");
            return;
        }

        req.setAttribute("cart", s.getAttribute("cart"));

        req.getRequestDispatcher("payment.jsp").forward(req, resp);
    }
}