package com.example.ltwebnhom23.controller.adminPage8;

import com.example.ltwebnhom23.model.Promotion;
import com.example.ltwebnhom23.services.PromotionService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminPage8Servlet", urlPatterns = {"/adminPage8"})
public class AdminPage8Servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Promotion> list = PromotionService.getInstance().getAllPromotions();

        request.setAttribute("listPromotions", list);

        request.getRequestDispatcher("/adminPage8.jsp").forward(request, response);
    }
}