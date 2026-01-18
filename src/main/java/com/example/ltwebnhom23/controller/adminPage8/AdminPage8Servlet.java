package com.example.ltwebnhom23.controller.adminPage8;

import com.example.ltwebnhom23.services.PromotionService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AdminPage8Servlet", urlPatterns = {"/adminPage8"})
public class AdminPage8Servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. Tự động cập nhật trạng thái trước khi load
        PromotionService.getInstance().autoUpdateStatus();

        // 2. Lấy toàn bộ danh sách
        request.setAttribute("listPromotions", PromotionService.getInstance().getAllPromotions());

        // 3. Forward sang JSP
        request.getRequestDispatcher("/adminPage8.jsp").forward(request, response);
    }
}