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
        String action = request.getParameter("action");
        String idStr = request.getParameter("id");

        if ("delete".equals(action) && idStr != null) {
            try {
                int id = Integer.parseInt(idStr);
                PromotionService.getInstance().deletePromotion(id);

                response.sendRedirect(request.getContextPath() + "/adminPage8");
                return;
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        String keyword = request.getParameter("search");
        List<Promotion> listPromotions;

        if (keyword != null && !keyword.trim().isEmpty()) {
            listPromotions = PromotionService.getInstance().searchPromotions(keyword.trim());
        } else {
            listPromotions = PromotionService.getInstance().getAllPromotions();
        }

        request.setAttribute("listPromotions", listPromotions);
        request.setAttribute("searchKeyword", keyword);

        request.getRequestDispatcher("/adminPage8.jsp").forward(request, response);
    }
}